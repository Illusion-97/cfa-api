package fr.dawan.AppliCFABack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.CompetenceProfessionnelle;
@Repository
public interface CompetenceProfessionnelleRepository  extends JpaRepository<CompetenceProfessionnelle, Long>{

}
