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

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.FichePosteDto;
import fr.dawan.AppliCFABack.services.FichePosteService;

@RestController
@RequestMapping("/fichePostes")
public class FichePosteController {
	
	@Autowired
	FichePosteService fichePosteService;

	// ##################################################
	// # GET #
	// ##################################################

	@GetMapping(produces = "application/json")
	public List<FichePosteDto> getAll() {
		return fichePosteService.getAllFichePoste();
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public FichePosteDto getById(@PathVariable("id") long id) {
		return fichePosteService.getById(id);
	}

	@GetMapping(value = "/{page}/{size}", produces = "application/json")
	public @ResponseBody List<FichePosteDto> getAllByPage(@PathVariable("page") int page,
			@PathVariable(value = "size") int size) {
		return fichePosteService.getAllByPage(page, size, "");
	}
	
	@GetMapping(value = "/{page}/{size}/{search}", produces = "application/json")
 	public @ResponseBody List<FichePosteDto> getAllByPage(@PathVariable("page") int page,
 			@PathVariable(value = "size") int size, @PathVariable(value = "search", required = false) Optional<String> search) {
 		if(search.isPresent())
 			return fichePosteService.getAllByPage(page, size, search.get());
 		else
 			return fichePosteService.getAllByPage(page, size, "");
 	}

		
	@GetMapping(value = "/count", produces = "application/json")
	public CountDto count() {
		return fichePosteService.count("");
	}
    
    @GetMapping(value = "/count/{search}", produces = "application/json")
	public CountDto count(@PathVariable(value = "search", required = false) Optional<String> search) {
		if(search.isPresent())
			return fichePosteService.count(search.get());
		else
			return fichePosteService.count("");
	}
    
    @GetMapping(value = "/etudiant/{id}", produces = "application/json")
	public FichePosteDto getByIdEtudiant(@PathVariable("id") long id) {
		return fichePosteService.getByIdEtudiant(id);
	}
    


	// ##################################################
	// # POST #
	// ##################################################

	@PostMapping(consumes = "application/json", produces = "application/json")
	public FichePosteDto save(@RequestBody FichePosteDto fDto) {
		return fichePosteService.saveOrUpdate(fDto);
	}

	// ##################################################
	// # DELETE #
	// ##################################################

	@DeleteMapping(value = "/{id}", produces = "text/plain")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") long id) {
		try {
			fichePosteService.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression effectuée");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("suppression non réalisée");
		}

	}

	// ##################################################
	// # PUT #
	// ##################################################

	@PutMapping(consumes = "application/json", produces = "application/json")
	public FichePosteDto update(@RequestBody FichePosteDto fDto) {
		return fichePosteService.saveOrUpdate(fDto);
	}

}
