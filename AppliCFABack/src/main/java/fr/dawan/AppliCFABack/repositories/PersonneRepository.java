package fr.dawan.AppliCFABack.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Personne;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long> {
	@Query("FROM Personne p WHERE p.nom= :name OR p.prenom= :name")
	Personne findByName(@Param("name") String name);

	@Query("SELECT p.formateur FROM Personne p WHERE p.id = :idFormateur ")
	List<Personne> findByFormateur(@Param("idFormateur") long idFormateur);

	@Query("SELECT p.adresse FROM Personne p WHERE p.id = :idAddresse")
	List<Personne> findByAddresse(@Param("idAddresse") long idAddresse);

	@Query("SELECT p.referent FROM Personne p WHERE p.id =: idRef")
	List<Personne> findByReferent(@Param("idRef") long idRef);

	@Query("SELECT p.admin FROM Personne p WHERE p.id = : idAdmin")
	Personne findByAdmin(@Param("idAdmin") long idAdmin);

}