package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.PositionnementDto;

import java.util.List;

public interface PositionnementService extends GenericService<PositionnementDto> {

	List<PositionnementDto> getAllByPromotionId(long idPromotion);

	List<PositionnementDto> getAllByInterventionId(long idIntervention);

	PositionnementDto getByIdEtudiant(long etudiantId);
	

}
