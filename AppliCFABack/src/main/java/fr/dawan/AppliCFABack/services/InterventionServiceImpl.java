package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.InterventionDto;
import fr.dawan.AppliCFABack.entities.Intervention;
import fr.dawan.AppliCFABack.repositories.InterventionRepository;

@Service
@Transactional
public class InterventionServiceImpl implements InterventionService {

	@Autowired
	InterventionRepository interventionRepository;

	@Override
	public List<InterventionDto> getAllIntervention() {
		List<Intervention> lst = interventionRepository.findAll();

		List<InterventionDto> lstDto = new ArrayList<InterventionDto>();
		for (Intervention i : lst) {
			lstDto.add(DtoTools.convert(i, InterventionDto.class));
		}
		return null;
	}

	@Override
	public List<InterventionDto> getAllIntervention(int page, int size) {
		List<Intervention> lst = interventionRepository.findAll(PageRequest.of(page, size)).get()
				.collect(Collectors.toList());

		// conversion vers Dto
		List<InterventionDto> lstDto = new ArrayList<InterventionDto>();
		for (Intervention i : lst) {
			lstDto.add(DtoTools.convert(i, InterventionDto.class));
		}
		return lstDto;
	}

	@Override
	public InterventionDto getById(long id) {
		Optional<Intervention> i = interventionRepository.findById(id);
		if (i.isPresent())
			return DtoTools.convert(i.get(), InterventionDto.class);

		return null;
	}

	@Override
	public InterventionDto saveOrUpdate(InterventionDto iDto) {
		Intervention i = DtoTools.convert(iDto, Intervention.class);

		i = interventionRepository.saveAndFlush(i);

		return DtoTools.convert(i, InterventionDto.class);
	}

	@Override
	public void deleteById(long id) {
		interventionRepository.deleteById(id);

	}

}
