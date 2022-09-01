package fr.dawan.AppliCFABack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Validation;

@Repository
public interface ValidationRepository extends JpaRepository<Validation, Long>{

}
