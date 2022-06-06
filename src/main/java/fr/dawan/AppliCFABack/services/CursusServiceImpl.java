package fr.dawan.AppliCFABack.services;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.CursusDG2Dto;
import fr.dawan.AppliCFABack.dto.CursusDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.FormationDto;
import fr.dawan.AppliCFABack.dto.PromotionDto;
import fr.dawan.AppliCFABack.entities.Cursus;
import fr.dawan.AppliCFABack.entities.Formation;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.CursusRepository;

@Transactional
@Service
public class CursusServiceImpl implements CursusService {

	@Autowired
	CursusRepository cursusRepo;
	
	@Autowired
	PromotionService promoService;

	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();
	
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Récupération de la liste des cursus
	 * 
	 * @return lstDto	Liste des objets cursus
	 */
	
	@Override
	public List<CursusDto> getAll() {
		List<Cursus> lst = cursusRepo.findAll();
		List<CursusDto> lstDto = new ArrayList<CursusDto>();
		for (Cursus c : lst) {
			lstDto.add(mapper.CursusToCursusDto(c));
		}
		return lstDto;
	}

	/**
	 * Va permettre de récupérer tous les cursus avec pagination
	 * recherche par titre ou formation
	 * 
	 * @param page	numero de la page
	 * @param size	éléments sur la page
	 * @param search éléménts du cursus
	 * @return LstDto Liste des objets cursus
	 */
	
	@Override
	public List<CursusDto> getAllByPage(int page, int size, String search) {
		List<Cursus> lst = cursusRepo
				.findDistinctByTitreContainingIgnoringCaseOrFormationsTitreContainingIgnoringCase(search, search,
						PageRequest.of(page, size))
				.get().collect(Collectors.toList());

		// conversion vers Dto
		List<CursusDto> lstDto = new ArrayList<CursusDto>();
		for (Cursus c : lst) {
			CursusDto cDto = mapper.CursusToCursusDto(c);
			List<Formation> lstForm = c.getFormations();
			List<FormationDto> lstFormDto = new ArrayList<FormationDto>();
			for (Formation form : lstForm) {
				if (form != null)
					lstFormDto.add(mapper.FormationToFormationDto(form));
			}
			cDto.setFormationsDto(lstFormDto);
			lstDto.add(cDto);
		}
		return lstDto;
	}

	/**
	 * Recherche d'un cursus
	 * 
	 * @param search recherche par titre ou titre d'une formation
	 */
	
	@Override
	public CountDto count(String search) {
		return new CountDto(
				cursusRepo.countDistinctByTitreContainingIgnoringCaseOrFormationsTitreContainingIgnoringCase(search, search));
	}

	/**
	 * Sauvegarde ou mise à jour d'un cursus
	 * 
	 */
	
	@Override
	public CursusDto saveOrUpdate(CursusDto cDto) {
		Cursus c = DtoTools.convert(cDto, Cursus.class);
		cursusRepo.saveAndFlush(c);
		return mapper.CursusToCursusDto(c);
	}

	/**
	 * Suppression d'un cursus
	 * 
	 * @param id	Id concernant le cursus
	 */
	
	@Override
	public void deleteById(long id) {
		cursusRepo.deleteById(id);

	}

	/**
	 * Récupération des cursus en fonction de l'id
	 * 
	 */
	
	@Override
	public CursusDto getById(long id) {
		Optional<Cursus> c = cursusRepo.findById(id);
		if (c.isPresent()) {

			CursusDto cDto = mapper.CursusToCursusDto(c.get());
			List<FormationDto> lst = new ArrayList<FormationDto>();
			for (Formation f : c.get().getFormations()) {
				lst.add(mapper.FormationToFormationDto(f));
			}
			cDto.setFormationsDto(lst);
			return cDto;
		}
		return null;

	}

	/**
	 * Récupération des cursus en fonction de l'id de la promotion
	 * 
	 * @param id	id de la promotion
	 * @return cDto	objet cursus
	 */
	@Override
	public CursusDto getByIdPromotion(long id) {
		// TODO Auto-generated method stub
		PromotionDto pDto = promoService.getById(id);
		CursusDto cDto = getById(pDto.getCursusDto().getId());
		return cDto;
	}

	/**
	 * Récupération des promo en fonction de l'id du cursus
	 * 
	 * @param id Id du cursus
	 */
	//recuperation des promo par id cursus
	@Override
	public List<PromotionDto> getPromotionsById(long id) {
		return promoService.getAllByCursusId(id);
	}

	/**
	 * Va récupérer tous les cursus de DG2
	 * 
	 * @param email Email l'utilisateur dg2
	 * @param password   Mot de passe de l'utlisateur dg2
	 * 
	 * @exception Exception retourne une exception,
	 * si erreur dans la récupération des cursus
	 */
	
	//import des cursus DG2
	@Override
	public void fetchDG2Cursus(String email, String password) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		List<CursusDG2Dto> fResJson = new ArrayList<>();
		
		//url dg2 qui concerne la recupération des cursus
		URI url = new URI("https://dawan.org/api2/cfa/trainings");
		
		//recupérartion des headers / email / password dg2
		HttpHeaders headers = new HttpHeaders();
		headers.add("x-auth-token", email + ":" + password);

		HttpEntity<String> httpEntity = new HttpEntity<>(headers);

		ResponseEntity<String> repWs = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
		
		if (repWs.getStatusCode() == HttpStatus.OK) {
			String json = repWs.getBody();
			
			try {
				//recuperation des values en json et lecture
				fResJson = objectMapper.readValue(json, new TypeReference<List<CursusDG2Dto>>() { 
				});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (CursusDG2Dto cDG2 : fResJson) {
				Cursus cursusImport = mapper.cursusDG2DtoToCursus(cDG2);
				Optional<Cursus> optCursus = cursusRepo.findByIdDg2(cursusImport.getIdDg2());
				if (optCursus.isPresent()) {
					if (optCursus.get().equals(cursusImport))
						continue;
					else if (!optCursus.get().equals(cursusImport)) {
						cursusImport.setTitre(optCursus.get().getTitre());
						cursusImport.setId(optCursus.get().getId());
					}
					try {
						cursusRepo.saveAndFlush(cursusImport);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {
						cursusRepo.saveAndFlush(cursusImport);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} else {
			throw new Exception("ResponseEntity from the webservice WDG2 not correct");
		}
		
	}


}
