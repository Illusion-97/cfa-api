package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.ProgrammeCoursDto;
import fr.dawan.AppliCFABack.entities.ProgrammeCours;
import fr.dawan.AppliCFABack.repositories.ProgrammeCoursRepository;

@Service
@Transactional
public class ProgrammeCoursServiceImpl implements ProgrammeCoursService {

	@Autowired
	ProgrammeCoursRepository pgRepo;
	@Override
	public List<ProgrammeCoursDto> getAll() {
		List<ProgrammeCours> lst = pgRepo.findAll();
		
		List<ProgrammeCoursDto> lstDto = new ArrayList<ProgrammeCoursDto>();
		for (ProgrammeCours pcours : lst) {
			lstDto.add(DtoTools.convert(pcours, ProgrammeCoursDto.class));
		}
		return lstDto;
	}

	@Override
	public ProgrammeCoursDto getById(long id) {
		Optional<ProgrammeCours> c = pgRepo.findById(id);
		if (c.isPresent())
		return DtoTools.convert(c.get(), ProgrammeCoursDto.class);
		return null;
	}

}
