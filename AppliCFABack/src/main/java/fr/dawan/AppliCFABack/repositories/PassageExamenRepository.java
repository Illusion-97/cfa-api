package fr.dawan.AppliCFABack.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.PassageExamen;

@Repository
public interface PassageExamenRepository extends JpaRepository<PassageExamen, Long> {

	Page<PassageExamen> findAllByExamenEnonceContainingIgnoringCaseOrInterventionFormationTitreContainingIgnoringCase(
			String search, String search2, Pageable pageable);

	long countByExamenEnonceContainingIgnoringCaseOrInterventionFormationTitreContainingIgnoringCase(String search,
			String search2);

	PassageExamen findByInterventionId(long id);
}
