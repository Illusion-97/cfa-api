package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.SoutenanceDto;
import fr.dawan.AppliCFABack.dto.customdtos.EtudiantSoutenanceDto;
import fr.dawan.AppliCFABack.dto.customdtos.PromotionSoutenanceDto;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.Soutenance;
import fr.dawan.AppliCFABack.tools.ToPdf;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class SoutenanceMailService {

    private final JavaMailSender emailSender;
    private final Configuration freemarkerConfig;

    @Value("${app.storagefolder}")
    private String storageFolder;

    @Value("${backend.url}")
    private String backendUrl;

    @Value("${spring.mail.properties.mail.from}")
    private String from;

    private static final Logger logger = Logger.getGlobal();

    public SoutenanceMailService(
            @Autowired JavaMailSender emailSender,
            @Autowired Configuration freemarkerConfig
    ) {
        this.emailSender = emailSender;
        this.freemarkerConfig = freemarkerConfig;
    }



    public void sendMail(SoutenanceDto soutenance) {
        String mailContent = this.buildConvocationText(soutenance, soutenance.getEtudiant());

        // TODO finish email template
        try {
            MimeMessage mail = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setFrom(from);
            helper.setTo("npotier@dawan.fr");//TODO changer en prod : getEtudiant().getUtilisateurDto().getLogin()
            helper.setSubject("Convocation examen");
            helper.setText(mailContent, true);
            helper.addAttachment("convocation.pdf", buildConvocationPDF(soutenance));
            emailSender.send(mail);
        } catch(Exception err) {
            err.printStackTrace();
        }
    }

    private String buildConvocationText(SoutenanceDto soutenance, EtudiantSoutenanceDto student) {
    	PromotionSoutenanceDto promotionSoutenance = new PromotionSoutenanceDto();

        return "Bonjour"+ student.getUtilisateurDto().getFullName() +
        		".\nVous étes convoquer pour l'éxamen " + "Nom de l'examen : " + promotionSoutenance.getNom()  + " le " + soutenance.getExamDate().toString() +
                ".\nVous trouverez ci-joint la convocation.";
    }

    private Resource buildConvocationPDF(SoutenanceDto soutenance) throws IOException, TemplateException {
        Map<String, Object> model = new HashMap<>();
        model.put("backendUrl", backendUrl);
        model.put("tDto", soutenance);
        freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");
        Template template = freemarkerConfig.getTemplate("convocationExamen" + ".ftl");

        String htmlContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        
        String outputPdf = storageFolder + "pdfEmail/" + "convocationExamen" + ".pdf";
        try {
            ToPdf.convertHtmlToPdf(htmlContent, outputPdf);
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "convertHtmlToPdf failed", e);
        }
        return new FileSystemResource(outputPdf);
    }
}
