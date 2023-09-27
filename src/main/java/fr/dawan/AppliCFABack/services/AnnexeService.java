package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.AnnexeDto;

public interface AnnexeService extends GenericService<AnnexeDto>{
 
	boolean deleteAnnexe(Long annexeId);
}
