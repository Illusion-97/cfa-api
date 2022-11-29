package fr.dawan.AppliCFABack.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.CompetenceProfessionnelle;

@Repository
public interface CompetenceProfessionnelleRepository extends JpaRepository<CompetenceProfessionnelle, Long>{

//    @Query("SELECT DISTINCT cp FROM CompetenceProfessionnelle cp JOIN Etudiant e ON e.id = :etudiantId JOIN DossierProfessionnel dp ON dp.etudiant.id = e.id JOIN ExperienceProfessionnelle exp ON exp.dossierProfessionnel.id = dp.id WHERE exp.competenceProfessionnelle.id = cp.id ORDER BY cp.numeroFiche ASC")
//    List<CompetenceProfessionnelle> getCompetenceProfessionnellesByEtudiantDossier(long etudiantId);

	@Query("FROM CompetenceProfessionnelle cp WHERE cp.activiteType.id = :id")
	List<CompetenceProfessionnelle> findAllByActiviteTypeId(long id);

    @Query("SELECT cp FROM CompetenceProfessionnelle cp WHERE cp.activiteType.id = :activiteId ORDER BY cp.numeroFiche")
    List<CompetenceProfessionnelle> getCompetencesByActivite(long activiteId);
}
