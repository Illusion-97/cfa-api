package fr.dawan.AppliCFABack.repositories;

import fr.dawan.AppliCFABack.entities.ExperienceProfessionnelle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperienceProfessionnelleRepository extends JpaRepository<ExperienceProfessionnelle, Long> {

    @Query("SELECT DISTINCT exp FROM ExperienceProfessionnelle exp JOIN Etudiant e ON e.id =:etudiantId JOIN DossierProfessionnel dp ON dp.etudiant.id = e.id JOIN Cursus c ON c.id = dp.cursus.id JOIN Promotion p ON p.id = :promotionId JOIN CompetenceProfessionnelle cp ON cp.id = exp.competenceProfessionnelle.id WHERE dp.id = exp.dossierProfessionnel.id AND p.cursus.id = c.id ORDER BY cp.numeroFiche ASC")
    List<ExperienceProfessionnelle> getExperienceByEtudiantDossier(long etudiantId, long promotionId);
}
