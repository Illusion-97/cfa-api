package fr.dawan.AppliCFABack.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long>{

	long countByNomContaining(String nom);
	
	Page<Promotion> findAllByNomContaining(String nom, Pageable pageable );

}
