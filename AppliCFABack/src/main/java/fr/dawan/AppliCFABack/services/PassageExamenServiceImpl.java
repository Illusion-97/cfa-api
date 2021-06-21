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
import fr.dawan.AppliCFABack.dto.InterventionDto;
import fr.dawan.AppliCFABack.dto.PassageExamenDto;
import fr.dawan.AppliCFABack.entities.Examen;
import fr.dawan.AppliCFABack.entities.PassageExamen;
import fr.dawan.AppliCFABack.repositories.PassageExamenRepository;

@Service
@Transactional
public class PassageExamenServiceImpl implements PassageExamenService {

	@Autowired
	PassageExamenRepository passageExamenRepository;

	@Override
	public List<PassageExamenDto> getAllPassageExamen() {
		List<PassageExamen> lst = passageExamenRepository.findAll();

		List<PassageExamenDto> lstDto = new ArrayList<PassageExamenDto>();
		for (PassageExamen pe : lst) {
			lstDto.add(DtoTools.convert(pe, PassageExamenDto.class));
		}
		return lstDto;
	}

	@Override
	public List<PassageExamenDto> getAllByPage(int page, int size, String search) {
		List<PassageExamen> lst = passageExamenRepository.findAllByExamenEnonceContainingIgnoringCaseOrInterventionFormationTitreContainingIgnoringCase(search,search, PageRequest.of(page, size)).get().collect(Collectors.toList());

		// conversion vers Dto
		List<PassageExamenDto> lstDto = new ArrayList<PassageExamenDto>();
		for (PassageExamen p : lst) {
			PassageExamenDto pDto = DtoTools.convert(p, PassageExamenDto.class);
			pDto.setExamenDto(DtoTools.convert(p.getExamen(), ExamenDto.class));
			pDto.setInterventionDto(DtoTools.convert(p.getIntervention(), InterventionDto.class));
			pDto.getInterventionDto().setFormationDto(DtoTools.convert(p.getIntervention().getFormation(), FormationDto.class));
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
		
		PassageExamenDto pDto =  DtoTools.convert(pe.get(), PassageExamenDto.class);
		pDto.setExamenDto(DtoTools.convert(pe.get().getExamen(), ExamenDto.class));
		pDto.setInterventionDto(DtoTools.convert(pe.get().getIntervention(), InterventionDto.class));
		pDto.getInterventionDto().setFormationDto(DtoTools.convert(pe.get().getIntervention().getFormation(), FormationDto.class));
		
		return pDto;
	}

	@Override
	public PassageExamenDto saveOrUpdate(PassageExamenDto peDto) {
		PassageExamen pe = DtoTools.convert(peDto, PassageExamen.class);

		pe = passageExamenRepository.saveAndFlush(pe);

		return DtoTools.convert(pe, PassageExamenDto.class);
	}

	@Override
	public void deleteById(long id) {
		passageExamenRepository.deleteById(id);

	}

	

}
