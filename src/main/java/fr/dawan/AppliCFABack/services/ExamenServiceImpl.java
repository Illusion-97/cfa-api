package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
import fr.dawan.AppliCFABack.entities.Note;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.ActiviteTypeRepository;
import fr.dawan.AppliCFABack.repositories.CompetenceProfessionnelleRepository;
import fr.dawan.AppliCFABack.repositories.ExamenRepository;

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
	private DtoMapper mapper = new DtoMapperImpl();

	//recuperation de la liste des examens
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
	@Override
	public CountDto count(String search) {
		return new CountDto(examenRepository.countByTitreContainingIgnoringCaseOrDescriptifContainingIgnoringCase(search, search));
//		return new CountDto(examenRepository.countByEnonceContainingIgnoringCaseOrFormationTitreContainingIgnoringCaseOrCursusTitreContainingIgnoringCase(search, search, search));
		
	}

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

	//methode d'ajout ou modification d'un examen
	@Override
	public ExamenDtoSave saveOrUpdate(ExamenDtoSave eDto) throws Exception {
		
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
		if (eDto.getId() != 0) {
			return  DtoTools.convert(examenRepository.saveAndFlush(e),ExamenDtoSave.class);
		}
		else {
			Examen exDb =  examenRepository.saveAndFlush(e);
			//Ajout list de notes vide avec les etudiants 
			List<EtudiantDto> etudiantsParPromo = promotionService.getEtudiantsById(exDb.getPromotion().getId());
			Set<Note> Notes =  new HashSet<Note>();
			for (EtudiantDto etudiantDto : etudiantsParPromo) {
				Note note = new Note();
				note.setEtudiantNote(DtoTools.convert(etudiantDto, Etudiant.class));
				note.setNoteObtenue(0.0);
				note.setExamen(exDb);
				Notes.add(note);
			}
			exDb.setNotes(Notes);
			
			 return DtoTools.convert(examenRepository.saveAndFlush(exDb),ExamenDtoSave.class);
			
		}
		
		
	}

	//methode de suppression d'un examen
	@Override
	public void deleteById(long id) {
		examenRepository.deleteById(id);

	}

	

}
