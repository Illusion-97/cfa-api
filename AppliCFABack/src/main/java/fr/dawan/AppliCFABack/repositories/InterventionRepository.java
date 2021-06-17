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

	@Query(value = "SELECT itv FROM Intervention itv JOIN itv.promotions p WHERE LOWER(itv.formation.titre) LIKE %:keyword% OR LOWER(p.nom) LIKE %:keyword% OR DATE(itv.dateDebut)=:keyword")
	Page<Intervention> getAllByKeyword(@Param("keyword") String keyword, Pageable p);

	Page<Intervention> findAllByFormationTitreContainingIgnoringCaseOrPromotionsNomContainingIgnoringCase(
			String formationTitre, String promotionNom, Pageable p);

	long countByFormationTitreContainingIgnoringCaseOrPromotionsNomContainingIgnoringCase(String formationTitre,
			String promotionNom);

}
