package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.EntrepriseDto;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.NoteDto;
import fr.dawan.AppliCFABack.dto.PersonneDto;
import fr.dawan.AppliCFABack.dto.PromotionDto;


public interface EtudiantService {

	List<EtudiantDto> getAll();
	EtudiantDto getById(long id);
	EtudiantDto saveOrUpdate(EtudiantDto e);
	void deleteById(long id);
	
	// ##################################################
	// # 			     1er Niveau 					#
	// ##################################################
	
	List<PromotionDto> getPromotionsByIdEtudiant(long id);
	List<EntrepriseDto> getEntrepriseByIdEtudiant(long id);
	List<PersonneDto> getPersonneByIdEtudiant(long id);
	List<NoteDto> getNotesByIdEtudiant(long id);
	List<PromotionDto> getGroupesByIdEtudiant(long id);
	List<PromotionDto> getAbsencesByIdEtudiant(long id);
	
	// ##################################################
	// # 			     2eme Niveau 					#
	// ##################################################
}
