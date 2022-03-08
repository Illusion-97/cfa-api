package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.EntrepriseDto;
import fr.dawan.AppliCFABack.entities.Entreprise;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.EntrepriseRepository;

@Service
@Transactional
public class EntrepriseServiceImpl implements EntrepriseService {

	@Autowired
	EntrepriseRepository entrepriseRepository;

	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();

	//recuperation de la liste des entreprises
	@Override
	public List<EntrepriseDto> getAllEntreprise() {
		List<Entreprise> lst = entrepriseRepository.findAll();

		List<EntrepriseDto> lstDto = new ArrayList<EntrepriseDto>();
		for (Entreprise e : lst) {
			EntrepriseDto eDto = mapper.EntrepriseToEntrepriseDto(e);
			eDto.setAdresseSiegeDto(mapper.AdresseToAdresseDto(e.getAdresseSiege()));
			lstDto.add(eDto);
		}
		return lstDto;
	}

	//recuperation des entreprises avec pagination
	@Override
	public List<EntrepriseDto> getAllEntreprise(int page, int size) {
		List<Entreprise> lst = entrepriseRepository.findAll(PageRequest.of(page, size)).get()
				.collect(Collectors.toList());

		// conversion vers Dto
		List<EntrepriseDto> lstDto = new ArrayList<EntrepriseDto>();
		for (Entreprise e : lst) {
			EntrepriseDto eDto = mapper.EntrepriseToEntrepriseDto(e);
			eDto.setAdresseSiegeDto(mapper.AdresseToAdresseDto(e.getAdresseSiege()));
			//lstDto.add(DtoTools.convert(e, EntrepriseDto.class));
			lstDto.add(eDto);
		}
		return lstDto;
	}

	//recuperation des entreprises par id
	@Override
	public EntrepriseDto getById(long id) {
		Optional<Entreprise> e = entrepriseRepository.findById(id);
		if (e.isPresent()){
			EntrepriseDto eDto = mapper.EntrepriseToEntrepriseDto(e.get());
			eDto.setAdresseSiegeDto(mapper.AdresseToAdresseDto(e.get().getAdresseSiege()));
			return eDto;
		}
		
		return null;
	}

	//methode d'ajout ou modification d'une entreprise
	@Override
	public EntrepriseDto saveOrUpdate(EntrepriseDto eDto) {
		Entreprise e = DtoTools.convert(eDto, Entreprise.class);

		e = entrepriseRepository.saveAndFlush(e);

		return mapper.EntrepriseToEntrepriseDto(e);
	}

	//methode de suppression d'une entreprise
	@Override
	public void deleteById(long id) {
		entrepriseRepository.deleteById(id);

	}

	//methode count
	@Override
	public CountDto count(String search) {
		return new CountDto(entrepriseRepository.countByRaisonSocialeContaining(search));
	}

	//recuperation de la liste des entreprises avec pagination et recherche
	@Override
	public List<EntrepriseDto> getAllEntreprises(int page, int size, String search) {
		List<Entreprise> entreprises = entrepriseRepository.findAllByRaisonSocialeContaining(search, PageRequest.of(page, size)).get().collect(Collectors.toList());
		List<EntrepriseDto> res = new ArrayList<EntrepriseDto>();
		for (Entreprise e : entreprises) {
			EntrepriseDto eDto = mapper.EntrepriseToEntrepriseDto(e);
			eDto.setAdresseSiegeDto(mapper.AdresseToAdresseDto(e.getAdresseSiege()));
			res.add(eDto);
		}
		return res;
	}
	

}
