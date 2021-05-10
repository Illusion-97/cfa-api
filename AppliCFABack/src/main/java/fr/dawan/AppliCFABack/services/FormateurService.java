package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.FormateurDto;

public interface FormateurService {
	List<FormateurDto> getAll();

	List<FormateurDto> getAllWithObject();

	List<FormateurDto> getAllByPage(int page, int size);

	FormateurDto getById(long id);

	FormateurDto getByIdWithObject(long id);

	FormateurDto saveOrUpdate(FormateurDto fDto);

	void deleteById(long id);
}
