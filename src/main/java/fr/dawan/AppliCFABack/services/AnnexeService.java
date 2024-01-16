package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.AnnexeDto;
import fr.dawan.AppliCFABack.services.generic.GenericService;

public interface AnnexeService extends GenericService<AnnexeDto> {
 
	boolean deleteAnnexe(Long annexeId);
}
