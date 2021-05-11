package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.PromotionDto;
import fr.dawan.AppliCFABack.dto.UtilisateurDto;
import fr.dawan.AppliCFABack.entities.Promotion;
import fr.dawan.AppliCFABack.repositories.PromotionRepository;

@Service
@Transactional
public class PromotionServiceImpl implements PromotionService {
	
	@Autowired
	private PromotionRepository promoRepo;
	
	@Autowired
	FilesService filesService;

	@Override
	public List<PromotionDto> getAll() {
		List<Promotion> lst = promoRepo.findAll();
		List<PromotionDto> lstDto = new ArrayList<PromotionDto>();
		for (Promotion promo : lst) {
			lstDto.add(DtoTools.convert(promo, PromotionDto.class));
		}
		return lstDto;
	}

	@Override
	public PromotionDto getById(long id) {
		Promotion promo = promoRepo.getOne(id);
		
		return DtoTools.convert(promo, PromotionDto.class);
	}

	@Override
	public PromotionDto saveOrUpdate(PromotionDto pDto) {
		Promotion p = DtoTools.convert(pDto, Promotion.class);
		promoRepo.saveAndFlush(p);
		
		filesService.createDirectory("promotions/" + p.getId());
		
		return DtoTools.convert(p, PromotionDto.class);
	}

	@Override
	public void deleteById(long id) {
		promoRepo.deleteById(id);
//		filesService.deleteDirectoryWithContent("promotions/"+id);
	}

	@Override
	public UtilisateurDto getReferentById(long id) {
		return DtoTools.convert(promoRepo.getOne(id).getReferentPedagogique(), UtilisateurDto.class);
	}

}
