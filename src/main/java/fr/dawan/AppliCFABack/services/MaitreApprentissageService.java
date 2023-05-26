package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.MaitreApprentissageDto;

import java.util.List;

public interface MaitreApprentissageService {


	List<MaitreApprentissageDto> getAllMaitreApprentissage();

	List<MaitreApprentissageDto> getAllMaitreApprentissage(int page, int size);

	MaitreApprentissageDto getById(long id);
	
	MaitreApprentissageDto getMaitreApprentissageByEtudiantId(long id);
	
	MaitreApprentissageDto saveOrUpdate(MaitreApprentissageDto cDto);

	void deleteById(long id);


}
