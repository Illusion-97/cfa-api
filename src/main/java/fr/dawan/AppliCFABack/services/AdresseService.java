package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.AdresseDto;
import fr.dawan.AppliCFABack.dto.CountDto;

public interface AdresseService {
	
	List<AdresseDto> getAllAdresse();
	
	List<AdresseDto> getAllAdresses(int page, int size, String string);

	AdresseDto getById(long id);

	List<AdresseDto> getAllByPage(int page, int size, String search);

	AdresseDto saveOrUpdate(AdresseDto aDto);

	void deleteById(long id);

	CountDto count(String string);


}
