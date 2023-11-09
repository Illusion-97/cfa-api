package fr.dawan.AppliCFABack.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.PromotionDto;
import fr.dawan.AppliCFABack.dto.UtilisateurDto;
import fr.dawan.AppliCFABack.entities.Conge;
import fr.dawan.AppliCFABack.entities.Utilisateur;
import fr.dawan.AppliCFABack.interceptors.TokenSaver;
import fr.dawan.AppliCFABack.repositories.BlocEvaluationRepository;
import fr.dawan.AppliCFABack.repositories.CongeRepository;
import fr.dawan.AppliCFABack.repositories.FormateurRepository;
import fr.dawan.AppliCFABack.repositories.InterventionRepository;
import fr.dawan.AppliCFABack.repositories.UtilisateurRepository;
import fr.dawan.AppliCFABack.tools.EmailResetPasswordException;
import fr.dawan.AppliCFABack.tools.JwtTokenUtil;
import fr.dawan.AppliCFABack.tools.TimerCache;
import freemarker.template.Configuration;

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
	UtilisateurRepository userRepository;

	@Autowired
	private JavaMailSender emailSender;
	@Value("${backend.url}")
	private String backendUrl;
	@Autowired
	private Configuration freemarkerConfig;
	@Value("${app.storagefolder}")
	private String storageFolder;
	private static Logger logger = Logger.getGlobal();
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private BlocEvaluationRepository blocEvaluationRepository;
	@Autowired
	private FormateurRepository formateurRepository;
	@Autowired
	private InterventionRepository interventionRepository;

	@Autowired
	private TimerCache timerCache;
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

		String resetLink = "http://localhost:8081/#/reset-password?token=" + token;
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

	@Override
	public void sendMailSmtpUser(long idTo, String header, String msg, Optional<String> path, Optional<String> fileName) {
		Optional<Utilisateur> user = userRepository.findById(idTo);

		if (user.isPresent()) {
			try {
				MimeMessage message = emailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message, true);
				helper.setTo(user.get().getLogin());
				helper.setSubject(header);
				helper.setText(msg, true);

				// Partie pour ajouter un pdf si un "path" est renseigné
				String pdfFilePath = path.get(); // Utilisez le chemin du fichier PDF s'il est présent
				if (!pdfFilePath.isEmpty()) {
					FileSystemResource pdfFile = new FileSystemResource(pdfFilePath);
					helper.addAttachment(String.valueOf(fileName), pdfFile);
				}

				emailSender.send(message);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			ResponseEntity.status(HttpStatus.NOT_FOUND);
		}
	}
	/* TODO
	@Override
	public <T> void sendMailSmtpUser(long idTo, String header, String msg, Optional<String> path, Optional<String> fileName, T TDto) {
		Optional<Utilisateur> user = userRepository.findById(idTo);

		if (user.isPresent()) {
			try {
				MimeMessage message = emailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message, true);
				helper.setTo(user.get().getLogin());
				helper.setSubject(header);
				helper.setText(msg, true);

				// Partie pour ajouter un pdf si un "path" est renseigné
				String pdfFilePath = path.get(); // Utilisez le chemin du fichier PDF s'il est présent
				if (!pdfFilePath.isEmpty()) {
					
					Map<String, Object> model = new HashMap<>();
					model.put("backendUrl", backendUrl);
					model.put("dto", TDto);
					freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");
				//SoutenanceDto ... enlever Dto...
					Template template = freemarkerConfig.getTemplate(TDto.getClass().getName() + ".ftl");

					String htmlContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
					String outputPdf = storageFolder + "Convocation_Examen.pdf";
					try {
						ToPdf.convertHtmlToPdf(htmlContent, outputPdf);
					} catch (Exception e) {
						logger.log(Level.SEVERE, "convertHtmlToPdf failed", e);
					}
				}

				emailSender.send(message);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			ResponseEntity.status(HttpStatus.NOT_FOUND);
		}
	}
	*/

	private void cacheTimer(long idUser){
		LocalDate timer = LocalDate.now();

	}
	/**
	 * Envoi de automatique pour prévenir le formateur de remplir le livret d'évaluation des étudiants lui étant affilié
	 * avant 3
	 *
	 * @param c objet Conge
	 * @return SimpleMailMessage a être envoyé
	 */
	@Override
	public void scheduleMailSender(long idUser) {
		boolean isInCache = timerCache.startTimerForUserConnected(idUser, 5);
		if (isInCache){
			if (userRepository.isLivretFormateurReferentEmpty(idUser)){
				Optional<LocalDate> dateFinPromotion = userRepository.findDatePromotionOfFormateurByUtilisateurId(idUser);
				Period dateDiff = Period.between(LocalDate.now(), dateFinPromotion.get());
				if (dateDiff.getYears() < 2 && dateDiff.getMonths() <= 3) {
					ScheduledExecutorService threadUsesForSchedule = Executors.newScheduledThreadPool(1);
					String message = "Pensez à remplir l'évaluation : " + idUser;
					threadUsesForSchedule.schedule(() -> {
						sendMailSmtpUser(idUser, "Titre automatique", message, Optional.of(""), Optional.of(""));
					},1, TimeUnit.SECONDS);
				}
			}
		}
	}
}
