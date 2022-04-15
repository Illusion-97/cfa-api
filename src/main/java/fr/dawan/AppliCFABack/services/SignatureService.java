package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.SignatureDto;

public interface SignatureService extends GenericService<SignatureDto> {

	SignatureDto getByUtilisateurId(long uId);
	
}
