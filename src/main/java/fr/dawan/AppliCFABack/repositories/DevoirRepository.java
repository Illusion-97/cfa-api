package fr.dawan.AppliCFABack.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Devoir;

@Repository
public interface DevoirRepository extends JpaRepository<Devoir, Long> {

	Page<Devoir> findAllByConsigneContainingIgnoringCaseOrInterventionFormationTitreContainingIgnoringCase(
			String consigne, String formationTitre, Pageable pageable);

	long countByConsigneContainingIgnoringCaseOrInterventionFormationTitreContainingIgnoringCase(String consigne,
			String formationTitre);

	// @Query(value = "SELECT n FROM Note n WHERE n.etudiant.id = :id")
	// Page<Note> getNotesByIdEtudiant(@Param("id") long id, Pageable pageRequest);

	// @Query(value = "SELECT n FROM Devoir n WHERE n.etudiant.id = :id")
	// Page<Devoir> getDevoirsByIdEtudiant(@Param("id") long id,Pageable
	// pageRequest);
	
	@Query("FROM Devoir d WHERE d.intervention.id = :id")
	List<Devoir> findAllByInterventionId(long id);
	
	
	Devoir findByInterventionId(long id);

	Page<Devoir> findAllByInterventionPromotionsEtudiantsId(long id, Pageable pageable);

}
