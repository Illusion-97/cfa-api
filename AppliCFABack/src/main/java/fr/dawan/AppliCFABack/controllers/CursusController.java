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

import fr.dawan.AppliCFABack.dto.CursusDto;
import fr.dawan.AppliCFABack.services.CursusService;

@RestController
@RequestMapping("/AppliCFABack/cursus")
public class CursusController {

	
	@Autowired
	private CursusService cursusService;
	
	@GetMapping(produces = "application/json")
	public List<CursusDto> getAll() {
		return cursusService.getAll();
	}
	
	@GetMapping(value = "/{id}",produces = "application/json")
	public CursusDto getById(@PathVariable("id") long id) {
		return cursusService.getById(id);
	}
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public CursusDto save(@RequestBody CursusDto cDto) {
		return cursusService.saveOrUpdate(cDto);
	}
	
	
	@DeleteMapping(value = "/delete/{id}", produces = "text/plain")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") long id) {
		try {
			cursusService.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression effectuée");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("suppression non réalisée");
		}

	}
	
	
	@PutMapping(consumes = "application/json", produces = "application/json")
	public CursusDto update(@RequestBody CursusDto cDto) {
		return cursusService.saveOrUpdate(cDto);
	}
	
}
