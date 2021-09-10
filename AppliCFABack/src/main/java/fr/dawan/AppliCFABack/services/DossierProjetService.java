package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.DossierProjetDto;

public interface DossierProjetService {

	List<DossierProjetDto> getAll();

	DossierProjetDto getById(long id);

	List<DossierProjetDto> getAllByPage(int page, int size, String string);

	DossierProjetDto saveOrUpdate(DossierProjetDto dpDto);

	void deleteById(long id);
	

}
