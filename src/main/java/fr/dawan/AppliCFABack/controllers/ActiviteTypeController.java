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

import fr.dawan.AppliCFABack.dto.ActiviteTypeDto;
import fr.dawan.AppliCFABack.services.ActiviteTypeService;

/**
 * @author Valentin C, Feres BG.
 * @see fr.dawan.appliCFABack.service
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return Controller de la classe activité type
 */
@RestController
@RequestMapping("/activiteTypes")
public class ActiviteTypeController {
	@Autowired
	private ActiviteTypeService activiteTypeService;

	/**
	 * 
	 * @return récupère toutes les activités types
	 */
	@GetMapping(produces = "application/json")
	List<ActiviteTypeDto> getAll() {
		return activiteTypeService.getAllActiviteType();
	}

	/**
	 * 
	 * @param id de la promotion
	 * @return retourne toutes les activités types présentes dans une promotion
	 */
	@GetMapping(value = "/promotion/{id}", produces = "application/json")
	List<ActiviteTypeDto> getAllByPromotion(@PathVariable("id") long id) {
		return activiteTypeService.getAllActiviteTypesByPromotionId(id);
	}

	/**
	 * 
	 * @param id de l'activite type
	 * @return l'activité type
	 */
	@GetMapping(value = "/{id}", produces = "application/json")
	ActiviteTypeDto getById(@PathVariable("id") long id) {

		return activiteTypeService.getById(id);
	}

	/**
	 * 
	 * @param ActiviteType DTO Object
	 * @return méthode de sauvegarde d'un objet en base de données
	 */
	@PostMapping(consumes = "application/json", produces = "application/json")
	ResponseEntity<ActiviteTypeDto> save(@RequestBody ActiviteTypeDto actDto) {

		return ResponseEntity.status(HttpStatus.CREATED).body(activiteTypeService.saveOrUpdate(actDto));
	}

	/**
	 * 
	 * @param ActiviteType DTO Object
	 * @return méthode de mise à jour d'un objet en base de données
	 */
	@PutMapping(consumes = "application/json", produces = "application/json")
	ActiviteTypeDto update(@RequestBody ActiviteTypeDto actDto) {
		return activiteTypeService.saveOrUpdate(actDto);
	}

	/**
	 * 
	 * @param id a supprimer
	 * @return le status de la requête un message d'information
	 */
	@DeleteMapping(value = "/{id}", produces = "text/plain")
	public ResponseEntity<String> deleteById(@PathVariable(value = "id") long id) {
		try {
			activiteTypeService.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression effectuée");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("suppression non réalisée");
		}

	}

	@GetMapping(value = "/cursus/{id}", produces = "application/json")
	List<ActiviteTypeDto> getAllByCursus(@PathVariable("id") long id) {
		return activiteTypeService.getAllActiviteTypesByCursus(id);
	}
}
