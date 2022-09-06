package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.PositionnementDto;

public interface PositionnementService extends GenericService<PositionnementDto> {

	List<PositionnementDto> getAllByPromotionId(long idPromotion);

	List<PositionnementDto> getAllByInterventionId(long idIntervention);
	

}
