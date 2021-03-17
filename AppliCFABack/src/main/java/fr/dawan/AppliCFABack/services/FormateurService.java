package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.CoursDto;
import fr.dawan.AppliCFABack.dto.FormateurDto;
import fr.dawan.AppliCFABack.dto.PersonneDto;

public interface FormateurService {

	List<FormateurDto> getAll();

	List<FormateurDto> getAll(int page, int max);

	FormateurDto findById(long id);

	CoursDto findByFormateurId(long id);

	FormateurDto findByCours(CoursDto coursDto);

	FormateurDto insert(FormateurDto formateurDto);
	
	void deleteById(long id);

}
