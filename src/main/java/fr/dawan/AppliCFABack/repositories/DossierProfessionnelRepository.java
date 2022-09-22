package fr.dawan.AppliCFABack.repositories;

import java.util.List;

import fr.dawan.AppliCFABack.dto.customdtos.DossierProEtudiantDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.DossierProfessionnel;

@Repository
public interface DossierProfessionnelRepository extends JpaRepository<DossierProfessionnel, Long> {

	Page<DossierProfessionnel> findByNomContaining(String string, PageRequest of);

	@Query("SELECT d FROM DossierProfessionnel d where nom = :nom")
	DossierProfessionnel getByName(String nom);

	@Query("SELECT DISTINCT d FROM DossierProfessionnel d")
	List<DossierProfessionnel> findAllDossierPro();

	@Query("SELECT d FROM DossierProfessionnel d JOIN d.etudiant e ON e.id = :id JOIN e.promotions p JOIN p.cursus c WHERE d.cursus.id = c.id")
    DossierProfessionnel findDossierProByEtudiantIdAndCursusId(long id);
}
