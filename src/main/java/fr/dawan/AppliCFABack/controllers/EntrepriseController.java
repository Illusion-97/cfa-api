package fr.dawan.AppliCFABack.controllers;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.EntrepriseDto;
import fr.dawan.AppliCFABack.services.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/entreprises")
public class EntrepriseController {

	@Autowired
	EntrepriseService entrepriseService;

	// ##################################################
	// # GET #
	// ##################################################

	@GetMapping(produces = "application/json")
	public List<EntrepriseDto> getAll() {
		return entrepriseService.getAllEntreprise();
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public EntrepriseDto getById(@PathVariable("id") long id) {
		return entrepriseService.getById(id);
	}

	/**
	 * 
	 * @param page
	 * @param size
	 * @return all entreprises by page
	 */
	@GetMapping(value = "/{page}/{size}", produces = "application/json")
	public @ResponseBody List<EntrepriseDto> getAllByPage(@PathVariable("page") int page,
			@PathVariable(value = "size") int size) {
		return entrepriseService.getAllEntreprise(page, size);
	}
	
	@GetMapping(value = "/{page}/{size}/{search}", produces = "application/json")
 	public @ResponseBody List<EntrepriseDto> getAllByPage(@PathVariable("page") int page,
 			@PathVariable(value = "size") int size, @PathVariable(value = "search", required = false) Optional<String> search) {
 		if(search.isPresent())
 			return entrepriseService.getAllEntreprises(page, size, search.get());
 		else
 			return entrepriseService.getAllEntreprises(page, size, "");
 	}
	
	@GetMapping(value = "/count", produces = "application/json")
	public CountDto count() {
		return entrepriseService.count("");
	}
    
    @GetMapping(value = "/count/{search}", produces = "application/json")
	public CountDto count(@PathVariable(value = "search", required = false) Optional<String> search) {
		if(search.isPresent())
			return entrepriseService.count(search.get());
		else
			return entrepriseService.count("");
	}

	// ##################################################
	// # POST #
	// ##################################################

	@PostMapping(consumes = "application/json", produces = "application/json")
	public EntrepriseDto save(@RequestBody EntrepriseDto eDto) {
		return entrepriseService.saveOrUpdate(eDto);
	}

	// ##################################################
	// # DELETE #
	// ##################################################

	@DeleteMapping(value = "/{id}", produces = "text/plain")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") long id) {
		try {
			entrepriseService.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression effectuée");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("suppression non réalisée");
		}

	}

	// ##################################################
	// # PUT #
	// ##################################################

	@PutMapping(consumes = "application/json", produces = "application/json")
	public EntrepriseDto update(@RequestBody EntrepriseDto eDto) {
		return entrepriseService.saveOrUpdate(eDto);
	}
}
