package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.*;
import fr.dawan.AppliCFABack.dto.customdtos.DoubleDto;
import fr.dawan.AppliCFABack.dto.customdtos.EvalByBlocDto;
import fr.dawan.AppliCFABack.entities.*;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.*;
import fr.dawan.AppliCFABack.tools.AvgException;
import fr.dawan.AppliCFABack.tools.PdfTools;
import fr.dawan.AppliCFABack.tools.SaveInvalidException;
import fr.dawan.AppliCFABack.tools.ToPdf;
import freemarker.core.ParseException;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/***
 * 
 * @author Feres BG Valentin C Nicolas P.
 * @see fr.dawan.AppliCFABack.repositories.ExamenRepository
 * @see fr.dawan.AppliCFABack.dto.ExamenDto
 * @since 1.0
 * @version 1.0
 *
 */
@Service
@Transactional
public class ExamenServiceImpl implements ExamenService {

	@Autowired
	ExamenRepository examenRepository;
	
	@Autowired
	PromotionService promotionService;
	
	@Autowired
	ActiviteTypeRepository activiteTypeRepository;
	
	@Autowired
	CompetenceProfessionnelleRepository competenceProfessionnelleRepository;
	
	@Autowired
	PromotionRepository promotionRepository;
	
	@Autowired
	InterventionRepository interventionRepository;

	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();

	@Autowired
	private DtoTools mapperTools;

	@Autowired
	private EtudiantRepository etudiantRepository;

	@Autowired
	private Configuration freemarkerConfig;

	@Value("${backend.url}")
	private String backendUrl;

	@Value("src/main/resources/files/bulletinsEvaluations")
	private String storageFolder;
	
	private static final Logger logger = Logger.getGlobal();

	/**
	 * Récupération de la liste des examens
	 * 
	 * @return lstDto	Liste des objets examen
	 */
	private List<ExamenDto> listAllExam(List<Examen> examen){
		List<ActiviteTypeDto> lstActDto = new ArrayList<>();
		Set<CompetenceProfessionnelleDto> lstCpDto = new HashSet<>();
		List<ExamenDto> lstDto = new ArrayList<>();
		for (Examen e : examen) {
			ExamenDto eDto = mapper.examenToExamenDto(e);
			for (ActiviteType act : e.getActiviteTypes()){
				lstActDto.add(mapper.activiteTypeToActiviteTypeDto(act));
			}
			for (CompetenceProfessionnelle cp : e.getCompetencesProfessionnelles()){
				lstCpDto.add(mapper.competenceProfessionnelleToCompetenceProfessionnelleDto(cp));
			}
			eDto.setCompetencesProfessionnellesDto(lstCpDto);
			eDto.setActiviteTypesDto(lstActDto);
			lstDto.add(eDto);
		}
		return lstDto;
	}
	@Override
	public List<ExamenDto> getAllExamen() {
		List<Examen> lst = examenRepository.findAll();
		return listAllExam(lst);
	}

	/**
	 * Va permettre de récupérer tous les examens avec pagination
	 * recherche par titre ou descriptif
	 * 
	 * @param page	numero de la page
	 * @param size	éléments sur la page
	 * @param search	éléments examen (titre, descriptif)
	 * @return lstDto Liste des objets examens
	 */
	@Override
	public List<ExamenDto> getAllByPage(int page, int size, String search) {
		List<Examen> lst = examenRepository.findAllByTitreContainingIgnoringCaseOrDescriptifContainingIgnoringCase(search,search, PageRequest.of(page, size)).get().collect(Collectors.toList());
		return listAllExam(lst);
	}

	//methode count
	/**
	 * Recherche d'un examen
	 * 
	 * @param search recherche par titre ou descriptif
	 */
	
	@Override
	public CountDto count(String search) {
		return new CountDto(examenRepository.countByTitreContainingIgnoringCaseOrDescriptifContainingIgnoringCase(search, search));
		
	}

	/**
	 * Récupération des examens en fonction de l'id
	 * 
	 * @param id	id de l'examen
	 */
	
	@Override
	public ExamenDto getById(long id) {
		Optional<Examen> e = examenRepository.findById(id);
		if (e.isPresent()) {
			ExamenDto eDto = DtoTools.convert(e.get(), ExamenDto.class);
			return eDto;
		}		
		return null;

	}

