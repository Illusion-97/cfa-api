package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.CentreFormationDto;

public interface CentreFormationService {

	List<CentreFormationDto> getAllCentreFormation();

	List<CentreFormationDto> getAllCentreFormation(int page, int size);

	CentreFormationDto getById(long id);

	CentreFormationDto saveOrUpdate(CentreFormationDto cfDto);

	void deleteById(long id);

}
