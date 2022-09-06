package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.PassageExamenDto;
import fr.dawan.AppliCFABack.entities.PassageExamen;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.PassageExamenRepository;

@Service
@Transactional
public class PassageExamenServiceImpl implements PassageExamenService {

	@Autowired
	PassageExamenRepository passageExamenRepository;

	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();

	/**
	 * Récupération de la liste des passsages exeamens
	 * 
	 * @return lstDto	Liste des objets passages examens
	 */
	
	@Override
	public List<PassageExamenDto> getAllPassageExamen() {
		List<PassageExamen> lst = passageExamenRepository.findAll();

		List<PassageExamenDto> lstDto = new ArrayList<>();
		for (PassageExamen pe : lst) {
			lstDto.add(mapper.passageExamenToPassageExamenDto(pe));
		}
		return lstDto;
	}

	/**
	 * Va permettre de récupérer tous les passages examens avec pagination
	 * et recherche
	 * 
	 * @param page	numero de la page
	 * @param size	éléments sur la page
	 * @param search	éléments passage examens
	 * @return lstDto Liste des objets passage examens
	 */
	
	// recuperation de la liste des passages d'examen avec pagination et recherche
	@Override
	public List<PassageExamenDto> getAllByPage(int page, int size, String search) {
//		List<PassageExamen> lst = passageExamenRepository.findAllByExamenEnonceContainingIgnoringCaseOrInterventionFormationTitreContainingIgnoringCase(search,search, PageRequest.of(page, size)).get().collect(Collectors.toList());
//
//		// conversion vers Dto
//		List<PassageExamenDto> lstDto = new ArrayList<PassageExamenDto>();
//		for (PassageExamen p : lst) {
//			PassageExamenDto pDto = mapper.PassageExamenToPassageExamenDto(p);
//			pDto.setExamenDto(mapper.ExamenToExamenDto(p.getExamen()));
//			pDto.setInterventionDto(mapper.InterventionToInterventionDto(p.getIntervention()));
//			pDto.getInterventionDto().setFormationDto(mapper.FormationToFormationDto(p.getIntervention().getFormation()));
//			lstDto.add(pDto);
//		}
		return null;
	}

	/**
	 * Recherche d'un passage examen
	 * 
	 * @param search recherche par contenu
	 */
	// count search
	@Override
	public CountDto count(String search) {
//		return new CountDto(passageExamenRepository.countByExamenEnonceContainingIgnoringCaseOrInterventionFormationTitreContainingIgnoringCase(search, search));
		return null;
	}

	/**
	 * Récupération des passages examens en fonction de l'id
	 * 
	 * @param id	id du passage examen
	 */
	
	@Override
	public PassageExamenDto getById(long id) {
		Optional<PassageExamen> pe = passageExamenRepository.findById(id);
		if (!pe.isPresent())
			return null;

		PassageExamenDto pDto = mapper.passageExamenToPassageExamenDto(pe.get());
		pDto.setExamenDto(mapper.examenToExamenDto(pe.get().getExamen()));
		pDto.setInterventionDto(mapper.interventionToInterventionDto(pe.get().getIntervention()));
//		pDto.getInterventionDto().setFormationDto(mapper.FormationToFormationDto(pe.get().getExamen().getFormation()));

		return pDto;
	}

	/**
	 * Sauvegarde ou mise à jour d'un passage d'examen
	 * 
	 */
	
	@Override
	public PassageExamenDto saveOrUpdate(PassageExamenDto peDto) {
		PassageExamen pe = DtoTools.convert(peDto, PassageExamen.class);

		pe = passageExamenRepository.saveAndFlush(pe);

		return mapper.passageExamenToPassageExamenDto(pe);
	}

	/**
	 * Suppression d'un passage examen
	 * 
	 * @param id	Id concernant le passage examen
	 */
	
	@Override
	public void deleteById(long id) {
		passageExamenRepository.deleteById(id);

	}

}
