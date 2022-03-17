package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.CompetenceProfessionnelleDto;

public interface CompetenceProfessionnelleService {
	List< CompetenceProfessionnelleDto> getAllCompetenceProfessionnelle();
	 CompetenceProfessionnelleDto getById(long id);
	 CompetenceProfessionnelleDto saveOrUpdate( CompetenceProfessionnelleDto cpDto);
	void deleteById(long id);
	
}
