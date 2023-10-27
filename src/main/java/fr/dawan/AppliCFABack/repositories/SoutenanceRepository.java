package fr.dawan.AppliCFABack.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Soutenance;

@Repository
public interface SoutenanceRepository extends JpaRepository<Soutenance, Long> {
	//SELECT s.* FROM soutenance s JOIN etudiant e ON(s.etudiant_id = e.id) JOIN  promotion_etudiants p ON(e.id = p.etudiants_id) WHERE p.promotions_id = 323;
	@Query(value = "SELECT s FROM Soutenance s JOIN s.etudiant e JOIN e.promotions p JOIN e.utilisateur u WHERE p.id = :id")
	List<Soutenance> getByPromotionId(long id);
	
	@Query(value = "SELECT s FROM Soutenance s JOIN s.etudiant e JOIN e.promotions p JOIN e.utilisateur u WHERE p.id = :id")
	Page<Soutenance> getPageByPromotionId(long id, Pageable pageable);
	
	@Query(value = "SELECT COUNT(s) FROM Soutenance s JOIN s.etudiant e JOIN e.promotions p JOIN e.utilisateur u JOIN u.roles r WHERE p.id = :id")	
	long countByPromotionId(long id);
}
