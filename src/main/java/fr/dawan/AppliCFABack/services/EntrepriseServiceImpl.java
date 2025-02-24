package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.EntrepriseDto;
import fr.dawan.AppliCFABack.entities.Entreprise;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.EntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class EntrepriseServiceImpl implements EntrepriseService {

	@Autowired
	EntrepriseRepository entrepriseRepository;

	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();

	/**
	 * Récupération de la liste des entreprises
	 * 
	 * @return lstDto	Liste des objets entreprises
	 */

	@Override
	public List<EntrepriseDto> getAllEntreprise() {
		List<Entreprise> lst = entrepriseRepository.findAll();

		List<EntrepriseDto> lstDto = new ArrayList<>();
		for (Entreprise e : lst) {
			EntrepriseDto eDto = mapper.entrepriseToEntrepriseDto(e);
			eDto.setAdresseSiegeId(e.getAdresseSiege().getId());
			eDto.setAdresseSiegeDto(mapper.adresseToAdresseDto(e.getAdresseSiege()));
			lstDto.add(eDto);
		}
		return lstDto;
	}

	/**
	 * Va permettre de récupérer toutes les entreprises avec pagination
	 * 
	 * @param page	numero de la page
	 * @param size	éléments sur la page
	 * @return LstDto Liste des objets entreprise
	 */

	@Override
	public List<EntrepriseDto> getAllEntreprise(int page, int size) {
		List<Entreprise> lst = entrepriseRepository.findAll(PageRequest.of(page, size)).get()
				.collect(Collectors.toList());

		// conversion vers Dto
		List<EntrepriseDto> lstDto = new ArrayList<>();
		for (Entreprise e : lst) {
			EntrepriseDto eDto = mapper.entrepriseToEntrepriseDto(e);
			eDto.setAdresseSiegeId(e.getAdresseSiege().getId());
			System.out.println("--------------------------------------");
			System.out.println(e.getAdresseSiege().toString());
			System.out.println("--------------------------------------");
			eDto.setAdresseSiegeDto(mapper.adresseToAdresseDto(e.getAdresseSiege()));

			lstDto.add(eDto);
		}
		return lstDto;
	}

	/**
	 * Récupération des entreprises en fonction de l'id
	 * 
	 * @param id	id de l'entreprise
	 */

	@Override
	public EntrepriseDto getById(long id) {
		Optional<Entreprise> e = entrepriseRepository.findById(id);
		if (e.isPresent()){
			EntrepriseDto eDto = mapper.entrepriseToEntrepriseDto(e.get());
			eDto.setAdresseSiegeDto(mapper.adresseToAdresseDto(e.get().getAdresseSiege()));
			return eDto;
		}

		return null;
	}

	/**
	 * Sauvegarde ou mise à jour d'une entreprise
	 * 
	 */

	@Override
	public EntrepriseDto saveOrUpdate(EntrepriseDto eDto) {
		Entreprise e = DtoTools.convert(eDto, Entreprise.class);

		e = entrepriseRepository.saveAndFlush(e);

		return mapper.entrepriseToEntrepriseDto(e);
	}

	/**
	 * Suppression d'une entreprise
	 * 
	 * @param id	Id concernant l'entreprise
	 */

	@Override
	public void deleteById(long id) {
		entrepriseRepository.deleteById(id);

	}

	/**
	 * Recherche d'une entreprise
	 * 
	 * @param search recherche par raison sociale 
	 */

	@Override
	public CountDto count(String search) {
		return new CountDto(entrepriseRepository.countByRaisonSocialeContaining(search));
	}

	/**
	 * Va permettre de récupérer toutes les entreprises avec pagination
	 * recherche par raison sociale
	 * 
	 * @param page	numero de la page
	 * @param size	éléments sur la page
	 * @param search	éléments entreprises (raison sociale)
	 * @return res Liste des objets entreprises
	 */

	@Override
	public List<EntrepriseDto> getAllEntreprises(int page, int size, String search) {
		List<Entreprise> entreprises = entrepriseRepository.findAllByRaisonSocialeContaining(search, PageRequest.of(page, size)).get().collect(Collectors.toList());
		List<EntrepriseDto> lstDto = new ArrayList<>();
		for (Entreprise e : entreprises) {
			EntrepriseDto eDto = mapper.entrepriseToEntrepriseDto(e);
			eDto.setAdresseSiegeDto(mapper.adresseToAdresseDto(e.getAdresseSiege()));
			lstDto.add(eDto);
		}
		return lstDto;
	}


}
