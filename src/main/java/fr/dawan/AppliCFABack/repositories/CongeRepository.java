package fr.dawan.AppliCFABack.repositories;

import fr.dawan.AppliCFABack.entities.Conge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CongeRepository extends JpaRepository<Conge, Long>{

	@Query("Select c FROM Conge c WHERE c.utilisateur.id = :id")
	List<Conge> findByIdUtilisateur(@Param("id") long id);
	
	Page<Conge> findAllByUtilisateurPrenomContainingIgnoringCaseOrUtilisateurNomContainingIgnoringCase(String prenom, String nom, Pageable pageable );

	long countByUtilisateurPrenomContainingIgnoringCaseOrUtilisateurNomContainingIgnoringCase(String prenom, String nom);

	List<Conge> findAllByUtilisateurId(long id);

}
