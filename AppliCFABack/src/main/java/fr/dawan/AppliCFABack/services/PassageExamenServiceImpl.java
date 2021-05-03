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
import fr.dawan.AppliCFABack.dto.PassageExamenDto;
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
	public List<PassageExamenDto> getAllPassageExamen(int page, int size) {
		List<PassageExamen> lst = passageExamenRepository.findAll(PageRequest.of(page, size)).get()
				.collect(Collectors.toList());

		// conversion vers Dto
		List<PassageExamenDto> lstDto = new ArrayList<PassageExamenDto>();
		for (PassageExamen pe : lst) {
			lstDto.add(DtoTools.convert(pe, PassageExamenDto.class));
		}
		return lstDto;
	}

	@Override
	public PassageExamenDto getById(long id) {
		Optional<PassageExamen> pe = passageExamenRepository.findById(id);
		if (pe.isPresent())
			return DtoTools.convert(pe.get(), PassageExamenDto.class);

		return null;
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
