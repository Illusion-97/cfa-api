package fr.dawan.AppliCFABack.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@GetMapping(produces = "application/json", value = "/{id}")
	public FormateurDto getById(@PathVariable("id") long id) {
		return formateurService.getById(id);
	}

	@GetMapping(produces = "application/json", value = "/{page}/{size}")
	public @ResponseBody List<FormateurDto> getAll(@PathVariable("page") int page, @PathVariable("size") int size) {
		return formateurService.getAllByPage(page, size);
	}

	@GetMapping(value = "/with-object", produces = "application/json")
	public List<FormateurDto> getAllWithObject() {
		return formateurService.getAllWithObject();
	}

	@PostMapping(consumes = "application/json", produces = "application/json")
	public FormateurDto saveOrUpdate(@RequestBody FormateurDto fDto) {
		return formateurService.saveOrUpdate(fDto);
	}

	@DeleteMapping(value = "/{id}", produces = "text/plain")
	public ResponseEntity<?> deleteById(@PathVariable("id") long id) {
		try {
			formateurService.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression effectuée");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("suppression non réalisée");
		}
	}
}
