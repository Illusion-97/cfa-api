package fr.dawan.AppliCFABack.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Note;


@Repository
public interface NoteRepository extends JpaRepository<Note, Long>{

	@Query("SELECT n FROM Note n WHERE n.etudiant.id = :id")
	List<Note> getNotesByIdEtudiant(@Param("id") long id);

	Page<Note> findAllByEtudiantPrenomContainingIgnoringCaseOrEtudiantNomContainingIgnoringCaseOrExamenExamenEnonceContainingIgnoringCaseOrDevoirEnonceContainingIgnoringCase(
			String search, String search2, String search3, String search4, Pageable pageable);

	long countByEtudiantPrenomContainingIgnoringCaseOrEtudiantNomContainingIgnoringCaseOrExamenExamenEnonceContainingIgnoringCaseOrDevoirEnonceContainingIgnoringCase(
			String search, String search2, String search3, String search4);

}
