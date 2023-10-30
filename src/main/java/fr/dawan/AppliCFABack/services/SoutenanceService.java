package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.SoutenanceDto;

public interface SoutenanceService extends GenericService<SoutenanceDto> {
	
	List<SoutenanceDto> getByPromotionId(long id);
	
	List<SoutenanceDto> getPageByPromotionId(long id, int page, int size);
	
	SoutenanceDto save(SoutenanceDto soutenanceDto);
}
