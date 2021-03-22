package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.CEFDto;

public interface CEFService {

	List<CEFDto> getAllCef();

	List<CEFDto> getAllCef(int page, int size);

	CEFDto getById(long id);

	CEFDto saveOrUpdate(CEFDto cDto);

	void deleteById(long id);

}
