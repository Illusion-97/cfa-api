package fr.dawan.AppliCFABack.repositories;

import fr.dawan.AppliCFABack.dto.DossierProjetDto;
import fr.dawan.AppliCFABack.entities.DossierProjet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DossierProjetRepository extends JpaRepository<DossierProjet, Long> {

	Page<DossierProjet> findByNomContaining(String string, PageRequest of);

	@Query("SELECT d FROM DossierProjet d where d.nom = :nom")
	DossierProjet getByName(String nom);
	
	@Query("SELECT dp FROM DossierProjet dp JOIN dp.etudiant e WHERE e.id = :id")
	List<DossierProjet> findByIdEtudiant(long id);
	
	@Query("SELECT dp FROM DossierProjet dp WHERE dp.id = :id")
	DossierProjet getByDossierProjetId(@Param("id")long id);
}
