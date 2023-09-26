package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.AdresseDto;
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.entities.Adresse;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.AdresseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
		List<AdresseDto> lstDto = new ArrayList<>();
		for (Adresse a : lst) {
			lstDto.add(mapper.adresseToAdresseDto(a));
		}
		return lstDto;
	}

	/**
	 * Récupération des adresses en fonction de l'id
	 * 
	 */

	@Override
	public AdresseDto getById(long id) {
		return adresseRepository.findById(id)
				.map(a -> mapper.adresseToAdresseDto(a))
				.orElse(null);
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
		List<Adresse> lst = adresseRepository.findAllByLibelleContainingOrVilleContaining(search,search,PageRequest.of(page, size)).get().collect(Collectors.toList());

		// conversion vers Dto
		List<AdresseDto> lstDto = new ArrayList<>();
		for (Adresse a : lst) {
			AdresseDto aDto =mapper.adresseToAdresseDto(a);
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
		Adresse a = adresseRepository.saveAndFlush(DtoTools.convert(aDto, Adresse.class));
		return mapper.adresseToAdresseDto(a);
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

		return new CountDto(adresseRepository.countByLibelleContainingOrVilleContaining(search, search));
	}

	@Override
	public List<AdresseDto> getAllAdresses(int page, int size, String search) {
		List<Adresse> adresses = adresseRepository.findAllByLibelleContainingOrVilleContaining(search, search,PageRequest.of(page, size)).get().collect(Collectors.toList());
		List<AdresseDto> res = new ArrayList<>();
		for (Adresse a : adresses) {
			res.add(mapper.adresseToAdresseDto(a));
		}
		return res;
	}

}
