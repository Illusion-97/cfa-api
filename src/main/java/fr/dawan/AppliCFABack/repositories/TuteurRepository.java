package fr.dawan.AppliCFABack.repositories;

import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.Tuteur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TuteurRepository extends JpaRepository<Tuteur , Long>{

	long countByUtilisateurPrenomContainingOrUtilisateurNomContainingAllIgnoreCase(String prenom, String nom);

	//long countTuteursTitreContainingAllIgnoreCase( String search);

	// liste des Tuteurs + recherche par mot clé
	Page<Tuteur> findAllByUtilisateurPrenomContainingOrUtilisateurNomContainingAllIgnoreCase(String prenom, String nom, Pageable p);

	Optional<Tuteur> findByUtilisateurId(long id);

	//Page<Etudiant> findAllByEtudiantsId(long id, PageRequest p);

	@Query("SELECT e FROM Etudiant e JOIN e.tuteur tuteur WHERE tuteur.id=:id ")
	List<Etudiant> findAllByTuteurId(@Param("id")long id );

	@Query("SELECT e FROM Etudiant e JOIN e.tuteur t WHERE t.id=:id ")
	Page<Etudiant> findAllByTuteurId(@Param("id")long id, Pageable p);

	@Query("SELECT e FROM Etudiant e JOIN e.tuteur t WHERE t.id=:id AND e.utilisateur.nom LIKE :search OR e.utilisateur.prenom  LIKE :search ")
	Page<Etudiant> findEtudiantBySearch(@Param("id")long id, Pageable p, String search);

	long countByIdAndEtudiantsUtilisateurNomContainingAllIgnoringCase(long id, String search);

	long countById(long id);

}
