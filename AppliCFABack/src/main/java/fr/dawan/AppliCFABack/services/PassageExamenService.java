package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.PassageExamenDto;

public interface PassageExamenService {

	List<PassageExamenDto> getAllPassageExamen();

	List<PassageExamenDto> getAllPassageExamen(int page, int size);

	PassageExamenDto getById(long id);

	PassageExamenDto saveOrUpdate(PassageExamenDto peDto);

	void deleteById(long id);

}
