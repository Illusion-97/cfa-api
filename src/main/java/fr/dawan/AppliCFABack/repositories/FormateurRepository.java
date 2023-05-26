package fr.dawan.AppliCFABack.repositories;

import fr.dawan.AppliCFABack.entities.Formateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FormateurRepository extends JpaRepository<Formateur, Long> {
	// nb formateur + recherche par mot clé
	long countByUtilisateurPrenomContainingOrUtilisateurNomContainingAllIgnoreCase(String prenom, String nom);

	// liste des formateur + recherche par mot clé
	Page<Formateur> findAllByUtilisateurPrenomContainingOrUtilisateurNomContainingAllIgnoreCase(String prenom, String nom, Pageable p);

	// 					+++++ INTERVENTION FORMATEUR +++++
	// nb interventions du formateur + recherche par mot clé
	//TODO TESTER LE BON FONCTIONNEMENT OK mais que avec le search 
	@Query("SELECT COUNT(i) FROM Intervention i WHERE i.formateur.id = :id AND LOWER(i.formation.titre) LIKE CONCAT('%', LOWER(:search), '%')")
	long countByIdAndInterventionsFormationTitreContainingAllIgnoreCase(long id, String search);
	
	//TODO tester la requete Problème sur le modele objet 
	@Query("SELECT i.formateur FROM Intervention i WHERE i.id = :id")
	Formateur findByInterventionId(@Param("id") long id);
	
	// Liste des formateurs par l'id de l'intervention
	
//	@Query(nativeQuery = true, value="SELECT * FROM formateur as f JOIN utilisateur as u ON u.id=f.utilisateur_id JOIN intervention_formateurs as interf ON f.id=interf.formateurs_id JOIN intervention as i ON i.id=interf.interventions_id WHERE i.id_dg2=:idDg2")
//	List<Formateur> findByInterventionsIdDg2(long idDg2);

	Optional<Formateur> findByUtilisateurId(long id);

//	@Query("FROM Formateur f LEFT JOIN FETCH f.utilisateur WHERE f.utilisateur.idDg2=:id")
	@Query("FROM Formateur f LEFT JOIN FETCH f.utilisateur u WHERE u.idDg2 = :id")
	Optional<Formateur> findByIdDg2(long id);
}
