package fr.dawan.AppliCFABack.repositories;

import fr.dawan.AppliCFABack.entities.Intervention;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface InterventionRepository extends JpaRepository<Intervention, Long> {


	Page<Intervention> findAllDistinctByFormationTitreContainingIgnoringCaseOrPromotionsNomContainingIgnoringCase(
			String formationTitre, String promotionNom, Pageable p);

	long countDistinctByFormationTitreContainingIgnoringCaseOrPromotionsNomContainingIgnoringCase(String formationTitre,
			String promotionNom);

	/** ++++++++++++++ INTERVENTION PROMOTION ++++++++++++++ **/
	@Query("SELECT i FROM Intervention i JOIN i.promotions promotion WHERE promotion.id=:id ")
	List<Intervention> getInterventionsByIdPromotion(@Param("id") long id);

	// Interventions du formateur + recherche par mot clé
	@Query("SELECT i FROM Intervention i JOIN i.formateur f JOIN i.formation fo WHERE f.id = :id AND LOWER(fo.titre) LIKE CONCAT('%', LOWER(:titre), '%')")
	Page<Intervention> findByFormateurIdAndFormationTitreContainingAllIgnoreCase(long id, String titre, Pageable p);

	@Query("FROM Intervention i LEFT JOIN FETCH i.formateur f LEFT JOIN FETCH i.formation fo WHERE f.id=:id AND fo.titre=:search")
	long countByFormateurIdAndFormationTitreAllIgnoreCase(long id, String search);

	@Query("SELECT count(i.id) FROM Intervention i JOIN i.formateur f WHERE f.id= :id")
	long countByFormateurId(@Param("id") long id);

	/** ++++++++++++++ INTERVENTION FORMATION ++++++++++++++ **/
	List<Intervention> findAllByFormationId(long id);

	Optional<Intervention> findByIdDg2(long id);
	
	@Query("SELECT i FROM Intervention i LEFT JOIN FETCH i.formateur f WHERE f.id =:id And i.dateDebut =:dateDebut And i.dateFin =:dateFin")
	Optional<Intervention> findInterventionBydateFormationAndFormateur(LocalDate dateDebut, LocalDate dateFin, long id);

	/** ++++++++++++++ INTERVENTION FORMATEUR ++++++++++++++ **/
	@Query("SELECT i FROM Intervention i JOIN i.formateur f WHERE f.id =:id")
	Page<Intervention> findAllByFormateurId(long id, Pageable p);
	
	@Query("SELECT i FROM Intervention i JOIN i.formateur f WHERE f.id =:id")
	List<Intervention> findAllByFormateurId(long id);

	@Query(value = "SELECT * FROM intervention i JOIN intervention_promotions ip " +
			"JOIN promotion p ON(i.id = ip.promotions_id AND p.id = ip.promotions_id) " +
			"JOIN formation f ON(i.formation_id = f.id) " +
			"WHERE p.id = :id AND (:search IS NULL " +
			"OR LOWER(f.slug) LIKE CONCAT('%', LOWER(:search), '%') " +
			"OR LOWER(f.titre) LIKE CONCAT('%', LOWER(:search), '%') " +
			"OR REPLACE(f.slug,'-',' ') LIKE CONCAT('%', LOWER(:search), '%') " +
			"OR REPLACE(f.slug,'',' ') LIKE CONCAT('%', LOWER(:search), '%') " +
			"OR REPLACE(f.slug,'-','') LIKE CONCAT('%', LOWER(:search), '%') " +
			"OR REPLACE(f.titre,'-',' ') LIKE CONCAT('%', LOWER(:search), '%')" +
			"OR REPLACE(f.titre,'',' ') LIKE CONCAT('%', LOWER(:search), '%')" +
			"OR REPLACE(f.titre,'-','') LIKE CONCAT('%', LOWER(:search), '%'))",
			nativeQuery = true)
	Page<Intervention> findInterventionByPromotionId(@Param("id") long id, @Param("search") String search, Pageable page);
	
	@Query("SELECT COUNT(i) FROM Promotion p JOIN p.interventions i JOIN i.formation f WHERE p.id = :id AND f.titre LIKE %:search%")
	long countInterventionByPromotionId(Long id, String search);
}
