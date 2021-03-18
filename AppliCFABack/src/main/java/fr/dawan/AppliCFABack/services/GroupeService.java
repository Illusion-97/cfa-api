package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.GroupeDto;

public interface GroupeService {

	List<GroupeDto> getAllGroupe();

	GroupeDto getById(long id);

	GroupeDto saveOrUpdate(GroupeDto g);

	void deleteById(long id);

}
