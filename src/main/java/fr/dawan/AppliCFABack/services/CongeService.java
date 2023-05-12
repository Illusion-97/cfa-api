package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.CongeDto;
import fr.dawan.AppliCFABack.dto.CountDto;

import java.util.List;

public interface CongeService {

	List<CongeDto> getAllConge();

	CongeDto getById(long id);

	List<CongeDto> getAllByPage(int page, int size, String search);

	CongeDto saveOrUpdate(CongeDto cDto);

	void deleteById(long id);

	double[] getAcquisDisponiblesRestantsByIdUtilisateur(long id);

	CountDto count(String string);

	List<CongeDto> getAllByIdUtilisateur(long id);

}
