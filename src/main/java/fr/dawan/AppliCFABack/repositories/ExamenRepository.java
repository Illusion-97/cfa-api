package fr.dawan.AppliCFABack.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Examen;

/**
 * @author Valentin C, Feres BG.
 * @see fr.dawan.appliCFABack.service
 * @since 1.0
 * @version 1.0
 * @return Repository de l'entity Examen
 */
@Repository
public interface ExamenRepository extends JpaRepository<Examen, Long> {
	/**
	 * 
	 * @param titre      à rechercher
	 * @param descriptif à recherher
	 * @param pageable   pagination
	 * @return tous les examens dont le titre et la description contiennent le
	 *         champs recherché
	 */
	Page<Examen> findAllByTitreContainingIgnoringCaseOrDescriptifContainingIgnoringCase(String titre, String descriptif,
			Pageable pageable);

	long countByTitreContainingIgnoringCaseOrDescriptifContainingIgnoringCase(String titre, String descriptif);
	
	
//	//Sélectionner le(s) examens(s) d'une intervention et récupérer leurs Acttypes & compsProT
//		@Query("FROM Examen e JOIN e.promotions p JOIN p.interventions i "
//				+ "WHERE i.id = :interventionId")
		List<Examen> findExamensByInterventionId (long interventionId);

	/**
	 * @param id de l'étudiant
	 * @return tous les examens par id de l'étudiant en utilisant le mot clé DISTINCT pour éviter les doublons
	 */
	@Query("SELECT DISTINCT e FROM Examen e JOIN Note n ON n.etudiantNote.id =:id")
	List<Examen> findallByEtudiantId(@Param("id") long id);

	@Query("SELECT AVG(n.noteObtenue) FROM Note n JOIN n.etudiantNote etu JOIN n.examen.activiteTypes at"
			+ " WHERE etu.id= :etudiantId AND at.id = :activiteTypeId")
	double getAvgByEtudiantIdAndActiviteTypeId(@Param("etudiantId") long etudiantId, @Param("activiteTypeId") long activiteTypeId);

	@Query("SELECT AVG(n.noteObtenue) FROM Note n JOIN n.etudiantNote etu JOIN etu.promotions promo WHERE promo.id= :promotionId")
	double getAvgByPromotionId(@Param("promotionId") long promotionId);

	@Query("SELECT AVG(n.noteObtenue) FROM Note n JOIN n.etudiantNote etu JOIN etu.promotions promo JOIN n.examen.activiteTypes at WHERE promo.id= :promotionId AND at.id= :activiteTypeId")
	double getAvgByPromoIdAndActiviteTypeId(@Param("promotionId") long promotionId, @Param("activiteTypeId") long activiteTypeId);
}
