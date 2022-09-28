package fr.dawan.AppliCFABack.repositories;



import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Adresse;


@Repository
public interface AdresseRepository extends JpaRepository<Adresse, Long>{


	Page<Adresse> findAllByLibelleContainingOrVilleContaining(String rue,String ville, Pageable pageable);

	long countByLibelleContainingOrVilleContaining(String rue, String ville);

	@Query("Select a From Adresse a JOIN Utilisateur u on u.adresse.id = a.id Where u.id = :id")
	Optional<Adresse> getByUtilisateurId(long id);
	
}
