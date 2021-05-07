package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.FormateurDto;

public interface FormateurService {
	List<FormateurDto> getAll();

	List<FormateurDto> getAll(int page, int size);
	
	FormateurDto getById(long id);
	
	
}
