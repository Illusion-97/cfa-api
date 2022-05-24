package fr.dawan.AppliCFABack.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Examen;

/**
 * @author Valentin C, Feres BG.
 * @see fr.dawan.appliCFABack.service
 * @since 1.0
 * @version 1.0
 * @return Repository de l'entity Examen
 */
@Repository
public interface ExamenRepository extends JpaRepository<Examen, Long> {
	/**
	 * 
	 * @param titre      à rechercher
	 * @param descriptif à recherher
	 * @param pageable   pagination
	 * @return tous les examens dont le titre et la description contiennent le
	 *         champs recherché
	 */
	Page<Examen> findAllByTitreContainingIgnoringCaseOrDescriptifContainingIgnoringCase(String titre, String descriptif,
			Pageable pageable);

	long countByTitreContainingIgnoringCaseOrDescriptifContainingIgnoringCase(String titre, String descriptif);
	
//	//Sélectionner le(s) examens(s) d'une intervention
//	@Query("FROM Examen e JOIN e.promotion p JOIN p.interventions i WHERE i.id = :interventionId")
//	List<Examen> findExamensByInterventionId (long interventionId);
	
	//Sélectionner le(s) examens(s) d'une intervention et récupérer leurs Acttypes & compsProT
		@Query("FROM Examen e JOIN e.promotion p JOIN p.interventions i "
				+ "WHERE i.id = :interventionId")
		List<Examen> findExamensByInterventionId (long interventionId);

}
