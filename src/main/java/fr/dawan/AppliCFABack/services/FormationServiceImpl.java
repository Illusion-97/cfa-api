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
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.FormationDG2Dto;
import fr.dawan.AppliCFABack.dto.FormationDto;
import fr.dawan.AppliCFABack.dto.InterventionDto;
import fr.dawan.AppliCFABack.entities.Cursus;
import fr.dawan.AppliCFABack.entities.Formation;
import fr.dawan.AppliCFABack.entities.Intervention;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.CursusRepository;
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
	CursusRepository cursusRepository;

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
	 * @return lstDto Liste des objets formation
	 */

	@Override
	public List<FormationDto> getAllFormation() {
		List<Formation> lst = formationRepository.findAll();

		List<FormationDto> lstDto = new ArrayList<>();
		for (Formation f : lst) {

			lstDto.add(DtoTools.convert(f, FormationDto.class));

		}
		return lstDto;
	}

	/**
	 * Va permettre de récupérer toutes les fiches entreprises avec pagination et
	 * recherche par titre ou contenu
	 * 
	 * @param page   numero de la page
	 * @param size   éléments sur la page
	 * @param search éléments formation (titre, contenu)
	 * @return lstDto Liste des objets formation
	 */
	@Override
	public List<FormationDto> getAllByPage(int page, int size, String search) {
		List<Formation> lst = formationRepository
				.findAllByTitreContainingIgnoringCase(search, PageRequest.of(page, size)).get()
				.collect(Collectors.toList());

		// conversion vers Dto

		List<FormationDto> lstDto = new ArrayList<>();
		for (Formation f : lst) {

			lstDto.add(DtoTools.convert(f, FormationDto.class));

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
		return new CountDto(formationRepository.countByTitreContainingIgnoringCase(search));
	}

	/**
	 * Récupération des formations en fonction de l'id
	 * 
	 * @param id id de la formation
	 */

	@Override
	public FormationDto getById(long id) {
		Optional<Formation> f = formationRepository.findById(id);
		if (f.isPresent())
			return DtoTools.convert(f.get(), FormationDto.class);

		return null;
	}

	/**
	 * Sauvegarde ou mise à jour d'une formation
	 * 
	 */

	@Override
	public FormationDto saveOrUpdate(FormationDto fDto) {

		Formation formation = DtoTools.convert(fDto, Formation.class);

		if (fDto.getCursusLstId() != null) {
			for (long id : fDto.getCursusLstId()) {
				Optional<Cursus> cursusOpt = cursusRepository.findById(id);
				if (cursusOpt.isPresent()) {
					formation.getCursusLst().add(cursusOpt.get());
					cursusOpt.get().getFormations().add(formation);
				}

			}
		}
		formation = formationRepository.saveAndFlush(formation);

		return DtoTools.convert(formation, FormationDto.class);
		
	}

	/**
	 * Suppression d'une formation
	 * 
	 * @param id Id concernant la formation
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
	 * @param id id de la formation
	 * @return lstInDto Liste des interventions
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

	/**
	 * Sauvegarde toute les formations à partir d'une liste de formation
	 * 
	 * @param email    Email l'utilsateur dg2
	 * @param password Mot de passe de l'utlisateur dg2
	 * @return nombre de formation sauvgardé ou mise à jour
	 * @exception Exception retourne une exception, si erreur dans la sauvgarde des
	 *                      formations
	 */
	@Override
	public int fetchDG2Formations(String email, String password) throws Exception {
		List<Long> cursusDg2Ids = cursusRepository.findAll().stream().map(Cursus::getIdDg2)
				.collect(Collectors.toList());
		int result = 0;
		for (Long idCursus : cursusDg2Ids) {
			result += fetchDG2Formations(email, password, idCursus);
		}

		return result;

	}

	/**
	 * Sauvegarde toute les formations à partir d'une liste de formation
	 * 
	 * @param email       Email l'utilsateur dg2
	 * @param password    Mot de passe de l'utlisateur dg2
	 * @param idCursusDg2 Identifiant du cursus
	 * @return nombre de formation sauvgardé ou mise à jour
	 * @throws URISyntaxException 
	 * @throws Exception 
	 */
	@Override
	public int fetchDG2Formations(String email, String password, long idCursusDg2) throws FetchDG2Exception, URISyntaxException {

		List<Formation> formations = new ArrayList<>();

		formations.addAll(getFormationDG2ByIdCursus(email, password, idCursusDg2));

		for (Formation formation : formations) {
			try {
				saveOrUpdate(DtoTools.convert(formation, FormationDto.class));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return formations.size();
	}

	/**
	 * Récupére toute les formations de DG2
	 * 
	 * @param email    Email l'utilsateur dg2
	 * @param password Mot de passe de l'utlisateur dg2
	 * @return Liste de formation à partir de DG2
	 * @throws URISyntaxException 
	 * @exception Exception retourne une exception, si erreur dans la récupération
	 *                      des formations
	 */
	@Override
	public List<Formation> getFormationDG2ByIdCursus(String email, String password, long idCursusDg2) throws FetchDG2Exception, URISyntaxException {

		Optional<Cursus> cursusDb = cursusRepository.findByIdDg2(idCursusDg2);

		if (!cursusDb.isPresent()) {
			throw new FetchDG2Exception("Cursus non présent dans la BDD veuiller mettre à jour les cursus");
		}

		List<Formation> result = new ArrayList<>();

		List<FormationDG2Dto> fetchResJson = new ArrayList<>();

		// Récupérer la liste formation DG2
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);

		// Ma requête
		URI url = new URI("https://dawan.org/api2/cfa/pro-titles/" + idCursusDg2 + "/children");

		HttpHeaders headers = new HttpHeaders();
		headers.add("x-auth-token", email + ":" + password);
		HttpEntity<String> httpEntity = new HttpEntity<>(headers);
		ResponseEntity<String> rep = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

		// si statusCode OK
		if (rep.getStatusCode() == HttpStatus.OK) {
			String json = rep.getBody();

			try {
				fetchResJson = objectMapper.readValue(json, new TypeReference<List<FormationDG2Dto>>() {
				});
			} catch (Exception e) {
				logger.log(Level.WARNING, "objectMapper failed", e);
			}
		}

		// Comparer formationsDG2 & formationsDB
		for (FormationDG2Dto fDtoDG2 : fetchResJson) {
			// chercher en BDD <Optional> findByIdDg2
			Optional<Formation> formationDb = formationRepository.findByIdDg2(fDtoDG2.getId());

			DtoTools dtoTools = new DtoTools();
			Formation formationDG2 = new Formation();

			try {
				formationDG2 = dtoTools.formationDG2DtoToFormation(fDtoDG2);
			} catch (Exception e) {
				logger.log(Level.WARNING, "mapper failed", e);
			}

			// Si !isPresent() alors ajout
			if (!formationDb.isPresent()) {

				List<Cursus> newCursusDb = new ArrayList<Cursus>();
				newCursusDb.add(cursusDb.get());
				formationDG2.setCursusLst(newCursusDb);
				result.add(formationDG2);
			} else {
				// Si != modif
				List<Cursus> cursus = formationDb.get().getCursusLst();
				formationDG2.setCursusLst(cursus);
				// Si la liste ne contient pas le cursus en cours on le rajoute
				if (!formationDG2.getCursusLst().contains(cursusDb.get())) {
					cursus.add(cursusDb.get());
					formationDG2.setCursusLst(cursus);
				}
				if (!formationDb.get().equals(formationDG2)) {
					formationDG2.setVersion(formationDb.get().getVersion());
					formationDG2.setId(formationDb.get().getId());
					result.add(formationDG2);
				}
			}
		}

		return result;

	}

}
