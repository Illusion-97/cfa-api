package fr.dawan.AppliCFABack.repositories;

import fr.dawan.AppliCFABack.entities.Validation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidationRepository extends JpaRepository<Validation, Long>{

}
