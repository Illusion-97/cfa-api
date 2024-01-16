package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.PositionnementDto;
import fr.dawan.AppliCFABack.services.generic.GenericService;

import java.util.List;

public interface PositionnementService extends GenericService<PositionnementDto> {

	List<PositionnementDto> getAllByPromotionId(long idPromotion);

	List<PositionnementDto> getAllByInterventionId(long idIntervention);

	PositionnementDto getByIdEtudiant(long etudiantId);
	

}
