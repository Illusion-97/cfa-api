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

import fr.dawan.AppliCFABack.dto.GroupeDto;
import fr.dawan.AppliCFABack.services.GroupeService;

@RestController
@RequestMapping("/AppliCFABack/groupes")
public class GroupeController {

	@Autowired
	GroupeService groupeService;

	// ##################################################
	// # 					GET 						#
	// ##################################################

	@GetMapping(produces = "application/json")
	public List<GroupeDto> getAll() {
		return groupeService.getAllGroupe();
	}
	@GetMapping(value = "/{id}", produces = "application/json")
	public GroupeDto getById(@PathVariable("id") long id) {
		return groupeService.getById(id);
	}
	
	// ##################################################
	// # 					POST 						#
	// ##################################################
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public GroupeDto save(@RequestBody GroupeDto gDto) {
		return groupeService.saveOrUpdate(gDto);
	}

	// ##################################################
	// # 					DELETE 						#
	// ##################################################

	@DeleteMapping(value = "/{id}", produces = "text/plain")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") long id) {
		try {
			groupeService.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression effectuée");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("suppression non réalisée");
		}

	}
	
	// ##################################################
	// # 					PUT 						#
	// ##################################################
	
	@PutMapping(consumes = "application/json", produces = "application/json")
	public GroupeDto update(@RequestBody GroupeDto eDto) {
		return groupeService.saveOrUpdate(eDto);
	}

}
