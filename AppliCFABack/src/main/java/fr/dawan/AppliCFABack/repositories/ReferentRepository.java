package fr.dawan.AppliCFABack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Referent;

@Repository
public interface ReferentRepository extends JpaRepository<Referent, Long>{

}
