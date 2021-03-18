package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.CoursDto;
import fr.dawan.AppliCFABack.entities.Cours;

public interface CoursService {

	List<CoursDto> getAll();

	CoursDto getById(long id);

}
