package fr.dawan.AppliCFABack.services;

import java.net.URISyntaxException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import fr.dawan.AppliCFABack.dto.AdresseDto;
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DevoirDto;
import fr.dawan.AppliCFABack.dto.EntrepriseDto;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.GroupeEtudiantDto;
import fr.dawan.AppliCFABack.dto.InterventionDto;
import fr.dawan.AppliCFABack.dto.JourneePlanningDto;
import fr.dawan.AppliCFABack.dto.NoteDto;
import fr.dawan.AppliCFABack.dto.PromotionDto;
import fr.dawan.AppliCFABack.dto.UtilisateurDto;
import fr.dawan.AppliCFABack.dto.customdtos.EtudiantAbsencesDevoirsDto;
import fr.dawan.AppliCFABack.dto.customdtos.EtudiantDossierDto;
import fr.dawan.AppliCFABack.tools.FetchDG2Exception;


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

	List<InterventionDto> getInterventionByIdEtudiant(long id);

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

    void fetchAllEtudiantDG2(String email, String password, long idPromotionDg2) throws FetchDG2Exception, JsonProcessingException, URISyntaxException;


}
