package fr.dawan.AppliCFABack.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Contrat;

@Repository
public interface ContratRepository extends JpaRepository<Contrat, Long>{

	Page<Contrat> findAllByMaitreApprentissagePrenomContainingIgnoringCaseOrUtilisateurNomContainingIgnoringCase(
			String prenom, String nom, PageRequest pageable);

}
