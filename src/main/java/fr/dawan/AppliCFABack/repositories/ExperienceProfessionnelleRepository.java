package fr.dawan.AppliCFABack.repositories;

import fr.dawan.AppliCFABack.entities.ExperienceProfessionnelle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceProfessionnelleRepository extends JpaRepository<ExperienceProfessionnelle, Long> {
}
