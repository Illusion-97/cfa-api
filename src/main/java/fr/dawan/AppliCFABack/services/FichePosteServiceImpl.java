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
import fr.dawan.AppliCFABack.dto.FichePosteDto;
import fr.dawan.AppliCFABack.entities.FichePoste;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.FichePosteRepository;

@Service
@Transactional
public class FichePosteServiceImpl implements FichePosteService{
	
	@Autowired
	FichePosteRepository fichePosteRepository;
	
	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();

	/**
	 * Récupération de la liste des fiches de postes
	 * 
	 * @return lstDto	Liste des objets fiches de postes
	 */
	
	@Override
	public List<FichePosteDto> getAllFichePoste() {
		List<FichePoste> lst = fichePosteRepository.findAll();
		
		List<FichePosteDto> lstDto = new ArrayList<FichePosteDto>();
		for (FichePoste n : lst) {
			FichePosteDto fDto =mapper.FichePosteToFichePosteDto(n);
			fDto.setEtudiantDto(mapper.EtudiantToEtudiantDto(n.getEtudiant()));
			lstDto.add(fDto);
		}
		return lstDto;
	}

	/**
	 * Va permettre de récupérer toutes les fiches de poste avec pagination
	 * et recherche
	 * 
	 * @param page	numero de la page
	 * @param size	éléments sur la page
	 * @param search	éléments fiche poste 
	 * @return lstDto Liste des objets fiches poste
	 */
	
	@Override
	public List<FichePosteDto> getAllByPage(int page, int size, String search) {
		List<FichePoste> lst = fichePosteRepository.findAllByIntituleContainingIgnoringCase(search,PageRequest.of(page, size)).get().collect(Collectors.toList());

		// conversion vers Dto
		List<FichePosteDto> lstDto = new ArrayList<FichePosteDto>();
		for (FichePoste c : lst) {
			FichePosteDto fDto =mapper.FichePosteToFichePosteDto(c);
			lstDto.add(fDto);
		}
		return lstDto;
	}

	/**
	 * Recherche d'une fiche de poste
	 * 
	 * @param search recherche par intitule
	 */
	
	@Override
	public CountDto count(String search) {
		return new CountDto(fichePosteRepository.countByIntituleContainingIgnoringCase(search));
	}
	
	/**
	 * Récupération des fiches de postes en fonction de l'id
	 * 
	 * @param id	id de la fiche poste
	 */
	
	@Override
	public FichePosteDto getById(long id) {
		Optional<FichePoste> e = fichePosteRepository.findById(id);
		if (e.isPresent()) {
			
			FichePosteDto fDto = mapper.FichePosteToFichePosteDto(e.get());
			fDto.setEtudiantDto(mapper.EtudiantToEtudiantDto(e.get().getEtudiant()));			
			return fDto;
		}			

		return null;
	}

	/**
	 * Sauvegarde ou mise à jour d'une fiche de poste
	 * 
	 */
	
	@Override
	public FichePosteDto saveOrUpdate(FichePosteDto fDto) {
		FichePoste u = DtoTools.convert(fDto, FichePoste.class);
		
		u = fichePosteRepository.saveAndFlush(u);
		
		return mapper.FichePosteToFichePosteDto(u);
	}

	/**
	 * Suppression d'une fiche de poste
	 * 
	 * @param id	Id concernant la fiche poste
	 */

	@Override
	public void deleteById(long id) {
		fichePosteRepository.deleteById(id);
		
	}

	/**
	 * Récupération des fiches de poste en fonction de l'id de l'étudiant
	 * 
	 * @param id	id de l'etudiant
	 * @return f	fiche de poste de l'étudiant
	 */
	
	@Override
	public FichePosteDto getByIdEtudiant(long id) {
		List<FichePosteDto> lst = getAllFichePoste();
		FichePosteDto f = new FichePosteDto();
		for (FichePosteDto fichePosteDto : lst) {
			if(fichePosteDto.getEtudiantDto().getId() == id) {
				f = fichePosteDto;
			}
		}
		return f;
	}

}
