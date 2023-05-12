package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.UtilisateurRoleDto;
import fr.dawan.AppliCFABack.entities.UtilisateurRole;

import java.util.List;

public interface UtilisateurRoleService {

    List<UtilisateurRoleDto> getAllUtilisateurRole();

    List<UtilisateurRoleDto> getAllByPage(int page, int size, String string);

    CountDto count(String string);

    UtilisateurRoleDto getById(long id);

    UtilisateurRoleDto saveOrUpdate(UtilisateurRoleDto uDto);

    UtilisateurRoleDto findByIntitule(String intitule);

    UtilisateurRole findByIntituleBis(String intitule);

    void deleteById(long id);

}
