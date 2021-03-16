package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.FormateurDto;

public interface FormateurService {
	
	List<FormateurDto> getAllContacts();
	List<FormateurDto> getAllContacts(int page, int max);

}
