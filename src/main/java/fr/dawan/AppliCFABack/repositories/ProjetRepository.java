package fr.dawan.AppliCFABack.repositories;

import fr.dawan.AppliCFABack.entities.Projet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetRepository extends JpaRepository<Projet, Long>{

	Page<Projet> findAllByNomContainingIgnoringCaseOrDescriptionContainingIgnoringCaseOrGroupeNomContainingIgnoringCase(
			String search, String search2, String search3, Pageable pageable);

	long countByNomContainingIgnoringCaseOrDescriptionContainingIgnoringCaseOrGroupeNomContainingIgnoringCase(
			String search, String search2, String search3);

	List<Projet> findAllByGroupeId(long id);

}
