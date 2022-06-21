package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.PositionnementDto;
import fr.dawan.AppliCFABack.dto.customdtos.PositionnementDtoToSave;

public interface PositionnementService extends GenericService<PositionnementDto> {

	List<PositionnementDto> getAllByPromotionId(long idPromotion);

	List<PositionnementDto> getAllByInterventionId(long idIntervention);
	

}
