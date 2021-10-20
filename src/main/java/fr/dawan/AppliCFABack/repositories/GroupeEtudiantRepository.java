package fr.dawan.AppliCFABack.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.GroupeEtudiant;

@Repository
public interface GroupeEtudiantRepository extends JpaRepository<GroupeEtudiant, Long> {

	Page<GroupeEtudiant> findAllByNomContainingIgnoringCaseOrEtudiantsUtilisateurNomContainingIgnoringCaseOrEtudiantsUtilisateurPrenomContainingIgnoringCase(
			String search, String search2, String search3, Pageable pageable);

	long countByNomContainingIgnoringCaseOrEtudiantsUtilisateurNomContainingIgnoringCaseOrEtudiantsUtilisateurPrenomContainingIgnoringCase(
			String search, String search2, String search3);	
}