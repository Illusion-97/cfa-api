package fr.dawan.AppliCFABack.services;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import fr.dawan.AppliCFABack.dto.CursusDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.FormationDG2Dto;
import fr.dawan.AppliCFABack.dto.FormationDto;
import fr.dawan.AppliCFABack.dto.InterventionDto;
import fr.dawan.AppliCFABack.entities.Cursus;
import fr.dawan.AppliCFABack.entities.Formation;
import fr.dawan.AppliCFABack.entities.Intervention;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.FormationRepository;
import fr.dawan.AppliCFABack.repositories.InterventionRepository;
import fr.dawan.AppliCFABack.tools.FetchDG2Exception;

@Service
@Transactional
public class FormationServiceImpl implements FormationService {

	@Autowired
	FormationRepository formationRepository;
	
	@Autowired
	InterventionRepository interventionRepository;
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();
	
	private static Logger logger = Logger.getGlobal();
	
//	@Autowired
//	private RestTemplate restTemplate;

	/**
	 * Récupération de la liste des formations
	 * 
	 * @return lstDto	Liste des objets formation
	 */
	
	
	@Override
	public List<FormationDto> getAllFormation() {
		List<Formation> lst = formationRepository.findAll();

		List<FormationDto> lstDto = new ArrayList<>();
		for (Formation f : lst) {
			FormationDto formationDto = mapper.formationToFormationDto(f);

			List<Cursus> lstCursus = f.getCursusLst();
			List<CursusDto> lstCursusDto = new ArrayList<>();

			for (Cursus cursus : lstCursus) {
				if (cursus != null)
					lstCursusDto.add(mapper.cursusToCursusDto(cursus));
			}

			formationDto.setCursusLstDto(lstCursusDto);
			lstDto.add(formationDto);
		}
		return lstDto;
	}

	/**
	 * Va permettre de récupérer toutes les fiches entreprises avec pagination
	 * et recherche par titre ou contenu
	 * 
	 * @param page	numero de la page
	 * @param size	éléments sur la page
	 * @param search	éléments formation (titre, contenu)
	 * @return lstDto Liste des objets formation
	 */
	@Override
	public List<FormationDto> getAllByPage(int page, int size, String search) {
		List<Formation> lst = formationRepository
				.findAllByTitreContainingIgnoringCase(search,
						PageRequest.of(page, size))
				.get().collect(Collectors.toList());

		// conversion vers Dto
		List<FormationDto> lstDto = new ArrayList<>();
		for (Formation c : lst) {
			FormationDto cDto = mapper.formationToFormationDto(c);
			List<CursusDto> cursusLstDto = new ArrayList<>();
			for (Cursus cursus : c.getCursusLst()) {
				cursusLstDto.add(mapper.cursusToCursusDto(cursus));
			}
			cDto.setCursusLstDto(cursusLstDto);
			lstDto.add(cDto);
		}
		return lstDto;
	}

	/**
	 * Recherche d'une formation
	 * 
	 * @param search recherche par titre ou contenu
	 */
	
	@Override
	public CountDto count(String search) {
		return new CountDto(
				formationRepository.countByTitreContainingIgnoringCase(search));
	}

	/**
	 * Récupération des formations en fonction de l'id
	 * 
	 * @param id	id de la formation
	 */
	
	@Override
	public FormationDto getById(long id) {
		Optional<Formation> f = formationRepository.findById(id);
		if (f.isPresent()) {
			FormationDto formationDto = mapper.formationToFormationDto(f.get());
			List<Cursus> lstCursus = f.get().getCursusLst();
			List<CursusDto> lstCursusDto = new ArrayList<>();

			for (Cursus cursus : lstCursus) {
				if (cursus != null)
					lstCursusDto.add(mapper.cursusToCursusDto(cursus));
			}

			formationDto.setCursusLstDto(lstCursusDto);
			return formationDto;
		}

		return null;
	}

	/**
	 * Sauvegarde ou mise à jour d'une formation
	 * 
	 */
	
	@Override
	public FormationDto saveOrUpdate(FormationDto fDto) {
		Formation f = DtoTools.convert(fDto, Formation.class);

		f = formationRepository.saveAndFlush(f);

		return mapper.formationToFormationDto(f);
	}

	/**
	 * Suppression d'une formation
	 * 
	 * @param id	Id concernant la formation
	 */
	
	@Override
	public void deleteById(long id) {
		List<Intervention> lstInt = interventionRepository.findAllByFormationId(id);
		for (Intervention intervention : lstInt) {
			intervention.setFormation(null);
		}
		formationRepository.deleteById(id);

	}

	/**
	 * Récupération des interventions en fonction de l'id formation
	 * 
	 * @param id	id de la formation
	 * @return lstInDto	Liste des interventions
	 */
	
