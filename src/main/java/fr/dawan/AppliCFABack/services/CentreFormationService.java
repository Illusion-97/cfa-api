package fr.dawan.AppliCFABack.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.dawan.AppliCFABack.dto.CentreFormationDto;
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.tools.FetchDG2Exception;

import java.net.URISyntaxException;
import java.util.List;

public interface CentreFormationService {

	List<CentreFormationDto> getAllCentreFormation();

	List<CentreFormationDto> getAllCentreFormation(int page, int size);

	CentreFormationDto getById(long id);

	CentreFormationDto saveOrUpdate(CentreFormationDto cfDto);

	void deleteById(long id);

	CountDto count(String string);

	List<CentreFormationDto> getAllCentreFormations(int page, int size, String string);

}
