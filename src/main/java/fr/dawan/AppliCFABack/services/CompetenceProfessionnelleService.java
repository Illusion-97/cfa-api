package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.CompetenceProfessionnelleDto;

import java.util.List;

public interface CompetenceProfessionnelleService {
	
	List< CompetenceProfessionnelleDto> getAllCompetenceProfessionnelle();
	
	CompetenceProfessionnelleDto getById(long id);
	
	CompetenceProfessionnelleDto saveOrUpdate( CompetenceProfessionnelleDto cpDto);
	
	void deleteById(long id);

	/**
	 * Récupération de toutes les absences en fonctions d'un EtudiantId
	 */
	List<CompetenceProfessionnelleDto> getAllByActiviteTypeId(long id);
	
}
