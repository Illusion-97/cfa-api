package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.PromotionDto;
import fr.dawan.AppliCFABack.dto.UtilisateurDto;

public interface PromotionService {

	List<PromotionDto> getAll();

	PromotionDto getById(long id);

	PromotionDto saveOrUpdate(PromotionDto pDto);

	void deleteById(long id);

	UtilisateurDto getReferentById(long id);

}
