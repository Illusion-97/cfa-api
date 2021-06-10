package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.InterventionDto;

public interface InterventionService {

	List<InterventionDto> getAllIntervention();

	List<InterventionDto> getAllIntervention(int page, int size);

	List<InterventionDto> getAllInterventionWithObject();

	List<InterventionDto> getAllInterventionWithObject(int page, int size);

	InterventionDto getById(long id);

	InterventionDto saveOrUpdate(InterventionDto iDto);

	void deleteById(long id);

}
