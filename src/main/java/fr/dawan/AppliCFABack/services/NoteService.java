package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.NoteControleContinuDto;
import fr.dawan.AppliCFABack.dto.NoteDto;
import fr.dawan.AppliCFABack.dto.NoteDtoToSave;

public interface NoteService {

	List<NoteDto> getAllNote();

	CountDto count(String string);

	NoteDto getById(long id);

	CountDto count();
	
	NoteDtoToSave saveOrUpdate(NoteDtoToSave nDto);

	void deleteById(long id);

	List<NoteDto> getAllByIdEtudiant(long id);
	
	List<NoteDto> getAllByExamenId(long id);

	/**
	 * @param id de l'étudiant
	 * @return toutes les données nécessaires pour remplir la section Contrôles Continus dans le front partie étudiant, par le service implémenté
	 */
	List<NoteControleContinuDto> getNotesByIdEtudiant(long id);
	

}
