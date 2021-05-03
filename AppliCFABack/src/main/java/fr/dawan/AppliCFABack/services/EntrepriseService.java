package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.EntrepriseDto;

public interface EntrepriseService {

	List<EntrepriseDto> getAllEntreprise();

	List<EntrepriseDto> getAllEntreprise(int page, int size);

	EntrepriseDto getById(long id);

	EntrepriseDto saveOrUpdate(EntrepriseDto eDto);

	void deleteById(long id);

}
