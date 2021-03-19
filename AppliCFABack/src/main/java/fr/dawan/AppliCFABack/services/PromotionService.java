package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.PromotionDto;

public interface PromotionService {

	List<PromotionDto> getAll();

	PromotionDto getById(long id);

	PromotionDto saveOrUpdate(PromotionDto pDto);

}
