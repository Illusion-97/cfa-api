package fr.dawan.AppliCFABack.controllers;

import java.util.List;
import java.util.Optional;

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

import fr.dawan.AppliCFABack.dto.FormationDto;
import fr.dawan.AppliCFABack.services.FormationService;

@RestController
@RequestMapping("/AppliCFABack/formations")
public class FormationController {

	@Autowired
	FormationService formationService;

	// ##################################################
	// # GET #
	// ##################################################

	@GetMapping(produces = "application/json")
	public List<FormationDto> getAll() {
		return formationService.getAllFormation();
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public FormationDto getById(@PathVariable("id") long id) {
		return formationService.getById(id);
	}

	// /AppliCFABack/groupeEtudiants/{page}/{size}
	@GetMapping(value = "/{page}/{size}", produces = "application/json")
	public @ResponseBody List<FormationDto> getAllByPage(@PathVariable("page") Optional<Integer> page,
			@PathVariable(value = "size") Optional<Integer> size) {
		if(page.isPresent() && size.isPresent())
			return formationService.getAllFormation(page.get(), size.get());
		else 
			return formationService.getAllFormation();
	}

	// ##################################################
	// # POST #
	// ##################################################
	@CrossOrigin(origins = "*")
	@PostMapping(consumes = "application/json", produces = "application/json")
	public FormationDto save(@RequestBody FormationDto fDto) {
		return formationService.saveOrUpdate(fDto);
	}

	// ##################################################
	// # DELETE #
	// ##################################################

	@DeleteMapping(value = "/{id}", produces = "text/plain")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") long id) {
		try {
			formationService.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression effectuée");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("suppression non réalisée");
		}

	}

	// ##################################################
	// # PUT #
	// ##################################################

	@PutMapping(consumes = "application/json", produces = "application/json")
	public FormationDto update(@RequestBody FormationDto fDto) {
		return formationService.saveOrUpdate(fDto);
	}
}
