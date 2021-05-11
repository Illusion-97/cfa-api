package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.AdresseDto;
import fr.dawan.AppliCFABack.dto.CongeDto;
import fr.dawan.AppliCFABack.dto.JourneePlanningDto;
import fr.dawan.AppliCFABack.dto.UtilisateurDto;

public interface UtilisateurService {
	List<UtilisateurDto> getAll();

	List<UtilisateurDto> getAll(int page, int size);

	List<UtilisateurDto> getAllWithObject();

	UtilisateurDto getById(long id);

	UtilisateurDto getName(String name);

	UtilisateurDto insertUpdate(UtilisateurDto uDto);

	void deleteById(long id);

	List<UtilisateurDto> findByAdresse(String ville);

	List<UtilisateurDto> findByEntreprise(long idEntreprise);

	List<JourneePlanningDto> getAllJourneePlanningByIdUtilisateur(long id);

	List<CongeDto> getAllCongesByIdUtilisateur(long id);

	AdresseDto getAdresseByIdUtilisateur(long id);
}
