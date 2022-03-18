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
import fr.dawan.AppliCFABack.dto.ExamenDto;
import fr.dawan.AppliCFABack.dto.NoteDto;
import fr.dawan.AppliCFABack.entities.CompetenceProfessionnelle;
import fr.dawan.AppliCFABack.entities.Examen;
import fr.dawan.AppliCFABack.entities.Note;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.ExamenRepository;

@Service
@Transactional
public class ExamenServiceImpl implements ExamenService {

	@Autowired
	ExamenRepository examenRepository;

	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();

	//recuperation de la liste des examens
	@Override
	public List<ExamenDto> getAllExamen() {
		List<Examen> lst = examenRepository.findAll();
		List<ExamenDto> lstDto = new ArrayList<ExamenDto>();
		for (Examen e : lst) {
			ExamenDto eDto = mapper.ExamenToExamenDto(e);
			
			Set<CompetenceProfessionnelle> lstCp = e.getCompetenceProfessionnelle();
			Set<CompetenceProfessionnelleDto> lstCpDto = new HashSet<CompetenceProfessionnelleDto>();
			for (CompetenceProfessionnelle cp : lstCp) {
				if (cp != null)
					lstCpDto.add(mapper.CompetenceProfessionnelleDto(cp));
			}
			
			Set<Note> lstNotes = e.getNotes();
			Set<NoteDto> lstNoteDto = new HashSet<NoteDto>();
			for (Note note : lstNotes) {
				if(note != null) {
					NoteDto noteDto = mapper.NoteToNoteDto(note);
					
					noteDto.setPrenom(note.getEtudiantNote().);
//					noteDto.setPrenom(note.getEtudiantNote().getUtilisateur().getPrenom());
//					noteDto.setNom("chevallereau");
//					noteDto.setPrenom("valentin");
					lstNoteDto.add(noteDto);	
				}
	

			}
			
			eDto.setPromotionDto(mapper.PromotionToPromotionDto(e.getPromotion()));
// Le pb			eDto.setActiviteTypeDto(mapper.ActiviteTypeToActiviteDto(e.getActiviteType()));		
//			eDto.setCompetenceProfessionnelleDto(lstCpDto);
			eDto.setNotesDto(lstNoteDto);
// ************* AU DESSUS = A RAJOUTER *************
			
			//eDto.setCursusDto(mapper.CursusToCursusDto(e.getCursus()));
			//eDto.setFormationDto(mapper.FormationToFormationDto(e.getFormation()));
			
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
			
			eDto.setPromotionDto(mapper.PromotionToPromotionDto(e.getPromotion()));
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
			ExamenDto eDto = mapper.ExamenToExamenDto(e.get());

			eDto.setPromotionDto(mapper.PromotionToPromotionDto(e.get().getPromotion()));	
//			eDto.setCursusDto(mapper.CursusToCursusDto(e.get().getCursus()));
//			eDto.setFormationDto(mapper.FormationToFormationDto(e.get().getFormation()));
			
			return eDto;
		}		
		return null;

	}

	//methode d'ajout ou modification d'un examen
	@Override
	public ExamenDto saveOrUpdate(ExamenDto eDto) {
		Examen e = DtoTools.convert(eDto, Examen.class);
		examenRepository.saveAndFlush(e);
		return mapper.ExamenToExamenDto(e);
	}

	//methode de suppression d'un examen
	@Override
	public void deleteById(long id) {
		examenRepository.deleteById(id);

	}

	

}
