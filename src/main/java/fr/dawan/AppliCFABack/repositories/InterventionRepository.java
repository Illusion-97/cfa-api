package fr.dawan.AppliCFABack.repositories;

import java.util.List;

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

	/** ++++++++++++++ INTERVENTION FORMATEUR ++++++++++++++ **/
	Page<Intervention> findAllByFormateursId(long id, Pageable p); // Interventions du formateur
	List<Intervention> findAllByFormateursId(long id); // Interventions du formateur

	// Interventions du formateur + recherche par mot clé
	Page<Intervention> findByFormateursIdAndFormationTitreContainingAllIgnoreCase(long id, String titre, Pageable p);

	long countByFormateursIdAndFormationTitreAllIgnoreCase(long id, String search);

	long countByFormateursId(long id);

	/** ++++++++++++++ INTERVENTION FORMATION ++++++++++++++ **/
	List<Intervention> findAllByFormationId(long id);
}
