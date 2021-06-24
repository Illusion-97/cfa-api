package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.AbsenceDto;
import fr.dawan.AppliCFABack.dto.CountDto;

public interface AbsenceService {

	List<AbsenceDto> getAllAbsence();

	List<AbsenceDto> getAllAbsence(int page, int size, String search);

	List<AbsenceDto> findAllByEtudiantPromotionsReferentPedagogiqueId(long idRef);

	AbsenceDto getById(long id);

	AbsenceDto saveOrUpdate(AbsenceDto aDto);

	void deleteById(long id);

	CountDto count(String search);

}
