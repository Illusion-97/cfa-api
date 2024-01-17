package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.*;
import fr.dawan.AppliCFABack.entities.Intervention;
import fr.dawan.AppliCFABack.tools.FetchDG2Exception;

import java.net.URISyntaxException;
import java.util.List;

public interface InterventionService {

	List<InterventionDto> getAllIntervention();

	List<InterventionDto> getAllIntervention(int page, int size);

	List<InterventionDto> getAllByPage(int page, int size,String sort, String string);

	InterventionDto getById(long id);

	InterventionDto saveOrUpdate(InterventionDto iDto);

	void deleteById(long id);

	CountDto count(String string);

	List<EtudiantDto> findAllEtudiantsByPromotionInterventionsId(long id);

	List<PromotionDto> findPromotionsByInterventionId(long id);

	List<FormateurDto> findFormateursByInterventionsId(long id);

	List<InterventionDto> findInterventionByPromotionId(long id, int page, int size, String search);

	CountDto countInterventionByPromotionId(long id, String search);

	List<InterventionDto> findAllByFormateurId(long formateurId);

	void deleteLstIntervention(List<InterventionDto> interventionDtos);
}
