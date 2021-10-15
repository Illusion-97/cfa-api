package fr.dawan.AppliCFABack.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Formation;


@Repository
public interface FormationRepository extends JpaRepository<Formation, Long>{

	Page<Formation> findAllByTitreContainingIgnoringCaseOrContenuContainingIgnoringCase(String search, String search2, Pageable pageable);

	long countByTitreContainingIgnoringCaseOrContenuContainingIgnoringCase(String search, String search2);

}
