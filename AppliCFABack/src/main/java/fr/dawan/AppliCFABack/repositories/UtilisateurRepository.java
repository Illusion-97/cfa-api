package fr.dawan.AppliCFABack.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
	@Query("FROM Utilisateur u WHERE u.nom=:user OR u.prenom=:user")
	Utilisateur findByName(@Param("user") String name);
	
	@Query("FROM Utilisateur u WHERE u.login=:email")
	Utilisateur findByEmail(String email);

	@Query("FROM Utilisateur u WHERE u.adresse.ville=:ville")
	List<Utilisateur> findByAdresse(@Param("ville") String ville);

	@Query("FROM Utilisateur u WHERE u.entreprise.id=:entrepriseId")
	List<Utilisateur> findByEntreprise(@Param("entrepriseId") long entrepriseId);

	Page<Utilisateur> findAllByPrenomContainingIgnoringCaseOrNomContainingIgnoringCaseOrLoginContainingIgnoringCase(String prenom, String nom,
			String login, Pageable pageable );

	long countByPrenomContainingIgnoringCaseOrNomContainingIgnoringCaseOrLoginContainingIgnoringCase(String prenom, String nom, String login);

	
}
