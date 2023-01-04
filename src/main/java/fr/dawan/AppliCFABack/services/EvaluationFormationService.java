package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.EvaluationFormationDto;

public interface EvaluationFormationService extends GenericService<EvaluationFormationDto> {

	List<EvaluationFormationDto> getByPromotionIdAndActiviteTypeId(long promotionId , long activiteTypeId);

	List<EvaluationFormationDto> getByInterventionId(long idIntervention);
}
