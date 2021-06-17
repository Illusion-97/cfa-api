package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DevoirDto;

public interface DevoirService {

	List<DevoirDto> getAllDevoir();

	DevoirDto getById(long id);

	DevoirDto saveOrUpdate(DevoirDto dDto);

	List<DevoirDto> getAllDevoir(int page, int size);

	void deleteById(long id);

	CountDto count();

}
