package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.DevoirEtudiantDto;

public interface DevoirEtudiantService extends GenericService<DevoirEtudiantDto> {

	List<DevoirEtudiantDto> getAllByEtudiantId(long id);
	
	List<DevoirEtudiantDto> getAllByDevoirId(long id);
	
	
}
