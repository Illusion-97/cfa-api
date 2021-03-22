package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.ProjetDto;

public interface ProjetService {

	List<ProjetDto> getAllProjet();

	ProjetDto getById(long id);

	ProjetDto saveOrUpdate(ProjetDto pDto);

	void deleteById(long id);

	List<ProjetDto> getAllProjet(int page, int size);

}
