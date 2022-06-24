package fr.dawan.AppliCFABack.services;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.dawan.AppliCFABack.dto.ActiviteTypeDto;
import fr.dawan.AppliCFABack.dto.CompetenceProfessionnelleDto;
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.ExamenDto;
import fr.dawan.AppliCFABack.dto.GroupeEtudiantDto;
import fr.dawan.AppliCFABack.dto.InterventionDto;
import fr.dawan.AppliCFABack.dto.PromotionDG2Dto;
import fr.dawan.AppliCFABack.dto.PromotionDto;
import fr.dawan.AppliCFABack.dto.PromotionForSelectDto;
import fr.dawan.AppliCFABack.dto.UtilisateurDto;
import fr.dawan.AppliCFABack.dto.customdtos.PromotionEtudiantDto;
import fr.dawan.AppliCFABack.entities.ActiviteType;
import fr.dawan.AppliCFABack.entities.CentreFormation;
import fr.dawan.AppliCFABack.entities.CompetenceProfessionnelle;
import fr.dawan.AppliCFABack.entities.Cursus;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.Examen;
import fr.dawan.AppliCFABack.entities.GroupeEtudiant;
import fr.dawan.AppliCFABack.entities.Intervention;
import fr.dawan.AppliCFABack.entities.Promotion;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.CentreFormationRepository;
import fr.dawan.AppliCFABack.repositories.CursusRepository;
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
	CursusRepository cursusRepository;
	@Autowired
	CentreFormationRepository centreFormationRepository;
	@Autowired
	PromotionRepository promotionRepository;
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();
	@Autowired
	private DtoTools mapperTools;

	/**
	 * Récupération de la liste des promo
	 * 
	 * @return lstDto	Liste des objets promotion
	 */
	//recuperation de la liste des promo
	@Override
	public List<PromotionDto> getAll() {
		List<Promotion> lst = promoRepo.findAll();
		List<PromotionDto> lstDto = new ArrayList<PromotionDto>();
		for (Promotion promo : lst) {
			lstDto.add(mapper.PromotionToPromotionDto(promo));
		}
		return lstDto;
	}

	/**
	 * Récupération des promotions en fonction de l'id
	 * 
	 * @param id	id de la promotion
	 * @return pDto	la promo
	 */
	
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
			iDto.setHeuresDisponsees();
			iDtos.add(iDto);
		}
		pDto.setInterventionsDto(iDtos);
		Set<Examen> examens = promo.getExamens();
		Set<ExamenDto> examenDtos = new HashSet<ExamenDto>();

		for (Examen examen : examens) {
			ExamenDto eDto = mapper.ExamenToExamenDto(examen);
			Set<CompetenceProfessionnelle>competenceProfessionnelles = examen.getCompetencesProfessionnelles();
			Set<CompetenceProfessionnelleDto> competenceProfessionnellesDto = new HashSet<CompetenceProfessionnelleDto>();
			for (CompetenceProfessionnelle cptP : competenceProfessionnelles) {
				competenceProfessionnellesDto.add(mapper.CompetenceProfessionnelleDto(cptP));
			}
			eDto.setCompetencesProfessionnellesDto(competenceProfessionnellesDto);
			
			List<ActiviteType> activiteTypes = examen.getActiviteTypes();
			List<ActiviteTypeDto> activiteTypesDto = new ArrayList<ActiviteTypeDto>();
			for (ActiviteType at : activiteTypes) {
				activiteTypesDto.add(mapper.ActiviteTypeToActiviteDto(at));
			}
			eDto.setActiviteTypesDto(activiteTypesDto);
			examenDtos.add(eDto);
		}		
		pDto.setExamensDto(examenDtos);
		return pDto;
	}

	/**
	 * Sauvegarde ou mise à jour d'une promotion
	 * 
	 */
	
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

	/**
	 * Suppression d'une promotion
	 * 
	 * @param id	Id concernant la promotion
	 */
	
	@Override
	public void deleteById(long id) {
		promoRepo.deleteById(id);
		filesService.deleteDirectoryWithContent("promotions/"+id);
	}

	/**
	 * Récuperation du referent avec id promo
	 * 
	 * @param id	Id de la promotion
	 */
	
	@Override
	public UtilisateurDto getReferentById(long id) {
		return mapper.UtilisateurToUtilisateurDto(promoRepo.getOne(id).getReferentPedagogique());
	}

	/**
	 * Recherche d'une promotion
	 * 
	 * @param search recherche par nom
	 */
	@Override
	public CountDto count(String search) {
		return new CountDto(promoRepo.countByNomContaining(search));
	}

	/**
	 * Va permettre de récupérer toutes les promotions avec pagination
	 * et recherche
	 * 
	 * @param page	numero de la page
	 * @param size	éléments sur la page
	 * @param search	éléments promotions (nom)
	 * @return res Liste des objets promotions
	 */
	
	@Override
	public List<PromotionDto> getAllPromotions(int page, int size, String search) {
		List<Promotion> promo = promoRepo.findAllByNomContainingAllIgnoreCase(search, PageRequest.of(page, size)).get().collect(Collectors.toList());
		List<PromotionDto> res = new ArrayList<PromotionDto>();
		for (Promotion p : promo) {
			res.add(DtoTools.convert(p, PromotionDto.class));
		}
		return res;
	}

	/**
	 * Récupération des etudiants en fonction de l'id de la promotion
	 * 
	 * @param id	id de la promotion
	 */
	
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

	/**
	 * Récupération des promo en fonction de l'id du cursus
	 * 
	 * @param id	id du cursus
	 * @return result	List des promotions
	 */
	
	@Override
	public List<PromotionDto> getAllByCursusId(long id) {
		List<Promotion> lst = promoRepo.findAllByCursusId(id);
		
		List<PromotionDto> result = new ArrayList<PromotionDto>();
		
		for(Promotion p : lst) {
			result.add(mapper.PromotionToPromotionDto(p));
		}
		
		return result;
	}
	
	/**
	 * Erreur méthodes controller-service-repo à refaire avec un dto custom pour l'accueil entier
	 * Récupération du cef en fonction de l'id de la promotion
	 *
	 * @param id	id de la promotion
	 */
	
	@Override
    public UtilisateurDto getCefById(long id) {
        return mapper.UtilisateurToUtilisateurDto(promoRepo.getOne(id).getCef().getUtilisateur());
    }

	/**
	 * Récupération des promotions en fonction de l'id du cursus et de l'etudiant
	 * 
	 * @param id	id de l'etudiant
	 * @return result	Liste des promotions
	 */
	@Override
	public List<PromotionDto> getPromotionByEtudiantIdAndByCursusId(long id) {
		List<Promotion> promos = promoRepo.getByEtudiantId(id);
		List<PromotionDto> result = new ArrayList<PromotionDto>();
		
		for(Promotion p : promos) {
			PromotionDto pDto = mapper.PromotionToPromotionDto(p);
			result.add(pDto);
		}
		return result;
	}

	/**
	 * @param id de l'étudiant
	 * utilise le PromotionRepository pour récupérer toutes les promotions par id de l'étudiant
	 * @return toutes les données nécessaires pour remplir la section Cursus du front partie étudiant par le mapper (DtoTools) :
	 * 			- titre du cursus
	 * 			- description du cursus
	 * 			- durée du cursus
	 * 			- nom de la promotion
	 * 			- dates de début et de fin de la promotion
	 * 			- plannings de l'étudiant par rapport à ses promotions
	 */
	@Override
	public List<PromotionEtudiantDto> getCursusByIdEtudiant(long id) {
		List<Promotion> promotions = promoRepo.getByEtudiantId(id);

		return promotions.stream().map(p -> mapperTools.PromotionToPromotionEtudiantDto(p)).collect(Collectors.toList());
	}

	/**
	 * @param idIntervention identifiant de l'intervention
	 * @return liste de promotions (seulement avec l'id le version et le nom)  pour le select de promotions
	 */
	@Override
	public List<PromotionForSelectDto> getPromotionByInterventionIdForSelect(long idIntervention) {
		List<PromotionForSelectDto> result = new ArrayList<PromotionForSelectDto>();
		List<Promotion> promotions = promoRepo.findAllByInterventionsId(idIntervention);
		
		for (Promotion promotion : promotions) {
			result.add(DtoTools.convert(promotion, PromotionForSelectDto.class));
		}
		
		return result;
	}

	@Override
	public int fetchDGPromotion(String email, String password) throws Exception {
		List<Cursus> cursus = new ArrayList<Cursus>();
		List<Promotion> promoLst = new ArrayList<Promotion>();
		cursus = cursusRepository.findAll();
		for(Cursus c: cursus) {
			List<Promotion> promoDg2 = new ArrayList<Promotion>();
			promoDg2 = getPromotionDG2ByIdCursusDG2(email, password, c.getIdDg2());
			if(promoDg2.size() != 0) {
				promoLst.addAll(promoDg2);
				System.out.println(c.getId()+ "Liste non vide ");
			}
		}
		for(Promotion p : promoLst) {
			try {
				promotionRepository.saveAndFlush(p);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return promoLst.size();
	}
	
	@Override
	public List<Promotion> getPromotionDG2ByIdCursusDG2(String email, String password, long idCursusDg2) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		List<PromotionDG2Dto> fetchResJson = new ArrayList<>(); 
		List<Promotion> result = new ArrayList<Promotion>();
		
		URI url = new URI("https://dawan.org/api2/cfa/trainings/" +idCursusDg2+ "/sessions");
		HttpHeaders headers = new HttpHeaders();
		headers.add("x-auth-token", email+ ":" +password);
		HttpEntity<String> httpEntity = new HttpEntity<>(headers);
		ResponseEntity<String> rep = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

		if(rep.getStatusCode()  == HttpStatus.OK) {
			String json = rep.getBody();
			
			try {
				fetchResJson = objectMapper.readValue(json, new TypeReference<List<PromotionDG2Dto>>() {} );
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			for(PromotionDG2Dto pDtoDG2 : fetchResJson) {
				System.out.println(pDtoDG2.getId());
				Optional<Promotion> promoDb = promotionRepository.findByIdDG2(pDtoDG2.getId());
				
				DtoTools dtoTools = new DtoTools();
				Promotion promotionDG2 = new Promotion();
				try {
					 promotionDG2 = dtoTools.PromotionDG2DtoToPromotion(pDtoDG2);
				} catch (Exception e) {
					e.printStackTrace();
				}
				Optional<CentreFormation> cfOpt = centreFormationRepository.findByIdDg2(pDtoDG2.getLocationId());
				System.out.println(pDtoDG2.getId() + ":" +pDtoDG2.getLocationId());
				if(cfOpt.isPresent()) {
					promotionDG2.setCentreFormation(cfOpt.get());
				}
				Optional<Cursus> cursusOpt = cursusRepository.findByIdDg2(pDtoDG2.getCourseId());
				if(cursusOpt.isPresent()) {
					promotionDG2.setCursus(cursusOpt.get());
				}
				
				//comparer voir sil existe en BDD
				if(!promoDb.isPresent()) {
					result.add(promotionDG2);
					//si existe en BDD -> comparer tous les champs et si différents -> faire update
				} else {	
					if(!promoDb.get().equals(promotionDG2)) {
						promotionDG2.setId(promoDb.get().getId());
						promotionDG2.setVersion(promoDb.get().getVersion());
						result.add(promotionDG2);
					} 
				}	
			}
			return result;
		} else {
			throw new Exception("ResponseEntity from the webservice WDG2 not correct");
		}
		
		
	}
}
