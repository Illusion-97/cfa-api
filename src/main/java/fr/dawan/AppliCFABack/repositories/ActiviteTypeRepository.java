package fr.dawan.AppliCFABack.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.ActiviteType;
/**
 * @author Valentin C, Feres BG.
 * @see fr.dawan.appliCFABack.service
 * @since 1.0
 * @version 1.0
 * @return Repository de l'entity ActiviteType
 */
@Repository
public interface ActiviteTypeRepository extends JpaRepository<ActiviteType, Long> {
	/**
	 * 
	 * @param id de la promotion a rechercher
	 * @return toutes les activites types de la promotion recherchée
	 */
	@Query(nativeQuery = true, value = "SELECT * FROM activite_type WHERE activite_type.cursus_activite_type_id ="
			+ " (SELECT c.id FROM cursus c INNER JOIN promotion p ON p.cursus_id = c.id WHERE p.id = :id) order by numero_fiche ")
	List<ActiviteType> getActiviteTypesByPromotionId(@Param("id") long id);

	List<ActiviteType> findAllByCursusActiviteTypeIdOrderByNumeroFiche(long id);

	@Query(nativeQuery = true, value = "SELECT * \r\n"
			+ "FROM activite_type \r\n"
			+ "WHERE activite_type.cursus_activite_type_id = :id")
	List<ActiviteType> getActiviteTypesByCursus(@Param("id") long id);
}
