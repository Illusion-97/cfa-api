package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.FacultatifDto;

public interface FacultatifService extends GenericService<FacultatifDto>{
	void deleteById(long facultatifId);
}
