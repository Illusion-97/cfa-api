package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.EtudiantDto;


public interface EtudiantService {

	List<EtudiantDto> getAll();
	EtudiantDto getById(long id);
	EtudiantDto saveOrUpdate(EtudiantDto e);
	void deleteById(long id);
}
