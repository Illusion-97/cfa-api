package fr.dawan.AppliCFABack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Remuneration;

@Repository
public interface RemunerationRepository extends JpaRepository<Remuneration, Long> {

}
