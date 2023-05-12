package fr.dawan.AppliCFABack.repositories;

import fr.dawan.AppliCFABack.entities.LivretEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
/**
 * @author Valentin C,Feres BG.
 * @see fr.dawan.appliCFABack.service
 * @since 1.0
 * @version 1.0
 */
@Repository
public interface LivretEvaluationRepository extends JpaRepository<LivretEvaluation, Long>{
	/**
	 * 
	 * @param id: identifiant de l'étudiant concerné
	 * @return toutes les livrets d'évaluations de l'étudiant concerné
	 */
	@Query("FROM LivretEvaluation l WHERE l.etudiant.id = :id")
	List<LivretEvaluation> findLivretEvaluationByEtudiantId(long id);
	/**
	 * 
	 * @param idEtudiant: identifiant de l'étudiant concerné
	 * @param idCursus :identifiant du cursus concerné
	 * @return Optional de livret d'évaluation
	 */
	@Query(nativeQuery = true , value = "SELECT * FROM livret_evaluation WHERE etudiant_id = :idEtudiant "
			+ "AND titre_professionnel_id = :idCursus ")
	Optional<LivretEvaluation> findByEtudiantIdAndTitreProfessionnelId(long idEtudiant, long idCursus);

	@Query("SELECT l.observation FROM LivretEvaluation l WHERE l.etudiant.id = :id")
    List<String> findObservationsByEtudiantId(long id);
}
