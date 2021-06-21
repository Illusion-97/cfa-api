package fr.dawan.AppliCFABack.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.dawan.AppliCFABack.entities.Formateur;

public interface FormateurRepository extends JpaRepository<Formateur, Long> {
	// nb formateur + recherche par mot clé
	long countByPrenomContainingOrNomContainingAllIgnoreCase(String prenom, String nom);

	// liste des formateur + recherche par mot clé
	Page<Formateur> findAllByPrenomContainingOrNomContainingAllIgnoreCase(String prenom, String nom, Pageable p);

	// nb interventions du formateur + recherche par mot clé
	long countByIdAndInterventionsFormationTitreContainingAllIgnoreCase(long id, String search);
}
