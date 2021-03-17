package fr.dawan.AppliCFABack.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.dto.EntrepriseDto;
import fr.dawan.AppliCFABack.dto.NoteDto;
import fr.dawan.AppliCFABack.dto.PersonneDto;
import fr.dawan.AppliCFABack.dto.PromotionDto;
import fr.dawan.AppliCFABack.entities.Etudiant;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long>{

	// ##################################################
	// # 			     1er Niveau 					#
	// ##################################################
	
	@Query("SELECT e.personne FROM Etudiant e WHERE e.id = :id")
	List<PersonneDto> getPersonneByIdEtudiant(long id);
	
	@Query("SELECT e.entreprise FROM Etudiant e WHERE e.id = :id")
	List<EntrepriseDto> getEntrepriseByIdEtudiant(long id);
	
	@Query("SELECT e.promotions FROM Etudiant e WHERE e.id = :id")
	List<PromotionDto> getPromotionsByIdEtudiant(long id);

	@Query("SELECT e.notes FROM Etudiant e WHERE e.id = :id")
	List<NoteDto> getNotesByIdEtudiant(long id);

	@Query("SELECT e.groupes FROM Etudiant e WHERE e.id = :id")
	List<PromotionDto> getGroupesByIdEtudiant(long id);

	@Query("SELECT e.absences FROM Etudiant e WHERE e.id = :id")
	List<PromotionDto> getAbsencesByIdEtudiant(long id);

	// ##################################################
	// # 			     2eme Niveau 					#
	// ##################################################
	
//	@Query("SELECT f FROM Formateur f JOIN f.cours c JOIN c.etudiants e WHERE e.idEtudiant = :id")
//	List<Formateur> getFormateurByEtudiantId(long id);

}
