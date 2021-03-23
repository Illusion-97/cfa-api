package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.FormationDto;

public interface FormationService {

	List<FormationDto> getAllFormation();

	List<FormationDto> getAllFormation(int page, int size);

	FormationDto getById(long id);

	FormationDto saveOrUpdate(FormationDto fDto);

	void deleteById(long id);

}
