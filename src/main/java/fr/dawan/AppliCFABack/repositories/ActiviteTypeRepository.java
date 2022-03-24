package fr.dawan.AppliCFABack.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.ActiviteType;

@Repository
public interface ActiviteTypeRepository extends JpaRepository<ActiviteType, Long> {

	@Query (nativeQuery=true , value = "SELECT * FROM activite_type WHERE activite_type.cursus_activite_type_id = (SELECT c.id FROM cursus c INNER JOIN promotion p ON p.cursus_id = c.id WHERE p.id = :id)")
	List<ActiviteType> getActiviteTypesByPromotionId( @Param("id") long id);
}
