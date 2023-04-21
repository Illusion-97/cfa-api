package fr.dawan.AppliCFABack.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.dto.UtilisateurRoleDto;
import fr.dawan.AppliCFABack.entities.Formateur;
import fr.dawan.AppliCFABack.entities.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
	@Query("FROM Utilisateur u WHERE u.nom=:user OR u.prenom=:user")
	Utilisateur findByName(@Param("user") String name);
	
	@Query("FROM Utilisateur u WHERE u.login=:email")
	Utilisateur findByEmail(String email);

	List<Utilisateur> findByAdresseVille(@Param("ville") String ville);

	//@Query("FROM Utilisateur u JOIN u.roles JOIN u.adresse a WHERE u.prenom LIKE %:prenom% OR u.nom LIKE %:nom% OR u.login LIKE %:login% OR a.libelle LIKE %:adresseLibelle%")
	Page<Utilisateur> findAllByPrenomContainingIgnoringCaseOrNomContainingIgnoringCaseOrLoginContainingIgnoringCaseOrAdresseLibelleContainingIgnoringCase(String prenom, String nom,
			String login, String adresseLibelle, Pageable pageable );

	long countByPrenomContainingIgnoringCaseOrNomContainingIgnoringCaseOrLoginContainingIgnoringCaseOrAdresseLibelleContainingIgnoringCase(String prenom, String nom, String login, String search);

	Page<Utilisateur> findAllByRolesIntituleIgnoringCaseAndPrenomContainingIgnoringCaseOrRolesIntituleIgnoringCaseAndNomContainingIgnoringCaseOrRolesIntituleIgnoringCaseAndLoginContainingIgnoringCase(
			String role1, String prenom, String role2, String nom, String role3, String login, Pageable pageable);

	long countByRolesIntituleIgnoringCaseAndPrenomContainingIgnoringCaseOrRolesIntituleIgnoringCaseAndNomContainingIgnoringCaseOrRolesIntituleIgnoringCaseAndLoginContainingIgnoringCase(
			String role1, String prenom, String role2, String nom, String role3, String login);
	
	Optional<Utilisateur> findByIdDg2(long idDg2);

	Optional<Utilisateur> findDistinctByIdDg2(long personId);
	
	Optional<Utilisateur> findByFormateur(Formateur formateur);

}
