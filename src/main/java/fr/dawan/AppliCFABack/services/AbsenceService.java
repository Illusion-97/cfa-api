package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.AbsenceDto;

public interface AbsenceService extends GenericService<AbsenceDto>{

	
	List<AbsenceDto> getAllByInterventionId(long id);
	List<AbsenceDto> getAllByEtudiantId(long id);
	List<AbsenceDto> getAllByNomPrenomContaining(String search);
//	List<AbsenceDto> getAllByPromotionId(long id);
	
	
//	List<AbsenceDto> getAllAbsence();
//	List<AbsenceDto> getAllAbsence(int page, int size, String search);
//	AbsenceDto getById(long id);
//	AbsenceDto saveOrUpdate(AbsenceDto aDto);
//	void deleteById(long id);
//	CountDto count(String search);
//	List<AbsenceDto> getAllByIdEtudiant(long id);


}
