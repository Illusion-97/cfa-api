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
import fr.dawan.AppliCFABack.dto.ProjetDto;
import fr.dawan.AppliCFABack.entities.GroupeEtudiant;
import fr.dawan.AppliCFABack.entities.Projet;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.ProjetRepository;

@Service
@Transactional
public class ProjetServiceImpl implements ProjetService {

	@Autowired
	private ProjetRepository projetRepository;
	
	@Autowired
	FilesService filesService;

	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();

	@Override
	public List<ProjetDto> getAllProjet() {
		List<Projet> lst = projetRepository.findAll();

		List<ProjetDto> lstDto = new ArrayList<ProjetDto>();
		for (Projet p : lst) {
			ProjetDto pDto = mapper.ProjetToProjetDto(p);
			pDto.setGroupe(mapper.GroupeEtudiantToGroupEtudiantDto(p.getGroupe()));
			lstDto.add(pDto);
		}
		return lstDto;
	}

	@Override
	public List<ProjetDto> getAllByPage(int page, int size, String search) {
		List<Projet> lst = projetRepository.findAllByNomContainingIgnoringCaseOrDescriptionContainingIgnoringCaseOrGroupeNomContainingIgnoringCase(search,search, search, PageRequest.of(page, size)).get().collect(Collectors.toList());

		// conversion vers Dto
		List<ProjetDto> lstDto = new ArrayList<ProjetDto>();
		for (Projet p : lst) {
			ProjetDto pDto = mapper.ProjetToProjetDto(p);
			pDto.setGroupe(mapper.GroupeEtudiantToGroupEtudiantDto(p.getGroupe()));
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
		if (p.isPresent()) {

			ProjetDto pDto = mapper.ProjetToProjetDto(p.get());
			pDto.setGroupe(mapper.GroupeEtudiantToGroupEtudiantDto(p.get().getGroupe()));
			return pDto;
		}

		return null;
	}

	@Override
	public ProjetDto saveOrUpdate(ProjetDto pDto) {
		Projet p = DtoTools.convert(pDto, Projet.class);

		p = projetRepository.saveAndFlush(p);
		
		filesService.createDirectory("projets/" + p.getId());

		return mapper.ProjetToProjetDto(p);
	}

	@Override
	public void deleteById(long id) {
		projetRepository.deleteById(id);
		filesService.deleteDirectoryWithContent("projets/"+id);
	}

	@Override
	public List<ProjetDto> getByGroupeId(long id) {
		List<Projet> projets = projetRepository.findAllByGroupeId(id);	
		
		List<ProjetDto> result = new ArrayList<ProjetDto>();
		for(Projet p : projets) {
		ProjetDto pDto = mapper.ProjetToProjetDto(p);
		pDto.setGroupe(mapper.GroupeEtudiantToGroupEtudiantDto(p.getGroupe()));
		result.add(pDto);
		}
		
		return result;
	}	

}
