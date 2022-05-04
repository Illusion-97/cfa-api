package fr.dawan.AppliCFABack.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.dawan.AppliCFABack.entities.Positionnement;
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

}
