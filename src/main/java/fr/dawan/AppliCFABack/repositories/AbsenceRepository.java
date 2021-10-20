package fr.dawan.AppliCFABack.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Absence;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long> {

	@Query("SELECT a FROM Absence a WHERE a.etudiant.id = :id")
	List<Absence> getAbsencesByIdEtudiant(@Param("id") long id);

	@Query("SELECT a FROM Absence a WHERE a.etudiant.id = :id")
	Page<Absence> getAbsencesByIdEtudiant(@Param("id") long id, Pageable pageable);


	List<Absence> findAllByEtudiantId(long id);

	Page<Absence> findByEtudiantUtilisateurNomContainingOrEtudiantUtilisateurPrenomContainingAllIgnoreCase(String prenom, String nom,
			Pageable p);

	long countByEtudiantUtilisateurNomContainingOrEtudiantUtilisateurPrenomContainingAllIgnoreCase(String prenom, String nom);

	List<Absence> findByEtudiantPromotionsId(long id);

	List<Absence> findDistinctByEtudiantPromotionsReferentPedagogiqueId(long id);

	long countDistinctByEtudiantPromotionsReferentPedagogiqueId(long id);

	@Query(value = "SELECT abs.id, abs.dateDebut, abs.dateFin, promo.referentPedagogique, etud.id FROM Absence abs JOIN abs.etudiant etud JOIN etud.promotions promo WHERE promo.referentPedagogique.id =:id ")
	List<Absence> findByRefId(@Param("id") long id);
}
