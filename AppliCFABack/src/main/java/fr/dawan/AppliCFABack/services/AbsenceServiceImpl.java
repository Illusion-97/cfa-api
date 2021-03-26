package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.AbsenceDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.NoteDto;
import fr.dawan.AppliCFABack.entities.Absence;
import fr.dawan.AppliCFABack.entities.Note;
import fr.dawan.AppliCFABack.repositories.AbsenceRepository;

@Service
@Transactional
public class AbsenceServiceImpl implements AbsenceService{

	@Autowired
	AbsenceRepository absenceRepository;
	
	@Override
	public List<AbsenceDto> getAllAbsence() {
		List<Absence> lst = absenceRepository.findAll();

		List<AbsenceDto> lstDto = new ArrayList<AbsenceDto>();
		for (Absence a : lst) {
			lstDto.add(DtoTools.convert(a, AbsenceDto.class));
		}
		return lstDto;
	}

	@Override
	public List<AbsenceDto> getAllAbsence(int page, int size) {
		List<Absence> lst = absenceRepository.findAll(PageRequest.of(page, size)).get().collect(Collectors.toList());

		// conversion vers Dto
		List<AbsenceDto> lstDto = new ArrayList<AbsenceDto>();
		for (Absence a : lst) {
			lstDto.add(DtoTools.convert(a, AbsenceDto.class));
		}
		return lstDto;
	}

	@Override
	public AbsenceDto getById(long id) {
		Optional<Absence> a = absenceRepository.findById(id);
		if (a.isPresent())
			return DtoTools.convert(a.get(), AbsenceDto.class);

		return null;
	}

	@Override
	public AbsenceDto saveOrUpdate(AbsenceDto aDto) {
		Absence a = DtoTools.convert(aDto, Absence.class);

		a = absenceRepository.saveAndFlush(a);

		return DtoTools.convert(a, AbsenceDto.class);
	}

	@Override
	public void deleteById(long id) {
		absenceRepository.deleteById(id);
		
	}

}
