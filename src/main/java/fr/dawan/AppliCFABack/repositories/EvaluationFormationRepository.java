package fr.dawan.AppliCFABack.repositories;

import fr.dawan.AppliCFABack.entities.EvaluationFormation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EvaluationFormationRepository extends JpaRepository<EvaluationFormation, Long> {
	/***
	 * 
	 * @param idPromotion : identifiant de la promotion
	 * @param idActiviteType : identifiant de l'activité type
	 * @return Liste des évaluations 
	 */
   @Query("Select  DISTINCT e from EvaluationFormation e Join e.intervention i  Join i.promotions p on p.id =:idPromotion "
   		+ "Join e.competencesEvaluees c on c.activiteType.id =:idActiviteType")
//   @Query(nativeQuery = true, value = "SELECT * FROM (SELECT * FROM evaluation_formation e JOIN intervention_promotions i ON i.interventions_id = e.intervention_id WHERE i.promotions_id =143) as eval"
//   		+ "Inner Join evaluation_formation_competences_evaluees c on c.evaluations_formations_id = eval.id")
	List<EvaluationFormation> getByPrmotionIdAndActiviteTypeId(long idPromotion , long idActiviteType);

	List<EvaluationFormation> findAllByInterventionId(long idIntervention);
	
}
