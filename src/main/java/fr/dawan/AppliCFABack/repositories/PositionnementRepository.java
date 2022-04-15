package fr.dawan.AppliCFABack.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.dawan.AppliCFABack.entities.Positionnement;

public interface PositionnementRepository  extends JpaRepository<Positionnement, Long>{
	
	@Query("SELECT p FROM Positionnement p JOIN p.etudiant e JOIN e.promotions promo On promo.id = : idPromotion")
	List<Positionnement> getAllByPromotionId(long idPromotion);

	List<Positionnement> getAllByInterventionId(long idIntervention);
	
	long countByEtudiantUtilisateurNomContainingOrEtudiantUtilisateurPrenomContaining(String nom,String prenom);

}
