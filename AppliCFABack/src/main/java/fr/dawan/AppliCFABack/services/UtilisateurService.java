package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.UtilisateurDto;

public interface UtilisateurService {
	List<UtilisateurDto> getAll();

	List<UtilisateurDto> getAll(int page, int size);
}
