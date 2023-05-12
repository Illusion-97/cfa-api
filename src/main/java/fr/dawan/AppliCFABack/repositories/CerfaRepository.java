package fr.dawan.AppliCFABack.repositories;

import fr.dawan.AppliCFABack.entities.Cerfa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CerfaRepository extends JpaRepository<Cerfa,Long> {

}
