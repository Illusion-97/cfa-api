package fr.dawan.AppliCFABack.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.CreateSoutenanceDto;
import fr.dawan.AppliCFABack.dto.SoutenanceDto;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.Soutenance;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.repositories.EtudiantRepository;
import fr.dawan.AppliCFABack.repositories.SoutenanceRepository;
import fr.dawan.AppliCFABack.tools.DossierProjetException;
import fr.dawan.AppliCFABack.tools.SaveInvalidException;
import fr.dawan.AppliCFABack.tools.ToPdf;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
@Transactional
public class SoutenanceServiceImpl implements SoutenanceService {

	@Autowired
	SoutenanceRepository soutenanceRepository;
	@Autowired
	EtudiantRepository etudiantRepository;
	@Autowired
	EmailService emailService;
	private static final Logger logger =Logger.getGlobal();
	@Value("${app.storagefolder}")
	private String storageFolder;
	@Value("${spring.mail.username}")
	private String mail;
	@Value("${backend.url}")
	private String backendUrl;
	@Autowired
	private Configuration freemarkerConfig;
	@Autowired
	private DtoMapper mapper;

	@Override
	public SoutenanceDto getById(long id) {
		Optional<Soutenance> soutenance = soutenanceRepository.findById(id);
		if (soutenance.isPresent()) {

			return mapper.soutenanceToSoutenanceDto(soutenance.get());
		}

		return null;
	}

	@Override
	public List<SoutenanceDto> getAll() {
		List<Soutenance> soutenances = soutenanceRepository.findAll();
		if (!soutenances.isEmpty()) {
			List<SoutenanceDto> soutenanceDtos = new ArrayList<>();
			for (Soutenance soutenance : soutenances) {
				SoutenanceDto soutenanceDto = new SoutenanceDto();
				soutenanceDto = mapper.soutenanceToSoutenanceDto(soutenance);
				soutenanceDtos.add(soutenanceDto);
			}

			return soutenanceDtos;
		}

		return null;
	}

	public SoutenanceDto saveOrUpdate(SoutenanceDto tDto) throws SaveInvalidException {
		throw new NotImplementedException("Invalid route");
	}
	@Override
	public SoutenanceDto saveOrUpdate(CreateSoutenanceDto tDto) throws SaveInvalidException {
		Soutenance soutenance = new Soutenance();

		// Récupération Etudiant
		if (tDto.getStudentId() > 0) {
			Etudiant etudiant = etudiantRepository.getOne(tDto.getStudentId());
			soutenance.setEtudiant(etudiant);
		}
		// CHECK SI ETUDIANT DANS LA PROMOTION

		// TRAITEMENT DES DATES
		soutenance.setExamDate(tDto.getExamDate());
		soutenance.setMinDeliberation(tDto.getMinDeliberation());
		soutenance.setMinAccueil(tDto.getMinAccueil());
		soutenance.setMinEntretien(tDto.getMinEntretien());
		soutenance.setMinEntretienFinal(tDto.getMinEntretienFinal());
		soutenance.setMinQuestion(tDto.getMinQuestion());
		soutenance.setHasSpecialManagement(tDto.hasSpecialManagement());

		//Sauvegarde de la soutenance
		try {
			soutenanceRepository.saveAndFlush(soutenance);
		} catch (Exception ex) {
			logger.log(Level.WARNING, "Error save soutenance", ex);
		}
		
		//Envoi de la convocation
		//scheduleEmail(soutenance);

		return mapper.soutenanceToSoutenanceDto(soutenance);
	}
	
	private void scheduleEmail(Soutenance soutenance) {
		emailService.scheduleConfirmationEmail(soutenance);
	}

			/*// Méthode envoie de mail
		PromotionSoutenanceDto promotionSoutenance = new PromotionSoutenanceDto();

		// TODO corriger date dans le texte du mail
		String messageMail = "Vous étes convoquer pour l'éxamen "+ promotionSoutenance.getNom() + " le " + soutenance.getExamDate().toString() +
				".\nVous trouverez ci-joint la convocation.";
		emailService.sendMailUser1ToUser2(mail, tDto.getEtudiant().getUtilisateurDto().getLogin(),
				"Convocation " + promotionSoutenance.getNom()
				, messageMail,
				"convocationExamen", tDto );
		*/

	@Override
	public CountDto count(String search) {
		long nb = soutenanceRepository.countByPromotionId(Long.parseLong(search));
		CountDto result =  new CountDto();
		result.setNb(nb);

		return result;
	}

	@Override
	public void delete(long id) {
		soutenanceRepository.deleteById(id);
	}

	@Override
	public List<SoutenanceDto> getByPromotionId(long id) {
		List<Soutenance> soutenances = soutenanceRepository.getByPromotionId(id);
		List<SoutenanceDto> soutenanceDtos = new ArrayList<>();
		for (Soutenance soutenance : soutenances) {
			soutenanceDtos.add(mapper.soutenanceToSoutenanceDto(soutenance));
		}

		return soutenanceDtos;
	}

	@Override
	public List<SoutenanceDto> getPageByPromotionId(long id, int page, int size) {
		List<Soutenance> lstSoutenances = soutenanceRepository.getPageByPromotionId(id, PageRequest.of(page, size)).get().collect(Collectors.toList());
		List<SoutenanceDto> lstSoutenanceDtos = new ArrayList<>();
		if (!lstSoutenances.isEmpty()) {
			for (Soutenance soutenance : lstSoutenances) {
				SoutenanceDto soutenanceDto = mapper.soutenanceToSoutenanceDto(soutenance);
				lstSoutenanceDtos.add(soutenanceDto);
			}
		}
		return lstSoutenanceDtos;
	}

	@Override
	public String genererLstSoutenance(String promotion, long idPromotion) throws
            IOException, TemplateException, DossierProjetException {
		List<SoutenanceDto> soutenanceDto = getByPromotionId(idPromotion);
		Map<String, Object> model = new HashMap<>();
		model.put("backendUrl", backendUrl);
		model.put("soutenanceDto", soutenanceDto);
		freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");
		// TODO modifier le fichier ftl
		Template template = freemarkerConfig.getTemplate("LstSoutenance.ftl");

		String htmlContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
		String outputPdf = storageFolder + "/soutenance/List_soutenance_"+ promotion +".pdf";
		try {
			ToPdf.convertHtmlToPdf(htmlContent, outputPdf);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "convertHtmlToPdf failed", e);
		}

		return outputPdf;
	}

}
