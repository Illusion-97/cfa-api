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

	@Override
	public List<PassageExamenDto> getAllPassageExamen() {
		List<PassageExamen> lst = passageExamenRepository.findAll();

		List<PassageExamenDto> lstDto = new ArrayList<PassageExamenDto>();
		for (PassageExamen pe : lst) {
			lstDto.add(mapper.PassageExamenToPassageExamenDto(pe));
		}
		return lstDto;
	}

	@Override
	public List<PassageExamenDto> getAllByPage(int page, int size, String search) {
		List<PassageExamen> lst = passageExamenRepository.findAllByExamenEnonceContainingIgnoringCaseOrInterventionFormationTitreContainingIgnoringCase(search,search, PageRequest.of(page, size)).get().collect(Collectors.toList());

		// conversion vers Dto
		List<PassageExamenDto> lstDto = new ArrayList<PassageExamenDto>();
		for (PassageExamen p : lst) {
			PassageExamenDto pDto = mapper.PassageExamenToPassageExamenDto(p);
			pDto.setExamenDto(mapper.ExamenToExamenDto(p.getExamen()));
			pDto.setInterventionDto(mapper.InterventionToInterventionDto(p.getIntervention()));
			pDto.getInterventionDto().setFormationDto(mapper.FormationToFormationDto(p.getIntervention().getFormation()));
			lstDto.add(pDto);
		}
		return lstDto;
	}

	@Override
	public CountDto count(String search) {
		return new CountDto(passageExamenRepository.countByExamenEnonceContainingIgnoringCaseOrInterventionFormationTitreContainingIgnoringCase(search, search));
	}

	@Override
	public PassageExamenDto getById(long id) {
		Optional<PassageExamen> pe = passageExamenRepository.findById(id);
		if (!pe.isPresent())
			return null;
		
		PassageExamenDto pDto =  mapper.PassageExamenToPassageExamenDto(pe.get());
		pDto.setExamenDto(mapper.ExamenToExamenDto(pe.get().getExamen()));
		pDto.setInterventionDto(mapper.InterventionToInterventionDto(pe.get().getIntervention()));
		pDto.getInterventionDto().setFormationDto(mapper.FormationToFormationDto(pe.get().getExamen().getFormation()));
		
		return pDto;
	}

	@Override
	public PassageExamenDto saveOrUpdate(PassageExamenDto peDto) {
		PassageExamen pe = DtoTools.convert(peDto, PassageExamen.class);

		pe = passageExamenRepository.saveAndFlush(pe);

		return mapper.PassageExamenToPassageExamenDto(pe);
	}

	@Override
	public void deleteById(long id) {
		passageExamenRepository.deleteById(id);

	}

	

}
