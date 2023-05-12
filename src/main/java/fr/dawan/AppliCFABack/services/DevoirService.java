package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.DevoirDto;

import java.util.List;


public interface DevoirService extends GenericService<DevoirDto> {

	List<DevoirDto> getAllDevoir();
	
	List<DevoirDto> getAllByPage(int page, int size, String string);

	List<DevoirDto> getAllByInterventionId(long id);
	
	
	



}
