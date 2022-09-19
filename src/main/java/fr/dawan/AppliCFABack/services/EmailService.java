package fr.dawan.AppliCFABack.services;

import javax.mail.MessagingException;

import fr.dawan.AppliCFABack.dto.UtilisateurDto;
import fr.dawan.AppliCFABack.entities.Conge;
import fr.dawan.AppliCFABack.tools.EmailResetPasswordException;

public interface EmailService {
	
	void alertDemandeCongetoReferent(Conge conge);
	
	void newPassword(String email, String password);

	void sendMailForResetPassword(UtilisateurDto uDto) throws EmailResetPasswordException, MessagingException;
}
