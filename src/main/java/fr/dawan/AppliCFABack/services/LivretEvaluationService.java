package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.LivretEvaluationDto;

public interface LivretEvaluationService extends GenericService<LivretEvaluationDto>{

	//une liste car si étudiant fait plusieurs formations
	List<LivretEvaluationDto> getByEtudiantId(long id);
}
