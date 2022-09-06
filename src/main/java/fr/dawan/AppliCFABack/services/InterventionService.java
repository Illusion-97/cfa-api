package fr.dawan.AppliCFABack.services;

import java.net.URISyntaxException;
import java.util.List;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.FormateurDto;
import fr.dawan.AppliCFABack.dto.InterventionDG2Dto;
import fr.dawan.AppliCFABack.dto.InterventionDto;
import fr.dawan.AppliCFABack.dto.PromotionDto;
import fr.dawan.AppliCFABack.entities.Intervention;
import fr.dawan.AppliCFABack.tools.FetchDG2Exception;

public interface InterventionService {

	List<InterventionDto> getAllIntervention();

//	List<InterventionDto> getAllInterventionWithObject();

	List<InterventionDto> getAllIntervention(int page, int size);

	List<InterventionDto> getAllByPage(int page, int size, String string);

	InterventionDto getById(long id);

	InterventionDto saveOrUpdate(InterventionDto iDto);

	void deleteById(long id);

	CountDto count(String string);

	List<EtudiantDto> findAllEtudiantsByPromotionInterventionsId(long id);

	List<PromotionDto> findPromotionsByInterventionId(long id);

	List<FormateurDto> findFormateursByInterventionsId(long id);

	List<Intervention> getInterventionDG2ByIdPromotionDG2(String email, String password, long idPrmotionDg2)
			throws FetchDG2Exception, URISyntaxException;
	int fetchDGInterventions(String email, String password) throws Exception;

	int fetchDGInterventions(String email, String password, long idPrmotionDg2) throws Exception;

}
