package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.FichePosteDto;

public interface FichePosteService {

	List<FichePosteDto> getAllFichePoste();
	
	List<FichePosteDto> getAllByPage(int page, int size, String string);

	CountDto count(String string);

	FichePosteDto getById(long id);

	FichePosteDto saveOrUpdate(FichePosteDto fDto);

	void deleteById(long id);

	FichePosteDto getByIdEtudiant(long id);
}
