package fr.dawan.AppliCFABack.repositories;

import fr.dawan.AppliCFABack.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NoteRepository extends JpaRepository<Note, Long>{

//	@Query(value = "SELECT n FROM Note n WHERE n.etudiant.id = :id")
//	Page<Note> getNotesByIdEtudiant(@Param("id") long id, Pageable pageRequest);
//
//	@Query("SELECT n FROM Note n WHERE n.etudiant.id = :id")
//	List<Note> getNotesByIdEtudiant(@Param("id") long id );
//
//	Page<Note> findAllByEtudiantUtilisateurPrenomContainingIgnoringCaseOrEtudiantUtilisateurNomContainingIgnoringCaseOrExamenExamenEnonceContainingIgnoringCaseOrDevoirEnonceContainingIgnoringCase(
//			String search, String search2, String search3, String search4, Pageable pageable);

	/**
	 * @param id de l'étudiant
	 * @return une liste de notes par id de l'étudiant
	 */
	List<Note> findAllByEtudiantNoteId(long id);
	
	List<Note> findAllByExamenId(long id);

	long countByEtudiantNoteUtilisateurPrenomContainingIgnoringCaseOrEtudiantNoteUtilisateurNomContainingIgnoringCaseOrExamenTitreContainingIgnoringCase(
			String search, String search2, String search3);
	/***
	 * 
	 * @param idPromotion
	 * @param idExamen
	 * @return  Liste des notes en fonction de promotion et l'examen
	 */
//	@Query("SELECT n FROM Note n JOIN n.examen e Join e.promotions p Where p.id =:idPromotion And  e.id = :idExamen")

	List<Note> findAllByExamenPromotionsIdAndExamenId(long idPromotion,long idExamen);
	/***
	 * 
	 * @param idIntervention identifiant de l'intervention
	 * @param idExamen identifiant de l'examen
	 * @param search la saisie de la recherche
	 * @return Liste des notes en fonction de l'intervention et l'examen
	 */
	@Query("SELECT n FROM Note n JOIN n.examen e On e.id = :idExamen Join e.intervention i On i.id "
			+ "=:idIntervention Join Fetch n.etudiantNote etu Join etu.utilisateur u Where u.nom LIKE %:search% OR u.prenom  LIKE %:search% ")
	List<Note> findAllByExamenInterventionIdAndExamenId(long idIntervention ,long idExamen,String search);

}
