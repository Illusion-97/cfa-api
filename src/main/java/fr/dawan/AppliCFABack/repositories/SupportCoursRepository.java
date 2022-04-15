package fr.dawan.AppliCFABack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.SupportCours;

@Repository
public interface SupportCoursRepository extends JpaRepository<SupportCours, Long>{

	long countByTitreContaining(String search);
}
