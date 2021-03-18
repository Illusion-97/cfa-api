package fr.dawan.AppliCFABack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Personne;


@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long>{

}
