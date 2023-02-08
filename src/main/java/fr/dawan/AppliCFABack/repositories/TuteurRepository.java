package fr.dawan.AppliCFABack.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Tuteur;

@Repository

public interface TuteurRepository extends JpaRepository<Tuteur , Long>{
	
	long countById(long id);
	
	@Query("SELECT DISTINCT t FROM Tuteur t JOIN FETCH t.etudiants e WHERE e.utilisateur.nom LIKE :search OR e.utilisateur.prenom LIKE :search")
	List<Tuteur> findAllDistinctByNomAndPrenom(@Param("search") String search);

}
