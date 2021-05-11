package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.CongeDto;

public interface CongeService {

	List<CongeDto> getAllConge();

	CongeDto getById(long id);

	List<CongeDto> getAllConge(int page, int size);

	CongeDto saveOrUpdate(CongeDto cDto);

	void deleteById(long id);

}
