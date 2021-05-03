package fr.dawan.AppliCFABack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Cursus;

@Repository
public interface CursusRepository extends JpaRepository<Cursus, Long> {

}
