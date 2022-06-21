package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import fr.dawan.AppliCFABack.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import fr.dawan.AppliCFABack.dto.ActiviteTypeDto;
import fr.dawan.AppliCFABack.dto.CompetenceProfessionnelleDto;
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.ExamenDto;
import fr.dawan.AppliCFABack.dto.ExamenDtoSave;
import fr.dawan.AppliCFABack.dto.NoteDto;
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
import fr.dawan.AppliCFABack.repositories.ExamenRepository;
import fr.dawan.AppliCFABack.repositories.InterventionRepository;
import fr.dawan.AppliCFABack.repositories.PromotionRepository;
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

	/**
	 * Récupération de la liste des examens
	 * 
	 * @return lstDto	Liste des objets examen
	 */
	
	@Override
	public List<ExamenDto> getAllExamen() {
		List<Examen> lst = examenRepository.findAll();
		List<ExamenDto> lstDto = new ArrayList<ExamenDto>();
		for (Examen e : lst) {
			ExamenDto eDto = mapper.ExamenToExamenDto(e);
			
			Set<CompetenceProfessionnelle> lstCp = e.getCompetencesProfessionnelles();
			Set<CompetenceProfessionnelleDto> lstCpDto = new HashSet<CompetenceProfessionnelleDto>();
			for (CompetenceProfessionnelle cp : lstCp) {
				if (cp != null)
					lstCpDto.add(mapper.CompetenceProfessionnelleDto(cp));
			}
			
			Set<Note> lstNotes = e.getNotes();
			Set<NoteDto> lstNoteDto = new HashSet<NoteDto>();
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
		List<ExamenDto> lstDto = new ArrayList<ExamenDto>();
		for (Examen e : lst) {
			ExamenDto eDto = mapper.ExamenToExamenDto(e);
			
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
//		return new CountDto(examenRepository.countByEnonceContainingIgnoringCaseOrFormationTitreContainingIgnoringCaseOrCursusTitreContainingIgnoringCase(search, search, search));
		
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
	public ExamenDtoSave saveOrUpdate(ExamenDtoSave eDto) throws Exception {
		
		Set<Promotion> promotions = new HashSet<Promotion>();
		Set<Long>  promotionsId = null;
		if (eDto.getPromotionsId().isEmpty() || eDto.getPromotionsId() == null) 
		{
			Optional<Intervention> intervention =  interventionRepository.findById(eDto.getInterventionId());
			
			if (!intervention.isPresent()) 
				throw new Exception("Intervention Types manquante");
			
			  promotionsId =  intervention.get().getPromotions().stream().map(p -> p.getId()).collect(Collectors.toSet());
		}
		else {
			promotionsId = eDto.getPromotionsId();
		}
		for (long idP : promotionsId ) {
			promotions.add(promotionRepository.getOne(idP));
		}
		if (eDto.getActiviteTypesId().isEmpty() || eDto.getActiviteTypesId() == null) 
			throw new Exception("Activites Types manquante");

		List<ActiviteType> activiteTypes = new ArrayList<ActiviteType>();
		for(long idA : eDto.getActiviteTypesId()) {
			activiteTypes.add(activiteTypeRepository.getOne(idA));
		}
		if (eDto.getCompetencesProfessionnellesId().isEmpty() || eDto.getCompetencesProfessionnellesId() == null) 
				throw new Exception("Compétences Professionnelles manquante");
			
		Set<CompetenceProfessionnelle> competenceProfessionnelles = new HashSet<CompetenceProfessionnelle>();
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
			Set<Note> Notes =  new HashSet<Note>();
			//Ajout list de notes vide avec les etudiants 
			for (Promotion promo : exDb.getPromotions()) {
				List<EtudiantDto> etudiantsParPromo = promotionService.getEtudiantsById(promo.getId());
				for (EtudiantDto etudiantDto : etudiantsParPromo) {
					Note note = new Note();
					note.setEtudiantNote(DtoTools.convert(etudiantDto, Etudiant.class));
					note.setNoteObtenue(0.0);
					note.setExamen(exDb);
					Notes.add(note);
				}
			}
			
			exDb.setNotes(Notes);			
			
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

	@Override
	public List<LivretEvaluationDto> getLivretEvaluation(long id) {
		List<LivretEvaluationDto> result = new ArrayList<>();
		List<Examen> list = examenRepository.findallByEtudiantId(id);

		for(Examen e : list) {
			result.add(mapperTools.ExamenToLivretEvaluationDto(e));
		}
		return result;
	}

	@Override
	public List<ExamenDto> findExamensByInterventionId(long id) {
		List<Examen> examens = examenRepository.findExamensByInterventionId(id);
		List<ExamenDto> result = new ArrayList<ExamenDto>();
		for(Examen e : examens) {	
			ExamenDto exDto = DtoTools.convert(e, ExamenDto.class);
			
			Set<CompetenceProfessionnelleDto> cpDto = new HashSet<CompetenceProfessionnelleDto>();
			for (CompetenceProfessionnelle cp : e.getCompetencesProfessionnelles()) {
				cpDto.add(DtoTools.convert(cp, CompetenceProfessionnelleDto.class));
			}
			
			List<ActiviteTypeDto> actDto = new ArrayList<ActiviteTypeDto>();
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
