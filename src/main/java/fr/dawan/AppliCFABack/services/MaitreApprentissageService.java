package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.MaitreApprentissageDto;

public interface MaitreApprentissageService {


	List<MaitreApprentissageDto> getAllMaitreApprentissage();

	List<MaitreApprentissageDto> getAllMaitreApprentissage(int page, int size);

	MaitreApprentissageDto getById(long id);
	
	MaitreApprentissageDto saveOrUpdate(MaitreApprentissageDto cDto);

	void deleteById(long id);


}
