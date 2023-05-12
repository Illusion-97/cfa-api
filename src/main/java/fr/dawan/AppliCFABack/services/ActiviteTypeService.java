package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.ActiviteTypeDto;

import java.util.List;

public interface ActiviteTypeService {

	List<ActiviteTypeDto> getAllActiviteType();
	List<ActiviteTypeDto> getAllActiviteType(int page, int size, String string);
	ActiviteTypeDto getById(long id);
	ActiviteTypeDto saveOrUpdate(ActiviteTypeDto atDto);
	void deleteById(long id);
	List<ActiviteTypeDto> getAllActiviteTypesByPromotionId(long id);
	List<ActiviteTypeDto> getAllActiviteTypesByCursus(long id);

	
}
