package fr.dawan.AppliCFABack.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.AppliCFABack.dto.InterventionDto;
import fr.dawan.AppliCFABack.services.InterventionService;

@RestController
@RequestMapping("/AppliCFABack/interventions")
public class InterventionController {

	@Autowired
	InterventionService interventionService;

	// ##################################################
	// # GET #
	// ##################################################

	@GetMapping(produces = "application/json")
	public List<InterventionDto> getAll() {
		return interventionService.getAllIntervention();
	}

	@GetMapping(produces = "application/json", value = "/with-object")
	public List<InterventionDto> getAllWithObject() {
		return interventionService.getAllInterventionWithObject();
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public InterventionDto getById(@PathVariable("id") long id) {
		return interventionService.getById(id);
	}

	// /AppliCFABack/groupeEtudiants/{page}/{size}
	@GetMapping(value = "/{page}/{size}", produces = "application/json")
	public @ResponseBody List<InterventionDto> getAllByPage(@PathVariable("page") int page,
			@PathVariable(value = "size") int size) {
		return interventionService.getAllIntervention(page, size);
	}

	// ##################################################
	// # POST #
	// ##################################################
	@CrossOrigin(origins = "*")
	@PostMapping(consumes = "application/json", produces = "application/json")
	public InterventionDto save(@RequestBody InterventionDto iDto) {
		return interventionService.saveOrUpdate(iDto);
	}

	// ##################################################
	// # DELETE #
	// ##################################################

	@DeleteMapping(value = "/{id}", produces = "text/plain")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") long id) {
		try {
			interventionService.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression effectuée");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("suppression non réalisée");
		}

	}

	// ##################################################
	// # PUT #
	// ##################################################

	@PutMapping(consumes = "application/json", produces = "application/json")
	public InterventionDto update(@RequestBody InterventionDto iDto) {
		return interventionService.saveOrUpdate(iDto);
	}
}