	/**
	 * Sauvegarde ou mise à jour d'un examen
	 * 
	 * @param ExamenDtoSave
	 * @return ExamenDtoSave
	 * @throws Si l'activité types n'est pas précisé
	 * @throws Si les competences professionnelles ne sont pas précisé
	 * @throws Si les promotions ne sont pas précisé.
	 */
	
	
	@Override
	public ExamenDtoSave saveOrUpdate(ExamenDtoSave eDto) throws SaveInvalidException {
		
		Set<Promotion> promotions = new HashSet<>();
		Set<Long>  promotionsId = null;
		if (eDto.getPromotionsId().isEmpty() || eDto.getPromotionsId() == null) 
		{
			Optional<Intervention> intervention =  interventionRepository.findById(eDto.getInterventionId());
			
			if (!intervention.isPresent()) 
				throw new SaveInvalidException("Intervention Types manquante");
			
			  promotionsId =  intervention.get().getPromotions().stream().map(p -> p.getId()).collect(Collectors.toSet());
		}
		else {
			promotionsId = eDto.getPromotionsId();
		}
		for (long idP : promotionsId ) {
			promotions.add(promotionRepository.getOne(idP));
		}
		if (eDto.getActiviteTypesId().isEmpty() || eDto.getActiviteTypesId() == null) 
			throw new SaveInvalidException("Activites Types manquante");

		List<ActiviteType> activiteTypes = new ArrayList<>();
		for(long idA : eDto.getActiviteTypesId()) {
			activiteTypes.add(activiteTypeRepository.getOne(idA));
		}
		if (eDto.getCompetencesProfessionnellesId().isEmpty() || eDto.getCompetencesProfessionnellesId() == null) 
				throw new SaveInvalidException("Compétences Professionnelles manquante");
			
		Set<CompetenceProfessionnelle> competenceProfessionnelles = new HashSet<>();
		for(long idC : eDto.getCompetencesProfessionnellesId() ) {
			competenceProfessionnelles.add(competenceProfessionnelleRepository.getOne(idC));
		}
		
		System.out.println(eDto);
		Examen e = DtoTools.convert(eDto, Examen.class);
		e.setActiviteType(activiteTypes);
		e.setCompetencesProfessionnelles(competenceProfessionnelles);
		e.setPromotions(promotions);
		if (eDto.getId() != 0) {
			return  DtoTools.convert(examenRepository.saveAndFlush(e),ExamenDtoSave.class);
		}
		else {
			Examen exDb =  examenRepository.saveAndFlush(e);
			Set<Note> notes =  new HashSet<>();
			//Ajout list de notes vide avec les etudiants 
			for (Promotion promo : exDb.getPromotions()) {
				List<EtudiantDto> etudiantsParPromo = promotionService.getEtudiantsById(promo.getId());
				for (EtudiantDto etudiantDto : etudiantsParPromo) {
					Note note = new Note();
					note.setEtudiantNote(DtoTools.convert(etudiantDto, Etudiant.class));
					note.setNoteObtenue(0.0);
					note.setExamen(exDb);
					notes.add(note);
				}
			}
			
			exDb.setNotes(notes);			
			
			 return DtoTools.convert(examenRepository.saveAndFlush(exDb),ExamenDtoSave.class);
			
		}
		
		
	}

	/**
	 * Suppression d'un examen
	 * 
	 * @param id	Id concernant l'entreprise
	 */
	
	@Override
	public void deleteById(long id) {
		examenRepository.deleteById(id);

	}

	/**
	 * @param id de l'étudiant
	 * utilise l'ExamenRepository pour récupérer une liste d'examens par id de l'étudiant
	 * @return toutes les données nécessaires pour remplir la section livret Evaluation du front partie étudiant par le mapper (DtoTools) :
	 * 			- nom promotion
	 * 			- titre examen
	 * 			- liste de compétences
	 * 			- liste de satisfactions
	 * 			- liste d'observations
	 */

