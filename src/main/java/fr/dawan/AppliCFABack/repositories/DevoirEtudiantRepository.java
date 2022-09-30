package fr.dawan.AppliCFABack.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.dawan.AppliCFABack.entities.DevoirEtudiant;
/**
 * @author Feres BG, Valentin C.
 * @see fr.dawan.appliCFABack.service
 * @since 1.0
 * @version 1.0
 * @return Repository de l'entity DevoirEtudiant
 */
public interface DevoirEtudiantRepository extends JpaRepository<DevoirEtudiant, Long> {

	/***
	 * 
	 * @param id : identifiant de l'etudiant
	 * @return list des devoirsEtudiant par etudiant
	 */
	List<DevoirEtudiant> getAllByEtudiantId(long id);
	
	/***
	 * 
	 * @param id : identifiant du devoir
	 * @return list des devoirsEtudiant par devoir
	 */
	List<DevoirEtudiant> getAllByDevoirId(long id);
	/**
	 * 
	 * @param search hamps de recherche par nom ou prénom des étudiants
	 * @return le nombre d'occurence de la recherche
	 */
	long countByEtudiantUtilisateurNomContainingOrEtudiantUtilisateurPrenomContaining(String nom,String prenom);
	
	/***
	 * 
	 * @param interventionId : identifiant de l'intervention
	 * @param etudiantId : identifiant de l'etudiant 
	 * @return  list des devoirsEtudiant par intervention et etudiant
	 */
	@Query("SELECT DISTINCT de FROM DevoirEtudiant de JOIN de.devoir d ON d.intervention.id = :interventionId JOIN de.etudiant e  ON e.id = :etudiantId")
	List<DevoirEtudiant> getAllDevoirsEtudiantByInterventionId(long interventionId, long etudiantId);
	
}
