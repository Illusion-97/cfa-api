

package fr.dawan.AppliCFABack.repositories;

import fr.dawan.AppliCFABack.entities.GroupeEtudiant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupeEtudiantRepository extends JpaRepository<GroupeEtudiant, Long> {

	Page<GroupeEtudiant> findAllByNomContainingIgnoringCase(
			String search, Pageable pageable);

	long countByNomContainingIgnoringCase(String search);

}