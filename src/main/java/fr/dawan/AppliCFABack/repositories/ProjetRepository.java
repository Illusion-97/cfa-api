package fr.dawan.AppliCFABack.repositories;

import fr.dawan.AppliCFABack.entities.Projet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetRepository extends JpaRepository<Projet, Long>{

	Page<Projet> findAllByNomContainingIgnoringCaseOrDescriptionContainingIgnoringCaseOrGroupeNomContainingIgnoringCase(
			String search, String search2, String search3, Pageable pageable);
	@Query(value = "SELECT * FROM projet p LEFT JOIN groupe_etudiant gp ON (p.groupe_id = gp.id)" +
			"WHERE (?1 IS NULL OR LOWER(p.nom) LIKE LOWER(concat('%',?1,'%')) " +
			"OR LOWER(gp.nom) LIKE LOWER(concat('%',?1,'%'))) OR p.groupe_id IS NULL", nativeQuery = true)
	Page<Projet> findAllByNomOrGroupeProjet(String search, Pageable pageable);
	long countByNomContainingIgnoringCaseOrDescriptionContainingIgnoringCaseOrGroupeNomContainingIgnoringCase(
			String search, String search2, String search3);

	List<Projet> findAllByGroupeId(long id);

}
