package fr.dawan.AppliCFABack.repositories;

import fr.dawan.AppliCFABack.entities.Sifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SifaRepository extends JpaRepository<Sifa, Long> {
    String getByName(String name);
}
