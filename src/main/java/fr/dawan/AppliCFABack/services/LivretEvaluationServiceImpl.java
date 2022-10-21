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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.LivretEvaluationDto;
import fr.dawan.AppliCFABack.dto.LivretEvaluationFileDto;
import fr.dawan.AppliCFABack.dto.customdtos.EvaluationDto;
import fr.dawan.AppliCFABack.entities.ActiviteType;
import fr.dawan.AppliCFABack.entities.BlocEvaluation;
import fr.dawan.AppliCFABack.entities.Cursus;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.EvaluationFormation;
import fr.dawan.AppliCFABack.entities.LivretEvaluation;
import fr.dawan.AppliCFABack.entities.Validation;
import fr.dawan.AppliCFABack.entities.Validation.Etat;
import fr.dawan.AppliCFABack.repositories.ActiviteTypeRepository;
import fr.dawan.AppliCFABack.repositories.BlocEvaluationRepository;
import fr.dawan.AppliCFABack.repositories.CursusRepository;
import fr.dawan.AppliCFABack.repositories.EtudiantRepository;
import fr.dawan.AppliCFABack.repositories.EvaluationFormationRepository;
import fr.dawan.AppliCFABack.repositories.LivretEvaluationRepository;
import fr.dawan.AppliCFABack.repositories.ValidationRepository;
import fr.dawan.AppliCFABack.tools.Filter;
import fr.dawan.AppliCFABack.tools.LivretEvaluationException;
import fr.dawan.AppliCFABack.tools.SaveInvalidException;
import fr.dawan.AppliCFABack.tools.ToPdf;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

/**
 * 
 * @author Feres BG, Valentin C.
 * @see fr.dawan.appliCFABack.services
 * @since 1.0
 * @version 1.0
 */
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
	@Autowired
	private EtudiantRepository etudiantRepository;
	@Autowired
	private ActiviteTypeRepository activiteTypeRepository;

	@Autowired
	private CursusRepository cursusRepository;
	@Autowired
	private LivretEvaluationRepository livertEvaluationRepository;
	@Autowired
	private EvaluationFormationRepository evaluationFormationRepository;

	@Autowired
	private BlocEvaluationRepository blocEvaluationRepository;

	private static Logger logger = Logger.getGlobal();

	@Override

	public LivretEvaluationDto getById(long id) {
		Optional<LivretEvaluation> livretEvalOpt = livretEvaluationRepository.findById(id);

		if (livretEvalOpt.isPresent()) {
			return DtoTools.convert(livretEvalOpt, LivretEvaluationDto.class);
		}
		return null;
	}

	@Override
	public LivretEvaluationDto saveOrUpdate(LivretEvaluationDto tDto) throws SaveInvalidException {
		LivretEvaluation livretEval = DtoTools.convert(tDto, LivretEvaluation.class);
		if (tDto.getId() == 0) {
			// créer un object Validation et l'entrer dans table Validation
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
		for (LivretEvaluation l : livrets) {
			result.add(DtoTools.convert(l, LivretEvaluationDto.class));
		}
		return result;
	}

	/**
	 * Permet de générer le livret d'évaluation
	 * @author Feres BG 
	 * 
	 * @param id étudiant id du cursus
	 * @return fichier pdf
	 */
	@Override
	public String getLivretEvaluation(long idEtudiant, long idCursus) throws TemplateNotFoundException,
			MalformedTemplateNameException, ParseException, IOException, TemplateException, LivretEvaluationException {
		Optional<Etudiant> etudiant = etudiantRepository.findById(idEtudiant);

		if (!etudiant.isPresent())
			throw new LivretEvaluationException("Etudiant non trouvé");

		Optional<Cursus> cursus = cursusRepository.findById(idCursus);

		if (!cursus.isPresent()) {
			throw new LivretEvaluationException("Cursus non trouvé");
		}

		Optional<LivretEvaluation> livretEvaluation = livertEvaluationRepository
				.findByEtudiantIdAndTitreProfessionnelId(idEtudiant, idCursus);

		if (!livretEvaluation.isPresent()) {
			throw new LivretEvaluationException("Livret Evaluation non trouvé");
		}
		List<ActiviteType> activiteTypes = activiteTypeRepository
				.findAllByCursusActiviteTypeIdOrderByNumeroFiche(idCursus);
		if (activiteTypes.isEmpty() || activiteTypes == null) {
			throw new LivretEvaluationException("Pas d'activité Types trouvé pour ce cursus");
		}
		LivretEvaluationFileDto livretEvalFile = new LivretEvaluationFileDto();

		livretEvalFile.setCursus(cursus.get());
		livretEvalFile.setEtudiant(etudiant.get());
		livretEvalFile.setLivretEvaluation(livretEvaluation.get());
		List<BlocEvaluation> formateursEvaluateurs = new ArrayList<>();
		List<EvaluationDto> evaluations = new ArrayList<>();
		for (ActiviteType activiteType : activiteTypes) {

			EvaluationDto evaluationDto = new EvaluationDto();
			evaluationDto.setActiviteType(activiteType);
			BlocEvaluation blocEvaluation = blocEvaluationRepository.findByActiviteTypeId(activiteType.getId());
			evaluationDto.setBlocEvaluation(blocEvaluation);
			formateursEvaluateurs.add(blocEvaluation);
			List<EvaluationFormation> evaluationFormations = evaluationFormationRepository
					.findAllByBlocEvaluationActiviteTypeId(activiteType.getId());
			evaluationDto.setEvaluationFormations(evaluationFormations);
			evaluations.add(evaluationDto);
		}
		livretEvalFile.setEvaluations(evaluations);
	
		formateursEvaluateurs = formateursEvaluateurs.stream().filter(Filter.distinctByKey(BlocEvaluation::getFormateurEvaluateur) ).collect(Collectors.toList());
		livretEvalFile.setFormateursEvaluateurs(formateursEvaluateurs);
		Map<String, Object> model = new HashMap<>();
		model.put("backendUrl", backendUrl);
		model.put("livertEval", livretEvalFile);
		freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");
		Template template = freemarkerConfig.getTemplate("LivretEval.ftl");

		String htmlContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
		String outputPdf = storageFolder + "livretEvaluation/livretEvaluation.pdf";
		try {
			ToPdf.convertHtmlToPdf(htmlContent, outputPdf);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "convertHtmlToPdf failed", e);
		}

		return outputPdf;
	}

}
