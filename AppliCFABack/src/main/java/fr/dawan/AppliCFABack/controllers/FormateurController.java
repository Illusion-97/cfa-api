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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.AppliCFABack.dto.FormateurDto;
import fr.dawan.AppliCFABack.services.FormateurService;

@RestController
@RequestMapping("/AppliCFABack/formateurs")
public class FormateurController {
	@Autowired
	FormateurService formateurService;

	@GetMapping(produces = "application/json")
	public List<FormateurDto> getAll() {
		return formateurService.getAll();
	}

	// GET : /AppliCFABack/formateurs/{id}
	@GetMapping(produces = "application/json", value = "/{id}")
	public FormateurDto getById(@PathVariable("id") long id) {
		return formateurService.getById(id);
	}

	// GET : /AppliCFABack/formateurs/{id}/interventions
	@GetMapping(produces = "application/json", value = "/{id}/interventions")
	public FormateurDto getByIdWithObject(@PathVariable("id") long id) {
		return formateurService.getByIdWithObject(id);
	}

	// GET : /AppliCFABack/formateurs/{page}/{size}
	@GetMapping(produces = "application/json", value = "/{page}/{size}")
	public @ResponseBody List<FormateurDto> getAll(@PathVariable("page") int page, @PathVariable("size") int size) {
		return formateurService.getAllByPage(page, size);
	}

	// GET : /AppliCFABack/formateurs/with-object
	@GetMapping(value = "/with-object", produces = "application/json")
	public List<FormateurDto> getAllWithObject() {
		return formateurService.getAllWithObject();
	}

	// POST : /AppliCFABack/formateurs
	@PostMapping(consumes = "application/json", produces = "application/json")
	public FormateurDto save(@RequestBody FormateurDto fDto) {
		return formateurService.saveOrUpdate(fDto);
	}

	// DELETE : /AppliCFABack/formateurs/{id}
	@DeleteMapping(value = "/{id}", produces = "text/plain")
	public ResponseEntity<?> deleteById(@PathVariable("id") long id) {
		try {
			formateurService.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression effectuée");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("suppression non réalisée");
		}
	}

	// PUT : /AppliCFABack/formateurs
	@PutMapping(consumes = "application/json", produces = "application/json")
	public FormateurDto update(@RequestBody FormateurDto fDto) {
		return formateurService.saveOrUpdate(fDto);
	}
}
