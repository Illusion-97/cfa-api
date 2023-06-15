package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.AnnexeDto;
import fr.dawan.AppliCFABack.dto.DossierProfessionnelDto;

public interface AnnexeService extends GenericService<AnnexeDto>{
 
	boolean deleteAnnexe(Long annexeId);
}
