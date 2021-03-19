package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.GroupeEtudiantDto;
import fr.dawan.AppliCFABack.dto.PromotionDto;

public interface EtudiantService {
	
	// ##################################################
	// # 					CRUD 						#
	// ##################################################

	List<EtudiantDto> getAll();
	EtudiantDto getById(long id);
	EtudiantDto saveOrUpdate(EtudiantDto e);
	void deleteById(long id);
	
	// ##################################################
	// # 			     1er Niveau 					#
	// ##################################################
	
	List<PromotionDto> getPromotionsByIdEtudiant(long id);
//	List<EntrepriseDto> getEntrepriseByIdEtudiant(long id);
//	List<PersonneDto> getPersonneByIdEtudiant(long id);
//	List<NoteDto> getNotesByIdEtudiant(long id);
	List<GroupeEtudiantDto> getGroupesByIdEtudiant(long id);
//	List<AbsenceDto> getAbsencesByIdEtudiant(long id);
	
	// ##################################################
	// # 			     2eme Niveau 					#
	// ##################################################
	
//	List<ProgrammeCoursDto> getProgrammeCoursByIdEtudiant(long id);
//	List<ProjetDto> getProjetByIdEtudiant(long id);
//	List<AdresseDto> getAdresseByIdEtudiant(long id);
	
	// ##################################################
	// # 			     3eme Niveau 					#
	// ##################################################
	
//	List<FormateurDto> getFormateursByIdEtudiant(long id);
//	List<DevoirDto> getDevoirsByIdEtudiant(long id);
//	List<ExamenDto> getExamensByIdEtudiant(long id);
}
