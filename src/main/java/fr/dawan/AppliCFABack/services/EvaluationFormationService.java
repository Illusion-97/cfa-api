package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.EvaluationFormationDto;

import java.util.List;

public interface EvaluationFormationService extends GenericService<EvaluationFormationDto> {

	List<EvaluationFormationDto> getByPromotionIdAndActiviteTypeId(long promotionId , long activiteTypeId);

	List<EvaluationFormationDto> getByInterventionId(long idIntervention);
}
