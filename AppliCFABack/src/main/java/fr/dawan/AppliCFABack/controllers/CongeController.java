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

import fr.dawan.AppliCFABack.dto.CongeDto;
import fr.dawan.AppliCFABack.services.CongeService;

@RestController
@RequestMapping("/AppliCFABack/conges")
public class CongeController {
	
	@Autowired
	CongeService congeService;
	
	// ##################################################
	// # GET #
	// ##################################################

	@GetMapping(produces = "application/json")
	public List<CongeDto> getAll() {
		return congeService.getAllConge();
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public CongeDto getById(@PathVariable("id") long id) {
		return congeService.getById(id);
	}

	// /AppliCFABack/groupeEtudiants/{page}/{size}
	@GetMapping(value = "/{page}/{size}", produces = "application/json")
	public @ResponseBody List<CongeDto> getAllByPage(@PathVariable("page") int page,
			@PathVariable(value = "size") int size) {
		return congeService.getAllConge(page, size);
	}

	// ##################################################
	// # POST #
	// ##################################################
	
	@CrossOrigin(origins = "*")
	@PostMapping(consumes = "application/json", produces = "application/json")
	public CongeDto save(@RequestBody CongeDto cDto) {
		return congeService.saveOrUpdate(cDto);
	}

	// ##################################################
	// # DELETE #
	// ##################################################

	@DeleteMapping(value = "/{id}", produces = "text/plain")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") long id) {
		try {
			congeService.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression effectuée");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("suppression non réalisée");
		}

	}

	// ##################################################
	// # PUT #
	// ##################################################

	@PutMapping(consumes = "application/json", produces = "application/json")
	public CongeDto update(@RequestBody CongeDto cDto) {
		return congeService.saveOrUpdate(cDto);
	}

}
