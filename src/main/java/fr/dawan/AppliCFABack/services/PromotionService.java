package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.*;
import fr.dawan.AppliCFABack.dto.customdtos.PromotionEtudiantDto;
import fr.dawan.AppliCFABack.entities.Promotion;


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
	
	int fetchDGPromotion(String email, String password) throws Exception;
	
	List<Promotion> getPromotionDG2ByIdCursusDG2(String email, String password, long idCursis) throws Exception;
}
