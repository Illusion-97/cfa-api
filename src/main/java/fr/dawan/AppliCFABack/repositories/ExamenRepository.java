package fr.dawan.AppliCFABack.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import fr.dawan.AppliCFABack.entities.Examen;
import fr.dawan.AppliCFABack.entities.Intervention;

/**
 * @author Valentin C, Feres BG.
 * @see fr.dawan.appliCFABack.service
 * @since 1.0
 * @version 1.0
 * @return Repository de l'entity Examen
 */
@Repository
public interface ExamenRepository extends JpaRepository<Examen, Long> {
	/**
	 * 
	 * @param titre      à rechercher
	 * @param descriptif à recherher
	 * @param pageable   pagination
	 * @return tous les examens dont le titre et la description contiennent le
	 *         champs recherché
	 */
	Page<Examen> findAllByTitreContainingIgnoringCaseOrDescriptifContainingIgnoringCase(String titre, String descriptif,
			Pageable pageable);

	long countByTitreContainingIgnoringCaseOrDescriptifContainingIgnoringCase(String titre, String descriptif);

}
