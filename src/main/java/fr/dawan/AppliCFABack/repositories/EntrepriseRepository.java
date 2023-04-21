package fr.dawan.AppliCFABack.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.DossierProjet;
import fr.dawan.AppliCFABack.entities.Entreprise;
import fr.dawan.AppliCFABack.entities.Etudiant;


@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, Long>{

	long countByRaisonSocialeContaining(String raisonSociale);
	
	Page<Entreprise> findAllByRaisonSocialeContaining(String raisonSociale, Pageable pageable );
}
