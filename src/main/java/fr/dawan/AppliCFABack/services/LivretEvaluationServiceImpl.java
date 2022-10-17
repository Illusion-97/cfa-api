package fr.dawan.AppliCFABack.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.client.RestTemplate;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.LivretEvaluationDto;
import fr.dawan.AppliCFABack.dto.NiveauDto;
import fr.dawan.AppliCFABack.dto.customdtos.GrillePositionnementDto;
import fr.dawan.AppliCFABack.entities.Formation;
import fr.dawan.AppliCFABack.entities.LivretEvaluation;
import fr.dawan.AppliCFABack.entities.Positionnement;
import fr.dawan.AppliCFABack.entities.Promotion;
import fr.dawan.AppliCFABack.entities.Validation;
import fr.dawan.AppliCFABack.entities.Validation.Etat;
import fr.dawan.AppliCFABack.repositories.LivretEvaluationRepository;
import fr.dawan.AppliCFABack.repositories.ValidationRepository;
import fr.dawan.AppliCFABack.tools.GrilleException;
import fr.dawan.AppliCFABack.tools.SaveInvalidException;
import fr.dawan.AppliCFABack.tools.ToPdf;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@Service
@Transactional
public class LivretEvaluationServiceImpl implements LivretEvaluationService {

	@Autowired
	private LivretEvaluationRepository livretEvaluationRepository;
	@Autowired 
	private ValidationRepository validationRepository;
	
	@Value("${app.storagefolder}")
	private String storageFolder;
    @Value("${backend.url}")
    private String backendUrl;
	@Autowired
	private Configuration freemarkerConfig;

	private static Logger logger = Logger.getGlobal();
	@Override
	
	public LivretEvaluationDto getById(long id) {
		Optional<LivretEvaluation> livretEvalOpt = livretEvaluationRepository.findById(id);
		
		if(livretEvalOpt.isPresent()) {	
			return DtoTools.convert(livretEvalOpt, LivretEvaluationDto.class);
		}
		return null;
	}

	@Override
	public LivretEvaluationDto saveOrUpdate(LivretEvaluationDto tDto) throws SaveInvalidException {
		LivretEvaluation livretEval = DtoTools.convert(tDto, LivretEvaluation.class);
		if(tDto.getId() == 0 ) {
			//créer un object Validation et l'entrer dans table Validation 
			Validation validation = new Validation();
			validation.setSignature(null);
			validation.setEtat(Etat.NONTRAITE);
			validation.setVersion(0);
			validation = validationRepository.saveAndFlush(validation);
			livretEval.setValidation(validation);
			LivretEvaluation livretEvalDb = livretEvaluationRepository.saveAndFlush(livretEval);
			return DtoTools.convert(livretEvalDb, LivretEvaluationDto.class);
		} else {
			LivretEvaluation livretEvalDb = livretEvaluationRepository.saveAndFlush(livretEval);
			return DtoTools.convert(livretEvalDb, LivretEvaluationDto.class);
		}
		
	}

	@Override
	public CountDto count(String search) {
		long nb = livretEvaluationRepository.count();
		CountDto d = new CountDto();
		d.setNb(nb);
		return d;
	}

	@Override
	public void delete(long id) {
		livretEvaluationRepository.deleteById(id);
		
	}

	@Override
	public List<LivretEvaluationDto> getByEtudiantId(long id) {
		List<LivretEvaluation> livrets = livretEvaluationRepository.findLivretEvaluationByEtudiantId(id);
		List<LivretEvaluationDto> result = new ArrayList<>();
		for(LivretEvaluation l: livrets) {
			result.add(DtoTools.convert(l, LivretEvaluationDto.class));
		}
		return result;
	}
	@Override
	public String getLivretEvaluation(long idEtudiant) throws GrilleException, TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
//		Optional<Promotion> promotion = promotionRepository.findById(idPromotion);
//
//		if (!promotion.isPresent())
//			throw new GrilleException("Promotion non trouvé");
//
//		List<Intervention> interventions = interventionRepository.getInterventionsByIdPromotion(idPromotion);
//
//		if (interventions.isEmpty() || interventions == null)
//			throw new GrilleException("Pas d'interventions encore pour cette promotion non trouvé");
//
//		Map<String, List<?>> gp = new HashMap<>();
//		List<GrillePositionnementDto> grillesPositionnements = new ArrayList<>();
//		for (Intervention i : interventions) {
//
//			GrillePositionnementDto gpd = new GrillePositionnementDto();
//			gpd.setDateDebut(i.getDateDebut());
//			gpd.setDateFin(i.getDateFin());
//			Formation f = i.getFormation();
//			if (f != null) {
//				gpd.setModule(f.getTitre());	
//			}else {
//				gpd.setModule("Pas de Formation");
//			}
//			
//		
////			gpd.setObjectifPedagogiques(f.getObjectifsPedagogique());
//			gpd.setObjectifPedagogiques("A définir");
//			List<String> formateursNomPrenom = i.getFormateurs().stream().map(
//					fr -> fr.getUtilisateur().getNom() + " " + fr.getUtilisateur().getPrenom()
//					).collect(Collectors.toList());
//			gpd.setFormateurs(formateursNomPrenom);
//
//			Map<String, Positionnement> etudiantsPositionnement = new HashMap<>();
//			List<Positionnement> positionnements =  positionnementRepository.getAllByInterventionId(i.getId());
//			for (Positionnement p : positionnements) {
//				etudiantsPositionnement.put(p.getEtudiant().getUtilisateur().getNom() + " " +p.getEtudiant().getUtilisateur().getPrenom(), p);
//			}
//			gpd.setEtudiantsPositionnements(etudiantsPositionnement);
//			grillesPositionnements.add(gpd);
//		}
//		gp.put("interventions", grillesPositionnements);
//		List<NiveauDto> niveaux = niveauService.getAllNiveaux();
//		gp.put("niveaux", niveaux);
		  Map<String, Object> model = new HashMap<>();
          model.put("backendUrl", backendUrl);
		freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");
		Template template = freemarkerConfig.getTemplate("LivretEval.ftl");

		String htmlContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
		String outputPdf = storageFolder + "livretEvaluation/livretEvaluation.pdf";
		try {
			ToPdf.convertHtmlToPdf(htmlContent, outputPdf);
		} catch (Exception e) {
			logger.log(Level.SEVERE,"convertHtmlToPdf failed", e);
		}

		return outputPdf;
	}


}
