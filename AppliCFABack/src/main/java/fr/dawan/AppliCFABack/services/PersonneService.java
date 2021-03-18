package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.PersonneDto;

public interface PersonneService {
	List<PersonneDto> getAll();

	List<PersonneDto> getAll(int page, int size);

	PersonneDto findById(Long id);

	PersonneDto findByName(String name);

	List<PersonneDto> findByFormateurId (long id);

	List<PersonneDto> findByAdresse(long idAddr);

	List<PersonneDto> findByStudent(long idStud);

	PersonneDto findByCEF(long idCEF);

	List<PersonneDto> findByReferent(long idRef);

	PersonneDto insertUpdate(PersonneDto persDto);

	void deleteById(long id);
}
