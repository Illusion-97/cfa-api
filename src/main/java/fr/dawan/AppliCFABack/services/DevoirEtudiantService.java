package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.DevoirEtudiantDto;
import fr.dawan.AppliCFABack.services.generic.GenericService;

import java.util.List;

public interface DevoirEtudiantService extends GenericService<DevoirEtudiantDto> {

	List<DevoirEtudiantDto> getAllByEtudiantId(long id);
	
	List<DevoirEtudiantDto> getAllByDevoirId(long id);
	
	
}
