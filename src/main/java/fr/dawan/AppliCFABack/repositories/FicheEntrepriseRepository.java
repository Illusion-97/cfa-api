package fr.dawan.AppliCFABack.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.dawan.AppliCFABack.entities.FicheEntreprise;

public interface FicheEntrepriseRepository extends JpaRepository<FicheEntreprise, Long> {

//	Page<FicheEntreprise> findAllByNomContainingIgnoringCase(String nom, Pageable pageable);
	
//	long countByNomContainingIgnoringCase(String search);
}
