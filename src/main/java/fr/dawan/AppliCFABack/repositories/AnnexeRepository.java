package fr.dawan.AppliCFABack.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Adresse;
import fr.dawan.AppliCFABack.entities.Annexe;

@Repository
public interface AnnexeRepository extends JpaRepository<Annexe, Long>{

	@Query("SELECT a FROM Annexe a WHERE a.dossierProfessionnel=:id")
	List<Annexe> findAllByDossierProId(@Param("id")long id);
}
