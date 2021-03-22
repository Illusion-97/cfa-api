package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.GroupeEtudiantDto;

public interface GroupeEtudiantService {

	List<GroupeEtudiantDto> getAllGroupeEtudiant();

	GroupeEtudiantDto getById(long id);

	GroupeEtudiantDto saveOrUpdate(GroupeEtudiantDto gDto);

	void deleteById(long id);

	List<GroupeEtudiantDto> getAllGroupeEtudiant(int page, int max);

}
