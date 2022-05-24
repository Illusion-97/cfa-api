package fr.dawan.AppliCFABack.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Etudiant;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
	Page<Etudiant> findDistinctAllByUtilisateurPrenomContainingIgnoringCaseOrUtilisateurNomContainingOrPromotionsNomContainingOrGroupesNomContaining(
			String prenom, String nom, String promotionsNom, String groupesNom, Pageable p);

	long countDistinctByUtilisateurPrenomContainingIgnoringCaseOrUtilisateurNomContainingOrPromotionsNomContainingOrGroupesNomContaining(
			String prenom, String nom, String promotionsNom, String groupesNom);

	Page<Etudiant> findAllByUtilisateurPrenomContainingIgnoringCaseOrUtilisateurNomContainingIgnoringCaseOrUtilisateurLoginContainingIgnoringCase(
			String search, String search2, String search3, Pageable pageable);

	long countByUtilisateurPrenomContainingIgnoringCaseOrUtilisateurNomContainingIgnoringCaseOrUtilisateurLoginContainingIgnoringCase(
			String search, String search2, String search3);

	@Query("SELECT DISTINCT e FROM Etudiant e JOIN e.promotions p JOIN p.interventions i ON i.id = :id JOIN e.utilisateur u WHERE u.nom LIKE %:search% OR u.prenom  LIKE %:search% ")
	List<Etudiant> findAllDistinctByPromotionsInterventionsId(long id, String search);

	List<Etudiant> findAllDistinctByPromotionsInterventionsId(long id);
}
