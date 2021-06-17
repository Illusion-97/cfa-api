package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.FormationDto;

public interface FormationService {

	List<FormationDto> getAllFormation();

	List<FormationDto> getAllByPage(int page, int size, String string);

	CountDto count(String string);

	FormationDto getById(long id);

	FormationDto saveOrUpdate(FormationDto fDto);

	void deleteById(long id);

<<<<<<< Updated upstream
	
=======
	CountDto count();
>>>>>>> Stashed changes

}
