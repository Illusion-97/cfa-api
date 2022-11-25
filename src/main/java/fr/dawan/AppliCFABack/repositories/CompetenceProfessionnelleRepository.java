package fr.dawan.AppliCFABack.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.CompetenceProfessionnelle;
@Repository
public interface CompetenceProfessionnelleRepository  extends JpaRepository<CompetenceProfessionnelle, Long>{

	@Query("FROM CompetenceProfessionnelle cp WHERE cp.activiteType.id = :id")
	List<CompetenceProfessionnelle> findAllByActiviteTypeId(long id);

}
