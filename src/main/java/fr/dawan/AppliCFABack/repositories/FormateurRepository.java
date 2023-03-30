package fr.dawan.AppliCFABack.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
	
	// Liste des formateurs par l'id de l'intervention
	
	@Query(nativeQuery = true, value="SELECT * FROM formateur as f JOIN utilisateur as u ON u.id=f.utilisateur_id JOIN intervention_formateurs as interf ON f.id=interf.formateurs_id JOIN intervention as i ON i.id=interf.interventions_id WHERE i.id_dg2=:idDg2")
	List<Formateur> findByInterventionsIdDg2(long idDg2);

	Optional<Formateur> findByUtilisateurId(long id);
}
