package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.GroupeEtudiantDto;

public interface GroupeEtudiantService {

	List<GroupeEtudiantDto> getAllGroupeEtudiant();

	GroupeEtudiantDto getById(long id);
	
	List<GroupeEtudiantDto> getAllByPage(int page, int size, String string);

	CountDto count(String string);

	GroupeEtudiantDto saveOrUpdate(GroupeEtudiantDto gDto);

	void deleteById(long id);

	List<EtudiantDto> getEtudiantsByGroupeId(long id);

	

}
