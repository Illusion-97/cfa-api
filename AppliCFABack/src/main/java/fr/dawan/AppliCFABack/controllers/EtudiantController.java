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

import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.services.EtudiantService;

@RestController
@RequestMapping("/AppliCFABack/etudiants")
public class EtudiantController {

	@Autowired
	EtudiantService etudiantService;

	// ##################################################
	// # 					GET 						#
	// ##################################################

	@GetMapping(produces = "application/json")
	public List<EtudiantDto> getAll() {
		return etudiantService.getAll();
	}

	@GetMapping(value = "{id}", produces = "application/json")
	public EtudiantDto getById(@PathVariable("id") long id) {
		return etudiantService.getById(id);
	}
	
	// ##################################################
	// # 					POST 						#
	// ##################################################
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public EtudiantDto save(@RequestBody EtudiantDto eDto) {
		return etudiantService.saveOrUpdate(eDto);
	}

	// ##################################################
	// # 					DELETE 						#
	// ##################################################

	@DeleteMapping(value = "{id}", produces = "text/plain")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") long id) {
		try {
			etudiantService.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression effectuée");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("suppression non réalisée");
		}

	}
	
	// ##################################################
	// # 					PUT 						#
	// ##################################################
	
	@PutMapping(consumes = "application/json", produces = "application/json")
	public EtudiantDto update(@RequestBody EtudiantDto eDto) {
		return etudiantService.saveOrUpdate(eDto);
	}
}
