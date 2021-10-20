package fr.dawan.AppliCFABack.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Note;


@Repository
public interface NoteRepository extends JpaRepository<Note, Long>{

	@Query(value = "SELECT n FROM Note n WHERE n.etudiant.id = :id")
	Page<Note> getNotesByIdEtudiant(@Param("id") long id, Pageable pageRequest);

	@Query("SELECT n FROM Note n WHERE n.etudiant.id = :id")
	List<Note> getNotesByIdEtudiant(@Param("id") long id );

	Page<Note> findAllByEtudiantUtilisateurPrenomContainingIgnoringCaseOrEtudiantUtilisateurNomContainingIgnoringCaseOrExamenExamenEnonceContainingIgnoringCaseOrDevoirEnonceContainingIgnoringCase(
			String search, String search2, String search3, String search4, Pageable pageable);

	long countByEtudiantUtilisateurPrenomContainingIgnoringCaseOrEtudiantUtilisateurNomContainingIgnoringCaseOrExamenExamenEnonceContainingIgnoringCaseOrDevoirEnonceContainingIgnoringCase(
			String search, String search2, String search3, String search4);

	List<Note> findAllByEtudiantId(long id);

}
