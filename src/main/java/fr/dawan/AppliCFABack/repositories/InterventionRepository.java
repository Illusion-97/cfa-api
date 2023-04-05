package fr.dawan.AppliCFABack.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Intervention;

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

	@Query("FROM Intervention i LEFT JOIN FETCH Formateur f LEFT JOIN FETCH Formation fo WHERE f.id=:id AND fo.titre=:search")
	long countByFormateurIdAndFormationTitreAllIgnoreCase(long id, String search);

	@Query("FROM Intervention i LEFT JOIN FETCH Formateur f WHERE f.id=:id")
	long countByFormateurId(long id);

	/** ++++++++++++++ INTERVENTION FORMATION ++++++++++++++ **/
	List<Intervention> findAllByFormationId(long id);

	Optional<Intervention> findByIdDg2(long id);
	
	@Query("SELECT i FROM Intervention i LEFT JOIN FETCH Formateur f WHERE f.id =:id And i.dateDebut =:dateDebut And i.dateFin =:dateFin")
	Optional<Intervention> findInterventionBydateFormationAndFormateur(LocalDate dateDebut, LocalDate dateFin, long id);

	/** ++++++++++++++ INTERVENTION FORMATEUR ++++++++++++++ **/
	@Query("SELECT i FROM Intervention i JOIN i.formateur f WHERE f.id =:id")
	Page<Intervention> findAllByFormateurId(long id, Pageable p);
	
	@Query("SELECT i FROM Intervention i JOIN i.formateur f WHERE f.id =:id")
	List<Intervention> findAllByFormateurId(long id);
}
