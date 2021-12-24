package fr.dawan.AppliCFABack.controllers;

import java.util.List;
import java.util.Optional;

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

import fr.dawan.AppliCFABack.dto.ContratDto;
import fr.dawan.AppliCFABack.services.ContratService;

@RestController
@RequestMapping("/contrats")
public class ContratController {

	@Autowired
	ContratService contratService;
	
	// ##################################################
		// # GET #
		// ##################################################

		@GetMapping(produces = "application/json")
		public List<ContratDto> getAll() {
			return contratService.getAllContrat();
		}

		@GetMapping(value = "/{id}", produces = "application/json")
		public ContratDto getById(@PathVariable("id") long id) {
			return contratService.getById(id);
		}
		@GetMapping(value = "/etudiant{id}", produces = "application/json")
		public ContratDto getByEtudiantId(@PathVariable("id") long id) {
			return contratService.getByEtudiantId(id);
		}

		
		@GetMapping(value = "/{page}/{size}", produces = "application/json")
		public @ResponseBody List<ContratDto> getAllByPage(@PathVariable("page") int page,
				@PathVariable(value = "size") int size) {
			return contratService.getAllByPage(page, size, "");
		}
		
		@GetMapping(value = "/{page}/{size}/{search}", produces = "application/json")
	 	public @ResponseBody List<ContratDto> getAllByPage(@PathVariable("page") int page,
	 			@PathVariable(value = "size") int size, @PathVariable(value = "search", required = false) Optional<String> search) {
	 		if(search.isPresent())
	 			return contratService.getAllByPage(page, size, search.get());
	 		else
	 			return contratService.getAllByPage(page, size, "");
	 	}

			
		@GetMapping(value = "/count", produces = "application/json")
		public ContratDto count() {
			return contratService.count("");
		}
	    
	    @GetMapping(value = "/count/{search}", produces = "application/json")
		public ContratDto count(@PathVariable(value = "search", required = false) Optional<String> search) {
			if(search.isPresent())
				return contratService.count(search.get());
			else
				return contratService.count("");
		}
	    
	 // ##################################################
		// # POST #
		// ##################################################
		
		@PostMapping(consumes = "application/json", produces = "application/json")
		public ContratDto save(@RequestBody ContratDto cDto) {
			return contratService.saveOrUpdate(cDto);
		}

		// ##################################################
		// # DELETE #
		// ##################################################

		@DeleteMapping(value = "/{id}", produces = "text/plain")
		public ResponseEntity<?> deleteById(@PathVariable(value = "id") long id) {
			try {
				contratService.deleteById(id);
				return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression effectuée");
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("suppression non réalisée");
			}

		}

		// ##################################################
		// # PUT #
		// ##################################################

		@PutMapping(consumes = "application/json", produces = "application/json")
		public ContratDto update(@RequestBody ContratDto cDto) {
			return contratService.saveOrUpdate(cDto);
		}
}
