package fr.dawan.AppliCFABack.repositories;

import fr.dawan.AppliCFABack.entities.Annexe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnexeRepository extends JpaRepository<Annexe, Long>{

	@Query("SELECT a FROM Annexe a WHERE a.dossierProfessionnel=:id")
	List<Annexe> findAllByDossierProId(@Param("id")long id);
}
