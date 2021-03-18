package fr.dawan.AppliCFABack.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.dto.AbsenceDto;
import fr.dawan.AppliCFABack.dto.AdresseDto;
import fr.dawan.AppliCFABack.dto.CoursDto;
import fr.dawan.AppliCFABack.dto.DevoirDto;
import fr.dawan.AppliCFABack.dto.EntrepriseDto;
import fr.dawan.AppliCFABack.dto.ExamenDto;
import fr.dawan.AppliCFABack.dto.FormateurDto;
import fr.dawan.AppliCFABack.dto.GroupeDto;
import fr.dawan.AppliCFABack.dto.NoteDto;
import fr.dawan.AppliCFABack.dto.PersonneDto;
import fr.dawan.AppliCFABack.dto.ProgrammeCoursDto;
import fr.dawan.AppliCFABack.dto.ProjetDto;
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
	List<GroupeDto> getGroupesByIdEtudiant(long id);

	@Query("SELECT e.absences FROM Etudiant e WHERE e.id = :id")
	List<AbsenceDto> getAbsencesByIdEtudiant(long id);

	// ##################################################
	// # 			     2eme Niveau 					#
	// ##################################################
	
	@Query("SELECT c FROM ProgrammeCours c JOIN c.promotions p JOIN p.etudiants e WHERE e.id = :id")
	List<ProgrammeCoursDto> getProgrammeCoursByIdEtudiant(long id);

	@Query("SELECT p FROM Projet p JOIN p.groupe g JOIN g.etudiants e WHERE e.id = :id")
	List<ProjetDto> getProjetByIdEtudiant(long id);

	@Query("SELECT a FROM Adresse a JOIN a.personnes p JOIN p.etudiant e WHERE e.id = :id")
	List<AdresseDto> getAdresseByIdEtudiant(long id);

	// ##################################################
	// # 			     3eme Niveau 					#
	// ##################################################

	@Query("SELECT f FROM Formateur f JOIN f.programmeCours c JOIN c.promotions p JOIN p.etudiants e WHERE e.id = :id")
	List<FormateurDto> getFormateursByIdEtudiant(long id);

	@Query("SELECT d FROM Devoir d JOIN d.programmeCours c JOIN c.promotions p JOIN p.etudiants e WHERE e.id = :id")
	List<DevoirDto> getDevoirsByIdEtudiant(long id);

	@Query("SELECT ex FROM Examen ex JOIN ex.programmeCours c JOIN c.promotions p JOIN p.etudiants e WHERE e.id = :id")
	List<ExamenDto> getExamensByIdEtudiant(long id);
}
