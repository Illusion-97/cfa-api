package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.CursusDto;

public interface CursusService {

	List<CursusDto> getAll();

	CursusDto saveOrUpdate(CursusDto pDto);

	void deleteById(long id);

	CursusDto getById(long id);

	List<CursusDto> getAllByPage(int page, int size, String search);

	CountDto count(String string);

}
