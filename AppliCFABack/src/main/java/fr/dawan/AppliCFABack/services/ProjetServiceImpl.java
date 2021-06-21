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
import fr.dawan.AppliCFABack.dto.CursusDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.ExamenDto;
import fr.dawan.AppliCFABack.dto.FormationDto;
import fr.dawan.AppliCFABack.dto.GroupeEtudiantDto;
import fr.dawan.AppliCFABack.dto.ProjetDto;
import fr.dawan.AppliCFABack.entities.Examen;
import fr.dawan.AppliCFABack.entities.Projet;
import fr.dawan.AppliCFABack.repositories.ProjetRepository;

@Service
@Transactional
public class ProjetServiceImpl implements ProjetService {

	@Autowired
	private ProjetRepository projetRepository;

	@Override
	public List<ProjetDto> getAllProjet() {
		List<Projet> lst = projetRepository.findAll();

		List<ProjetDto> lstDto = new ArrayList<ProjetDto>();
		for (Projet p : lst) {
			lstDto.add(DtoTools.convert(p, ProjetDto.class));
		}
		return lstDto;
	}

	@Override
	public List<ProjetDto> getAllByPage(int page, int size, String search) {
		List<Projet> lst = projetRepository.findAllByNomContainingIgnoringCaseOrDescriptionContainingIgnoringCaseOrGroupeNomContainingIgnoringCase(search,search, search, PageRequest.of(page, size)).get().collect(Collectors.toList());

		// conversion vers Dto
		List<ProjetDto> lstDto = new ArrayList<ProjetDto>();
		for (Projet p : lst) {
			ProjetDto pDto = DtoTools.convert(p, ProjetDto.class);
			pDto.setGroupe(DtoTools.convert(p.getGroupe(), GroupeEtudiantDto.class));
			lstDto.add(pDto);
		}
		return lstDto;
	}

	@Override
	public CountDto count(String search) {
		return new CountDto(projetRepository.countByNomContainingIgnoringCaseOrDescriptionContainingIgnoringCaseOrGroupeNomContainingIgnoringCase(search, search, search));
	}

	@Override
	public ProjetDto getById(long id) {
		Optional<Projet> p = projetRepository.findById(id);
		if (p.isPresent())
			return DtoTools.convert(p.get(), ProjetDto.class);

		return null;
	}

	@Override
	public ProjetDto saveOrUpdate(ProjetDto pDto) {
		Projet p = DtoTools.convert(pDto, Projet.class);

		p = projetRepository.saveAndFlush(p);

		return DtoTools.convert(p, ProjetDto.class);
	}

	@Override
	public void deleteById(long id) {
		projetRepository.deleteById(id);

	}	

}
