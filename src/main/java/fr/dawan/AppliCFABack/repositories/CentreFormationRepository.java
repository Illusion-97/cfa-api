package fr.dawan.AppliCFABack.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.CentreFormation;


@Repository
public interface CentreFormationRepository extends JpaRepository<CentreFormation, Long>{

	long countByNomContaining(String nom);
	
	Page<CentreFormation> findAllByNomContaining(String nom, Pageable pageable );
}
