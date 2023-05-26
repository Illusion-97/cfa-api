package fr.dawan.AppliCFABack.repositories;

import fr.dawan.AppliCFABack.entities.Entreprise;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, Long>{
	
	@Query("SELECT e FROM Entreprise e ORDER by e.raisonSociale")
	List<Entreprise> findAll();

	long countByRaisonSocialeContaining(String raisonSociale);
	
	Page<Entreprise> findAllByRaisonSocialeContaining(String raisonSociale, Pageable pageable );
}
