package fr.dawan.AppliCFABack.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Cursus;

@Repository
public interface CursusRepository extends JpaRepository<Cursus, Long> {

	Page<Cursus> findAllByTitreContainingIgnoringCaseOrFormationsTitreContainingIgnoringCase(String search, String search2, Pageable pageable);

	long countByTitreContainingIgnoringCaseOrFormationsTitreContainingIgnoringCase(String search, String search2);

}
