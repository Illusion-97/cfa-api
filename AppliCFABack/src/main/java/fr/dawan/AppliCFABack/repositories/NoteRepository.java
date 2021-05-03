package fr.dawan.AppliCFABack.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Absence;
import fr.dawan.AppliCFABack.entities.Note;


@Repository
public interface NoteRepository extends JpaRepository<Note, Long>{

	@Query("SELECT n FROM Note n WHERE n.etudiant.id = :id")
	List<Note> getNotesByIdEtudiant(@Param("id") long id);

}
