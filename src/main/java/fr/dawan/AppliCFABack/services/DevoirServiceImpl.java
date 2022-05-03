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
import fr.dawan.AppliCFABack.dto.DevoirDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.entities.Devoir;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.DevoirRepository;

@Service
@Transactional
public class DevoirServiceImpl implements DevoirService {

	@Autowired
	private DevoirRepository devoirRepository;

	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();

	/**
	 * Récupération de la liste des devoirs
	 * 
	 * @return lstDto	Liste des objets devoir
	 */
	
	@Override
	public List<DevoirDto> getAllDevoir() {
		List<Devoir> lst = devoirRepository.findAll();

		List<DevoirDto> lstDto = new ArrayList<DevoirDto>();
		for (Devoir d : lst) {
			lstDto.add(mapper.DevoirToDevoirDto(d));
		}
		return lstDto;
	}

	/**
	 * Va permettre de récupérer toutes les adresses avec pagination
	 * recherche par consigne et titre de l'intervention
	 * 
	 * @param page	numero de la page
	 * @param size	éléments sur la page
	 * @param search éléménts du devoir
	 * @return LstDto Liste des objets devoirs
	 */
	
	@Override
	public List<DevoirDto> getAllByPage(int page, int size, String search) {
		List<Devoir> lst = devoirRepository.findAllByConsigneContainingIgnoringCaseOrInterventionFormationTitreContainingIgnoringCase(search, search, PageRequest.of(page, size)).get().collect(Collectors.toList());

		// conversion vers Dto
		List<DevoirDto> lstDto = new ArrayList<DevoirDto>();
		for (Devoir d : lst) {
			DevoirDto dDto = DtoTools.convert(d, DevoirDto.class);
		
			lstDto.add(dDto);
		}
		return lstDto;
	}

	/**
	 * Recherche d'un devoir
	 * 
	 * @param search recherche par consigne ou titre d'une intervention
	 */
	@Override
	public CountDto count(String search) {
		return new CountDto(devoirRepository.countByConsigneContainingIgnoringCaseOrInterventionFormationTitreContainingIgnoringCase(search, search));
	}

	/**
	 * Récupération des Devoirs en fonction de l'id
	 * 
	 */
	
	@Override
	public DevoirDto getById(long id) {
		Optional<Devoir> d = devoirRepository.findById(id);
		if (d.isPresent()) {
			return DtoTools.convert(d.get(),DevoirDto.class);
		}
			
		return null;
	}

	/**
	 * Sauvegarde ou mise à jour d'un devoir
	 * 
	 */
	
	@Override
	public DevoirDto saveOrUpdate(DevoirDto dDto) {
		Devoir d = DtoTools.convert(dDto, Devoir.class);

		d = devoirRepository.saveAndFlush(d);

		return mapper.DevoirToDevoirDto(d);
	}

	/**
	 * Suppression d'un devoir
	 * 
	 * @param id	Id concernant le devoir
	 */
	
	@Override
	public void delete(long id) {
		devoirRepository.deleteById(id);

	}



	



}
