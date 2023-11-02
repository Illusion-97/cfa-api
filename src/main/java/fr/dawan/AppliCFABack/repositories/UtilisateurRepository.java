package fr.dawan.AppliCFABack.repositories;

import fr.dawan.AppliCFABack.entities.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
	@Query("SELECT u.id FROM LivretEvaluation l JOIN BlocEvaluation b ON b.livretEvaluation.id = l.id " +
			"JOIN Formateur f ON b.formateurEvaluateur.id = f.id JOIN Utilisateur u ON u.id = f.id WHERE l.id = :idLivret")
	Optional<Long> findidUtilisateurByLivretEvaluation(long idLivret);

	@Query("SELECT DISTINCT p.dateFin FROM Promotion p JOIN Utilisateur u ON p.referentPedagogique.id = u.id WHERE u.id = :idUser")
	Optional<LocalDate> findDatePromotionOfFormateurByUtilisateurId(long idUser);

	@Query("SELECT CASE WHEN l.etat = 'ENATTENTEDEVALIDATION' THEN TRUE ELSE FALSE END " +
			"FROM Cursus c " +
			"JOIN LivretEvaluation l ON l.titreProfessionnel.id = c.id " +
			"JOIN Promotion p ON c.id = p.cursus.id " +
			"JOIN Utilisateur u ON p.referentPedagogique.id = u.id " +
			"WHERE u.id = :idUser")
	boolean isLivretFormateurReferentEmpty(long idUser);
}
