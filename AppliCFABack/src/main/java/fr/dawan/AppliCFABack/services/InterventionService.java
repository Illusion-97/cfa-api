package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.InterventionDto;

public interface InterventionService {

	List<InterventionDto> getAllIntervention();

	List<InterventionDto> getAllIntervention(int page, int size);

	List<InterventionDto> getAllInterventionWithObject();

	List<InterventionDto> getAllInterventionWithObject(int page, int size);

	InterventionDto getById(long id);

	InterventionDto saveOrUpdate(InterventionDto iDto);

	void deleteById(long id);

	

	List<InterventionDto> getAllByPage(int page, int size, String string);
	
	CountDto count(String string);

}
