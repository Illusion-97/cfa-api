package fr.dawan.AppliCFABack.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.LivretEvaluation;
@Repository
public interface LivretEvaluationRepository extends JpaRepository<LivretEvaluation, Long>{

	@Query("FROM LivretEvaluation l WHERE l.etudiant.id = :id")
	List<LivretEvaluation> findLivretEvaluationByEtudiantId(long id);

	Optional<LivretEvaluation> findByEtudiantIdAndTitreProfessionnelId(long idEtudiant, long idCursus);
}
