package fr.dawan.AppliCFABack.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Etudiant;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
	Page<Etudiant> findDistinctAllByPrenomContainingIgnoringCaseOrNomContainingIgnoringCaseOrPromotionsNomContainingIgnoringCaseOrGroupesNomContainingIgnoringCase(
			String prenom, String nom, String promotionsNom, String groupesNom, Pageable p);

	long countDistinctByPrenomContainingIgnoringCaseOrNomContainingIgnoringCaseOrPromotionsNomContainingIgnoringCaseOrGroupesNomContainingIgnoringCase(
			String prenom, String nom, String promotionsNom, String groupesNom);
	
	Page<Etudiant> findAllByPrenomContainingIgnoringCaseOrNomContainingIgnoringCaseOrLoginContainingIgnoringCase(
			String search, String search2, String search3, Pageable pageable);

	long countByPrenomContainingIgnoringCaseOrNomContainingIgnoringCaseOrLoginContainingIgnoringCase(
			String search, String search2, String search3);	

	
}
