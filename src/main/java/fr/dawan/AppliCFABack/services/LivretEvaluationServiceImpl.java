package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.*;
import fr.dawan.AppliCFABack.dto.customdtos.EtudiantLivretEvaluationDto;
import fr.dawan.AppliCFABack.dto.customdtos.EvaluationDto;
import fr.dawan.AppliCFABack.entities.*;
import fr.dawan.AppliCFABack.repositories.*;
import fr.dawan.AppliCFABack.tools.Filter;
import fr.dawan.AppliCFABack.tools.LivretEvaluationException;
import fr.dawan.AppliCFABack.tools.SaveInvalidException;
import fr.dawan.AppliCFABack.tools.ToPdf;
import freemarker.core.ParseException;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
	private EmailService emailService;

	@Value("${app.storagefolder}")
	private String storageFolder;
	@Value("${backend.url}")
	private String backendUrl;
	@Autowired
	private Configuration freemarkerConfig;
	@Autowired
	private SignatureService signatureService;
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
	private EtudiantRepository etudiantRepository;
	@Autowired
	private ActiviteTypeRepository activiteTypeRepository;

	@Autowired
	private CursusRepository cursusRepository;
	@Autowired
	private LivretEvaluationRepository livertEvaluationRepository;
	@Autowired
	private BlocEvaluationRepository blocEvaluationRepository;

	@Autowired
	private PromotionRepository promotionRepository;

	private static final Logger logger = Logger.getGlobal();

	@Autowired
	private DtoTools mapper;
	@Autowired
	private FormateurRepository formateurRepository;

	@Override

	public LivretEvaluationDto getById(long id) {
		Optional<LivretEvaluation> livretEvalOpt = livretEvaluationRepository.findById(id);

		if (livretEvalOpt.isPresent()) {
			return DtoTools.convert(livretEvalOpt, LivretEvaluationDto.class);
		}
		return null;
	}
	@Override
	public LivretEvaluationDto getByIdEtudiantAndIdCurcus(long idEtudiant, long idCursus) {
		Optional<LivretEvaluation> livretEvalOpt = livretEvaluationRepository.findByEtudiantIdAndTitreProfessionnelId(idEtudiant, idCursus);

		if (livretEvalOpt.isPresent()) {
			return DtoTools.convert(livretEvalOpt, LivretEvaluationDto.class);
		}
		return null;
	
	}
	@Override
	public LivretEvaluationDto saveOrUpdate(LivretEvaluationDto tDto) throws SaveInvalidException {
			 LivretEvaluation livret = DtoTools.convert(tDto, LivretEvaluation.class);
			 Optional<Long> idUser = utilisateurRepository.findidUtilisateurByLivretEvaluation(tDto.getId());
			 mailNotification(tDto.getId(), idUser.get());
			 livretEvaluationRepository.saveAndFlush(livret);
			 return DtoTools.convert(livret, LivretEvaluationDto.class);

	}
	/*
	* Méthode pour le calcul de la date limite de formation a compté du jour d'appel de la méthode
	*/


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

	private void verificationSignature(Optional<Etudiant> etudiant){
		Optional<SignatureDto> signature = Optional.ofNullable(signatureService.getByUtilisateurId(etudiant.get().getUtilisateur().getId()));
		if(signature.get().getPieceJointe() == null || signature.get().getPieceJointe().equals("") ){
			throw new NullPointerException("La Signature de l'étudiant ne doit pas être null");
		}
	}
	/**
	 * Permet de générer le livret d'évaluation
	 * @author Feres BG 
	 * @param idEtudiant identifiant de l'etudiant
	 * @param idCursus identifiant du cursus
	 * @return fichier pdf
	 * @exception TemplateNotFoundException,MalformedTemplateNameException, ParseException, IOException, 
	 * TemplateException, LivretEvaluationException
	 */
	@Override
	public String getLivretEvaluation(long idEtudiant, long idCursus) throws
			IOException, TemplateException, LivretEvaluationException {
		Optional<Etudiant> etudiant = etudiantRepository.findById(idEtudiant);
		verificationSignature(etudiant);
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
			BlocEvaluation blocEvaluation = blocEvaluationRepository.findByActiviteTypeIdAndLivretEvaluationId(activiteType.getId(),livretEvaluation.get().getId()).get();
			evaluationDto.setBlocEvaluation(blocEvaluation);
			formateursEvaluateurs.add(blocEvaluation);
			List<EvaluationFormation> evaluationFormations = blocEvaluation.getEvaluationsFormations();
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

	@Override
	public List<EtudiantLivretEvaluationDto> getLivretEtudiant(long id) {

		List<Promotion> promotions = promotionRepository.getByEtudiantId(id);

		List<EtudiantLivretEvaluationDto> evaluationDtos = promotions.stream().map(promotion -> mapper.promotionToLivretEvaluationDto(promotion)).collect(Collectors.toList());

		List<LivretEvaluation> livretEvaluations = livretEvaluationRepository.findLivretEvaluationByEtudiantId(id);

		for(EtudiantLivretEvaluationDto e : evaluationDtos) {
			for(LivretEvaluation livretEvaluation : livretEvaluations) {
				if(e.getCursusId() == livretEvaluation.getTitreProfessionnel().getId()) {
					e.setObservation(livretEvaluation.getObservation());
				}
			}
		}
		return evaluationDtos;
	}

	/*
	* Vérifier la liste de livret d'évaluation associé au bloc_evaluation qui est associé au formateur
	* pour valider si toutes les évaluation des livret et envoyer un mail de validation du tableau
	*/
	@Override
	public void mailNotification(long idLivret,long idUtilisateur) {
		Optional<String> titreCursus = livretEvaluationRepository.findCursusTitredByLivretEvaluation(idLivret);
		String message = "Validation de l'évaluation du Cursus : "+titreCursus+ " le : " + LocalDate.now();
		emailService.sendMailSmtpUser(idUtilisateur,"Confirmation de l'évaluation du Livret",
				message, Optional.of(""), Optional.of(""));

	}


}
