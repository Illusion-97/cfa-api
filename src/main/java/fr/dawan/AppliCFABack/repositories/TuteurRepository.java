package fr.dawan.AppliCFABack.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.Formateur;
import fr.dawan.AppliCFABack.entities.Tuteur;

public interface TuteurRepository extends JpaRepository<Tuteur , Long>{
	
	
	
	@Query("SELECT DISTINCT t FROM Tuteur t JOIN t.etudiants e WHERE e.nom LIKE %:search% OR e.prenom LIKE %:search%")
	List<Tuteur> findAllDistinctByNomAndPrenom(String search);

}
