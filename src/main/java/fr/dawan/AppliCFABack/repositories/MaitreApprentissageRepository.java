package fr.dawan.AppliCFABack.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.MaitreApprentissage;


@Repository
public interface MaitreApprentissageRepository extends JpaRepository<MaitreApprentissage, Long>{

	Optional<MaitreApprentissage> findMaitreApprentissageByEtudiantId(long id);
}
