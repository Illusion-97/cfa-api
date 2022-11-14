package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.CerfaDto;
import fr.dawan.AppliCFABack.dto.CountDto;

public interface CerfaService {

	CountDto count(String string);

	List<CerfaDto> getAllByPage(int page, int size, String string);

	CerfaDto getById(long id);

	List<CerfaDto> getAll();

	void deleteById(long id);

	CerfaDto saveOrUpdate(CerfaDto cDto);

	CerfaDto getByIdEtudiant(long id);

}
