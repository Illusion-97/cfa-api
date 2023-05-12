package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.ProjetDto;

import java.util.List;

public interface ProjetService {

	List<ProjetDto> getAllProjet();

	ProjetDto getById(long id);
	
	List<ProjetDto> getAllByPage(int page, int size, String string);

	CountDto count(String string);

	ProjetDto saveOrUpdate(ProjetDto pDto);

	void deleteById(long id);
	
	List<ProjetDto> getByGroupeId(long id);

	
}
