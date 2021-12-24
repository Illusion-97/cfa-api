package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.ContratDto;

public interface ContratService {

	List<ContratDto> getAllContrat();

	ContratDto getById(long id);

	List<ContratDto> getAllByPage(int page, int size, String search);

	ContratDto saveOrUpdate(ContratDto cDto);

	void deleteById(long id);

	ContratDto count(String string);

	ContratDto getByEtudiantId(long id);



}
