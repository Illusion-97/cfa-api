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
import fr.dawan.AppliCFABack.dto.ExamenDto;
import fr.dawan.AppliCFABack.entities.Examen;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.ExamenRepository;

@Service
@Transactional
public class ExamenServiceImpl implements ExamenService {

	@Autowired
	ExamenRepository examenRepository;

	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();

	//recuperation de la liste des examens
	@Override
	public List<ExamenDto> getAllExamen() {
//		List<Examen> lst = examenRepository.findAll();
//
//		List<ExamenDto> lstDto = new ArrayList<ExamenDto>();
//		for (Examen e : lst) {
//			ExamenDto eDto = mapper.ExamenToExamenDto(e);
//		eDto.setCursusDto(mapper.CursusToCursusDto(e.getCursus()));
//		eDto.setFormationDto(mapper.FormationToFormationDto(e.getFormation()));
//		lstDto.add(eDto);
//		}
//		return lstDto;
		return null;
	}

	//recuperation de la liste des examens avec pagination et recherche
	@Override
	public List<ExamenDto> getAllByPage(int page, int size, String search) {
//		List<Examen> lst = examenRepository.findAllByEnonceContainingIgnoringCaseOrFormationTitreContainingIgnoringCaseOrCursusTitreContainingIgnoringCase(search,search, search, PageRequest.of(page, size)).get().collect(Collectors.toList());
//
//		// conversion vers Dto
//		List<ExamenDto> lstDto = new ArrayList<ExamenDto>();
//		for (Examen e : lst) {
//			ExamenDto eDto = mapper.ExamenToExamenDto(e);
//			eDto.setCursusDto(mapper.CursusToCursusDto(e.getCursus()));
//			eDto.setFormationDto(mapper.FormationToFormationDto(e.getFormation()));
//			lstDto.add(eDto);
//		}
//		return lstDto;
		return null;
	}

	//methode count
	@Override
	public CountDto count(String search) {
//		return new CountDto(examenRepository.countByEnonceContainingIgnoringCaseOrFormationTitreContainingIgnoringCaseOrCursusTitreContainingIgnoringCase(search, search, search));
		return null;
	}

	@Override
	public ExamenDto getById(long id) {
//		Optional<Examen> e = examenRepository.findById(id);
//		if (e.isPresent()) {
//			ExamenDto eDto = mapper.ExamenToExamenDto(e.get());
//						
//			eDto.setCursusDto(mapper.CursusToCursusDto(e.get().getCursus()));
//			eDto.setFormationDto(mapper.FormationToFormationDto(e.get().getFormation()));
//			
//			return eDto;
//		}			

		return null;
	}

	//methode d'ajout ou modification d'un examen
	@Override
	public ExamenDto saveOrUpdate(ExamenDto eDto) {
//		Examen e = DtoTools.convert(eDto, Examen.class);
//
//		e = examenRepository.saveAndFlush(e);
//
//		return mapper.ExamenToExamenDto(e);
		return null;
	}

	//methode de suppression d'un examen
	@Override
	public void deleteById(long id) {
		examenRepository.deleteById(id);

	}

	

}
