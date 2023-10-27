package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.ProjetDto;
import fr.dawan.AppliCFABack.entities.Projet;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.ProjetRepository;
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
public class ProjetServiceImpl implements ProjetService {

	@Autowired
	private ProjetRepository projetRepository;
	
	@Autowired
	FilesService filesService;

	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();

	/**
	 * Récupération de la liste des projets
	 * 
	 * @return lstDto	Liste des objets projets
	 */
	@Override
	public List<ProjetDto> getAllProjet() {
		List<Projet> lst = projetRepository.findAll();

		List<ProjetDto> lstDto = new ArrayList<>();
		for (Projet p : lst) {
			ProjetDto pDto = mapper.projetToProjetDto(p);
			lstDto.add(pDto);
		}
		return lstDto;
	}

	/**
	 * Va permettre de récupérer tous les projets avec pagination
	 * et recherche
	 * 
	 * @param page	numero de la page
	 * @param size	éléments sur la page
	 * @param search	éléments projets (nom, description, nom groupe) 
	 * @return lstDto Liste des objets projet
	 */
	
	@Override
	public List<ProjetDto> getAllByPage(int page, int size, String search) {
		List<Projet> lst = projetRepository.findAllByNomOrGroupeProjet(search, PageRequest.of(page, size)).get().collect(Collectors.toList());

		List<ProjetDto> lstDto = new ArrayList<>();
		for (Projet p : lst) {
			ProjetDto pDto = mapper.projetToProjetDto(p);
			lstDto.add(pDto);
		}
		return lstDto;
	}

	/**
	 * Recherche d'un projet / nb
	 * 
	 * @param search recherche par nom, description, nom groupe
	 */
	
	@Override
	public CountDto count(String search) {
		return new CountDto(projetRepository.countByNomContainingIgnoringCaseOrDescriptionContainingIgnoringCaseOrGroupeNomContainingIgnoringCase(search, search, search));
	}

	/**
	 * Récupération des projets en fonction de l'id
	 * 
	 * @param id	id du projet
	 */
	
	@Override
	public ProjetDto getById(long id) {
		Optional<Projet> p = projetRepository.findById(id);
		if (p.isPresent()) {

			ProjetDto pDto = mapper.projetToProjetDto(p.get());
			return pDto;
		}

		return null;
	}

	/**
	 * Sauvegarde ou mise à jour d'un projet
	 * 
	 */
	
	@Override
	public ProjetDto saveOrUpdate(ProjetDto pDto) {
		Projet p = mapper.projetDtoToProjet(pDto);

		p = projetRepository.saveAndFlush(p);
		
		filesService.createDirectory("projets/" + p.getId());

		return mapper.projetToProjetDto(p);
	}

	/**
	 * Suppression d'un projet
	 * 
	 * @param id	Id concernant un projet
	 */
	
	@Override
	public void deleteById(long id) {
		projetRepository.deleteById(id);
		filesService.deleteDirectoryWithContent("projets/"+id);
	}

	/**
	 * Récupération des projets en fonction de l'id du groupe
	 * 
	 * @param id	id du groupe
	 * @return result	Liste de projet du groupe
	 */
	
	@Override
	public List<ProjetDto> getByGroupeId(long id) {
		List<Projet> projets = projetRepository.findAllByGroupeId(id);	
		
		List<ProjetDto> result = new ArrayList<>();
		for(Projet p : projets) {
		ProjetDto pDto = mapper.projetToProjetDto(p);
		result.add(pDto);
		}
		
		return result;
	}	

}
