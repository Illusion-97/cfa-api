package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.ProgrammeCoursDto;

public interface ProgrammeCoursService {

	List<ProgrammeCoursDto> getAll();

	ProgrammeCoursDto getById(long id);

}
