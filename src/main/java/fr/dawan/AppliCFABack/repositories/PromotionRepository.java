package fr.dawan.AppliCFABack.repositories;

import fr.dawan.AppliCFABack.entities.Promotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Valentin C, Feres BG.
 * @see fr.dawan.appliCFABack.service
 * @since 1.0
 * @version 1.0
 * @return Repository de l'entity Promotion
 */
@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
	/**
	 *
	 * @param champs de recherche
	 * @return nombre d'occurence du champs de recherche
	 */
	long countByNomContaining(String nom);

	/**
	 *
	 * @param centre de formation id
	 * @return nombre d'occurence du centre de formation
	 */
	//@Query("FROM Promotion p WHERE lower(p.dateDebut) LIKE lower(concat('%', :date, '%')) AND p.centreFormation = :id")
	long countByCentreFormationIdAndDateDebutContainingAllIgnoringCase(long id, String date);

	/**
	 *
	 * @param nom      de l'utilisateur
	 * @param pageable pagination
	 * @return toutes les promotions dont le nom contient le champs de recherche,
	 *         paginé
	 */
	@Query("SELECT p FROM Promotion p WHERE (:search IS NULL OR p.centreFormation.nom LIKE %:search% OR p.nom LIKE %:search%) ORDER BY p.nbParticipants DESC, p.dateFin DESC")
	Page<Promotion> findAllByNomOrCentreFormationNomIgnoreCase(
			@Param("search") String search,
			Pageable pageable);



	Page<Promotion> findAllByOrderByNbParticipantsDesc(Pageable pageable);
	Page<Promotion> findAllByOrderByDateFinDesc(Pageable pageable);
	Page<Promotion> findAllByOrderByDateDebutDesc(Pageable pageable);
	/**
	 *
	 * @param id de l'intervention recherchée
	 * @return toutes les promotions contenants l'id de l'intervention concercnée
	 */
	List<Promotion> findAllByInterventionsId(long id);

	List<Promotion> findAllByReferentPedagogiqueId(long id);

	@Query("SELECT DISTINCT p FROM Promotion p JOIN p.interventions i JOIN p.centreFormation cf WHERE i.formateur.id = :id AND cf.nom LIKE %:search%")
	Page<Promotion> findAllByFormateurId(@Param("id") long id, Pageable pageable, String search);

	@Query("SELECT COUNT(DISTINCT p.id) FROM Promotion p JOIN p.interventions i JOIN p.centreFormation cf WHERE i.formateur.id = :id AND cf.nom LIKE %:search%")
	long countByFormateurId(@Param("id") long id, String search);

	/**
	 *
	 * @param id du cursus recherché
	 * @return toutes les promotions dont contenant le cursus id recherché
	 */
	List<Promotion> findAllByCursusId(long id);

	@Query("SELECT p FROM Promotion p WHERE p.cursus.id = :idCursus ORDER BY p.dateFin DESC,p.nbParticipants DESC")
	Page<Promotion> getAllPageablePromotionByCursusId (long idCursus, Pageable pageable);

	@Query("SELECT COUNT(DISTINCT p.id)FROM Promotion p WHERE p.cursus.id = :idCursus")
	long countPromotionByCursusId(long idCursus);

	/**
	 *
	 * @param id de l'étudiant recherché
	 * @return la promotion de l'étudiant concerné
	 */
	@Query("FROM Promotion p JOIN p.etudiants e ON e.id = :id")
	List<Promotion> getByEtudiantId(long id);

	/***
	 *
	 * @param etudiantId : identifiant de l'etudiant
	 * @param interventionId : identifiant de l'intervention
	 * @return return une promotion si elle existe
	 */
	Optional<Promotion> getByEtudiantsIdAndInterventionsId( long etudiantId, long interventionId);

	//@Query("SELECT p from Promotion p JOIN FETCH p.etudiants WHERE p.idDg2 = :id")
	Optional<Promotion> findByIdDg2(long id);

	Page<Promotion> findPromotionsByCentreFormationIdAndDateDebutContainingAllIgnoringCase(long id, Pageable pageable, String date);

	@Query("From Promotion p WHERE p.idDg2 = :idPromotionDg2")
	List<Promotion> findAllByIdPromotionDg2(long idPromotionDg2);

	@Query("SELECT COUNT(DISTINCT p.id) FROM Promotion p JOIN CentreFormation cf ON p.centreFormation = cf.id WHERE p.nom LIKE %:search% OR p.dateDebut LIKE %:search% OR cf.nom LIKE %:search%")
	long countByNomOrCentreFormationOrDate(String search);
}
