package fr.dawan.AppliCFABack.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Projet;

@Repository
public interface ProjetRepository extends JpaRepository<Projet, Long>{

	Page<Projet> findAllByNomContainingIgnoringCaseOrDescriptionContainingIgnoringCaseOrGroupeNomContainingIgnoringCase(
			String search, String search2, String search3, Pageable pageable);

	long countByNomContainingIgnoringCaseOrDescriptionContainingIgnoringCaseOrGroupeNomContainingIgnoringCase(
			String search, String search2, String search3);

	List<Projet> findAllByGroupeId(long id);

}
