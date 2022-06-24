package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.dawan.AppliCFABack.dto.AdresseDto;
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.LocationDto;
import fr.dawan.AppliCFABack.entities.Adresse;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.AdresseRepository;

@Service
@Transactional
public class AdresseServiceImpl implements AdresseService{
	
	@Autowired
	AdresseRepository adresseRepository;

	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();

	/**
	 * Récupération de la liste des adresses
	 * 
	 * @return lstDto	Liste des objets adresses
	 */
	
	@Override
	public List<AdresseDto> getAllAdresse() {
		List<Adresse> lst = adresseRepository.findAll();
		
		List<AdresseDto> lstDto = new ArrayList<AdresseDto>();
		for (Adresse a : lst) {
			lstDto.add(mapper.AdresseToAdresseDto(a));
		}
		return lstDto;
	}

	/**
	 * Récupération des adresses en fonction de l'id
	 * 
	 */
	
	@Override
	public AdresseDto getById(long id) {
		Optional<Adresse> adresseOpt = adresseRepository.findById(id);
		if (adresseOpt.isPresent())
			return mapper.AdresseToAdresseDto(adresseOpt.get());
		return null;
	}
	
	/**
	 * Va permettre de récupérer toutes les adresses avec pagination
	 * recherche par ville ou rue
	 * 
	 * @param page	numero de la page
	 * @param size	éléments sur la page
	 * @param search éléménts de l'adresse
	 * @return LstDto Liste des objets adresse
	 */
	
	@Override
	public List<AdresseDto> getAllByPage(int page, int size, String search) {
		List<Adresse> lst = adresseRepository.findAllByRueContainingOrVilleContaining(search,search,PageRequest.of(page, size)).get().collect(Collectors.toList());

		// conversion vers Dto
		List<AdresseDto> lstDto = new ArrayList<AdresseDto>();
		for (Adresse a : lst) {
			AdresseDto aDto =mapper.AdresseToAdresseDto(a);
			lstDto.add(aDto);
		}
		return lstDto;
	}
	
	/**
	 * Sauvegarde ou mise à jour d'une adresse
	 * 
	 */

	@Override
	public AdresseDto saveOrUpdate(AdresseDto aDto) {
		Adresse a = DtoTools.convert(aDto, Adresse.class);

		a= adresseRepository.saveAndFlush(a);

		return mapper.AdresseToAdresseDto(a);
	}
	
	/**
	 * Suppression d'une adresse
	 * 
	 * @param id	Id concernant l'adresse
	 */

	@Override
	public void deleteById(long id) {
		adresseRepository.deleteById(id);
		
	}

	/**
	 * Recherche d'une adresse
	 * 
	 * @param search recherche par rue ou ville 
	 */
	
	@Override
	public CountDto count(String search) {
		
		return new CountDto(adresseRepository.countByRueContainingOrVilleContaining(search, search));
	}

	@Override
	public List<AdresseDto> getAllAdresses(int page, int size, String search) {
		List<Adresse> adresses = adresseRepository.findAllByRueContainingOrVilleContaining(search, search,PageRequest.of(page, size)).get().collect(Collectors.toList());
		List<AdresseDto> res = new ArrayList<AdresseDto>();
		for (Adresse a : adresses) {
			res.add(mapper.AdresseToAdresseDto(a));
		}
		return res;
	}
	
	//@Override
//	public int fetchAdressesFromDg2() throws Exception {
//		RestTemplate restTemplate = new RestTemplate();// objet permettant de faire des requêtes HTTP
//
//		ObjectMapper mapper = new ObjectMapper(); // objet de la librairie Jackson permettant de convertir de json>objet
//		mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
//
//		ResponseEntity<String> rep = restTemplate.getForEntity("https://dawan.org/public/location/", String.class);// req
//				
//		// GET
//		int nb = 0;
//		if (rep.getStatusCode() == HttpStatus.OK) {
//
//			LocationDto[] villes = mapper.readValue(rep.getBody(), LocationDto[].class);
//			// traitement à faire avec les localisations récupérées
//			for (LocationDto locDto : villes) {
////				VilleDto vDto = DtoTools.convert(locDto, VilleDto.class);
//				AdresseDto aDto = new AdresseDto();
//				aDto.setVille(locDto.getName());
//				aDto.setLibelle(locDto.getAddress());
//				aDto.setCodePostal(locDto.getZipCode());
//				// vérifier qu'elles n'existent pas en bdd (comparaison par rapport au slug)
//				// puis insertion s'il n'existe pas
//				Adresse a = null;
//				try {
//					//Ajouter id Dg2 une fois que Mohamed a modifier l'API
//					a = adresseRepository.findByIdDg2(22);
//
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				if (a == null) {
//					saveOrUpdate(aDto);
//					nb++;
//				}
//			}
//		}
//		return nb;
//	}

}