	@Override
	public List<InterventionDto> findAllByFormationId(long id) {

		List<Intervention> lstInt = interventionRepository.findAllByFormationId(id);
		List<InterventionDto> lstIntDto = new ArrayList<>();
		for (Intervention itv : lstInt) {
			if (itv != null)
				lstIntDto.add(mapper.interventionToInterventionDto(itv));
		}
		return lstIntDto;
	}

	@Override
	public int fetchDG2Formations(String email, String password) throws Exception {
		List<Formation> result = new ArrayList<Formation>();
		List<FormationDG2Dto> fetchResJson = new ArrayList<>();
		int nbChangement = 0;
		
		//Récupérer la liste formation DG2
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		
		//Ma requête
		URI url = new URI("https://dawan.org/api2/cfa/trainings");
		HttpHeaders headers = new HttpHeaders();
		headers.add("x-auth-token", email+ ":" +password);
		HttpEntity<String> httpEntity = new HttpEntity<>(headers);
		ResponseEntity<String> rep = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
		
		//si statusCode OK
		if(rep.getStatusCode() == HttpStatus.OK) {
			String json = rep.getBody();
			
			try {
				fetchResJson = objectMapper.readValue(json, new TypeReference<List<FormationDG2Dto>>() {} );
			} catch (Exception e) {
				logger.log(Level.WARNING, "objectMapper failed", e);
			}
		}
		
		//Comparer formationsDG2 & formationsDB
		for (FormationDG2Dto fDtoDG2 : fetchResJson) {
			//chercher en BDD <Optional> findByIdDg2
			Optional<Formation> formationDb = formationRepository.findByIdDg2(fDtoDG2.getId());
			
			DtoTools dtoTools = new DtoTools();
			Formation formationDG2 = new Formation();
			
			try {
				formationDG2 = dtoTools.formationDG2DtoToFormation(fDtoDG2);
			} catch (Exception e) {
				logger.log(Level.WARNING, "mapper failed", e);
			}
			
			//Si !isPresent() alors ajout
			if(!formationDb.isPresent()) {
				result.add(formationDG2);
			} else {
				//Si != modif
				if(!formationDb.get().equals(formationDG2)) {
					formationDG2.setVersion(formationDb.get().getVersion());
					formationDG2.setId(formationDb.get().getVersion());
					result.add(formationDG2);
				}
			}
		}
		for(Formation f: result) {
			try {
				formationRepository.saveAndFlush(f);
				nbChangement ++;
			} catch (Exception e) {
				logger.log(Level.WARNING, "save and fush failed", e);
			}
		}
		return nbChangement;
	}
	
	/**
	 * Va récupérer toute les formations de DG2
	 * 
	 * @param email Email l'utilsateur dg2
	 * @param password   Mot de passe de l'utlisateur dg2
	 * @throws URISyntaxException 
	 * 
	 * @exception FetchDG2Exception retourne une exception,
	 * 								si erreur dans la récupération des formations
	 * 			  
	 */
	
	//import des formations DG2
	@Override
	public void fetchDG2Formations2(String email, String password) throws FetchDG2Exception, URISyntaxException {
		ObjectMapper objectMapper = new ObjectMapper();
		List<FormationDG2Dto> fResJson = new ArrayList<>();
		
		//url dg2 qui concerne la recupération des formations
		URI url = new URI("https://dawan.org/api2/cfa/trainings");

		//recupération des headers / email / password dg2
		HttpHeaders headers = new HttpHeaders();
		headers.add("x-auth-token", email + ":" + password);

		HttpEntity<String> httpEntity = new HttpEntity<>(headers);

		ResponseEntity<String> repWs = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

		if (repWs.getStatusCode() == HttpStatus.OK) {
			String json = repWs.getBody();
			
			try {
				//recuperation des values en json et lecture
				fResJson = objectMapper.readValue(json, new TypeReference<List<FormationDG2Dto>>() { 
				});
			} catch (Exception e) {
				logger.log(Level.WARNING, "objectMapper failed", e);
			}
			for (FormationDG2Dto fDG2 : fResJson) {
				Formation formationImport = mapper.formationDG2DtoToFormation(fDG2);
				Optional<Formation> optFormation = formationRepository.findByIdDg2(formationImport.getIdDg2());
				if (optFormation.isPresent()) {
					if (optFormation.get().equals(formationImport))
						continue;
					else if (!optFormation.get().equals(formationImport)) {
						formationImport.setTitre(optFormation.get().getTitre());
						formationImport.setId(optFormation.get().getId());
						formationImport.setSlug(optFormation.get().getSlug());
						formationImport.setVersion(optFormation.get().getVersion());
					}
					try {
						formationRepository.saveAndFlush(formationImport);
					} catch (Exception e) {
						logger.log(Level.WARNING, "save failed", e);
					}
				} else {
					try {
						formationRepository.saveAndFlush(formationImport);
					} catch (Exception e) {
						logger.log(Level.WARNING, "save2 failed", e);
					}
				}
			}
		} else {
			throw new FetchDG2Exception("ResponseEntity from the webservice WDG2 not correct");
		}


	}

}
