package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.NoteControleContinuDto;
import fr.dawan.AppliCFABack.dto.NoteDto;
import fr.dawan.AppliCFABack.dto.NoteDtoToSave;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.Examen;
import fr.dawan.AppliCFABack.entities.Note;
import fr.dawan.AppliCFABack.entities.Promotion;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.ExamenRepository;
import fr.dawan.AppliCFABack.repositories.NoteRepository;
import fr.dawan.AppliCFABack.repositories.PromotionRepository;

/***
 * 
 * @author Feres BG Valentin C Nicolas P.
 * @see fr.dawan.AppliCFABack.repositories.NoteRepository
 * @see fr.dawan.AppliCFABack.dto.NoteDto
 * @since 1.0
 * @version 1.0
 *
 */
@Service
@Transactional
public class NoteServiceImpl implements NoteService {

	@Autowired
	NoteRepository noteRepository;

	@Autowired
	PromotionRepository promotionRepository;
	@Autowired
	ExamenRepository examenRepository;

	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();

	/**
	 * Récupération de la liste des notes
	 * 
	 * @return lstDto Liste des objets note
	 */

	@Override
	public List<NoteDto> getAllNote() {
		List<Note> lst = noteRepository.findAll();

		List<NoteDto> lstDto = new ArrayList<NoteDto>();
		for (Note n : lst) {
			lstDto.add(mapper.NoteToNoteDto(n));
		}
		return lstDto;
	}

	/***
	 * Compte le nombre de Notes en fonction de l'élément de la recherche
	 * 
	 * @param search Élément de la recherche
	 * @return Count DTO
	 */

	@Override
	public CountDto count(String search) {
		return new CountDto(noteRepository
				.countByEtudiantNoteUtilisateurPrenomContainingIgnoringCaseOrEtudiantNoteUtilisateurNomContainingIgnoringCaseOrExamenTitreContainingIgnoringCase(
						search, search, search));
	}

	/**
	 * Récupération de la Note en fonction de son id
	 * 
	 * @param id Note
	 * @return Note DTO
	 * 
	 */

	@Override
	public NoteDto getById(long id) {
		Optional<Note> n = noteRepository.findById(id);
		if (!n.isPresent())
			return null;

		return DtoTools.convert(n.get(), NoteDto.class);
	}

	/*
	 * Sauvegarde ou mise à jour du DevoirEtudiant
	 * 
	 * @param Note DTO
	 * 
	 * @return Note DTO
	 */

	@Override
	public NoteDtoToSave saveOrUpdate(NoteDtoToSave nDto) {
		Note n = DtoTools.convert(nDto, Note.class);

		n = noteRepository.saveAndFlush(n);

		return DtoTools.convert(n, NoteDtoToSave.class);
	}

	/***
	 * Compte le nombre total des Notes
	 * 
	 * @return Count DTO
	 */

	@Override
	public CountDto count() {
		return new CountDto(noteRepository.count());
	}

	/**
	 * 
	 * 
	 * Suppression d'une Note
	 * 
	 * @param id Note
	 */

	@Override
	public void deleteById(long id) {
		noteRepository.deleteById(id);

	}

	/***
	 * Récupération des notes en fonction de l'id de l'étudiant
	 * 
	 * @param id Etudiant
	 * @return NoteDto Liste des objets NoteDto
	 */

	@Override
	public List<NoteDto> getAllByIdEtudiant(long id) {
		List<NoteDto> result = new ArrayList<NoteDto>();
		List<Note> list = noteRepository.findAllByEtudiantNoteId(id);
		for (Note n : list) {

			NoteDto nDto = DtoTools.convert(n, NoteDto.class);
			result.add(nDto);
		}
		return result;

	}

	@Override
	public List<NoteControleContinuDto> getNotesByIdEtudiant(long id)  {
		List<NoteControleContinuDto> result = new ArrayList<>();
		List<Note> list = noteRepository.findAllByEtudiantNoteId(id);
		for (Note n : list) {
			// result.add(_mapper.NoteToNoteControleContinuDto(n));
			result.add(DtoTools.convert(n, NoteControleContinuDto.class));
		}
		return result;

	}

	/***
	 * Récupération des Notes en fonction de l'id de l'examen
	 * 
	 * @param id Examen
	 * @return NoteDto Liste des objets NoteDto
	 */
	@Override
	public List<NoteDto> getAllByExamenId(long id) {
		List<NoteDto> result = new ArrayList<NoteDto>();
		List<Note> list = noteRepository.findAllByExamenId(id);
		for (Note note : list) {
			NoteDto nDto = DtoTools.convert(note, NoteDto.class);
			result.add(nDto);
		}
		return result;
	}

	/***
	 * Récupération des Notes en fonction de l'id de l'intervention
	 * 
	 * @param id intervention
	 * @return NoteDto Liste des objets NoteDto
	 */
	@Override
	public List<NoteDto> getAllByInterventionIdAndExamenId(long idIntervention, long idExamen, String search) {
		List<NoteDto> result = new ArrayList<>();

		List<Note> notes = noteRepository.findAllByExamenInterventionIdAndExamenId(idIntervention, idExamen,search);

		for (Note note : notes) {
			Optional<Promotion> promo = promotionRepository
					.getByEtudiantsIdAndInterventionsId(note.getEtudiantNote().getId(), idIntervention);

			String cdf = "Pas de Ville";
			if (promo.isPresent()) {

				cdf = promo.get().getCentreFormation().getNom();
			}
			NoteDto noteDto = DtoTools.convert(note, NoteDto.class);
			noteDto.setVille(cdf);
			result.add(noteDto);
		}
		return result;

	}

	/***
	 * Récupération des Notes en fonction de l'id de la promotion et l'id de
	 * l'examen
	 * 
	 * @param id promotion
	 * @return NoteDto Liste des objets NoteDto
	 */
	@Override
	public List<NoteDto> getAllByPromotionIdAndExamenId(long idPromotion, long idExamen) {
		// verification par exam

		Examen examen = examenRepository.getOne(idExamen);
		
		Promotion promotion =  examen.getPromotions().stream().filter(p ->p.getId() == idPromotion).findAny().get() ;
		
		List <Etudiant> etudiants = promotion.getEtudiants() ;
		
		List<Note> notes = noteRepository.findAllByExamenPromotionsIdAndExamenId(idPromotion, idExamen);
		
		String cdf = promotion.getCentreFormation().getNom();


		List<NoteDto> result = new ArrayList<>();
		
		
		for (Note note : notes) {
		for(Etudiant e : etudiants) {
			if (e.getId() == note.getEtudiantNote().getId()) {
				
				NoteDto noteDto = DtoTools.convert(note, NoteDto.class);
				noteDto.setVille(cdf);
				result.add(noteDto);			}
		}
			
		}
		return result;
	}

}
