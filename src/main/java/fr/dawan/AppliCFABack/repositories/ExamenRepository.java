package fr.dawan.AppliCFABack.repositories;

import java.util.List;

import fr.dawan.AppliCFABack.entities.Promotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Examen;
import fr.dawan.AppliCFABack.entities.Intervention;


@Repository
public interface ExamenRepository extends JpaRepository<Examen, Long>{

	Page<Examen> findAllByTitreContainingIgnoringCaseOrDescriptifContainingIgnoringCase(String titre,
	String descriptif, Pageable pageable);
	
	long countByTitreContainingIgnoringCaseOrDescriptifContainingIgnoringCase(String titre, String descriptif);

//	Page<Examen> findAllByEnonceContainingIgnoringCaseOrFormationTitreContainingIgnoringCaseOrCursusTitreContainingIgnoringCase(String enonce,
//			String formationTitre, String cursusTitre, Pageable pageable);
//
//	long countByEnonceContainingIgnoringCaseOrFormationTitreContainingIgnoringCaseOrCursusTitreContainingIgnoringCase(String search, String search2,
//			String search3);



}
