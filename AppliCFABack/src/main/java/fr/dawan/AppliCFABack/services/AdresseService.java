package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.AdresseDto;

public interface AdresseService {

	List<AdresseDto> getAllAdresse();

	AdresseDto getById(long id);

	AdresseDto saveOrUpdate(AdresseDto aDto);

	void deleteById(long id);

}
