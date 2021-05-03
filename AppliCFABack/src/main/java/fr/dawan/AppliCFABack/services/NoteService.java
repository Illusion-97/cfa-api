package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.NoteDto;

public interface NoteService {

	List<NoteDto> getAllNote();

	List<NoteDto> getAllNote(int page, int size);

	NoteDto getById(long id);

	NoteDto saveOrUpdate(NoteDto nDto);

	void deleteById(long id);

}
