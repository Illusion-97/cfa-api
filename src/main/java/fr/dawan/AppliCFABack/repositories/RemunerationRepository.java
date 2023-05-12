package fr.dawan.AppliCFABack.repositories;

import fr.dawan.AppliCFABack.entities.Remuneration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemunerationRepository extends JpaRepository<Remuneration, Long> {

}
