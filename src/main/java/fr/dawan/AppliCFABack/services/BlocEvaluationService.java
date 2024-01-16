package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.BlocEvaluationDto;
import fr.dawan.AppliCFABack.services.generic.GenericService;

public interface BlocEvaluationService extends GenericService<BlocEvaluationDto> {

	BlocEvaluationDto finddByActiviteTypeIdAndlivretEvaluationId(long idAt, long idLivretEval);
}
