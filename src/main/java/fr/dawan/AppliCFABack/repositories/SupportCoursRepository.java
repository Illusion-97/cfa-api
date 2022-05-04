package fr.dawan.AppliCFABack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.SupportCours;
/**
 * @author Valentin C, Feres BG.
 * @see fr.dawan.appliCFABack.service
 * @since 1.0
 * @version 1.0
 * @return Repository de l'entity SupportCours
 */
@Repository
public interface SupportCoursRepository extends JpaRepository<SupportCours, Long>{
	/**
	 * 
	 * @param search champs de recherche
	 * @return le nombre d'occurence du champs de recherche 
	 */
	long countByTitreContaining(String search);
}
