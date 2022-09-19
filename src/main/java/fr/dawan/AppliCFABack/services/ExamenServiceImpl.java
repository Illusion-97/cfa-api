package fr.dawan.AppliCFABack.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import fr.dawan.AppliCFABack.dto.ActiviteTypeDto;
import fr.dawan.AppliCFABack.dto.CompetenceProfessionnelleDto;
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.ExamenDto;
import fr.dawan.AppliCFABack.dto.ExamenDtoSave;
import fr.dawan.AppliCFABack.dto.NoteDto;
import fr.dawan.AppliCFABack.dto.customdtos.DoubleDto;
import fr.dawan.AppliCFABack.dto.customdtos.EvalByBlocDto;
import fr.dawan.AppliCFABack.dto.customdtos.LivretEvaluationDto;
import fr.dawan.AppliCFABack.entities.ActiviteType;
import fr.dawan.AppliCFABack.entities.CompetenceProfessionnelle;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.Examen;
import fr.dawan.AppliCFABack.entities.Intervention;
import fr.dawan.AppliCFABack.entities.Note;
import fr.dawan.AppliCFABack.entities.Promotion;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.ActiviteTypeRepository;
import fr.dawan.AppliCFABack.repositories.CompetenceProfessionnelleRepository;
import fr.dawan.AppliCFABack.repositories.EtudiantRepository;
import fr.dawan.AppliCFABack.repositories.ExamenRepository;
import fr.dawan.AppliCFABack.repositories.InterventionRepository;
import fr.dawan.AppliCFABack.repositories.PromotionRepository;
import fr.dawan.AppliCFABack.tools.AvgException;
import fr.dawan.AppliCFABack.tools.PdfTools;
import fr.dawan.AppliCFABack.tools.SaveInvalidException;
import fr.dawan.AppliCFABack.tools.ToPdf;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

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
	
	private static Logger logger = Logger.getGlobal();

	/**
	 * Récupération de la liste des examens
	 * 
	 * @return lstDto	Liste des objets examen
	 */
	
	@Override
	public List<ExamenDto> getAllExamen() {
		List<Examen> lst = examenRepository.findAll();
		List<ExamenDto> lstDto = new ArrayList<>();
		for (Examen e : lst) {
			ExamenDto eDto = mapper.examenToExamenDto(e);
			
			Set<CompetenceProfessionnelle> lstCp = e.getCompetencesProfessionnelles();
			Set<CompetenceProfessionnelleDto> lstCpDto = new HashSet<>();
			for (CompetenceProfessionnelle cp : lstCp) {
				if (cp != null)
					lstCpDto.add(mapper.competenceProfessionnelleDto(cp));
			}
			
			Set<Note> lstNotes = e.getNotes();
			Set<NoteDto> lstNoteDto = new HashSet<>();
			for (Note note : lstNotes) {
				if(note != null) {
					NoteDto noteDto = DtoTools.convert(note, NoteDto.class);
					lstNoteDto.add(noteDto);	
				}
			}
//			eDto.setPromotionDto(mapper.PromotionToPromotionDto(e.getPromotion()));
			eDto.setNotesDto(lstNoteDto);
			lstDto.add(eDto);
		}
		return lstDto;
	}

	//recuperation de la liste des examens avec pagination et recherche
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

		// conversion vers Dto
		List<ExamenDto> lstDto = new ArrayList<>();
		for (Examen e : lst) {
			ExamenDto eDto = mapper.examenToExamenDto(e);
			
//			eDto.setPromotionDto(mapper.PromotionToPromotionDto(e.getPromotion()));
//			eDto.setCursusDto(mapper.CursusToCursusDto(e.getCursus()));
//			eDto.setFormationDto(mapper.FormationToFormationDto(e.getFormation()));
			lstDto.add(eDto);
		}
		return lstDto;
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

			
//			eDto.setCursusDto(mapper.CursusToCursusDto(e.get().getCursus()));
//			eDto.setFormationDto(mapper.FormationToFormationDto(e.get().getFormation()));
			
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
	public List<LivretEvaluationDto> getLivretEvaluation(long id) {
		List<LivretEvaluationDto> result = new ArrayList<>();
		List<Examen> list = examenRepository.findallByEtudiantId(id);

		for(Examen e : list) {
			result.add(mapperTools.examenToLivretEvaluationDto(e));
		}
		return result;
	}

	@Override
	public String generateBulletinPdfByStudentAndPromo(long etudiantId, long promotionId) throws ToPdf, TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
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
						.findAllByCursusActiviteTypeId(promo.getCursus().getId());

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
