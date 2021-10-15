package fr.dawan.AppliCFABack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.CEF;


@Repository
public interface CEFRepository extends JpaRepository<CEF, Long>{

}
