package fr.dawan.AppliCFABack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.DossierProjet;

@Repository
public interface DossierProjetRepository extends JpaRepository<DossierProjet, Long>{

}
