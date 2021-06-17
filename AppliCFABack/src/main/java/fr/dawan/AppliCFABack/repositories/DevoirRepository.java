package fr.dawan.AppliCFABack.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Devoir;


@Repository
public interface DevoirRepository extends JpaRepository<Devoir, Long>{
	
	Page<Devoir> findAllByEnonceContainingIgnoringCaseOrInterventionFormationTitreContainingIgnoringCase(
			String enonce, String formationTitre, Pageable pageable);

	long countByEnonceContainingIgnoringCaseOrInterventionFormationTitreContainingIgnoringCase(String enonce,
			String formationTitre);

	

}
