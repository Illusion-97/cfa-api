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

	@Override
	public List<DevoirDto> getAllDevoir() {
		List<Devoir> lst = devoirRepository.findAll();

		List<DevoirDto> lstDto = new ArrayList<DevoirDto>();
		for (Devoir d : lst) {
			lstDto.add(mapper.DevoirToDevoirDto(d));
		}
		return lstDto;
	}

	@Override
	public List<DevoirDto> getAllDevoir(int page, int size) {
		List<Devoir> lst = devoirRepository.findAll(PageRequest.of(page, size)).get().collect(Collectors.toList());

		// conversion vers Dto
		List<DevoirDto> lstDto = new ArrayList<DevoirDto>();
		for (Devoir d : lst) {
			lstDto.add(mapper.DevoirToDevoirDto(d));
		}
		return lstDto;
	}

	@Override
	public List<DevoirDto> getAllByPage(int page, int size, String search) {
		List<Devoir> lst = devoirRepository.findAllByEnonceContainingIgnoringCaseOrInterventionFormationTitreContainingIgnoringCase(search, search, PageRequest.of(page, size)).get().collect(Collectors.toList());

		// conversion vers Dto
		List<DevoirDto> lstDto = new ArrayList<DevoirDto>();
		for (Devoir d : lst) {
			DevoirDto dDto = mapper.DevoirToDevoirDto(d);
			dDto.setInterventionDto(mapper.InterventionToInterventionDto(d.getIntervention()));
			dDto.getInterventionDto().setFormationDto(mapper.FormationToFormationDto(d.getIntervention().getFormation()));
			lstDto.add(dDto);
		}
		return lstDto;
	}

	@Override
	public CountDto count(String search) {
		return new CountDto(devoirRepository.countByEnonceContainingIgnoringCaseOrInterventionFormationTitreContainingIgnoringCase(search, search));
	}

	
	@Override
	public DevoirDto getById(long id) {
		Optional<Devoir> d = devoirRepository.findById(id);
		if (d.isPresent()) {
			DevoirDto dDto = mapper.DevoirToDevoirDto(d.get());
			dDto.setInterventionDto(mapper.InterventionToInterventionDto(d.get().getIntervention()));
			dDto.getInterventionDto().setFormationDto(mapper.FormationToFormationDto(d.get().getIntervention().getFormation()));
			return dDto;
		}
			

		return null;
	}

	@Override
	public DevoirDto saveOrUpdate(DevoirDto dDto) {
		Devoir d = DtoTools.convert(dDto, Devoir.class);

		d = devoirRepository.saveAndFlush(d);

		return mapper.DevoirToDevoirDto(d);
	}

	@Override
	public void deleteById(long id) {
		Optional<Devoir> d = devoirRepository.findById(id);
		
		if (!d.isPresent())
			return;
		
		d.get().setIntervention(null);
		devoirRepository.save(d.get());		
		devoirRepository.deleteById(id);	

	}

	@Override
	public CountDto count() {
		// TODO Auto-generated method stub
		return new CountDto(devoirRepository.count());
	}

}
