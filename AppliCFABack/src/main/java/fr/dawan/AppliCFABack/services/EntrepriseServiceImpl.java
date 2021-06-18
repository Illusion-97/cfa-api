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
import fr.dawan.AppliCFABack.repositories.EntrepriseRepository;

@Service
@Transactional
public class EntrepriseServiceImpl implements EntrepriseService {

	@Autowired
	EntrepriseRepository entrepriseRepository;

	@Override
	public List<EntrepriseDto> getAllEntreprise() {
		List<Entreprise> lst = entrepriseRepository.findAll();

		List<EntrepriseDto> lstDto = new ArrayList<EntrepriseDto>();
		for (Entreprise e : lst) {
			lstDto.add(DtoTools.convert(e, EntrepriseDto.class));
		}
		return lstDto;
	}

	@Override
	public List<EntrepriseDto> getAllEntreprise(int page, int size) {
		List<Entreprise> lst = entrepriseRepository.findAll(PageRequest.of(page, size)).get()
				.collect(Collectors.toList());

		// conversion vers Dto
		List<EntrepriseDto> lstDto = new ArrayList<EntrepriseDto>();
		for (Entreprise e : lst) {
			lstDto.add(DtoTools.convert(e, EntrepriseDto.class));
		}
		return lstDto;
	}

	@Override
	public EntrepriseDto getById(long id) {
		Optional<Entreprise> e = entrepriseRepository.findById(id);
		if (e.isPresent())
			return DtoTools.convert(e.get(), EntrepriseDto.class);

		return null;
	}

	@Override
	public EntrepriseDto saveOrUpdate(EntrepriseDto eDto) {
		Entreprise e = DtoTools.convert(eDto, Entreprise.class);

		e = entrepriseRepository.saveAndFlush(e);

		return DtoTools.convert(e, EntrepriseDto.class);
	}

	@Override
	public void deleteById(long id) {
		entrepriseRepository.deleteById(id);

	}

	@Override
	public CountDto count(String search) {
		return new CountDto(entrepriseRepository.countByRaisonSocialeContaining(search));
	}

	@Override
	public List<EntrepriseDto> getAllEntreprises(int page, int size, String search) {
		List<Entreprise> entreprises = entrepriseRepository.findAllByRaisonSocialeContaining(search, PageRequest.of(page, size)).get().collect(Collectors.toList());
		List<EntrepriseDto> res = new ArrayList<EntrepriseDto>();
		for (Entreprise e : entreprises) {
			res.add(DtoTools.convert(e, EntrepriseDto.class));
		}
		return res;
	}

}
