package fr.dawan.AppliCFABack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.ProgrammeCours;

@Repository
public interface ProgrammeCoursRepository extends JpaRepository<ProgrammeCours, Long>{

}
