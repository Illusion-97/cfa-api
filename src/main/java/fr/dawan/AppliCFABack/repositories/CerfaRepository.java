package fr.dawan.AppliCFABack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Cerfa;

@Repository
public interface CerfaRepository extends JpaRepository<Cerfa,Long> {

}
