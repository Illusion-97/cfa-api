package fr.dawan.AppliCFABack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.ProgrammePromotion;


@Repository
public interface ProgrammePromotionRepository extends JpaRepository<ProgrammePromotion, Long>{

}
