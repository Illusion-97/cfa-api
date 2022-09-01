package fr.dawan.AppliCFABack.services;

import java.net.URI;
import java.util.ArrayList;
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
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.dawan.AppliCFABack.dto.CentreFormationDG2Dto;
import fr.dawan.AppliCFABack.dto.CentreFormationDto;
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.entities.Adresse;
import fr.dawan.AppliCFABack.entities.CentreFormation;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.AdresseRepository;
import fr.dawan.AppliCFABack.repositories.CentreFormationRepository;

@Service
@Transactional
public class CentreFormationServiceImpl implements CentreFormationService {

	@Autowired
	CentreFormationRepository centreFormationRepository;

	@Autowired
	AdresseRepository adresseRepository;
	
	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();
	
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Récupération de la liste des centres de formation
	 * 
	 * @return lstDto	Liste des objets centre de formation
	 */
	
	@Override
	public List<CentreFormationDto> getAllCentreFormation() {
		List<CentreFormation> lst = centreFormationRepository.findAll();

		List<CentreFormationDto> lstDto = new ArrayList<>();
		for (CentreFormation cf : lst) {
			CentreFormationDto cDto = mapper.CentreFormationToCentreFormationDto(cf);
			cDto.setEntrepriseDto(mapper.EntrepriseToEntrepriseDto(cf.getEntreprise()));
			cDto.setAdresseDto(mapper.AdresseToAdresseDto(cf.getAdresse()));
			lstDto.add(cDto);
		}
		return lstDto;
	}

	/**
	 * Va permettre de récupérer tous les centres de formations avec pagination
	 * 
	 * @param page	numero de la page
	 * @param size	éléments sur la page
	 * @return LstDto Liste des objets centre de formation
	 */
	
	@Override
	public List<CentreFormationDto> getAllCentreFormation(int page, int size) {
		List<CentreFormation> lst = centreFormationRepository.findAll(PageRequest.of(page, size)).get()
				.collect(Collectors.toList());

		// conversion vers Dto
		List<CentreFormationDto> lstDto = new ArrayList<>();
		for (CentreFormation cf : lst) {
			CentreFormationDto cDto = mapper.CentreFormationToCentreFormationDto(cf);
			cDto.setEntrepriseDto(mapper.EntrepriseToEntrepriseDto(cf.getEntreprise()));
			cDto.setAdresseDto(mapper.AdresseToAdresseDto(cf.getAdresse()));
			lstDto.add(cDto);
		}
		return lstDto;
	}

	/**
	 * Récupération des centres de formation en fonction de l'id
	 * 
	 */
	
	@Override
	public CentreFormationDto getById(long id) {
		Optional<CentreFormation> cf = centreFormationRepository.findById(id);
		if (!cf.isPresent()) return null;
		
		CentreFormationDto cDto = mapper.CentreFormationToCentreFormationDto(cf.get());
		cDto.setEntrepriseDto(mapper.EntrepriseToEntrepriseDto(cf.get().getEntreprise()));
		cDto.setAdresseDto(mapper.AdresseToAdresseDto(cf.get().getAdresse()));
		
		return cDto;
	}

	/**
	 * Sauvegarde ou mise à jour d'un centre de formation
	 * 
	 */
	
	@Override
	public CentreFormationDto saveOrUpdate(CentreFormationDto cfDto) {
		CentreFormation cf = DtoTools.convert(cfDto, CentreFormation.class);

		cf = centreFormationRepository.saveAndFlush(cf);

		return mapper.CentreFormationToCentreFormationDto(cf);
	}

	/**
	 * Suppression d'un centre de formation
	 * 
	 * @param id	Id concernant un centre de formation
	 */
	
	@Override
	public void deleteById(long id) {
		centreFormationRepository.deleteById(id);

	}

	/**
	 * Recherche d'un centre de foramtion
	 * 
	 * @param search recherche par nom
	 */
	
