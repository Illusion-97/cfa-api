package fr.dawan.AppliCFABack.repositories;

import fr.dawan.AppliCFABack.entities.CEF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CEFRepository extends JpaRepository<CEF, Long>{

}
