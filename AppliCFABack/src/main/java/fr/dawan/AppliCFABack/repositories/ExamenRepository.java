package fr.dawan.AppliCFABack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Examen;


@Repository
public interface ExamenRepository extends JpaRepository<Examen, Long>{

}
