package fr.dawan.AppliCFABack.repositories;

import fr.dawan.AppliCFABack.entities.MaitreApprentissage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MaitreApprentissageRepository extends JpaRepository<MaitreApprentissage, Long>{

	Optional<MaitreApprentissage> findMaitreApprentissageByEtudiantId(long id);
}
