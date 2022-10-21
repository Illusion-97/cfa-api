package fr.dawan.AppliCFABack.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.dawan.AppliCFABack.entities.EvaluationFormation;

public interface EvaluationFormationRepository extends JpaRepository<EvaluationFormation, Long> {

	List<EvaluationFormation> findAllByBlocEvaluationActiviteTypeId(long id);

}
