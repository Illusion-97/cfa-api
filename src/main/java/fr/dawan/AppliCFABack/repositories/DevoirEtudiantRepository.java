package fr.dawan.AppliCFABack.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.dawan.AppliCFABack.entities.DevoirEtudiant;

public interface DevoirEtudiantRepository extends JpaRepository<DevoirEtudiant, Long> {

	List<DevoirEtudiant> getAllByEtudiantId(long id);
	
	List<DevoirEtudiant> getAllByDevoirId(long id);
	
	long countByEtudiantUtilisateurNomContainingOrEtudiantUtilisateurPrenomContaining(String nom,String prenom);
}
