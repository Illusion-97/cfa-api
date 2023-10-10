package fr.dawan.AppliCFABack.repositories;

import fr.dawan.AppliCFABack.entities.Positionnement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
/**
 * @author Valentin C, Feres BG.
 * @see fr.dawan.appliCFABack.service
 * @since 1.0
 * @version 1.0
 * @return Repository de l'entity Positionnement
 */
public interface PositionnementRepository  extends JpaRepository<Positionnement, Long>{
	/**
	 * 
	 * @param id de la promotion recherchée
	 * @return tous les positionnements d'une promotion recherchée
	 */
	@Query("SELECT p FROM Positionnement p JOIN p.etudiant e JOIN e.promotions promo WHERE promo.id = :idPromotion")
	List<Positionnement> getAllByPromotionId(long idPromotion);
	/**
	 * 
	 * @param id de l'étudiant recherchée 
	 * @return le positionnement de l'étudiant recherchée
	 */
	@Query("SELECT p FROM Positionnement p JOIN p.etudiant e WHERE e.id = :id")
	Positionnement getByIdEtudiant(long id);
	/**
	 * 
	 * @param id de l'intervention recherchée
	 * @return tous les positionnements d'une interventions donnée
	 */
	List<Positionnement> getAllByInterventionId(long idIntervention);
	/**
	 * 
	 * @param nom champs de recherche
	 * @param prenom champs de recherche
	 * @return retourne le nombre d'occurence du champs recherché
	 */
	long countByEtudiantUtilisateurNomContainingOrEtudiantUtilisateurPrenomContaining(String nom,String prenom);
	
	/***
	 * 
	 * @param idIntervention : identifiant de l'intervention 
	 * @param idEtudiant : identifiant de l'etudaint
	 * @return retourne un possitionnement s'il existe.
	 */
	 Optional<Positionnement> getDistinctByInterventionIdAndEtudiantId(long idIntervention,long idEtudiant );
}
