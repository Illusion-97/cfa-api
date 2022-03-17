package fr.dawan.AppliCFABack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.ActiviteType;

@Repository
public interface ActiviteTypeRepository extends JpaRepository<ActiviteType, Long> {

}
