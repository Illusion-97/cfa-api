package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.RemunerationDto;

import java.util.List;

public interface RemunerationService {
	CountDto count(String string);

	List<RemunerationDto> getAllByPage(int page, int size, String string);

	RemunerationDto getById(long id);

	List<RemunerationDto> getAll();

	void deleteById(long id);

	RemunerationDto saveOrUpdate(RemunerationDto rDto);
}
