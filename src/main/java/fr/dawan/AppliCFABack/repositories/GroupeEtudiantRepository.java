package fr.dawan.AppliCFABack.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.GroupeEtudiant;

@Repository
public interface GroupeEtudiantRepository extends JpaRepository<GroupeEtudiant, Long> {

	Page<GroupeEtudiant> findAllByNomContainingIgnoringCaseOrEtudiantsPersonneNomContainingIgnoringCaseOrEtudiantsPersonnePrenomContainingIgnoringCase(
			String search, String search2, String search3, Pageable pageable);

	long countByNomContainingIgnoringCaseOrEtudiantsPersonneNomContainingIgnoringCaseOrEtudiantsPersonnePrenomContainingIgnoringCase(
			String search, String search2, String search3);	
}