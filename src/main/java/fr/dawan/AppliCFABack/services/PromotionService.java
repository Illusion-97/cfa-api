package fr.dawan.AppliCFABack.services;

import java.net.URISyntaxException;
import java.util.List;

import fr.dawan.AppliCFABack.dto.*;
import fr.dawan.AppliCFABack.dto.customdtos.PromotionEtudiantDto;
import fr.dawan.AppliCFABack.entities.Promotion;
import fr.dawan.AppliCFABack.tools.FetchDG2Exception;


public interface PromotionService {

	List<PromotionDto> getAll();

	PromotionDto getById(long id);

	PromotionDto saveOrUpdate(PromotionDto pDto);

	void deleteById(long id);

	UtilisateurDto getReferentById(long id);

	CountDto count(String string);

	List<PromotionDto> getAllPromotions(int page, int size, String string);

	List<EtudiantDto> getEtudiantsById(long id);

	List<PromotionDto> getAllByCursusId(long id);

	List<PromotionDto> getPromotionByEtudiantIdAndByCursusId(long id);

	/**
	 * Erreur méthodes controller-service-repo à refaire avec un dto custom pour l'accueil entier
	 */
	UtilisateurDto getCefById(long id);

	/**
	 * @param id de l'étudiant
	 * @return toutes les données nécessaires pour remplir la section Cursus dans le front partie étudiant, par le service implémenté
	 */
	List<PromotionEtudiantDto> getCursusByIdEtudiant(long id);
	
	List<PromotionForSelectDto> getPromotionByInterventionIdForSelect(long idIntervention);
	
	int fetchDGPromotions(String email, String password) throws FetchDG2Exception, URISyntaxException;
	int fetchDGPromotions(String email, String password, long idCursusDg2) throws FetchDG2Exception, URISyntaxException;

	
	List<Promotion> getPromotionDG2ByIdCursusDG2(String email, String password, long idCursus) throws FetchDG2Exception, URISyntaxException;

	String getGrillePositionnement(long idPromotion) throws Exception;
}
