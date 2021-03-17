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

import fr.dawan.AppliCFABack.dto.FormateurDto;
import fr.dawan.AppliCFABack.services.FormateurService;

@RestController
@RequestMapping("/AppliCFABack/formateurs")
public class FormateurController {
	@Autowired
	private FormateurService formateurService;

	// GET: /AppliCFABack/formateurs
	@GetMapping(produces = { "application/json", "application/xml" })
	public List<FormateurDto> getAll() {
		return formateurService.getAll();
	}

	// GET: /AppliCFABack/formateurs/{id}
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	public FormateurDto findById(@PathVariable("id") long id) {
		return formateurService.findById(id);
	}

	// POST: /AppliCFABack/formateurs
	@PostMapping(produces = "application/json", consumes = "application/json")
	public FormateurDto insert(@RequestBody FormateurDto fDto) {
		return formateurService.insertUpdate(fDto);
	}

	// PUT: /AppliCFABack/formateurs
	@PutMapping(produces = "application/json", consumes = "application/json")
	public FormateurDto update(@RequestBody FormateurDto fDto) {
		return formateurService.insertUpdate(fDto);
	}

	// DELETE: /AppliCFABack/formateurs/{id}
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") long id) {
		formateurService.deleteById(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Suppression ok");
	}
}
