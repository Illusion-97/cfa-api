package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.PromotionDto;
import fr.dawan.AppliCFABack.dto.UtilisateurDto;
import fr.dawan.AppliCFABack.entities.Conge;
import fr.dawan.AppliCFABack.entities.Formateur;
import fr.dawan.AppliCFABack.entities.Utilisateur;
import fr.dawan.AppliCFABack.interceptors.TokenSaver;
import fr.dawan.AppliCFABack.repositories.CongeRepository;
import fr.dawan.AppliCFABack.repositories.FormateurRepository;
import fr.dawan.AppliCFABack.repositories.UtilisateurRepository;
import fr.dawan.AppliCFABack.tools.EmailResetPasswordException;
import fr.dawan.AppliCFABack.tools.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class EmailServiceImpl implements EmailService {

	@Autowired
	JavaMailSender javaMailSender;

	@Autowired
	EtudiantService etudiantService;

	@Autowired
	CongeRepository congeRepository;
	@Autowired
	FormateurRepository formateurRepository;
	@Autowired
	UtilisateurRepository userRepository;
	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	/**
	 * Envoi de mail au referent pour la demande de congé
	 * 
	 * @param c objet Conge
	 * @return SimpleMailMessage a être envoyé
	 */

	@Override
	public void alertDemandeCongetoReferent(Conge c) {
		// On détermine le référent de l'étudiant concerné
		Conge congeDb = congeRepository.getOne(c.getId());
		List<PromotionDto> promotions = etudiantService
				.getPromotionsByIdEtudiant(congeDb.getUtilisateur().getEtudiant().getId());
		UtilisateurDto referentPedagogique = null;
		PromotionDto pSelected = null;
		for (PromotionDto p : promotions) {
			// On sélectionne la promotion courante en fonction de la date,
			// Cas particulier d'un changement de promotion en cours de formation
			// => les deux promotions sont courantes => on choisit la dernière promotion de
			// la liste (à priori ranger par id)
			// A voir pour trouver une meilleur solution ?
			if ((p.getDateDebut().isBefore(LocalDate.now()) && p.getDateFin().isAfter(LocalDate.now()))
					|| p.getDateDebut().isEqual(LocalDate.now()) || p.getDateFin().isEqual(LocalDate.now())) {
				referentPedagogique = p.getReferentPedagogiqueDto();
				pSelected = p;
			}
		}

		SimpleMailMessage msg = new SimpleMailMessage();

//		msg.setTo(referentPedagogique.getLogin());
		msg.setTo("cfaDawan@gmail.com"); // Pour tests

		msg.setSubject("noreply - Demande de Congés");

		StringBuilder str = new StringBuilder("L'étudiant ");
		str.append(c.getUtilisateur().getPrenom());
		str.append(" ");
		str.append(c.getUtilisateur().getNom());
		str.append(" de la promotion : ");
		str.append(pSelected.getNom());
		str.append(" a fais une demande de congé du ");
		str.append(c.getDateDebut());
		str.append(" au ");
		str.append(c.getDateFin());
		str.append(".\n");
		str.append("Veuillez mettre à jours le status de cette demande sur le Portail CFA Dawan.");

		msg.setText(str.toString());

		javaMailSender.send(msg);

	}

	@Override
	public void newPassword(String email, String password) {
		// Envoie un mail avec son mot de passe au mail de la personne inscrite
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(email);
		msg.setSubject("noreply - Mot de passe");
		msg.setText("Voici votre mot de passe temporaire pour le portail cfa: " + password);
		javaMailSender.send(msg);
	}

	/**
	 * Envoie de mail avec lien pour modifier le mot de passe
	 * 
	 * @param uDto Objet Utilisateur
	 * @throws MessagingException 
	 * @exception Exception Returns une exception si le message n'est pas valide
	 */

	@Override
	public void sendMailForResetPassword(UtilisateurDto uDto) throws EmailResetPasswordException, MessagingException {
		Map<String, Object> claims = new HashMap<>();
		claims.put("name", uDto.getNom());

		String token = jwtTokenUtil.doGenerateToken(claims, uDto.getLogin());
		TokenSaver.getTokensbyemail().put(uDto.getLogin(), token);

		String resetLink = "http://localhost:8081//#/reset-password?token=" + token;
		String body = "<HTML><body> <a href=\"" + resetLink + "\">Réinitialiser mon mot de passe</a></body></HTML>";

		MimeMessage msg = emailSender.createMimeMessage();
		msg.addRecipients(Message.RecipientType.TO, uDto.getLogin());
		msg.setSubject("Réinitialisation du mot de passe du CFA Dawan");
		msg.setText("Bonjour " + uDto.getNom()
				+ ". <br /><br />Ce message vous a été envoyé car vous avez oublié votre mot de passe sur l'application"
				+ " CFA Dawan. <br />Pour réinitialiser votre mot de passe, veuillez cliquer sur ce lien : " + body,
				"UTF-8", "html");

		emailSender.send(msg);

	}

	@Override
	public void sendMailUser1ToUser2(String from, String to, String header, String msg){

		try {
			MimeMessage message = emailSender.createMimeMessage();
			message.setFrom(from);
			message.setRecipients(Message.RecipientType.TO, to);
			message.setSubject(header);
			message.setText(msg);

			emailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
