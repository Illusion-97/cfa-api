package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.BlocEvaluationDto;

public interface BlocEvaluationService extends GenericService<BlocEvaluationDto> {

	BlocEvaluationDto finddByActiviteTypeIdAndlivretEvaluationId(long idAt, long idLivretEval);
}
