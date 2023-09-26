package fr.dawan.AppliCFABack.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.dawan.AppliCFABack.dto.*;
import fr.dawan.AppliCFABack.dto.customdtos.AccueilEtudiantDto;
import fr.dawan.AppliCFABack.dto.customdtos.EtudiantAbsencesDevoirsDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprofessionnel.EtudiantDossierDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprojet.EtudiantDossierProjetDto;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.Utilisateur;
import fr.dawan.AppliCFABack.tools.FetchDG2Exception;

import java.net.URISyntaxException;
import java.util.List;


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


	List<ProjetDto> getProjetByIdEtudiant(long id);

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



	List<DevoirDto> getDevoirsByIdEtudiant(long id, int page, int size);


	List<EtudiantAbsencesDevoirsDto> getEtudiantsByInterventionId(long idIntervention ,String search);
	EtudiantDossierDto getByEtudiantIdForDossierPro(long id);
	EtudiantDossierDto saveOrUpdateEtudiantDossier(EtudiantDossierDto e);

    void fetchAllEtudiantDG2(String email, String password) throws Exception;

	void fetchAllEtudiantDG2ByIdPromotion(String email, String password, long idPromotionDg2)
			throws FetchDG2Exception, JsonProcessingException, URISyntaxException, Exception;


	AccueilEtudiantDto getAccueilEtudiant(long id);

    EtudiantDossierProjetDto getByEtudiantIdForDossierProjet(long id);
	
	List<EtudiantDto> getEtudiantByIdTuteurBySearch(long id, int page, int size, String search);
	
	List<EtudiantDto> getEtudiantByPromotion(long id, int page, int size, String search);
	
	CountDto countEtudiantByPromotion(long id, String search);
	
	Etudiant savEtudiant(Utilisateur utilisateur);
}
