package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.UtilisateurDto;
import fr.dawan.AppliCFABack.entities.Conge;
import fr.dawan.AppliCFABack.entities.Soutenance;
import fr.dawan.AppliCFABack.tools.EmailResetPasswordException;

import javax.mail.MessagingException;
import java.util.Optional;

public interface EmailService {

	void alertDemandeCongetoReferent(Conge conge);

	void newPassword(String email, String password);

	void sendMailForResetPassword(UtilisateurDto uDto) throws EmailResetPasswordException, MessagingException;

	void sendMailUser1ToUser2(String from, String to, String header, String msg);

	<T> void sendMailUser1ToUser2(String from, String to, String header, String msg,
							  String fileName, T tDto);

	void sendMailSmtpUser(long idUtilisateur, String header, String msg, Optional<String> path, Optional<String> fileName);

	// void scheduleMailSender(long user);
}
