package fr.dawan.AppliCFABack.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.BlocEvaluation;
@Repository
public interface BlocEvaluationRepository extends JpaRepository<BlocEvaluation,Long> {

	BlocEvaluation findByActiviteTypeId(long id);
	
	Optional<BlocEvaluation> findByActiviteTypeIdAndLivretEvaluationId(long idAt, long idLivretEval);
	

}