	@Override
	public String generateBulletinPdfByStudentAndPromo(long etudiantId, long promotionId) throws ToPdf, IOException, TemplateException {
		Optional<Etudiant> etuOpt = etudiantRepository.findById(etudiantId);
		if (etuOpt.isPresent()) {
			freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");
			Template template = freemarkerConfig.getTemplate("BulletinEval.ftl");

			Map<String, Object> model = new HashMap<>();
			model.put("backendUrl", backendUrl);

			Etudiant etu = etuOpt.get();
			model.put("etudiant", etu);

			Optional<Promotion> promoOpt = promotionRepository.findById(promotionId);
			if (promoOpt.isPresent()) {
				Promotion promo = promoOpt.get();
				model.put("promo", promo);
				String promoAnnee = String.valueOf(promo.getDateDebut().getYear()).trim();
				model.put("promoAnnee", promoAnnee);
				model.put("titrePro", promo.getCursus().getTitre());

				List<ActiviteType> activiteTypes = activiteTypeRepository
						.findAllByCursusActiviteTypeIdOrderByNumeroFiche(promo.getCursus().getId());

				List<EvalByBlocDto> evalList = new ArrayList<>();
				double s = 0;
				for (ActiviteType at : activiteTypes) {
					EvalByBlocDto evalByBlocDto = new EvalByBlocDto();
					evalByBlocDto.setActiviteType(at);
					try {
						logger.log(Level.INFO,"etuId = " + etudiantId);
						logger.log(Level.INFO, "atId = " + at.getId());
						double moyB = getAvgByEtudiantIdAndActiviteTypeId(etudiantId, at.getId()).getResult();
						evalByBlocDto.setMoyenne(moyB);
						s += moyB;
					} catch (Exception e) {
						//0 par défaut ou afficher un message
						logger.log(Level.WARNING, "error : pas d'activité type", e);
						logger.info("L'étudiant n'a pas de note associée à cette activité type");
						throw new ToPdf("L'étudiant n'a pas de note associée à cette activité type"); 
						
					}

					try {
						evalByBlocDto.setMoyennePromo(getAvgByPromoIdAndActiviteTypeId(promotionId, at.getId()).getResult());
					} catch (Exception e) {
						// 0 par défaut //ajouter un message pour dire note manquante
						String msg = "note manquante";
						logger.log(Level.WARNING, msg, e);
						logger.log(Level.SEVERE, msg);
						throw new ToPdf(msg);
					}
					evalList.add(evalByBlocDto);
				}
				model.put("evalList", evalList);
				model.put("moyEtudiant", (s/activiteTypes.size()));
				try {
					model.put("moyPromo",getAvgByPromotionId(promotionId).getResult());
				} catch (Exception e) {
					logger.log(Level.SEVERE,"getAvgByPromotionId failed", e);
				}
			}
			String htmlContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
			String outputPdf = storageFolder + "/bulletin-" + etudiantId + "-promo-" + promotionId + ".pdf";
			try {
				PdfTools.generatePdfFromHtml(outputPdf, htmlContent);
			} catch (Exception e) {
				logger.log(Level.SEVERE,"generatePdfFromHtml failed", e);
			}

			return outputPdf;
		}
		return null;
	}

	@Override
	public DoubleDto getAvgByEtudiantIdAndActiviteTypeId(long etudiantId, long activiteTypeId) throws AvgException {
		return new DoubleDto(examenRepository.getAvgByEtudiantIdAndActiviteTypeId(etudiantId, activiteTypeId));
	}

	@Override
	public DoubleDto getAvgByPromoIdAndActiviteTypeId(long promotionId, long activiteTypeId) throws AvgException {
		return new DoubleDto(examenRepository.getAvgByPromoIdAndActiviteTypeId(promotionId, activiteTypeId));
	}

	@Override
	public DoubleDto getAvgByPromotionId(long promotionId) throws AvgException {
		return new DoubleDto(examenRepository.getAvgByPromotionId(promotionId));
	}

	@Override
	public List<ExamenDto> findExamensByInterventionId(long id) {
		List<Examen> examens = examenRepository.findExamensByInterventionId(id);
		List<ExamenDto> result = new ArrayList<>();
		for(Examen e : examens) {	
			ExamenDto exDto = DtoTools.convert(e, ExamenDto.class);
			
			Set<CompetenceProfessionnelleDto> cpDto = new HashSet<>();
			for (CompetenceProfessionnelle cp : e.getCompetencesProfessionnelles()) {
				cpDto.add(DtoTools.convert(cp, CompetenceProfessionnelleDto.class));
			}
			
			List<ActiviteTypeDto> actDto = new ArrayList<>();
			for(ActiviteType at : e.getActiviteTypes()) {
				actDto.add(DtoTools.convert(at, ActiviteTypeDto.class));
			}

			exDto.setCompetencesProfessionnellesDto(cpDto);
			exDto.setActiviteTypesDto(actDto);
			result.add(exDto);
		}
		return result;
	}


}
