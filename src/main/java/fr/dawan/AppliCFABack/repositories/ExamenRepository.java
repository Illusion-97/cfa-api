package fr.dawan.AppliCFABack.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Examen;


@Repository
public interface ExamenRepository extends JpaRepository<Examen, Long>{

	Page<Examen> findAllByEnonceContainingIgnoringCaseOrFormationTitreContainingIgnoringCaseOrCursusTitreContainingIgnoringCase(String enonce,
			String formationTitre, String cursusTitre, Pageable pageable);

	long countByEnonceContainingIgnoringCaseOrFormationTitreContainingIgnoringCaseOrCursusTitreContainingIgnoringCase(String search, String search2,
			String search3);

}
