package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.*;
import fr.dawan.AppliCFABack.dto.customdtos.EtudiantAbsencesDevoirsDto;
import fr.dawan.AppliCFABack.dto.customdtos.EtudiantDossierDto;

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

	/**
	 * @param id de l'étudiant
	 * @return le formateur référent (tuteur) de l'étudiant et ses informations personnelles dans le service implémenté
	 */
	UtilisateurDto getFormateurReferentByIdEtudiant(long id);

//	UtilisateurDto getManagerByIdEtudiant(long id);

	List<DevoirDto> getDevoirsByIdEtudiant(long id, int page, int size);

//	List<AbsenceDto> getAbsencesByIdEtudiant(long id, int page, int size);

	List<EtudiantAbsencesDevoirsDto> getEtudiantsByInterventionId(long idIntervention ,String search);
	EtudiantDossierDto getByEtudiantIdForDossierPro(long id);
	EtudiantDossierDto saveOrUpdateEtudiantDossier(EtudiantDossierDto e);




}
