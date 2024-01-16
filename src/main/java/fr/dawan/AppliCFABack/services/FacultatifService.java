package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.FacultatifDto;
import fr.dawan.AppliCFABack.services.generic.GenericService;

public interface FacultatifService extends GenericService<FacultatifDto> {
	void deleteById(long facultatifId);
}
