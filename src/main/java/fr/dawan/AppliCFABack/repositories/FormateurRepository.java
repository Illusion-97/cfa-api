package fr.dawan.AppliCFABack.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.dawan.AppliCFABack.entities.Formateur;

public interface FormateurRepository extends JpaRepository<Formateur, Long> {
	// nb formateur + recherche par mot clé
	long countByUtilisateurPrenomContainingOrUtilisateurNomContainingAllIgnoreCase(String prenom, String nom);

	// liste des formateur + recherche par mot clé
	Page<Formateur> findAllByUtilisateurPrenomContainingOrUtilisateurNomContainingAllIgnoreCase(String prenom, String nom, Pageable p);
	

	// 					+++++ INTERVENTION FORMATEUR +++++
	// nb interventions du formateur + recherche par mot clé
	long countByIdAndInterventionsFormationTitreContainingAllIgnoreCase(long id, String search);
	
	// Liste des formateurs par l'id de l'intervention
	List<Formateur> findByInterventionsId(long id);
}
