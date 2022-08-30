package fr.dawan.AppliCFABack.services;

import java.util.List;
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.FormationDto;
import fr.dawan.AppliCFABack.dto.InterventionDto;
import fr.dawan.AppliCFABack.entities.Formation;

public interface FormationService {

	List<FormationDto> getAllFormation();

	List<FormationDto> getAllByPage(int page, int size, String string);

	CountDto count(String string);

	FormationDto getById(long id);

	FormationDto saveOrUpdate(FormationDto fDto);

	void deleteById(long id);

	List<InterventionDto> findAllByFormationId(long id);



	int fetchDG2Formations(String email, String password, long idCursusDg2) throws Exception;
	int fetchDG2Formations(String email, String password) throws Exception;
	List<Formation> getFormationDG2ByIdCursus(String email, String password, long idCursusDg2 ) throws Exception;


}
