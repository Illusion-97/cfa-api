package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.FormateurDto;
import fr.dawan.AppliCFABack.dto.InterventionDto;
import fr.dawan.AppliCFABack.dto.JourneePlanningDto;
import fr.dawan.AppliCFABack.entities.Formateur;
import fr.dawan.AppliCFABack.entities.Utilisateur;

import java.util.List;

public interface FormateurService {
	List<FormateurDto> getAll();

	List<FormateurDto> getAllWithObject();

	List<FormateurDto> getAllByPage(int page, int size);

	List<FormateurDto> getAllByPageWithKeyword(int page, int size, String search);

	FormateurDto getById(long id);

	List<InterventionDto> getAllInterventionsByFormateurIdPerPage(long id, int page, int size);

	List<InterventionDto> getAllInterventionsByFormateurIdPerPageByKeyword(long id, int page, int size, String search);

	FormateurDto saveOrUpdate(FormateurDto fDto);
	
	List<JourneePlanningDto> getAllJourneePlanningByIdFormateur(long id);
	
	void deleteById(long id);

	CountDto countInterventionById(long id);

	CountDto countInterventionById(long id, String search);

	CountDto count(String search);
	
	Formateur saFormateur(Utilisateur utilisateur);
}
