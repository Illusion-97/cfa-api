package fr.dawan.AppliCFABack.services;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.NiveauDto;
import fr.dawan.AppliCFABack.dto.PromotionDto;
import fr.dawan.AppliCFABack.dto.PromotionForSelectDto;
import fr.dawan.AppliCFABack.dto.PromotionOrInterventionDG2Dto;
import fr.dawan.AppliCFABack.dto.UtilisateurDto;
import fr.dawan.AppliCFABack.dto.customdtos.GrillePositionnementDto;
import fr.dawan.AppliCFABack.dto.customdtos.PromotionEtudiantDto;
import fr.dawan.AppliCFABack.entities.CentreFormation;
import fr.dawan.AppliCFABack.entities.Cursus;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.Formation;
import fr.dawan.AppliCFABack.entities.Intervention;
import fr.dawan.AppliCFABack.entities.Positionnement;
import fr.dawan.AppliCFABack.entities.Promotion;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.CentreFormationRepository;
import fr.dawan.AppliCFABack.repositories.CursusRepository;
import fr.dawan.AppliCFABack.repositories.InterventionRepository;
import fr.dawan.AppliCFABack.repositories.PositionnementRepository;
import fr.dawan.AppliCFABack.repositories.PromotionRepository;
import fr.dawan.AppliCFABack.tools.FetchDG2Exception;
import fr.dawan.AppliCFABack.tools.GrilleException;
import fr.dawan.AppliCFABack.tools.PdfTools;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

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
	PositionnementRepository positionnementRepository;
	
	@Autowired
	NiveauService niveauService;
	@Value("${app.storagefolder}")
	private String storageFolder;
	
	@Autowired
	private Configuration freemarkerConfig;
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();
	@Autowired
	private DtoTools mapperTools;

	private static Logger logger = Logger.getGlobal();
	
	/**
	 * Récupération de la liste des promo
	 * 
	 * @return lstDto	Liste des objets promotion
	 */
	//recuperation de la liste des promo
	@Override
	public List<PromotionDto> getAll() {
		List<Promotion> lst = promoRepo.findAll();
		List<PromotionDto> lstDto = new ArrayList<>();
		for (Promotion promo : lst) {
			PromotionDto pDto = mapper.promotionToPromotionDto(promo);
			pDto.setCentreFormationDto(mapper.centreFormationToCentreFormationDto(promo.getCentreFormation()));
			lstDto.add(pDto);
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
	public PromotionDto getById(long id){
		return mapper.promotionToPromotionDto(promotionRepository.findById(id).get());
	}
	//@Override
	/**public PromotionDto getById(long id) {
		Optional<Promotion> promoOpt = promoRepo.findById(id);
		Promotion promo = promoRepo.getOne(id);
		PromotionDto pDto = mapper.promotionToPromotionDto(promo);
				
		pDto.setCursusDto(mapper.cursusToCursusDto(promo.getCursus()));
		pDto.setCentreFormationDto(mapper.centreFormationToCentreFormationDto(promo.getCentreFormation()));
		pDto.setReferentPedagogiqueDto(mapper.utilisateurToUtilisateurDto(promo.getReferentPedagogique()));
		pDto.setCefDto(mapper.cefToCEFDto(promo.getCef()));
		pDto.getCefDto().setUtilisateurDto(mapper.utilisateurToUtilisateurDto(promo.getCef().getUtilisateur()));
		pDto.setCentreFormationDto(mapper.centreFormationToCentreFormationDto(promo.getCentreFormation()));
		
		List<Etudiant> etudiants = promo.getEtudiants();
		List<EtudiantDto> eDtos = new ArrayList<>();	
		for(Etudiant e : etudiants) {
			EtudiantDto eDto = mapper.etudiantToEtudiantDto(e);
			List<GroupeEtudiantDto> gDtos = new ArrayList<>();
			for(GroupeEtudiant g : e.getGroupes()) {
				gDtos.add(mapper.groupeEtudiantToGroupEtudiantDto(g));
			}
			eDto.setGroupesDto(gDtos);
			eDto.setUtilisateurDto(mapper.utilisateurToUtilisateurDto(e.getUtilisateur()));
			eDtos.add(eDto);
		}
		pDto.setEtudiantsDto(eDtos);
		
		List<Intervention> interventions = promo.getInterventions();
		List<InterventionDto> iDtos = new ArrayList<>();	
		for(Intervention i : interventions) {
			InterventionDto iDto =mapper.interventionToInterventionDto(i);
			iDto.setFormationDto(mapper.formationToFormationDto(i.getFormation()));
			iDto.setHeuresDisponsees();
			iDtos.add(iDto);
		}
		pDto.setInterventionsDto(iDtos);
		Set<Examen> examens = promo.getExamens();
		Set<ExamenDto> examenDtos = new HashSet<>();

		for (Examen examen : examens) {
			ExamenDto eDto = mapper.examenToExamenDto(examen);
			Set<CompetenceProfessionnelle>competenceProfessionnelles = examen.getCompetencesProfessionnelles();
			Set<CompetenceProfessionnelleDto> competenceProfessionnellesDto = new HashSet<>();
			for (CompetenceProfessionnelle cptP : competenceProfessionnelles) {
				competenceProfessionnellesDto.add(mapper.competenceProfessionnelleDto(cptP));
			}
			eDto.setCompetencesProfessionnellesDto(competenceProfessionnellesDto);
			
			List<ActiviteType> activiteTypes = examen.getActiviteTypes();
			List<ActiviteTypeDto> activiteTypesDto = new ArrayList<>();
			for (ActiviteType at : activiteTypes) {
				activiteTypesDto.add(mapper.activiteTypeToActiviteDto(at));
			}
			eDto.setActiviteTypesDto(activiteTypesDto);
			examenDtos.add(eDto);
		}		
		pDto.setExamensDto(examenDtos);
		return pDto;
	}
*/
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
		
		return mapper.promotionToPromotionDto(p);
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
		return mapper.utilisateurToUtilisateurDto(promoRepo.getOne(id).getReferentPedagogique());
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
	
	@Override
	public CountDto countByCentreFormationId(long id, String date) {
		return new CountDto(promoRepo.countByCentreFormationIdAndDateDebutContainingAllIgnoringCase(id, date));
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
		List<Promotion> promoSlug = promoRepo.findAllByNomContainingAllIgnoreCase(search, PageRequest.of(page, size)).get().collect(Collectors.toList());
		List<Promotion> promoVille = promoRepo.findAllByCentreFormationNomAllIgnoreCase(search, PageRequest.of(page, size)).get().collect(Collectors.toList());
		List<PromotionDto> res = new ArrayList<>();
		if (!promoSlug.isEmpty()){
			for (Promotion p : promoSlug) {
				PromotionDto promotionDto = mapper.promotionToPromotionDto(p);
				res.add(promotionDto);
			}
		}
		if (!promoVille.isEmpty()){
			for (Promotion p : promoVille) {
				PromotionDto promotionDto = mapper.promotionToPromotionDto(p);
				res.add(promotionDto);
			}
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
		List<EtudiantDto> lstDto = new ArrayList<>();
		for (Etudiant e : lst) {
			List<PromotionDto> promoList = new ArrayList<>();
			for(Promotion p : e.getPromotions()) {
				promoList.add(mapper.promotionToPromotionDto(p));
			}
			EtudiantDto eDto = mapper.etudiantToEtudiantDto(e);
			eDto.setPromotionsDto(promoList);
			eDto.setUtilisateurDto(mapper.utilisateurToUtilisateurDto(e.getUtilisateur()));
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
		
		List<PromotionDto> result = new ArrayList<>();
		
		for(Promotion p : lst) {
			result.add(mapper.promotionToPromotionDto(p));
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
        return mapper.utilisateurToUtilisateurDto(promoRepo.getOne(id).getCef().getUtilisateur());
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
		List<PromotionDto> result = new ArrayList<>();
		
		for(Promotion p : promos) {
			PromotionDto pDto = mapper.promotionToPromotionDto(p);
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
//	@Override
//	public List<PromotionEtudiantDto> getCursusByIdEtudiant(long id){
//		List<PromotionEtudiantDto> result = new ArrayList<>();
//		List<Promotion> promotions = promoRepo.getByEtudiantId(id);
//		for(Promotion p : promotions){
//			result.add(DtoTools.convert(p, PromotionEtudiantDto.class));
//		}
//		return result;
//	}

	@Override
	public List<PromotionEtudiantDto> getCursusByIdEtudiant(long id) {
		List<Promotion> promotions = promoRepo.getByEtudiantId(id);

		return promotions.stream().map(p -> mapperTools.promotionToPromotionEtudiantDto(p)).collect(Collectors.toList());
	}

	/**
	 * @param idIntervention identifiant de l'intervention
	 * @return liste de promotions (seulement avec l'id le version et le nom)  pour le select de promotions
	 */
	@Override
	public List<PromotionForSelectDto> getPromotionByInterventionIdForSelect(long idIntervention) {
		List<PromotionForSelectDto> result = new ArrayList<>();
		List<Promotion> promotions = promoRepo.findAllByInterventionsId(idIntervention);
		
		for (Promotion promotion : promotions) {
			result.add(DtoTools.convert(promotion, PromotionForSelectDto.class));
		}
		
		return result;
	}

	@Override
	public int fetchDGPromotions(String email, String password) throws FetchDG2Exception, URISyntaxException {
		List<Cursus> cursus = new ArrayList<>();
		List<Promotion> promoLst = new ArrayList<>();
		cursus = cursusRepository.findAll();
		for(Cursus c: cursus) {
			List<Promotion> promoDg2 = new ArrayList<>();
			promoDg2 = getPromotionDG2ByIdCursusDG2(email, password, c.getIdDg2());
			if(!promoDg2.isEmpty() ) {
				promoLst.addAll(promoDg2);
			}
			else {
				logger.info(c.getId()+"Liste non vide");
			}
		}
		for(Promotion p : promoLst) {
			try {
				Promotion pps =	promotionRepository.saveAndFlush(p);
				System.out.println("pps.getId() = " + pps.getId());
			} catch (Exception e) {
				logger.log(Level.SEVERE,"SaveAndFlush failed", e);
			}
		}
		return promoLst.size();
	}
	@Override
	public int fetchDGPromotions(String email, String password, long idCursusDg2) throws FetchDG2Exception, URISyntaxException {
		List<Promotion> promoLst = getPromotionDG2ByIdCursusDG2(email, password, idCursusDg2);
		
		for(Promotion p : promoLst) {
			try {
				promotionRepository.saveAndFlush(p);
			} catch (Exception e) {
				logger.log(Level.SEVERE,"SaveAndFlush failed", e);
			}
		}
		return promoLst.size();
	}
	
	@Override
	public List<Promotion> getPromotionDG2ByIdCursusDG2(String email, String password, long idCursusDg2) throws FetchDG2Exception, URISyntaxException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		List<PromotionOrInterventionDG2Dto> fetchResJson = new ArrayList<>(); 
		List<Promotion> result = new ArrayList<>();
		
		URI url = new URI("https://dawan.org/api2/cfa/trainings/" +idCursusDg2+ "/sessions");
		HttpHeaders headers = new HttpHeaders();
		headers.add("x-auth-token", email+ ":" +password);
		HttpEntity<String> httpEntity = new HttpEntity<>(headers);
		ResponseEntity<String> rep = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

		if(rep.getStatusCode()  == HttpStatus.OK) {
			String json = rep.getBody();
			
			try {
				fetchResJson = objectMapper.readValue(json, new TypeReference<List<PromotionOrInterventionDG2Dto>>() {} );
			} catch (Exception e) {
				logger.log(Level.SEVERE,"objectMapper failed", e);
			}
			
			
			for(PromotionOrInterventionDG2Dto pDtoDG2 : fetchResJson) {
				Optional<Promotion> promoDb = promotionRepository.findByIdDg2(pDtoDG2.getId());
				
				DtoTools dtoTools = new DtoTools();
				Promotion promotionDG2 = new Promotion();
				try {
					 promotionDG2 = dtoTools.promotionOrInterventionDG2DtoToPromotion(pDtoDG2);
					
				} catch (Exception e) {
					logger.log(Level.SEVERE,"dtoTools failed", e);
				}
//				Optional<CentreFormation> cfOpt = centreFormationRepository.findByIdDg2(pDtoDG2.getLocationId());
//				// valeur null centre de formation Id cause le problem
//				System.out.println(">>>>>>>>>" + pDtoDG2.getId() + ":" +pDtoDG2.getLocationId());
//				if(cfOpt.isPresent()) {
//					promotionDG2.setCentreFormation(cfOpt.get());
//				}
				Optional<Cursus> cursusOpt = cursusRepository.findByIdDg2(pDtoDG2.getCourseId());
				if(cursusOpt.isPresent()) {
					promotionDG2.setCursus(cursusOpt.get());
				}
				Optional<CentreFormation> centreDeFormationOptional = centreFormationRepository.findByIdDg2(pDtoDG2.getLocationId());
				if (centreDeFormationOptional.isPresent()) {
					promotionDG2.setCentreFormation(centreDeFormationOptional.get());
				}
				promotionDG2.setType(pDtoDG2.getType());
				promotionDG2.setNbParticipants(pDtoDG2.getNbParticipants());
				//comparer voir sil existe en BDD
				if(!promoDb.isPresent()) {
					result.add(promotionDG2);
					//si existe en BDD -> comparer tous les champs et si différents -> faire update
				} else {	
					if(!promoDb.get().equals(promotionDG2)) {
						promotionDG2.setId(promoDb.get().getId());
						promotionDG2.setVersion(promoDb.get().getVersion());
						promotionDG2.setType(pDtoDG2.getType());
						promotionDG2.setNbParticipants(pDtoDG2.getNbParticipants());
						result.add(promotionDG2);
					} 
				}	
			}
			return result;
		} else {
			throw new FetchDG2Exception("ResponseEntity from the webservice WDG2 not correct");
		}
		
		
	}

	
	@Override
	public String getGrillePositionnement(long idPromotion) throws GrilleException, TemplateNotFoundException, 
	MalformedTemplateNameException, ParseException, IOException, TemplateException {
		Optional<Promotion> promotion = promotionRepository.findById(idPromotion);
		if (!promotion.isPresent())
			throw new GrilleException("Promotion non trouvé");
		
		List<Intervention> interventions = interventionRepository.getInterventionsByIdPromotion(idPromotion);
		if (interventions.isEmpty() || interventions == null)
			throw new GrilleException("Pas d'interventions encore pour cette promotion non trouvé");

		Map<String, List<?>> gp = new HashMap<>();
		List<GrillePositionnementDto> grillesPositionnements = new ArrayList<>();
		
		for (Intervention i : interventions) {
			GrillePositionnementDto gpd = new GrillePositionnementDto();
			gpd.setDateDebut(i.getDateDebut());
			gpd.setDateFin(i.getDateFin());
			Formation f = i.getFormation();
			if (f != null) {
				gpd.setModule(f.getTitre());	
			}else {
				gpd.setModule("Pas de Formation");
			}
			gpd.setObjectifPedagogiques("A définir");
			gpd.setFormateur(i.getFormateur().getUtilisateur().getFullName());
			Map<String, Positionnement> etudiantsPositionnement = new HashMap<>();
			List<Positionnement> positionnements =  positionnementRepository.getAllByInterventionId(i.getId());
			
			for (Positionnement p : positionnements) {
				etudiantsPositionnement.put(p.getEtudiant().getUtilisateur().getNom() + " " 
			+p.getEtudiant().getUtilisateur().getPrenom(), p);
			}
			gpd.setEtudiantsPositionnements(etudiantsPositionnement);
			grillesPositionnements.add(gpd);
		}
		gp.put("interventions", grillesPositionnements);
		List<NiveauDto> niveaux = niveauService.getAllNiveaux();
		gp.put("niveaux", niveaux);
		freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");
		Template template = freemarkerConfig.getTemplate("GrillePositionnement.ftl");
		String htmlContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, gp);
		String outputPdf = storageFolder + "grillePositionnements/GrillePositionnement"+promotion.get().getNom()+".pdf";

		try {
			PdfTools.generatePdfFromHtml(outputPdf,htmlContent );
		} catch (Exception e) {
			logger.log(Level.SEVERE,"convertHtmlToPdf failed", e);
		}
		return outputPdf;
	}

	@Override
	public List<PromotionDto> getPromoByCentreFormationIdPagination(int page, int size, long id, String search) {
		List<Promotion> result = promoRepo.findPromotionsByCentreFormationIdAndDateDebutContainingAllIgnoringCase(id, PageRequest.of(page, size), search).get().collect(Collectors.toList());
		List<PromotionDto> res = new ArrayList<>();
		for(Promotion p: result) {
			res.add(DtoTools.convert(p, PromotionDto.class));
		}
		return res;
	}




}
