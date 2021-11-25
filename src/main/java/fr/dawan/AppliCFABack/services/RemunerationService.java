package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.RemunerationDto;

public interface RemunerationService {
	CountDto count(String string);

	List<RemunerationDto> getAllByPage(int page, int size, String string);

	RemunerationDto getById(long id);

	List<RemunerationDto> getAll();

	void deleteById(long id);

	RemunerationDto saveOrUpdate(RemunerationDto rDto);
}
