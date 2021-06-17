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

	@Query("SELECT i FROM Intervention i JOIN i.promotions promotion WHERE promotion.id=:id ")
	List<Intervention> getInterventionsByIdPromotion(@Param("id") long id);

	Page<Intervention> findAllByFormationTitreContainingIgnoringCaseOrPromotionsNomContainingIgnoringCase(
			String search, String search2, Pageable pageable);

	long countByFormationTitreContainingIgnoringCaseOrPromotionsNomContainingIgnoringCase(String search, String search2);

}
