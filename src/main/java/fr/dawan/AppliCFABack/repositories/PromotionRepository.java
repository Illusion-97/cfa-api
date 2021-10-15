package fr.dawan.AppliCFABack.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long>{

	long countByNomContaining(String nom);
	
	Page<Promotion> findAllByNomContainingAllIgnoreCase(String nom, Pageable pageable );
	
	List<Promotion> findAllByInterventionsId(long id);

	List<Promotion> findAllByReferentPedagogiqueId(long id);

	List<Promotion> findAllByCursusId(long id);
}
