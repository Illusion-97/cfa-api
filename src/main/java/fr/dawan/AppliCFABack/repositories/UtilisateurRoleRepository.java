package fr.dawan.AppliCFABack.repositories;

import fr.dawan.AppliCFABack.entities.UtilisateurRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UtilisateurRoleRepository extends JpaRepository<UtilisateurRole, Long>{

	
	Page<UtilisateurRole> findAllByIntituleContainingIgnoringCase(String intitule, Pageable pageable);
	
	long countByIntituleContainingIgnoringCase(String search);

	UtilisateurRole findByIntituleContaining(String intitule);
	
	List<UtilisateurRole> findAllByUtilisateursId(long UtilisateurId);
}
