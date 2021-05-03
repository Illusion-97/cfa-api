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
import fr.dawan.AppliCFABack.dto.ExamenDto;
import fr.dawan.AppliCFABack.entities.Examen;
import fr.dawan.AppliCFABack.repositories.ExamenRepository;

@Service
@Transactional
public class ExamenServiceImpl implements ExamenService {

	@Autowired
	ExamenRepository examenRepository;

	@Override
	public List<ExamenDto> getAllExamen() {
		List<Examen> lst = examenRepository.findAll();

		List<ExamenDto> lstDto = new ArrayList<ExamenDto>();
		for (Examen e : lst) {
			lstDto.add(DtoTools.convert(e, ExamenDto.class));
		}
		return lstDto;
	}

	@Override
	public List<ExamenDto> getAllExamen(int page, int size) {
		List<Examen> lst = examenRepository.findAll(PageRequest.of(page, size)).get().collect(Collectors.toList());

		// conversion vers Dto
		List<ExamenDto> lstDto = new ArrayList<ExamenDto>();
		for (Examen e : lst) {
			lstDto.add(DtoTools.convert(e, ExamenDto.class));
		}
		return lstDto;
	}

	@Override
	public ExamenDto getById(long id) {
		Optional<Examen> e = examenRepository.findById(id);
		if (e.isPresent())
			return DtoTools.convert(e.get(), ExamenDto.class);

		return null;
	}

	@Override
	public ExamenDto saveOrUpdate(ExamenDto eDto) {
		Examen e = DtoTools.convert(eDto, Examen.class);

		e = examenRepository.saveAndFlush(e);

		return DtoTools.convert(e, ExamenDto.class);
	}

	@Override
	public void deleteById(long id) {
		examenRepository.deleteById(id);

	}

}
