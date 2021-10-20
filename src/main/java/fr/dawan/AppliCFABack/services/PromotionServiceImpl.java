package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.GroupeEtudiantDto;
import fr.dawan.AppliCFABack.dto.InterventionDto;
import fr.dawan.AppliCFABack.dto.PromotionDto;
import fr.dawan.AppliCFABack.dto.UtilisateurDto;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.GroupeEtudiant;
import fr.dawan.AppliCFABack.entities.Intervention;
import fr.dawan.AppliCFABack.entities.Promotion;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.InterventionRepository;
import fr.dawan.AppliCFABack.repositories.PromotionRepository;

@Service
@Transactional
public class PromotionServiceImpl implements PromotionService {
	
	@Autowired
	private PromotionRepository promoRepo;
	
	@Autowired
	FilesService filesService;
	@Autowired
	InterventionRepository interventionRepository;

	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();

	@Override
	public List<PromotionDto> getAll() {
		List<Promotion> lst = promoRepo.findAll();
		List<PromotionDto> lstDto = new ArrayList<PromotionDto>();
		for (Promotion promo : lst) {
			lstDto.add(mapper.PromotionToPromotionDto(promo));
		}
		return lstDto;
	}

	@Override
	public PromotionDto getById(long id) {
		Promotion promo = promoRepo.getOne(id);
		PromotionDto pDto = mapper.PromotionToPromotionDto(promo);
				
		pDto.setCursusDto(mapper.CursusToCursusDto(promo.getCursus()));
		pDto.setCentreFormationDto(mapper.CentreFormationToCentreFormationDto(promo.getCentreFormation()));
		pDto.setReferentPedagogiqueDto(mapper.UtilisateurToUtilisateurDto(promo.getReferentPedagogique()));
		pDto.setCefDto(mapper.CEFToCEFDto(promo.getCef()));		
		pDto.getCefDto().setUtilisateurDto(mapper.UtilisateurToUtilisateurDto(promo.getCef().getUtilisateur()));
		
		List<Etudiant> etudiants = promo.getEtudiants();
		List<EtudiantDto> eDtos = new ArrayList<EtudiantDto>();	
		for(Etudiant e : etudiants) {
			EtudiantDto eDto = mapper.EtudiantToEtudiantDto(e);
			List<GroupeEtudiantDto> gDtos = new ArrayList<GroupeEtudiantDto>();
			for(GroupeEtudiant g : e.getGroupes()) {
				gDtos.add(mapper.GroupeEtudiantToGroupEtudiantDto(g));
			}
			eDto.setGroupesDto(gDtos);
			eDto.setUtilisateurDto(mapper.UtilisateurToUtilisateurDto(e.getUtilisateur()));
			eDtos.add(eDto);
		}
		pDto.setEtudiantsDto(eDtos);
		
		List<Intervention> interventions = promo.getInterventions();
		List<InterventionDto> iDtos = new ArrayList<InterventionDto>();	
		for(Intervention i : interventions) {
			InterventionDto iDto =mapper.InterventionToInterventionDto(i);
			iDto.setFormationDto(mapper.FormationToFormationDto(i.getFormation()));
			iDtos.add(iDto);
		}
		pDto.setInterventionsDto(iDtos);
		
		
		return pDto;
	}

	@Override
	public PromotionDto saveOrUpdate(PromotionDto pDto) {
		Promotion p = DtoTools.convert(pDto, Promotion.class);
		
		p = promoRepo.saveAndFlush(p);
		
		/*
	
		//Les interventions sont mappés dans Intervention
		if(pDto.getInterventionsDto() != null) {
			//On vérifie pour chaque intervention de promotion
			for(InterventionDto iDto : pDto.getInterventionsDto()) {
				Intervention intervention = interventionRepository.findById(iDto.getId()).get();
				//On vérifie si intervention ne connait pas promotion
				boolean verif = false;
				for(Promotion promotion : intervention.getPromotions()) {
					if(promotion.getId() == p.getId()) verif = true;
				}
				//Si intervention ne connait pas promotion :
				if(!verif) {
					List<Promotion> promos = intervention.getPromotions();
					promos.add(promoRepo.getOne(p.getId()));
					intervention.setPromotions(promos);
					interventionRepository.saveAndFlush(intervention);
				}
			}	
		}
		
		*/
		
		filesService.createDirectory("promotions/" + p.getId());
		
		return mapper.PromotionToPromotionDto(p);
	}

	@Override
	public void deleteById(long id) {
		promoRepo.deleteById(id);
		filesService.deleteDirectoryWithContent("promotions/"+id);
	}

	@Override
	public UtilisateurDto getReferentById(long id) {
		return mapper.UtilisateurToUtilisateurDto(promoRepo.getOne(id).getReferentPedagogique());
	}

	@Override
	public CountDto count(String search) {
		return new CountDto(promoRepo.countByNomContaining(search));
	}

	@Override
	public List<PromotionDto> getAllPromotions(int page, int size, String search) {
		List<Promotion> promo = promoRepo.findAllByNomContainingAllIgnoreCase(search, PageRequest.of(page, size)).get().collect(Collectors.toList());
		List<PromotionDto> res = new ArrayList<PromotionDto>();
		for (Promotion p : promo) {
			res.add(mapper.PromotionToPromotionDto(p));
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
				promoList.add(mapper.PromotionToPromotionDto(p));
			}
			EtudiantDto eDto = mapper.EtudiantToEtudiantDto(e);
			eDto.setPromotionsDto(promoList);
			eDto.setUtilisateurDto(mapper.UtilisateurToUtilisateurDto(e.getUtilisateur()));
			lstDto.add(eDto);
		}
		return lstDto;
	}

	@Override
	public List<PromotionDto> getAllByCursusId(long id) {
		List<Promotion> lst = promoRepo.findAllByCursusId(id);
		
		List<PromotionDto> result = new ArrayList<PromotionDto>();
		
		for(Promotion p : lst) {
			result.add(mapper.PromotionToPromotionDto(p));
		}
		
		return result;
	}

}
