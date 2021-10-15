package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.ExamenDto;

public interface ExamenService {

	List<ExamenDto> getAllExamen();

	List<ExamenDto> getAllByPage(int page, int size, String string);

	CountDto count(String string);

	ExamenDto getById(long id);

	ExamenDto saveOrUpdate(ExamenDto eDto);

	void deleteById(long id);

	

}
