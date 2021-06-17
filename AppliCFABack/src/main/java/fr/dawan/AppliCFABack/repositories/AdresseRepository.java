package fr.dawan.AppliCFABack.repositories;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Adresse;


@Repository
public interface AdresseRepository extends JpaRepository<Adresse, Long>{


	Page<Adresse> findAllByVille(String ville, Pageable pageable);

}
