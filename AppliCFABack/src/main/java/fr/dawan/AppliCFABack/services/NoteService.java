package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.NoteDto;

public interface NoteService {

	List<NoteDto> getAllNote();

	List<NoteDto> getAllByPage(int page, int size, String string);

	CountDto count(String string);

	NoteDto getById(long id);

	CountDto count();
	
	NoteDto saveOrUpdate(NoteDto nDto);

	void deleteById(long id);

	List<NoteDto> getAllByIdEtudiant(long id);

	

}
