package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.PromotionDto;
import fr.dawan.AppliCFABack.entities.Promotion;
import fr.dawan.AppliCFABack.repositories.PromotionRepository;

@Service
@Transactional
public class PromotionServiceImpl implements PromotionService {
	
	@Autowired
	private PromotionRepository promoRepo;

	@Override
	public List<PromotionDto> getAll() {
		// TODO Auto-generated method stub
		List<Promotion> lst = promoRepo.findAll();
		List<PromotionDto> lstDto = new ArrayList<PromotionDto>();
		for (Promotion promo : lst) {
			lstDto.add(DtoTools.convert(promo, PromotionDto.class));
		}
		return lstDto;
	}

	@Override
	public PromotionDto getById(long id) {
		// TODO Auto-generated method stub
		Promotion promo = promoRepo.getOne(id);
		
		return DtoTools.convert(promo, PromotionDto.class);
	}

	@Override
	public PromotionDto saveOrUpdate(PromotionDto pDto) {
		// TODO Auto-generated method stub
		Promotion p = DtoTools.convert(pDto, Promotion.class);
		promoRepo.saveAndFlush(p);
		return DtoTools.convert(p, PromotionDto.class);
	}

	@Override
	public void deleteById(long id) {
		// TODO Auto-generated method stub
		promoRepo.deleteById(id);
	}

}
