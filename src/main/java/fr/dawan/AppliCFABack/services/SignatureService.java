package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.SignatureDto;
import fr.dawan.AppliCFABack.services.generic.GenericService;

public interface SignatureService extends GenericService<SignatureDto> {

	SignatureDto getByUtilisateurId(long uId);
	
}
