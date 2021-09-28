package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.CursusDto;
import fr.dawan.AppliCFABack.dto.PromotionDto;

public interface CursusService {

	List<CursusDto> getAll();

	CursusDto saveOrUpdate(CursusDto pDto);

	void deleteById(long id);

	CursusDto getById(long id);

	List<CursusDto> getAllByPage(int page, int size, String search);

	CountDto count(String string);

	CursusDto getByIdPromotion(long id);

	List<PromotionDto> getPromotionsById(long id);

}
