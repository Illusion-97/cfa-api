package fr.dawan.AppliCFABack.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Entreprise;


@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, Long>{

}
