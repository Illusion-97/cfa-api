package fr.dawan.AppliCFABack.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.Formateur;
import fr.dawan.AppliCFABack.entities.Tuteur;

@Repository

public interface TuteurRepository extends JpaRepository<Tuteur , Long>{
	
	long countByUtilisateurPrenomContainingOrUtilisateurNomContainingAllIgnoreCase(String prenom, String nom);
	
	//long countTuteursTitreContainingAllIgnoreCase( String search);

	// liste des Tuteurs + recherche par mot clé
	Page<Tuteur> findAllByUtilisateurPrenomContainingOrUtilisateurNomContainingAllIgnoreCase(String prenom, String nom, Pageable p);

	Optional<Tuteur> findByUtilisateurId(long id);

	//Page<Etudiant> findAllByEtudiantsId(long id, PageRequest p);
	
	List<Etudiant> findAllByEtudiantsId(long id);
	
	Page<Etudiant> findAllByEtudiants(long id, Pageable p);

	
	

	
}
