package fr.dawan.AppliCFABack.repositories;

import fr.dawan.AppliCFABack.entities.DossierProfessionnel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DossierProfessionnelRepository extends JpaRepository<DossierProfessionnel, Long> {

	 
	@Query("SELECT d FROM DossierProfessionnel d where nom = :nom")
	DossierProfessionnel getByName(String nom);

	@Query("SELECT DISTINCT d FROM DossierProfessionnel d")
	List<DossierProfessionnel> findAllDossierPro();

	@Query("SELECT d FROM DossierProfessionnel d JOIN d.etudiant e ON e.id = :id JOIN e.promotions p JOIN p.cursus c WHERE d.cursus.id = c.id")
    List<DossierProfessionnel> findDossierProByEtudiantIdAndCursusId(long id);
	
	long countByNom(String nom);

	Page<DossierProfessionnel> findByNomContaining(String string , Pageable pageable);

	@Query("SELECT d FROM DossierProfessionnel d WHERE d.id = :id")
	DossierProfessionnel getByDossierbyId(@Param("id")long id);




}
