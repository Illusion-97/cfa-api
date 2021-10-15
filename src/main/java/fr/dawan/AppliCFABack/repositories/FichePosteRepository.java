package fr.dawan.AppliCFABack.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.dawan.AppliCFABack.entities.FichePoste;

public interface FichePosteRepository extends JpaRepository<FichePoste, Long> {

	Page<FichePoste> findAllByIntituleContainingIgnoringCase(String intitule, Pageable pageable);
	
	long countByIntituleContainingIgnoringCase(String search);


}
