package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.InterventionDto;

public interface InterventionService {

	List<InterventionDto> getAllIntervention();

	List<InterventionDto> getAllInterventionWithObject();

	List<InterventionDto> getAllIntervention(int page, int size);

	List<InterventionDto> getAllByPage(int page, int size, String string);

	InterventionDto getById(long id);

	InterventionDto saveOrUpdate(InterventionDto iDto);

	void deleteById(long id);

	CountDto count(String string);

}
