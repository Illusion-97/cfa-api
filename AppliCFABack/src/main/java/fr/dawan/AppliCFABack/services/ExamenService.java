package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.ExamenDto;

public interface ExamenService {

	List<ExamenDto> getAllExamen();

	List<ExamenDto> getAllExamen(int page, int size);

	ExamenDto getById(long id);

	ExamenDto saveOrUpdate(ExamenDto eDto);

	void deleteById(long id);

}
