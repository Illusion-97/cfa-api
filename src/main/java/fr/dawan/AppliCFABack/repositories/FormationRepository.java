package fr.dawan.AppliCFABack.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Formation;


@Repository
public interface FormationRepository extends JpaRepository<Formation, Long>{

	Page<Formation> findAllByTitreContainingIgnoringCase(String search, Pageable pageable);

	long countByTitreContainingIgnoringCase(String search);
	
	Optional<Formation> findByIdDg2(long id);

}
