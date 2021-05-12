package fr.dawan.AppliCFABack.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.AppliCFABack.dto.AbsenceDto;
import fr.dawan.AppliCFABack.dto.AdresseDto;
import fr.dawan.AppliCFABack.dto.EntrepriseDto;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.GroupeEtudiantDto;
import fr.dawan.AppliCFABack.dto.InterventionDto;
import fr.dawan.AppliCFABack.dto.NoteDto;
import fr.dawan.AppliCFABack.dto.PromotionDto;
import fr.dawan.AppliCFABack.dto.UtilisateurDto;
import fr.dawan.AppliCFABack.services.EtudiantService;

@RestController
@RequestMapping("/AppliCFABack/etudiants")
public class EtudiantController {

	@Autowired
	EtudiantService etudiantService;

	// ##################################################
	// # 					CRUD 						#
	// ##################################################

	/*
	 * On récupère tous les étudiants présents dans la bdd
	 */
	@GetMapping(produces = "application/json")
	public List<EtudiantDto> getAll() {
		return etudiantService.getAll();
	}

	/*
	 * On récupère l'étudiant avec l'id passé en paramètre
	 */
	@GetMapping(value = "/{id}", produces = "application/json")
	public EtudiantDto getById(@PathVariable("id") long id) {
		return etudiantService.getById(id);
	}
	
	/*
	 * On sauvegarde l'étudiant en paramètre dans la bdd
	 */
	@PostMapping(consumes = "application/json", produces = "application/json")
	public EtudiantDto save(@RequestBody EtudiantDto eDto) {
		return etudiantService.saveOrUpdate(eDto);
	}
	
	/*
	 * On supprime l'étudiant avec l'id passé en paramètre
	 */
	@DeleteMapping(value = "/{id}", produces = "text/plain")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") long id) {
		try {
			etudiantService.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression effectuée");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("suppression non réalisée");
		}

	}
	
	/*
	 * On update l'étudiant passé en paramètre
	 */
	@PutMapping(consumes = "application/json", produces = "application/json")
	public EtudiantDto update(@RequestBody EtudiantDto eDto) {
		return etudiantService.saveOrUpdate(eDto);
	}
		
	// ##################################################
	// # 			  Get : 1er Niveau 					#
	// ##################################################
	
	
	/*
	 * On récupère l'entreprise de l'étudiant à partir de son id
	 */
	@GetMapping(value = "/{id}/entreprise", produces = "application/json")
	public EntrepriseDto getEntrepriseByIdEtudiant(@PathVariable("id") long id){
		return etudiantService.getEntrepriseByIdEtudiant(id);
	}
	
	/*
	 * On récupère toutes les promotions auxquelles l'étudiant est inscrit à partir de son id
	 */
	@GetMapping(value = "/{id}/promotions", produces = "application/json")
	public List<PromotionDto> getPromotionsByIdEtudiant(@PathVariable("id") long id){
		return etudiantService.getPromotionsByIdEtudiant(id);
	}
	
	/*
	 * On récupère les groupes dont l'étudiant fait parti à partir de son id
	 */
	@GetMapping(value = "/{id}/groupes", produces = "application/json")
	public List<GroupeEtudiantDto> getGroupesByIdEtudiant(@PathVariable("id") long id){
		return etudiantService.getGroupesByIdEtudiant(id);
	}
	
	/*
	 * On récupère l'adresse de l'étudiant à partir de son id en passant par Personne
	 */
	@GetMapping(value = "/{id}/adresse", produces = "application/json")
	public AdresseDto getAdresseByIdEtudiant(@PathVariable("id") long id){
		return etudiantService.getAdresseByIdEtudiant(id);
	}
	
	// ##################################################
	// # 			 Get : 2eme Niveau 					#
	// ##################################################
	
	/*
	 * On récupère les notes de l'étudiants à partir de son id
	 */
	@GetMapping(value = "/{id}/notes", produces = "application/json")
	public List<NoteDto> getNotesByIdEtudiant(@PathVariable("id") long id){
		return etudiantService.getNotesByIdEtudiant(id);
	}
	
	/*
	 * On récupère les absences de l'étudiant à partir de son id
	 */
	@GetMapping(value = "/{id}/absences", produces = "application/json")
	public List<AbsenceDto> getAbsencesByIdEtudiant(@PathVariable("id") long id){
		return etudiantService.getAbsencesByIdEtudiant(id);
	}
		
	
	// ##################################################
	// # 			 Get : 3eme Niveau 					#
	// ##################################################
	
	/*
	 * On récupère les cours de l'étudiant à parti de son id en passant pas sa promotion
	 */
	@GetMapping(value = "/{id}/interventions", produces = "application/json")
	public List<InterventionDto> getIntervenionByIdEtudiant(@PathVariable("id") long id){
		return etudiantService.getIntervenionByIdEtudiant(id);
	}
	
	@GetMapping(value = "/{id}/formateurReferent", produces = "application/json")
	public UtilisateurDto getFormateurReferentByIdEtudiant(@PathVariable("id") long id){
		return etudiantService.getFormateurReferentByIdEtudiant(id);
	}
	
	@GetMapping(value = "/{id}/manager", produces = "application/json")
	public UtilisateurDto getManagerByIdEtudiant(@PathVariable("id") long id){
		return etudiantService.getManagerByIdEtudiant(id);
	}

	
	
}
