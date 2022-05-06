package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import fr.dawan.AppliCFABack.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.NoteDto;
import fr.dawan.AppliCFABack.dto.NoteDtoToSave;
import fr.dawan.AppliCFABack.entities.Note;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.NoteRepository;
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
	private DtoMapper mapper = new DtoMapperImpl();

	@Autowired
	private DtoTools _mapper;

	/**
	 * Récupération de la liste des notes
	 * 
	 * @return lstDto	Liste des objets note
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
	 * @param search Élément de la recherche
	 * @return Count DTO
	 */
	

	@Override
	public CountDto count(String search) {
		return new CountDto(noteRepository.countByEtudiantNoteUtilisateurPrenomContainingIgnoringCaseOrEtudiantNoteUtilisateurNomContainingIgnoringCaseOrExamenTitreContainingIgnoringCase(search, search, search));
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
	 * @return Count DTO
	 */

	@Override
	public CountDto count() {
		return new CountDto(noteRepository.count());
	}

	/**


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

	/***
	 * Récupération des Notes en fonction de l'id de l'examen
	 * 
	 * @param id Examen
	 * @return NoteDto Liste des objets NoteDto
	 */

	@Override
	public List<NoteControleContinuDto> getNotesByIdEtudiant(long id) throws Exception {
		List<NoteControleContinuDto> result = new ArrayList<>();
		List<Note> list = noteRepository.findAllByEtudiantNoteId(id);
		for(Note n : list) {
			//result.add(_mapper.NoteToNoteControleContinuDto(n));
			result.add(DtoTools.convert(n, NoteControleContinuDto.class));
		}
		return result;

	}

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

}
