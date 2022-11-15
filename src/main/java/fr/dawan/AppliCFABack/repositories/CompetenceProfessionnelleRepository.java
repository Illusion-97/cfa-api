package fr.dawan.AppliCFABack.repositories;

import fr.dawan.AppliCFABack.dto.customdtos.dossierprofessionnel.pdf.PdfActiviteDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprofessionnel.pdf.PdfCompetenceDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.CompetenceProfessionnelle;

import java.util.List;

@Repository
public interface CompetenceProfessionnelleRepository extends JpaRepository<CompetenceProfessionnelle, Long>{

//    @Query("SELECT DISTINCT cp FROM CompetenceProfessionnelle cp JOIN Etudiant e ON e.id = :etudiantId JOIN DossierProfessionnel dp ON dp.etudiant.id = e.id JOIN ExperienceProfessionnelle exp ON exp.dossierProfessionnel.id = dp.id WHERE exp.competenceProfessionnelle.id = cp.id ORDER BY cp.numeroFiche ASC")
//    List<CompetenceProfessionnelle> getCompetenceProfessionnellesByEtudiantDossier(long etudiantId);

    @Query("SELECT cp FROM CompetenceProfessionnelle cp WHERE cp.activiteType.id = :activiteId ORDER BY cp.numeroFiche")
    List<CompetenceProfessionnelle> getCompetencesByActivite(long activiteId);
}
