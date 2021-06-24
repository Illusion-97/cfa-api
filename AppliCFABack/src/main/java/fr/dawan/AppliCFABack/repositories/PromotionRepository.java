package fr.dawan.AppliCFABack.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long>{

	long countByNomContaining(String nom);

}
