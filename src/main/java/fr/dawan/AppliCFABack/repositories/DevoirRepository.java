package fr.dawan.AppliCFABack.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Devoir;
import fr.dawan.AppliCFABack.entities.Etudiant;

@Repository
public interface DevoirRepository extends JpaRepository<Devoir, Long> {

	Page<Devoir> findAllByEnonceContainingIgnoringCaseOrInterventionFormationTitreContainingIgnoringCase(String enonce,
			String formationTitre, Pageable pageable);

	long countByEnonceContainingIgnoringCaseOrInterventionFormationTitreContainingIgnoringCase(String enonce,
			String formationTitre);

	// @Query(value = "SELECT n FROM Note n WHERE n.etudiant.id = :id")
	// Page<Note> getNotesByIdEtudiant(@Param("id") long id, Pageable pageRequest);

	// @Query(value = "SELECT n FROM Devoir n WHERE n.etudiant.id = :id")
	// Page<Devoir> getDevoirsByIdEtudiant(@Param("id") long id,Pageable
	// pageRequest);

	List<Devoir> findAllByInterventionId(long id);

	Devoir findByInterventionId(long id);

	Page<Devoir> findAllByInterventionPromotionsEtudiantsId(long id, Pageable pageable);

}
