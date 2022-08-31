package fr.dawan.AppliCFABack.services;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
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

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.FormateurDto;
import fr.dawan.AppliCFABack.dto.FormationDto;
import fr.dawan.AppliCFABack.dto.InterventionDG2Dto;
import fr.dawan.AppliCFABack.dto.InterventionDto;
import fr.dawan.AppliCFABack.dto.PromotionDto;
import fr.dawan.AppliCFABack.dto.PromotionOrInterventionDG2Dto;
import fr.dawan.AppliCFABack.entities.CentreFormation;
import fr.dawan.AppliCFABack.entities.Cursus;
import fr.dawan.AppliCFABack.entities.Devoir;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.Formateur;
import fr.dawan.AppliCFABack.entities.Formation;
import fr.dawan.AppliCFABack.entities.Intervention;
import fr.dawan.AppliCFABack.entities.PassageExamen;
import fr.dawan.AppliCFABack.entities.Promotion;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.CentreFormationRepository;
import fr.dawan.AppliCFABack.repositories.DevoirRepository;
import fr.dawan.AppliCFABack.repositories.EtudiantRepository;
import fr.dawan.AppliCFABack.repositories.FormateurRepository;
import fr.dawan.AppliCFABack.repositories.FormationRepository;
import fr.dawan.AppliCFABack.repositories.InterventionRepository;
import fr.dawan.AppliCFABack.repositories.PassageExamenRepository;
import fr.dawan.AppliCFABack.repositories.PromotionRepository;

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
	CentreFormationRepository centreFormationRepository;


	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Récupération de toutes les interventions
	 * 
	 * @return lstDto Liste des objets interevention
	 */

	@Override
	public List<InterventionDto> getAllIntervention() {
		List<Intervention> lst = interventionRepository.findAll();

		List<InterventionDto> lstDto = new ArrayList<InterventionDto>();
		for (Intervention i : lst) {
			InterventionDto interventionDto = mapper.InterventionToInterventionDto(i);
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
		List<InterventionDto> lstDto = new ArrayList<InterventionDto>();

		for (Intervention i : lst) {
			InterventionDto interventionDto = mapper.InterventionToInterventionDto(i);
			FormationDto formationDto = mapper.FormationToFormationDto(i.getFormation());
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
	public List<InterventionDto> getAllByPage(int page, int size, String search) {
		List<Intervention> lstIn = interventionRepository
				.findAllDistinctByFormationTitreContainingIgnoringCaseOrPromotionsNomContainingIgnoringCase(search,
						search, PageRequest.of(page, size))
				.get().collect(Collectors.toList());

		List<InterventionDto> lstDto = new ArrayList<InterventionDto>();

		for (Intervention intervention : lstIn) {
			/**
			 * on recup une intervention de type Intervention que l'on convertis en
			 * InterventionDto
			 **/
			InterventionDto interventionDto = mapper.InterventionToInterventionDto(intervention);
			/**
			 * on recup une formation de type Formation que l'on convertis en FormationDto
			 **/
			FormationDto formationDto = mapper.FormationToFormationDto(intervention.getFormation());
			// Les convertion en Dto faite => on ajoute la formationDto à l'interventionDto
			interventionDto.setFormationDto(formationDto);

//			Intervention inter = intervention.getInterventionMere();
//
//			InterventionDto interventionMereDto = DtoTools.convert(inter, InterventionDto.class);
//			interventionDto.setInterventionMereDto(interventionMereDto);

			// On affiche une liste de promotions de type List<Promotion>
			List<Promotion> lstPromo = intervention.getPromotions();
			List<PromotionDto> lstPromoDto = new ArrayList<PromotionDto>();
			for (Promotion promotion : lstPromo) {
				/** On convertis List<Promotion> en List<PromotionDto> **/
				if (promotion != null)
					lstPromoDto.add(mapper.PromotionToPromotionDto(promotion));
			}

			List<FormateurDto> lstFormDto = new ArrayList<FormateurDto>();
			for (Formateur formateur : intervention.getFormateurs()) {
				if (formateur != null)
					lstFormDto.add(DtoTools.convert(formateur, FormateurDto.class));
			}

			// On ajoute la liste des formateurs a l'intervention
			interventionDto.setFormateursDto(lstFormDto);

			// On ajoute la liste de promotions a l'intervention
			interventionDto.setPromotionsDto(lstPromoDto);
			// On ajoute l'intervention a la liste d'intervention
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
			InterventionDto interventionDto = mapper.InterventionToInterventionDto(i.get());
//			// Recupere les formations par rapport à l'id de l'intervention
//			// Convertion de l'entitie Formation en FormationDto
			FormationDto formationDto = mapper.FormationToFormationDto(i.get().getFormation());

			List<PromotionDto> lstPromoDto = new ArrayList<PromotionDto>();
			for (Promotion promo : i.get().getPromotions()) {
				if (promo != null)
					lstPromoDto.add(mapper.PromotionToPromotionDto(promo));
			}

			List<FormateurDto> lstFormaDto = new ArrayList<FormateurDto>();
			for (Formateur formateur : i.get().getFormateurs()) {
				if (formateur != null)
					lstFormaDto.add(mapper.FormateurToFormateurDto(formateur));
			}

			interventionDto.setFormateursDto(lstFormaDto);
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

	@Override
	public InterventionDto saveOrUpdate(InterventionDto iDto) {
		Intervention i = DtoTools.convert(iDto, Intervention.class);

		if (iDto.getPromotionsId() !=  null) {
			for (long id : iDto.getPromotionsId()) {
				Optional<Promotion> promoOpt =  promoRepository.findById(id);
				if (promoOpt.isPresent()) {
					//subtilité sur update si promo déja présent
					i.getPromotions().add(promoOpt.get());
					promoOpt.get().getInterventions().add(i);
				}
			}
		}
		
		i = interventionRepository.saveAndFlush(i);

		filesService.createDirectory("interventions/" + i.getId());

		return mapper.InterventionToInterventionDto(i);
	}

	/**
	 * Suppression d'une intervention
	 * 
	 * @param Id Id concernant l'intervention
	 */

	@Override
	public void deleteById(long id) {
		// On regarde si un devoir est lié à une intervention
		Devoir dev = devoirRepository.findByInterventionId(id);
		// Si c'est le cas : on enleve sa liaison en rendant l'intervention à null
		if (dev != null)
			dev.setIntervention(null);
		// Meme chose : on regarde si un passage d'examen est lié une intervention
//		PassageExamen passExam = passageExamenRepository.findByInterventionId(id);
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
		List<EtudiantDto> lstEtuDto = new ArrayList<EtudiantDto>();
		for (Etudiant etu : lstEtu) {
			if (etu != null) {
				EtudiantDto eDto = mapper.EtudiantToEtudiantDto(etu);
				eDto.setUtilisateurDto(mapper.UtilisateurToUtilisateurDto(etu.getUtilisateur()));
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
		List<PromotionDto> lstPromDto = new ArrayList<PromotionDto>();
		for (Promotion prom : lstProm) {
			if (prom != null)
				lstPromDto.add(mapper.PromotionToPromotionDto(prom));
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
		List<Formateur> lstForm = formateurRepository.findByInterventionsId(id);
		List<FormateurDto> lstFormDto = new ArrayList<FormateurDto>();
		for (Formateur form : lstForm) {
			if (form != null) {
				FormateurDto fDto = mapper.FormateurToFormateurDto(form);
				fDto.setUtilisateurDto(mapper.UtilisateurToUtilisateurDto(form.getUtilisateur()));
				lstFormDto.add(fDto);
			}
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
	 */

	@Override
	public int fetchDGInterventions(String email, String password) throws Exception {
		List<Promotion> promoLst = new ArrayList<Promotion>();
		promoLst = promoRepository.findAll();
		int result = 0;
		for (Promotion p : promoLst) {
			result += fetchDGInterventions(email, password, p.getIdDg2());
		}
		
		return result;
	}

	@Override
	public int fetchDGInterventions(String email, String password, long idPrmotionDg2) throws Exception {
		List<Intervention> interventions = new ArrayList<Intervention>();
		interventions.addAll(getInerventionDG2ByIdPromotionDG2(email, password, idPrmotionDg2));
		for (Intervention i : interventions) {
			try {
				interventionRepository.saveAndFlush(i);
				//saveOrUpdate(DtoTools.convert(i, InterventionDto.class));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return interventions.size();
	}

	@Override
	public List<Intervention> getInerventionDG2ByIdPromotionDG2(String email, String password, long idPrmotionDg2)
			throws Exception {
		Optional<Promotion> promotionOpt = promoRepository.findByIdDg2(idPrmotionDg2);
		
		if (!promotionOpt.isPresent()) {
			throw new Exception("Promotion introuvable veuiller mettre à jour les promotions");
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		List<PromotionOrInterventionDG2Dto> fetchResJson = new ArrayList<>();
		List<Intervention> result = new ArrayList<Intervention>();

		URI url = new URI("https://dawan.org/api2/cfa/sessions/" + idPrmotionDg2 + "/children");
		HttpHeaders headers = new HttpHeaders();
		headers.add("x-auth-token", email + ":" + password);
		HttpEntity<String> httpEntity = new HttpEntity<>(headers);
		ResponseEntity<String> rep = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

		if (rep.getStatusCode() == HttpStatus.OK) {
			String json = rep.getBody();

			try {
				fetchResJson = objectMapper.readValue(json, new TypeReference<List<PromotionOrInterventionDG2Dto>>() {
				});
			} catch (Exception e) {
				e.printStackTrace();
			}

			for (PromotionOrInterventionDG2Dto iDtoDG2 : fetchResJson) {
				Optional<Intervention> interventionDb = interventionRepository.findByIdDg2(iDtoDG2.getId());

				DtoTools dtoTools = new DtoTools();
				Intervention interventionDG2 = new Intervention();
				try {
					interventionDG2 = dtoTools.PromotionOrInterventionDG2DtoToIntervention(iDtoDG2);
				} catch (Exception e) {
					e.printStackTrace();
				}

				Optional<Formation> formationOpt = formationRepository.findByIdDg2(iDtoDG2.getCourseId());
				if (formationOpt.isPresent()) {
					interventionDG2.setFormation(formationOpt.get());
//					throw new Exception("Formation introuvable veuiller mettre à jour les formations");
				}
				if (!interventionDb.isPresent()) {
					List<Promotion> promotions = new ArrayList<Promotion>(); 

					promotions.add(promotionOpt.get());
					interventionDG2.setPromotions(promotions);
					result.add(interventionDG2);
					// si existe en BDD -> comparer tous les champs et si différents -> faire update
				} else {
						List<Promotion> promotions = interventionDb.get().getPromotions();
						interventionDG2.setPromotions(promotions);
						if (!interventionDG2.getPromotions().contains(promotionOpt.get())) {
							interventionDG2.getPromotions().add(promotionOpt.get());
						}
					if (!interventionDb.get().equals(interventionDG2)) {
						interventionDG2.setId(interventionDb.get().getId());
						interventionDG2.setVersion(interventionDb.get().getVersion());

						result.add(interventionDG2);
					}
				}
			}
		} else {
			throw new Exception("ResponseEntity from the webservice WDG2 not correct");
		}
		return result;
	}



}
