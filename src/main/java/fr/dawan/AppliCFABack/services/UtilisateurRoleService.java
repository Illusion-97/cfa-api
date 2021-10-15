package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.UtilisateurRoleDto;

public interface UtilisateurRoleService {

	List<UtilisateurRoleDto> getAllUtilisateurRole();
	
	List<UtilisateurRoleDto> getAllByPage(int page, int size, String string);

	CountDto count(String string);

	UtilisateurRoleDto getById(long id);

	UtilisateurRoleDto saveOrUpdate(UtilisateurRoleDto uDto);

	void deleteById(long id);

}
