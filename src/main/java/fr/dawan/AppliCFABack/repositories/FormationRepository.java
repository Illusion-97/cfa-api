package fr.dawan.AppliCFABack.repositories;

import fr.dawan.AppliCFABack.entities.Formation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface FormationRepository extends JpaRepository<Formation, Long>{

	Page<Formation> findAllByTitreContainingIgnoringCase(String search, Pageable pageable);
	
	@Query("SELECT i.formation FROM Intervention i WHERE i.id = :id")
	Formation findByInterventionId(@Param("id") long id);

	long countByTitreContainingIgnoringCase(String search);
//	long countByTitreContainingIgnoringCaseOrContenuContainingIgnoringCase(String search, String search2);

	Optional<Formation> findByIdDg2(long idDg2);
	

}
