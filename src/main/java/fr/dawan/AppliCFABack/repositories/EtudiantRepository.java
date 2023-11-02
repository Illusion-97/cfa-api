package fr.dawan.AppliCFABack.repositories;

import fr.dawan.AppliCFABack.entities.Etudiant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
	Page<Etudiant> findDistinctAllByUtilisateurPrenomContainingIgnoringCaseOrUtilisateurNomContainingOrPromotionsNomContainingOrGroupesNomContaining(
			String prenom, String nom, String promotionsNom, String groupesNom, Pageable p);

	long countDistinctByUtilisateurPrenomContainingIgnoringCaseOrUtilisateurNomContainingOrPromotionsNomContainingOrGroupesNomContaining(
			String prenom, String nom, String promotionsNom, String groupesNom);

	Page<Etudiant> findAllByUtilisateurPrenomContainingIgnoringCaseOrUtilisateurNomContainingIgnoringCaseOrUtilisateurLoginContainingIgnoringCase(
			String search, String search2, String search3, Pageable pageable);
 
	long countByUtilisateurPrenomContainingIgnoringCaseOrUtilisateurNomContainingIgnoringCaseOrUtilisateurLoginContainingIgnoringCase(
			String search, String search2, String search3);
	
	long findByTuteurId(long id);
	
	@Query("SELECT DISTINCT e FROM Etudiant e JOIN e.promotions p JOIN p.interventions i ON i.id = :id JOIN e.utilisateur u WHERE u.nom LIKE %:search% OR u.prenom  LIKE %:search% ")
	List<Etudiant> findAllDistinctByPromotionsInterventionsId(long id, String search);

	List<Etudiant> findAllDistinctByPromotionsInterventionsId(long id);
	
	@Query("SELECT e FROM Etudiant e JOIN e.tuteur t WHERE t.id=:id AND e.utilisateur.nom LIKE :search OR e.utilisateur.prenom  LIKE :search ")	
	Page<Etudiant> findEtudiantBySearch(@Param("id")long id, Pageable p, String search);
	
	Page<Etudiant> findAllByTuteurId(long id, Pageable p);
	
	List<Etudiant> findAllByTuteurId(long id);
		
	long countByTuteurId(long id);
	
	@Query("FROM Etudiant e WHERE e.utilisateur.id = :id")
	Etudiant findByUtilisateurId(@Param("id") long id);

	@Query(value = "SELECT * FROM etudiant e " +
			"JOIN promotion p " +
			"JOIN promotion_etudiants pe ON(e.id = pe.etudiants_id AND p.id = pe.promotions_id) " +
			"JOIN utilisateur u ON(e.utilisateur_id = u.id) " +
			" WHERE p.id = :id AND (:search IS NULL " +
			"OR LOWER(u.nom) LIKE CONCAT('%', LOWER(:search), '%') " +
			"OR LOWER(u.prenom) LIKE CONCAT('%', LOWER(:search), '%') " +
			"OR CONCAT(LOWER(u.nom), LOWER(u.prenom)) LIKE CONCAT('%', LOWER(:search), '%') " +
			"OR CONCAT(LOWER(u.nom),' ',LOWER(u.prenom)) LIKE CONCAT('%', LOWER(:search), '%'))",
			nativeQuery = true)
	Page<Etudiant> getEtudiantByPromotion(@Param("id") long id, @Param("search") String search, Pageable pageable);
	
	@Query("SELECT COUNT(e) FROM Promotion p JOIN p.etudiants e JOIN e.utilisateur u WHERE p.id = :id AND (u.nom LIKE %:search% OR u.prenom LIKE %:search%)")
	long countEtudiantByPromotion(long id, String search);
	
	@Query("SELECT e FROM Promotion p JOIN p.etudiants e JOIN e.utilisateur u WHERE p.id = :id")
	List<Etudiant> getEtudiantByPromotionId(long id);
}
