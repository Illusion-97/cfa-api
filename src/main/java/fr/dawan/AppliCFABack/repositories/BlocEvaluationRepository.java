package fr.dawan.AppliCFABack.repositories;

import fr.dawan.AppliCFABack.entities.BlocEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface BlocEvaluationRepository extends JpaRepository<BlocEvaluation,Long> {

	BlocEvaluation findByActiviteTypeId(long id);
	
	Optional<BlocEvaluation> findByActiviteTypeIdAndLivretEvaluationId(long idAt, long idLivretEval);

	@Query("SELECT b FROM BlocEvaluation b JOIN  b.evaluationsFormations e ON e.id = :id ")
	List<BlocEvaluation> finAllByEvaluationsFormationsId(long id);
	

}
