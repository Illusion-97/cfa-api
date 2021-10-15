package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DevoirDto;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.FormateurDto;
import fr.dawan.AppliCFABack.dto.InterventionDto;
import fr.dawan.AppliCFABack.dto.PromotionDto;

public interface InterventionService {

	List<InterventionDto> getAllIntervention();

//	List<InterventionDto> getAllInterventionWithObject();

	List<InterventionDto> getAllIntervention(int page, int size);

	List<InterventionDto> getAllByPage(int page, int size, String string);

	InterventionDto getById(long id);

	InterventionDto saveOrUpdate(InterventionDto iDto);

	void deleteById(long id);

	CountDto count(String string);

	List<EtudiantDto> findAllByPromotionInterventionsId(long id);

	List<PromotionDto> findPromotionsByInterventionId(long id);

	List<DevoirDto> findDevoirsByInterventionId(long id);

	List<FormateurDto> findFormateursByInterventionsId(long id);

}
