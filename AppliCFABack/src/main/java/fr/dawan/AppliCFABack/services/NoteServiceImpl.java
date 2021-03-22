package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.NoteDto;
import fr.dawan.AppliCFABack.entities.Note;
import fr.dawan.AppliCFABack.repositories.NoteRepository;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {

	@Autowired
	NoteRepository noteRepository;

	@Override
	public List<NoteDto> getAllNote() {
		List<Note> lst = noteRepository.findAll();

		List<NoteDto> lstDto = new ArrayList<NoteDto>();
		for (Note n : lst) {
			lstDto.add(DtoTools.convert(n, NoteDto.class));
		}
		return null;
	}

	@Override
	public List<NoteDto> getAllNote(int page, int size) {
		List<Note> lst = noteRepository.findAll(PageRequest.of(page, size)).get().collect(Collectors.toList());

		// conversion vers Dto
		List<NoteDto> lstDto = new ArrayList<NoteDto>();
		for (Note n : lst) {
			lstDto.add(DtoTools.convert(n, NoteDto.class));
		}
		return lstDto;
	}

	@Override
	public NoteDto getById(long id) {
		Optional<Note> n = noteRepository.findById(id);
		if (n.isPresent())
			return DtoTools.convert(n.get(), NoteDto.class);

		return null;
	}

	@Override
	public NoteDto saveOrUpdate(NoteDto nDto) {
		Note n = DtoTools.convert(nDto, Note.class);

		n = noteRepository.saveAndFlush(n);

		return DtoTools.convert(n, NoteDto.class);
	}

	@Override
	public void deleteById(long id) {
		noteRepository.deleteById(id);

	}

}
