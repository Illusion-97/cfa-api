package fr.dawan.AppliCFABack.repositories;

import fr.dawan.AppliCFABack.entities.Entreprise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, Long>{

	long countByRaisonSocialeContaining(String raisonSociale);
	
	Page<Entreprise> findAllByRaisonSocialeContaining(String raisonSociale, Pageable pageable );
}
