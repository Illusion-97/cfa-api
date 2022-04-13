package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.SupportCoursDto;

public interface SupportCoursService extends GenericService<SupportCoursDto> {

	List<SupportCoursDto> getAll();
	
}
