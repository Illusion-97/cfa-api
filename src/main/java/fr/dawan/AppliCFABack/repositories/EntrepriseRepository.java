package fr.dawan.AppliCFABack.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Entreprise;


@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, Long>{

	long countByRaisonSocialeContaining(String raisonSociale);
	
	Page<Entreprise> findAllByRaisonSocialeContaining(String raisonSociale, Pageable pageable );

}
