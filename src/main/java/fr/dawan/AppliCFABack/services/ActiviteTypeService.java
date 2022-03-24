package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.ActiviteTypeDto;

public interface ActiviteTypeService {

	List<ActiviteTypeDto> getAllActiviteType();
	List<ActiviteTypeDto> getAllActiviteType(int page, int size, String string);
	ActiviteTypeDto getById(long id);
	ActiviteTypeDto saveOrUpdate(ActiviteTypeDto atDto);
	void deleteById(long id);
	List<ActiviteTypeDto> getAllActiviteTypesByPromotionId(long id);

	
}