	@Override
	public CountDto count(String search) {
		
		return new CountDto(centreFormationRepository.countByNomContaining(search));
	}

	/**
	 * Va permettre de récupérer tous les centres de formation avec pagination
	 * 
	 * @param page	numero de la page
	 * @param size	éléments sur la page
	 * @param search éléments centres de formation
	 * @return LstDto Liste des objets centre de formation
	 */
	
	@Override
	public List<CentreFormationDto> getAllCentreFormations(int page, int size, String search) {
		List<CentreFormation> cf = centreFormationRepository.findAllByNomContaining(search, PageRequest.of(page, size)).get().collect(Collectors.toList());
		List<CentreFormationDto> res = new ArrayList<>();
		for (CentreFormation c : cf) {
			CentreFormationDto cfDto = mapper.CentreFormationToCentreFormationDto(c);
			cfDto.setEntrepriseDto(mapper.EntrepriseToEntrepriseDto(c.getEntreprise()));
			cfDto.setAdresseDto(mapper.AdresseToAdresseDto(c.getAdresse()));
			res.add(cfDto);
		}
		return res;
	}

	/**
	 * Va récupérer tous les centres de formation de DG2
	 * 
	 * @param Id	Id concernant la session
	 * @param email Email l'utilsateur dg2
	 * @param password   Mot de passe de l'utlisateur dg2
	 * 
	 * @exception Exception retourne une exception si erreur dans la récupération des centres
	 */

	@Override
	public void fetchAllDG2CentreFormation(String email, String password) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		List<CentreFormationDG2Dto> cResJson;
		
		//url dg2 qui concerne la recupération des locations
		URI url = new URI("https://dawan.org/api2/cfa/locations");
		
		//recupérartion des headers / email / password dg2
		HttpHeaders headers = new HttpHeaders();
		headers.add("x-auth-token", email + ":" + password);

		HttpEntity<String> httpEntity = new HttpEntity<>(headers);

		ResponseEntity<String> repWs = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

		if (repWs.getStatusCode() == HttpStatus.OK) {
			String json = repWs.getBody();
			//recuperation des values en json et lecture
			cResJson = objectMapper.readValue(json, new TypeReference<List<CentreFormationDG2Dto>>() {
			});
			//boucle pour récupérer toute la liste
			for (CentreFormationDG2Dto cDG2 : cResJson) {
				CentreFormation centreImport = mapper.centreFormationDG2DtoToCentreFormation(cDG2);
				Optional<CentreFormation> optCentre = centreFormationRepository.findByIdDg2(centreImport.getIdDg2());
				//Vérification et ajout de l'adresse 
				if ( cDG2.getAddress() != null && !cDG2.getAddress().trim().isEmpty()) {
					
					Adresse  adresse = new Adresse();
					adresse.setLibelle(cDG2.getAddress());
					adresse.setCodePostal(cDG2.getZipCode());
					adresse.setVille(cDG2.getCity());
					adresse.setCountryCode(cDG2.getCountry());
					if (optCentre.isPresent()) {
						if (!adresse.equals(optCentre.get().getAdresse())) {
							adresse.setId(optCentre.get().getAdresse().getId());
							adresse.setVersion(optCentre.get().getAdresse().getVersion());
							adresseRepository.saveAndFlush(adresse);
						}
					}
					else {
						centreImport.setAdresse(adresse);
					}
					
				}
				
				if (optCentre.isPresent()) {
					if (optCentre.get().equals(centreImport))
						continue;
					else if (!optCentre.get().equals(centreImport)) {
						centreImport.setId(optCentre.get().getId());
						centreImport.setVersion(optCentre.get().getVersion());
					}
					centreFormationRepository.saveAndFlush(centreImport);
				} else {
					centreFormationRepository.saveAndFlush(centreImport);
				}
			}
		} else {
			throw new Exception("ResponseEntity from the webservice WDG2 not correct");
		}
	}

}
