package fr.dawan.AppliCFABack.controllers;

import fr.dawan.AppliCFABack.dto.CompetenceProfessionnelleDto;
import fr.dawan.AppliCFABack.services.CompetenceProfessionnelleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Valentin C, Feres BG.
 * @see fr.dawan.appliCFABack.service
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return Controller de la classe activité type
 */
@RestController
@RequestMapping("/competenceProfessionnelles")
public class CompetenceProfessionnelleController {

	@Autowired
	private CompetenceProfessionnelleService competenceProfessionnelleService;

	/**
	 * 
	 * @return retourne toutes les compétences professionnelles
	 */
	@GetMapping(produces = "application/json")
	List<CompetenceProfessionnelleDto> getAll() {
		return competenceProfessionnelleService.getAllCompetenceProfessionnelle();
	}

	/**
	 * 
	 * @param id de la compétence professionnelle
	 * @return la compétence professionnelle avec l'id correspondant
	 */
	@GetMapping(value = "/{id}", produces = "application/json")
	CompetenceProfessionnelleDto getById(@PathVariable("id") long id) {
		return competenceProfessionnelleService.getById(id);
	}

	/**
	 * 
	 * @param competenceProfessionnelleDto
	 * @return statut de la requête et détail de l'objet sauvegardé
	 */
	@PostMapping(consumes = "application/json", produces = "application/json")
	ResponseEntity<CompetenceProfessionnelleDto> save(@RequestBody CompetenceProfessionnelleDto cpDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(competenceProfessionnelleService.saveOrUpdate(cpDto));
	}

	/**
	 * 
	 * @param cometenceProfessionnelleDto
	 * @return l'objet sauvegardé
	 */

	@PutMapping(consumes = "application/json", produces = "application/json")
	CompetenceProfessionnelleDto update(@RequestBody CompetenceProfessionnelleDto cptDto) {
		return competenceProfessionnelleService.saveOrUpdate(cptDto);
	}

	/**
	 * 
	 * @param id de la competence professionnelle a supprimer
	 * @return le status de la requête un message d'information
	 */
	@DeleteMapping(value = "/{id}", produces = "text/plain")
	ResponseEntity<String> delete(@PathVariable("id") long id) {
		try {
			competenceProfessionnelleService.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression effectuée");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("suppression non réalisée");
		}

	}
	
	@GetMapping(value = "/activiteType/{id}", produces = "application/json")
	public List<CompetenceProfessionnelleDto> getAllByActiviteTypeId(@PathVariable("id") long id){
		return competenceProfessionnelleService.getAllByActiviteTypeId(id);
	}

}
