package fr.dawan.AppliCFABack.repositories;

import fr.dawan.AppliCFABack.entities.CentreFormation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CentreFormationRepository extends JpaRepository<CentreFormation, Long>{

	long countByNomContaining(String nom);
	
	Page<CentreFormation> findAllByNomContaining(String nom, Pageable pageable );
	
	Optional<CentreFormation> findByIdDg2(long centreFormationId);
}
