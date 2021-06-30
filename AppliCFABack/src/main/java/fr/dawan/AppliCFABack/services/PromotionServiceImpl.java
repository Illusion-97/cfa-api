package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.CentreFormationDto;
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.CursusDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.FormationDto;
import fr.dawan.AppliCFABack.dto.GroupeEtudiantDto;
import fr.dawan.AppliCFABack.dto.InterventionDto;
import fr.dawan.AppliCFABack.dto.PromotionDto;
import fr.dawan.AppliCFABack.dto.UtilisateurDto;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.GroupeEtudiant;
import fr.dawan.AppliCFABack.entities.Intervention;
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
		PromotionDto pDto = DtoTools.convert(promo, PromotionDto.class);
				
		pDto.setCursusDto(DtoTools.convert(promo.getCursus(), CursusDto.class));
		pDto.setCentreFormationDto(DtoTools.convert(promo.getCentreFormation(), CentreFormationDto.class));
		pDto.setReferentPedagogiqueDto(DtoTools.convert(promo.getReferentPedagogique(), UtilisateurDto.class));
		pDto.setCefDto(DtoTools.convert(promo.getCef(), UtilisateurDto.class));		
		
		List<Etudiant> etudiants = promo.getEtudiants();
		List<EtudiantDto> eDtos = new ArrayList<EtudiantDto>();	
		for(Etudiant e : etudiants) {
			EtudiantDto eDto = DtoTools.convert(e, EtudiantDto.class);
			List<GroupeEtudiantDto> gDtos = new ArrayList<GroupeEtudiantDto>();
			for(GroupeEtudiant g : e.getGroupes()) {
				gDtos.add(DtoTools.convert(g, GroupeEtudiantDto.class));
			}
			eDto.setGroupesDto(gDtos);
			eDtos.add(eDto);
		}
		pDto.setEtudiantsDto(eDtos);
		
		List<Intervention> interventions = promo.getInterventions();
		List<InterventionDto> iDtos = new ArrayList<InterventionDto>();	
		for(Intervention i : interventions) {
			InterventionDto iDto = DtoTools.convert(i, InterventionDto.class);
			iDto.setFormationDto(DtoTools.convert(i.getFormation(), FormationDto.class));
			iDtos.add(iDto);
		}
		pDto.setInterventionsDto(iDtos);
		
		
		return pDto;
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
		filesService.deleteDirectoryWithContent("promotions/"+id);
	}

	@Override
	public UtilisateurDto getReferentById(long id) {
		return DtoTools.convert(promoRepo.getOne(id).getReferentPedagogique(), UtilisateurDto.class);
	}

	@Override
	public CountDto count(String search) {
		return new CountDto(promoRepo.countByNomContaining(search));
	}

	@Override
	public List<PromotionDto> getAllPromotions(int page, int size, String search) {
		List<Promotion> promo = promoRepo.findAllByNomContaining(search, PageRequest.of(page, size)).get().collect(Collectors.toList());
		List<PromotionDto> res = new ArrayList<PromotionDto>();
		for (Promotion p : promo) {
			res.add(DtoTools.convert(p, PromotionDto.class));
		}
		return res;
	}

	@Override
	public List<EtudiantDto> getEtudiantsById(long id) {
		List<Etudiant> lst = promoRepo.getOne(id).getEtudiants();
		List<EtudiantDto> lstDto = new ArrayList<EtudiantDto>();
		for (Etudiant e : lst) {
			List<PromotionDto> promoList = new ArrayList<PromotionDto>();
			for(Promotion p : e.getPromotions()) {
				promoList.add(DtoTools.convert(p, PromotionDto.class));
			}
			EtudiantDto eDto = DtoTools.convert(e, EtudiantDto.class);
			eDto.setPromotionsDto(promoList);
			lstDto.add(eDto);
		}
		return lstDto;
	}

}
