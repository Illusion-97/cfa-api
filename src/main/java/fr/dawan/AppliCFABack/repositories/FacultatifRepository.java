package fr.dawan.AppliCFABack.repositories;

import fr.dawan.AppliCFABack.entities.Facultatif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultatifRepository extends JpaRepository<Facultatif, Long>{

	@Query("SELECT f FROM Facultatif f WHERE f.dossierProfessionnel=:id")
	List<Facultatif> findAllByDossierProId(@Param("id")long id);

}