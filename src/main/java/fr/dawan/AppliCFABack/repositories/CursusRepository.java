package fr.dawan.AppliCFABack.repositories;

import fr.dawan.AppliCFABack.entities.Cursus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CursusRepository extends JpaRepository<Cursus, Long> {

	@Query(value = "SELECT * FROM cursus c WHERE (?1 IS NULL " +
			"OR LOWER(c.titre) LIKE LOWER(concat('%',?1,'%')))", nativeQuery = true)
	Page<Cursus> findAllByTitre(String search, Pageable pageable);

	long countDistinctByTitreContainingIgnoringCaseOrFormationsTitreContainingIgnoringCase(String search, String search2);
	
	Optional<Cursus> findByIdDg2(long cursusId);
	

}
