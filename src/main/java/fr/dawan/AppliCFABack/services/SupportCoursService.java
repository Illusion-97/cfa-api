package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.SupportCoursDto;

import java.util.List;

public interface SupportCoursService extends GenericService<SupportCoursDto> {

	List<SupportCoursDto> getAll();
	
}
