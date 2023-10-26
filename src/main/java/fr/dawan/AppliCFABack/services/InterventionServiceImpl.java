package fr.dawan.AppliCFABack.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.dawan.AppliCFABack.dto.*;
import fr.dawan.AppliCFABack.entities.*;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.*;
import fr.dawan.AppliCFABack.tools.FetchDG2Exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class InterventionServiceImpl implements InterventionService {

	@Autowired
	InterventionRepository interventionRepository;
	@Autowired
	PromotionRepository promoRepository;
	@Autowired
	FormateurRepository formateurRepository;
	@Autowired
	FormationRepository formationRepository;
	@Autowired
	PassageExamenRepository passageExamRepository;
	@Autowired
	EtudiantRepository etudiantRepository;
	@Autowired
	DevoirRepository devoirRepository;
	@Autowired
	PassageExamenRepository passageExamenRepository;
	@Autowired
	FilesService filesService;
	@Autowired
	UtilisateurRepository utilisateurRepository;
	@Autowired
	CentreFormationRepository centreFormationRepository;

	@Autowired
	UtilisateurRoleRepository utilisateurRoleRepository;
	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();

	@Autowired
	private RestTemplate restTemplate;

	private static Logger logger = LoggerFactory.getLogger(InterventionServiceImpl.class);
	
	@Value("${base_url_dg2}")
    private String baseUrl;

	/**
	 * Récupération de toutes les interventions
	 * 
	 * @return lstDto Liste des objets interevention
	 */

	@Override
	public List<InterventionDto> getAllIntervention() {
		List<Intervention> lst = interventionRepository.findAll();

		List<InterventionDto> lstDto = new ArrayList<>();
		for (Intervention i : lst) {
			InterventionDto interventionDto = mapper.interventionToInterventionDto(i);
			interventionDto.setHeuresDisponsees();
			lstDto.add(interventionDto);

		}
		return lstDto;
	}

	/**
	 * Va permettre de récupérer toutes les interventions
	 * 
	 * @param page numero de la page
	 * @param size éléments sur la page
	 * @return LstDto Liste des objets interventions
	 */
	@Override
	public List<InterventionDto> getAllIntervention(int page, int size) {
		List<Intervention> lst = interventionRepository.findAll(PageRequest.of(page, size)).get()
				.collect(Collectors.toList());
		// conversion vers Dto
		List<InterventionDto> lstDto = new ArrayList<>();

		for (Intervention i : lst) {
			InterventionDto interventionDto = mapper.interventionToInterventionDto(i);
			FormationDto formationDto = mapper.formationToFormationDto(i.getFormation());
			interventionDto.setFormationDto(formationDto);

			lstDto.add(interventionDto);
		}
		return lstDto;
	}

	/**
	 * Va permettre de récupérer toutes les interventions avec pagination
	 * 
	 * @param page   numero de page
	 * @param size   nombre d'éléments
	 * @param search éléménts de l'intervention
	 * @return List Liste des devoirs concerné
	 */

	@Override
	public List<InterventionDto> getAllByPage(int page, int size, String sort, String search) {
		List<Intervention> lstIn = interventionRepository
				.findAllDistinctByFormationTitreContainingIgnoringCaseOrPromotionsNomContainingIgnoringCase(search,
						search, PageRequest.of(page, size))
				.get().collect(Collectors.toList());
		switch (sort){
			case "datefin": lstIn.sort(Comparator.comparing(Intervention::getDateFin).reversed());
				break;
			case "datedebut": lstIn.sort(Comparator.comparing(Intervention::getDateDebut));
				break;
		}
		List<InterventionDto> lstDto = new ArrayList<>();

		for (Intervention intervention : lstIn) {

			InterventionDto interventionDto = mapper.interventionToInterventionDto(intervention);

			FormationDto formationDto = mapper.formationToFormationDto(intervention.getFormation());
			interventionDto.setFormationDto(formationDto);
			List<Promotion> lstPromo = intervention.getPromotions();
			List<PromotionDto> lstPromoDto = new ArrayList<>();
			for (Promotion promotion : lstPromo) {
				if (promotion != null)
					lstPromoDto.add(mapper.promotionToPromotionDto(promotion));
			}

			FormateurDto lstFormDto = new FormateurDto();

			interventionDto.setFormateurDto(lstFormDto);

			interventionDto.setPromotionsDto(lstPromoDto);

			lstDto.add(interventionDto);
		}
		return lstDto;
	}

	/**
	 * Va permettre de récupérer l'intervention en fonction de son id
	 * 
	 * @param Id Id concernant l'intervention
	 * @return interventionDto l'objet intervention
	 */

	@Override
	public InterventionDto getById(long id) {
		Optional<Intervention> i = interventionRepository.findById(id);
		if (i.isPresent()) {
			InterventionDto interventionDto = mapper.interventionToInterventionDto(i.get());
			FormationDto formationDto = mapper.formationToFormationDto(i.get().getFormation());

			List<PromotionDto> lstPromoDto = new ArrayList<>();
			for (Promotion promo : i.get().getPromotions()) {
				if (promo != null)
					lstPromoDto.add(mapper.promotionToPromotionDto(promo));
			}

			FormateurDto lstFormaDto = new FormateurDto();
//			for (Formateur formateur : i.get().getFormateurs()) {
//				if (formateur != null)
//					lstFormaDto.add(mapper.formateurToFormateurDto(formateur));
//			}TODO

			interventionDto.setFormateurDto(lstFormaDto);
			interventionDto.setPromotionsDto(lstPromoDto);
			interventionDto.setFormationDto(formationDto);
			interventionDto.setHeuresDisponsees();
			return interventionDto;
		}

		return null;
	}

	/**
	 * Sauvegarde ou mise à jour d'une intervention
	 * 
	 */

//	@Override
//	public InterventionDto saveOrUpdate(InterventionDto iDto) {
//		Intervention i = DtoTools.convert(iDto, Intervention.class);
//
//		if (iDto.getPromotionsId() != null) {
//			for (long id : iDto.getPromotionsId()) {
//				Optional<Promotion> promoOpt = promoRepository.findById(id);
//				if (promoOpt.isPresent()) {
//					// subtilité sur update si promo déja présent
//					i.getPromotions().add(promoOpt.get());
//					promoOpt.get().getInterventions().add(i);
//				}
//			}
//		}
//
//		i = interventionRepository.saveAndFlush(i);
//
//		filesService.createDirectory("interventions/" + i.getId());
//
//		return mapper.interventionToInterventionDto(i);
//	}
	
	
	@Override
	public InterventionDto saveOrUpdate(InterventionDto iDto) {
		Intervention intervention;
		if (iDto.getId() != 0L) {
            intervention = interventionRepository.getOne(iDto.getId());
            DtoTools.convert(iDto, Intervention.class);
        } else {
            intervention = DtoTools.convert(iDto, Intervention.class);
        }


        // Charger les entités liées avant de les assigner à l'intervention
        Formateur formateur = intervention.getFormateur();
        if (formateur != null) {
            intervention.setFormateur(formateurRepository.getOne(formateur.getId()));
        }

        List<Promotion> promotions = intervention.getPromotions();
        if (promotions != null) {
            Intervention savedIntervention = interventionRepository.save(intervention);
            Set<Promotion> existingPromotions = new HashSet<>(savedIntervention.getPromotions());
            Set<Promotion> newPromotions = new HashSet<>();
            for (Promotion promotion : promotions) {
                if (existingPromotions.contains(promotion)) {
                    existingPromotions.remove(promotion);
                } else {
                    newPromotions.add(promoRepository.getOne(promotion.getId()));
                }
            }
            savedIntervention.getPromotions().addAll(newPromotions);
            savedIntervention.getPromotions().removeAll(existingPromotions);
            intervention = interventionRepository.save(savedIntervention);
        } else {
            intervention = interventionRepository.saveAndFlush(intervention);
        }
        
        // Mettre à jour la noteInfoPersonnel si elle est présente dans l'objet DTO
        if (iDto.getNoteInfoPersonnel() != null) {
            intervention.setNoteInfoPersonnel(iDto.getNoteInfoPersonnel());
            interventionRepository.save(intervention);
        }
        
        filesService.createDirectory("interventions/" + intervention.getId());

        return mapper.interventionToInterventionDto(intervention);
	}

	/**
	 * Suppression d'une intervention
	 * 
	 * @param Id Id concernant l'intervention
	 */

	@SuppressWarnings("unused")
	@Override
	public void deleteById(long id) {
		// On regarde si un devoir est lié à une intervention
		Devoir dev = devoirRepository.findByInterventionId(id);
		// Si c'est le cas : on enleve sa liaison en rendant l'intervention à null
		if (dev != null)
			dev.setIntervention(null);
		// Meme chose : on regarde si un passage d'examen est lié une intervention
		// PassageExamen passExam = passageExamenRepository.findByInterventionId(id);
		PassageExamen passExam = null;
		// Si c'est le cas : on enleve sa liaison en rendant l'intervention à null
		if (passExam != null)
			passExam.setIntervention(null);
		// Une fois les liaisions enlevées on peux supprimer l'intervention
		interventionRepository.deleteById(id);
		filesService.deleteDirectoryWithContent("interventions/" + id);
	}

	/**
	 * Recherche d'une intervention
	 * 
	 * @param search recherche par titre formation ou promo
	 */

	@Override
	public CountDto count(String search) {
		return new CountDto(interventionRepository
				.countDistinctByFormationTitreContainingIgnoringCaseOrPromotionsNomContainingIgnoringCase(search,
						search));
	}

	/**
	 * Va permettre de récupérer la liste des etudiants de la promotion en fonction
	 * de l'id de l'intervention
	 * 
	 * @param Id Id concernant l'intervention
	 * @return List Liste des etudiants concerné
	 */

	@Override
	public List<EtudiantDto> findAllEtudiantsByPromotionInterventionsId(long id) {
		List<Etudiant> lstEtu = etudiantRepository.findAllDistinctByPromotionsInterventionsId(id);
		List<EtudiantDto> lstEtuDto = new ArrayList<>();
		for (Etudiant etu : lstEtu) {
			if (etu != null) {
				EtudiantDto eDto = mapper.etudiantToEtudiantDto(etu);
				eDto.setUtilisateurDto(mapper.utilisateurToUtilisateurDto(etu.getUtilisateur()));
				lstEtuDto.add(eDto);
			}
		}
		return lstEtuDto;
	}

	/**
	 * Va permettre de récupérer les promotions en fonction de l'id de
	 * l'intervention
	 * 
	 * @param Id Id concernant l'intervention
	 * @return List Liste des promotions concerné
	 */

	@Override
	public List<PromotionDto> findPromotionsByInterventionId(long id) {
		List<Promotion> lstProm = promoRepository.findAllByInterventionsId(id);
		List<PromotionDto> lstPromDto = new ArrayList<>();
		for (Promotion prom : lstProm) {
			if (prom != null)
				lstPromDto.add(mapper.promotionToPromotionDto(prom));
		}
		return lstPromDto;
	}

	/**
	 * Va permettre de recupérer le formateur en fonction de l'intervention
	 * 
	 * @param Id Id concernant l'intervention
	 * @return List Liste des formateurs concerné
	 */

	@Override
	public List<FormateurDto> findFormateursByInterventionsId(long id) {
		Formateur formateur = formateurRepository.findByInterventionId(id);
		List<FormateurDto> lstFormDto = new ArrayList<>();
		if (formateur != null) {
			FormateurDto fDto = mapper.formateurToFormateurDto(formateur);
			fDto.setUtilisateurDto(mapper.utilisateurToUtilisateurDto(formateur.getUtilisateur()));
			lstFormDto.add(fDto);
		}
		return lstFormDto;
	}

	/**
	 * Va permettre l'import des intervention de Dg2
	 * 
	 * @param Id       Id concernant la session
	 * @param email    Email l'utilsateur dg2
	 * @param password Mot de passe de l'utlisateur dg2
	 * @return List Liste des interventions
	 * @throws Exception
	 */

	@Override
	public int fetchDGInterventions(String email, String password) throws Exception {
		List<Promotion> promoLst = new ArrayList<>();
		promoLst = promoRepository.findAll();
		int result = 0;
		for (Promotion p : promoLst) {
			result += fetchDGInterventions(email, password, p.getIdDg2());

		}

		return result;
	}

	@Override
	public int fetchDGInterventions(String email, String password, long idPrmotionDg2) throws Exception {
		List<Intervention> interventions = new ArrayList<>();
		interventions.addAll(getInterventionDG2ByIdPromotionDG2(email, password, idPrmotionDg2));
		for (Intervention i : interventions) {
			try {
				interventionRepository.saveAndFlush(i);
				// saveOrUpdate(DtoTools.convert(i, InterventionDto.class));
			} catch (Exception e) {
				logger.warn("save and flush intervention dg2 failed", e);
			}
		}
		return interventions.size();
	}

	/**
	 * Va permettre l'import des intervention de Dg2
	 * 
	 * @author Feres BG
	 * @param Id       Id concernant la promotion
	 * @param email    Email l'utilsateur dg2
	 * @param password Mot de passe de l'utlisateur dg2
	 * @return List Liste des interventions
	 * @throws Exception
	 */

	@Async("myTaskExecutor")
	@Override
	public List<Intervention> getInterventionDG2ByIdPromotionDG2(String email, String password, long idPrmotionDg2)
			throws Exception, FetchDG2Exception, URISyntaxException {
		Optional<Promotion> promotionOpt = promoRepository.findByIdDg2(idPrmotionDg2);
		logger.info(">>>>>>>promo>>>>>" + promotionOpt.get().getIdDg2());
		logger.info("FetchDg2Intervention >>> START");
		if (!promotionOpt.isPresent()) {
			logger.error("FetchDg2Intervention>>>>>>>>ERROR failed Pas de promo");
			throw new FetchDG2Exception("Promotion introuvable veuiller mettre à jour les promotions");
		}

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		List<PromotionOrInterventionDG2Dto> fetchResJson = new ArrayList<>();
		List<Intervention> result = new ArrayList<>();

		URI url = new URI(baseUrl + "sessions/" + idPrmotionDg2 + "/children");
		HttpHeaders headers = new HttpHeaders();
		headers.add("x-auth-token", email + ":" + password);
		HttpEntity<String> httpEntity = new HttpEntity<>(headers);
		ResponseEntity<String> rep = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
		logger.info("FetchDg2Intervention >>> START /children");
		if (rep.getStatusCode() == HttpStatus.OK) {
			String json = rep.getBody();

			try {
				fetchResJson = objectMapper.readValue(json, new TypeReference<List<PromotionOrInterventionDG2Dto>>() {
				});
			} catch (Exception e) {
				logger.warn("json intervention dg2 failed", e);
			}
			DtoTools dtoTools = new DtoTools();
			if (!fetchResJson.isEmpty()) {

//				int countFormateurs = findFormateur(fetchResJson);
//				logger.info("Nombre de formateurs ajoutés : " + countFormateurs);

				findFormateur(fetchResJson);
				logger.info("FetchDg2Intervention >>> START /for");
				for (PromotionOrInterventionDG2Dto iDtoDG2 : fetchResJson) {

					Intervention interventionImported = dtoTools.promotionOrInterventionDG2DtoToIntervention(iDtoDG2);

					Optional<Intervention> intervInDb = interventionRepository.findByIdDg2(iDtoDG2.getId());

					if (intervInDb.isPresent()) {
						if (interventionImported.equals(intervInDb.get())) {
							continue;
						}
						Optional<Promotion> promotion = promoRepository.findByIdDg2(idPrmotionDg2);
						if (promotion.isPresent() && interventionImported.getPromotions()!=null) {
								
								if( !interventionImported.getPromotions().contains(promotion.get())) {
							// Ajout de la promotion à la liste des promotions de l'intervention importée
							interventionImported.getPromotions().add(promotion.get());
								}
								} else {
							logger.warn("Promotion not found with idDg2 : " + idPrmotionDg2);
							continue;
						}
						// Récupération de la liste des promotions de l'intervention en base
						List<Promotion> promotionsInDb = intervInDb.get().getPromotions();
						if (promotionsInDb != null && !promotionsInDb.isEmpty()) {
							// Ajout de la liste des promotions de l'intervention en base à la liste des
							// promotions de l'intervention importée
							interventionImported.getPromotions().addAll(promotionsInDb);
						}
					} else {
						// Récupération de la promotion correspondante à l'ID en paramètre
						Optional<Promotion> promotion = promoRepository.findByIdDg2(idPrmotionDg2);
						if (interventionImported != null && promotion != null && promotion.isPresent()) {
							if (interventionImported.getPromotions() == null) {
								interventionImported.setPromotions(new ArrayList<>());
							}
							interventionImported.getPromotions().add(promotion.get());
						} else {
							logger.warn("Promotion not found with idDg2 : " + idPrmotionDg2);
							continue;
						}
					}

					Optional<Formation> formation = formationRepository.findByIdDg2(iDtoDG2.getCourseId());
					if (!formation.isPresent()) {
						logger.warn("Formation not found with idDg2 : " + iDtoDG2.getCourseId());
						continue;
					}
					interventionImported.setFormation(formation.get());

					Optional<Formateur> formateur = formateurRepository.findByIdDg2(iDtoDG2.getTrainerPersonId());
					logger.info("ID DG2 du formateur  : " + formateur);

					if (!formateur.isPresent()) {
						logger.warn("Formateur not found with idDg2 : " + iDtoDG2.getTrainerPersonId());
						continue;
					}

					interventionImported.setFormateur(formateur.get());

					interventionRepository.saveAndFlush(interventionImported);

					// utilisateurRepository.saveAndFlush(formateur.get().getUtilisateur());

					logger.info("interventionImported >>> ");
					result.add(interventionImported);
				}

			}
			logger.info("FetchDg2Intervention>>>>>>END");
		} else {
			logger.error("FetchDg2Intervention>>>>>>>>ERROR End failed");
			throw new FetchDG2Exception("ResponseEntity from the webservice WDG2 not correct");
		}
		return result;
	}

	public int findFormateur(List<PromotionOrInterventionDG2Dto> iDtoDG2) {

		int count = 0;

		for (PromotionOrInterventionDG2Dto i : iDtoDG2) {

			Optional<Utilisateur> userInDb = utilisateurRepository.findByIdDg2(i.getTrainerPersonId());
			logger.info("ID DG2 user  : " + userInDb);

			if (!userInDb.isPresent()) {
				continue;
			}

			Optional<Formateur> formateurInDB = formateurRepository.findByUtilisateurId(userInDb.get().getId());
			if (formateurInDB.isPresent()) {
				continue;

			}
			Formateur formateur = new Formateur();
			
			UtilisateurRole formateurRole = utilisateurRoleRepository.findByIntituleContaining("FORMATEUR");
			
			List<Utilisateur> utilisateurs = new ArrayList<>();
			
			utilisateurs.add(userInDb.get());
			
			if (formateurRole.getUtilisateurs() != null) {
				utilisateurs.addAll(formateurRole.getUtilisateurs());
			}
			
			formateurRole.setUtilisateurs(utilisateurs);
			if (userInDb.get().getRoles() != null) {
				if (!userInDb.get().getRoles().contains(formateurRole)) {
					userInDb.get().getRoles().add(formateurRole);
				}
			} else {
				List<UtilisateurRole> roles = new ArrayList<>();
				roles.add(formateurRole);
				userInDb.get().setRoles(roles);
			}
			formateur.setUtilisateur(userInDb.get());
			formateur = formateurRepository.saveAndFlush(formateur);
			userInDb.get().setFormateur(formateur);
			utilisateurRepository.saveAndFlush(userInDb.get());

			count++;

		}
		return count;

	}

	@Override
	public List<InterventionDto> findInterventionByPromotionId(long id, int page, int size, String search) {
		List<Intervention> result = interventionRepository.findInterventionByPromotionId(id, PageRequest.of(page, size), search).get().collect(Collectors.toList());
		List<InterventionDto> res = new ArrayList<>();
		if (!result.isEmpty()) {
			for (Intervention i: result) {
				InterventionDto interventionDto = mapper.interventionToInterventionDto(i);
				res.add(interventionDto);
			}
		}
		return res;
	}

	@Override
	public CountDto countInterventionByPromotionId(long id, String search) {
		return new CountDto(interventionRepository.countInterventionByPromotionId(id, search));
	}
	
	@Override
	public List<InterventionDto> findAllByFormateurId(long formateurId) {
		List<InterventionDto> interventionDtos = new ArrayList<>();
		List<Intervention> lst = interventionRepository.findAllByFormateurId(formateurId);
		lst.forEach(i -> {
			InterventionDto interventionDto = mapper.interventionToInterventionDto(i);
			interventionDto.setHeuresDisponsees();
			interventionDtos.add(interventionDto);
		});
		
		return interventionDtos;
	}
	
	@Override
	public void deleteLstIntervention(List<InterventionDto> interventionDtos) {
		List<Intervention> interventions = new ArrayList<>();
		interventionDtos.forEach(interventionDto -> {
			Intervention intervention = DtoTools.convert(interventionDto, Intervention.class);
			interventions.add(intervention);
		});
		interventionRepository.deleteInBatch(interventions);
	}
}
