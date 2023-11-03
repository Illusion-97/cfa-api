package fr.dawan.AppliCFABack.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Annexe;
import fr.dawan.AppliCFABack.entities.Facultatif;

@Repository
public interface FacultatifRepository extends JpaRepository<Facultatif, Long>{

	@Query("SELECT f FROM Facultatif f WHERE f.dossierProfessionnel=:id")
	List<Facultatif> findAllByDossierProId(@Param("id")long id);

}
