package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.CursusDto;
import fr.dawan.AppliCFABack.dto.PromotionDto;
import fr.dawan.AppliCFABack.tools.FetchDG2Exception;
import org.springframework.data.domain.Page;

import java.net.URISyntaxException;
import java.util.List;

public interface CursusService {

	List<CursusDto> getAll();

	CursusDto saveOrUpdate(CursusDto pDto);

	void deleteById(long id);

	CursusDto getById(long id);

	List<CursusDto> getAllByPage(int page, int size, String search);

	CountDto count(String string);

	CursusDto getByIdPromotion(long id);
	Page<PromotionDto> getByIdPromotionAndByPage(long idCursus, int page, int size);
	List<PromotionDto> getPromotionsById(long id);

	void fetchDG2Cursus(String email, String password) throws FetchDG2Exception, URISyntaxException;

	CountDto countPromotion(long id);

}
