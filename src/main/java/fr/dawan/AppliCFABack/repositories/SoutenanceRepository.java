package fr.dawan.AppliCFABack.repositories;

import fr.dawan.AppliCFABack.entities.Soutenance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoutenanceRepository extends JpaRepository<Soutenance, Long> {

    @Query(value = "SELECT s FROM Soutenance s JOIN s.etudiant e JOIN e.promotions p JOIN e.utilisateur u WHERE p.id = :id")
    List<Soutenance> getByPromotionId(long id);

    @Query(value = "SELECT s FROM Soutenance s JOIN s.etudiant e JOIN e.promotions p JOIN e.utilisateur u WHERE p.id = :id")
    Page<Soutenance> getPageByPromotionId(long id, Pageable pageable);

    @Query(value = "SELECT COUNT(s) FROM Soutenance s JOIN s.etudiant e JOIN e.promotions p JOIN e.utilisateur u JOIN u.roles r WHERE p.id = :id")
    long countByPromotionId(long id);
}
