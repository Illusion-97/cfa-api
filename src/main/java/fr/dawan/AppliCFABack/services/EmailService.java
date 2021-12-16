package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.UtilisateurDto;
import fr.dawan.AppliCFABack.entities.Conge;

public interface EmailService {
	
	void alertDemandeCongetoReferent(Conge conge);
	
	void newPassword(String email, String password);

	void sendMailForResetPassword(UtilisateurDto uDto) throws Exception;
}
