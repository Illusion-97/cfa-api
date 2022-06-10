package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.DevoirDto;


public interface DevoirService extends GenericService<DevoirDto> {

	List<DevoirDto> getAllDevoir();
	
	List<DevoirDto> getAllByPage(int page, int size, String string);

	List<DevoirDto> getAllByInterventionId(long id);
	
	
	



}
