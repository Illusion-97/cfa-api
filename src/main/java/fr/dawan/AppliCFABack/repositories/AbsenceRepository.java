package fr.dawan.AppliCFABack.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Absence;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long> {

	@Query("SELECT a FROM Absence a WHERE a.etudiant.id = :id")
	List<Absence> getByEtudiantId(@Param("id") long id);
	
	@Query("FROM Absence a WHERE a.intervention.id = :id ")
	List<Absence> findAllByInterventionId(@Param("id") long id);
	
	@Query("FROM Absence a WHERE a.etudiant.id = :id")
	List<Absence> findAllByEtudiantId(long id);
	
//	@Query("SELECT Absence a JOIN a.etudiant et JOIN et.promotion p WHERE p.id = :id")
//	List<Absence> findAllByPromotionId(long id);
	
	@Query("FROM Absence a JOIN a.etudiant e JOIN e.utilisateur u WHERE u.nom LIKE :search OR u.prenom LIKE :search")
	List<Absence> findByEtudiantUtilisateurNomContainingOrEtudiantUtilisateurPrenomContainingAllIgnoreCase(String search);

	@Query("SELECT COUNT(a) FROM Absence a JOIN a.etudiant e JOIN e.utilisateur u WHERE u.nom LIKE :search OR u.prenom LIKE :search")
	long countByUtilisateurEtudiantNomOrPrenomContaining(String search);
	
	
	
//	long countByEtudiantUtilisateurNomContainingOrEtudiantUtilisateurPrenomContainingAllIgnoreCase(String prenom, String nom);

//	List<Absence> findByEtudiantPromotionsId(long id);
	

//	List<Absence> findDistinctByEtudiantPromotionsReferentPedagogiqueId(long id);
	

//	long countDistinctByEtudiantPromotionsReferentPedagogiqueId(long id);

//	@Query(value = "SELECT abs.id, abs.dateDebut, abs.dateFin, promo.referentPedagogique, etud.id FROM Absence abs JOIN abs.etudiant etud JOIN etud.promotions promo WHERE promo.referentPedagogique.id =:id ")
//	List<Absence> findByRefId(@Param("id") long id);
}
