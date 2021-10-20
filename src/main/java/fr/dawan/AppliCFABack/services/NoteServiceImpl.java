package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.NoteDto;
import fr.dawan.AppliCFABack.entities.Note;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.NoteRepository;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {

	@Autowired
	NoteRepository noteRepository;

	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();

	@Override
	public List<NoteDto> getAllNote() {
		List<Note> lst = noteRepository.findAll();

		List<NoteDto> lstDto = new ArrayList<NoteDto>();
		for (Note n : lst) {
			lstDto.add(mapper.NoteToNoteDto(n));
		}
		return lstDto;
	}

	@Override
	public List<NoteDto> getAllByPage(int page, int size, String search) {
		List<Note> lst = noteRepository.findAllByEtudiantUtilisateurPrenomContainingIgnoringCaseOrEtudiantUtilisateurNomContainingIgnoringCaseOrExamenExamenEnonceContainingIgnoringCaseOrDevoirEnonceContainingIgnoringCase(search,search, search, search, PageRequest.of(page, size)).get().collect(Collectors.toList());

		// conversion vers Dto
		List<NoteDto> lstDto = new ArrayList<NoteDto>();
		for (Note n : lst) {
			NoteDto nDto = mapper.NoteToNoteDto(n);
			nDto.setDevoirDto(mapper.DevoirToDevoirDto(n.getDevoir()));
			nDto.setEtudiantDto(mapper.EtudiantToEtudiantDto(n.getEtudiant()));
			nDto.setExamenDto(mapper.PassageExamenToPassageExamenDto(n.getExamen()));
			lstDto.add(nDto);
		}
		return lstDto;
	}

	@Override
	public CountDto count(String search) {
		return new CountDto(noteRepository.countByEtudiantUtilisateurPrenomContainingIgnoringCaseOrEtudiantUtilisateurNomContainingIgnoringCaseOrExamenExamenEnonceContainingIgnoringCaseOrDevoirEnonceContainingIgnoringCase(search, search, search, search));
	}

	@Override
	public NoteDto getById(long id) {
		Optional<Note> n = noteRepository.findById(id);
		if (!n.isPresent())
			return null;
		
		NoteDto nDto = mapper.NoteToNoteDto(n.get());
		nDto.setDevoirDto(mapper.DevoirToDevoirDto(n.get().getDevoir()));
		nDto.setEtudiantDto(mapper.EtudiantToEtudiantDto(n.get().getEtudiant()));
		nDto.getEtudiantDto().setUtilisateurDto(mapper.UtilisateurToUtilisateurDto(n.get().getEtudiant().getUtilisateur()));
		nDto.setExamenDto(mapper.PassageExamenToPassageExamenDto(n.get().getExamen()));
		if(n.get().getExamen() != null) nDto.getExamenDto().setExamenDto(mapper.ExamenToExamenDto(n.get().getExamen().getExamen()));
		return nDto;
	}

	@Override
	public NoteDto saveOrUpdate(NoteDto nDto) {
		Note n = DtoTools.convert(nDto, Note.class);

		n = noteRepository.saveAndFlush(n);

		return mapper.NoteToNoteDto(n);
	}

	@Override
	public CountDto count() {
		return new CountDto(noteRepository.count());
	}
	@Override
	public void deleteById(long id) {
		noteRepository.deleteById(id);

	}

	@Override
	public List<NoteDto> getAllByIdEtudiant(long id) {
		List<NoteDto> result = new ArrayList<NoteDto>();
		List<Note> list = noteRepository.findAllByEtudiantId(id);
		for(Note n : list) {
			NoteDto nDto = mapper.NoteToNoteDto(n);
			nDto.setDevoirDto(mapper.DevoirToDevoirDto(n.getDevoir()));
			nDto.setExamenDto(mapper.PassageExamenToPassageExamenDto(n.getExamen()));
			if(n.getExamen() != null) nDto.getExamenDto().setExamenDto(mapper.ExamenToExamenDto(n.getExamen().getExamen()));
			result.add(nDto);
		}
		return result;
	}

}
