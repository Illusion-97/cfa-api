package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.PassageExamenDto;

import java.util.List;

public interface PassageExamenService {

	List<PassageExamenDto> getAllPassageExamen();

	List<PassageExamenDto> getAllByPage(int page, int size, String string);

	CountDto count(String string);

	PassageExamenDto getById(long id);

	PassageExamenDto saveOrUpdate(PassageExamenDto peDto);

	void deleteById(long id);

	

}
