package fr.dawan.AppliCFABack.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Absence;
/**
 * @author Valentin C, Feres BG.
 * @see fr.dawan.appliCFABack.service
 * @since 1.0
 * @version 1.0
 * @return Repository de l'entity Absence
 */
@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long> {
	/**
	 * 
	 * @param id de l'étudiant concerné
	 * @return objet étudiant concerné
	 */
	@Query("SELECT a FROM Absence a WHERE a.etudiant.id = :id")
	List<Absence> getByEtudiantId(@Param("id") long id);
	
	/**
	 * 
	 * @param id de l'intervention concernée
	 * @return toutes les absences pour une intervention
	 */
	@Query("FROM Absence a WHERE a.intervention.id = :id ")
	List<Absence> findAllByInterventionId(@Param("id") long id);
	
	/**
	 * 
	 * @param id de l'étudiant concerné
	 * @return toutes les absences de l'étudiant concerné
	 */
	@Query("FROM Absence a WHERE a.etudiant.id = :id")
	List<Absence> findAllByEtudiantId(long id);
	
//	@Query("SELECT Absence a JOIN a.etudiant et JOIN et.promotion p WHERE p.id = :id")
//	List<Absence> findAllByPromotionId(long id);
	
	/**
	 * 
	 * @param search champs de recherche par nom ou prénom des étudiants
	 * @return toutes les objets étudiants dans lesquels le champs de recherche est présent
	 */
	@Query("FROM Absence a JOIN a.etudiant e JOIN e.utilisateur u WHERE u.nom LIKE :search OR u.prenom LIKE :search")
	List<Absence> findByEtudiantUtilisateurNomContainingOrEtudiantUtilisateurPrenomContainingAllIgnoreCase(String search);

	/**
	 * 
	 * @param search hamps de recherche par nom ou prénom des étudiants
	 * @return le nombre d'occurence de la recherche
	 */
	@Query("SELECT COUNT(a) FROM Absence a JOIN a.etudiant e JOIN e.utilisateur u WHERE u.nom LIKE :search OR u.prenom LIKE :search")
	long countByUtilisateurEtudiantNomOrPrenomContaining(String search);
	
	/**
	 * 
	 * @param Identifiant de l'etudiant , identifiant de l'intervention
	 * @return list d'absence par etudiant dans une Intervention
	 */
	List<Absence> findAllByEtudiantIdAndInterventionId(long etudiantId, long interventionId);
	
	
//	long countByEtudiantUtilisateurNomContainingOrEtudiantUtilisateurPrenomContainingAllIgnoreCase(String prenom, String nom);

//	List<Absence> findByEtudiantPromotionsId(long id);
	

//	List<Absence> findDistinctByEtudiantPromotionsReferentPedagogiqueId(long id);
	

//	long countDistinctByEtudiantPromotionsReferentPedagogiqueId(long id);

//	@Query(value = "SELECT abs.id, abs.dateDebut, abs.dateFin, promo.referentPedagogique, etud.id FROM Absence abs JOIN abs.etudiant etud JOIN etud.promotions promo WHERE promo.referentPedagogique.id =:id ")
//	List<Absence> findByRefId(@Param("id") long id);
}
