package fr.dawan.AppliCFABack.services;

import java.util.List;
import java.util.Optional;

import fr.dawan.AppliCFABack.dto.AdresseDto;
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DevoirDto;
import fr.dawan.AppliCFABack.dto.EntrepriseDto;
import fr.dawan.AppliCFABack.dto.EtudiantAbsencesDevoirsDto;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.ExamenDto;
import fr.dawan.AppliCFABack.dto.GroupeEtudiantDto;
import fr.dawan.AppliCFABack.dto.InterventionDto;
import fr.dawan.AppliCFABack.dto.JourneePlanningDto;
import fr.dawan.AppliCFABack.dto.NoteDto;
import fr.dawan.AppliCFABack.dto.PromotionDto;

public interface EtudiantService {

	// ##################################################
	// # CRUD #
	// ##################################################

	List<EtudiantDto> getAll();

	List<EtudiantDto> getAllByPage(int page, int size, String search);

	CountDto count(String search);

	EtudiantDto getById(long id);

	EtudiantDto saveOrUpdate(EtudiantDto e);

	void deleteById(long id);
	// ##################################################
	// # 1er Niveau #
	// ##################################################

	List<PromotionDto> getPromotionsByIdEtudiant(long id);

	List<GroupeEtudiantDto> getGroupesByIdEtudiant(long id);

	EntrepriseDto getEntrepriseByIdEtudiant(long id);

	AdresseDto getAdresseByIdEtudiant(long id);

	// ##################################################
	// # 2eme Niveau #
	// ##################################################

	List<NoteDto> getNotesByIdEtudiant(long id, int page, int size);

	// ##################################################
	// # 3eme Niveau #
	// ##################################################

	List<InterventionDto> getIntervenionByIdEtudiant(long id);

	// ##################################################
	// # Utile #
	// ##################################################

	List<JourneePlanningDto> getAllJourneePlanningByIdEtudiant(long id);

//	UtilisateurDto getFormateurReferentByIdEtudiant(long id);

//	UtilisateurDto getManagerByIdEtudiant(long id);

	List<DevoirDto> getDevoirsByIdEtudiant(long id, int page, int size);

//	List<AbsenceDto> getAbsencesByIdEtudiant(long id, int page, int size);

	List<EtudiantAbsencesDevoirsDto> getEtudiantsByInterventionId(long idIntervention ,String search);

}
