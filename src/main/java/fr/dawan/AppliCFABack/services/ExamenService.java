package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.ExamenDto;
import fr.dawan.AppliCFABack.dto.ExamenDtoSave;
import fr.dawan.AppliCFABack.dto.LivretEvaluationDto;

public interface ExamenService {

	List<ExamenDto> getAllExamen();

	List<ExamenDto> getAllByPage(int page, int size, String string);

	CountDto count(String string);

	ExamenDto getById(long id);

	ExamenDtoSave saveOrUpdate(ExamenDtoSave eDto) throws Exception;

	void deleteById(long id);
	
	List<ExamenDto> findExamensByInterventionId(long id);

    List<LivretEvaluationDto> getLivretEvaluation(long id);
}
