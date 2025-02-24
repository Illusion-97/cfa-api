package fr.dawan.AppliCFABack.repositories;


import fr.dawan.AppliCFABack.entities.Adresse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AdresseRepository extends JpaRepository<Adresse, Long>{

	@Query("SELECT a FROM Adresse a ORDER BY a.ville")
	List<Adresse> findAll();

	Page<Adresse> findAllByLibelleContainingOrVilleContaining(String rue,String ville, Pageable pageable);

	long countByLibelleContainingOrVilleContaining(String rue, String ville);

	@Query("Select a From Adresse a JOIN Utilisateur u on u.adresse.id = a.id Where u.id = :id")
	Optional<Adresse> getByUtilisateurId(long id);
	
}
