package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.AbsenceDto;
import fr.dawan.AppliCFABack.services.generic.GenericService;
import fr.dawan.AppliCFABack.tools.JustificatifException;

import java.util.List;
/**
 * @author Valentin C, Feres BG.
 * @see fr.dawan.appliCFABack.repository
 * @since 1.0
 * @version 1.0
 * @return Repository de l'entity Absence
 */
public interface AbsenceService extends GenericService<AbsenceDto> {

	
	List<AbsenceDto> getAllByInterventionId(long id);
	List<AbsenceDto> getAllByEtudiantId(long id);
	List<AbsenceDto> getAllByNomPrenomContaining(String search);
	String getJustificatifByAbsenceId(long idAbsence) throws JustificatifException;
//	List<AbsenceDto> getAllByPromotionId(long id);
	//
	
//	List<AbsenceDto> getAllAbsence();
//	List<AbsenceDto> getAllAbsence(int page, int size, String search);
//	AbsenceDto getById(long id);
//	AbsenceDto saveOrUpdate(AbsenceDto aDto);
//	void deleteById(long id);
//	CountDto count(String search);
//	List<AbsenceDto> getAllByIdEtudiant(long id);


}
