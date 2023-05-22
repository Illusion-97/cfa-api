package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.EvaluationFormationDto;
import fr.dawan.AppliCFABack.tools.SaveInvalidException;

import java.util.List;

public interface EvaluationFormationService extends GenericService<EvaluationFormationDto> {

	List<EvaluationFormationDto> getByPromotionIdAndActiviteTypeId(long promotionId , long activiteTypeId);

	List<EvaluationFormationDto> getByInterventionId(long idIntervention);

	EvaluationFormationDto update(EvaluationFormationDto evaluationFormationDto) throws SaveInvalidException;
}
