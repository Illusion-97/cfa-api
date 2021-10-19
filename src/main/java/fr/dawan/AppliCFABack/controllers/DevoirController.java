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
import fr.dawan.AppliCFABack.dto.DevoirDto;
import fr.dawan.AppliCFABack.services.DevoirService;

@RestController
@RequestMapping("/devoirs")
public class DevoirController {

	@Autowired
	DevoirService devoirService;

	// ##################################################
	// # GET #
	// ##################################################

	@GetMapping(produces = "application/json")
	public List<DevoirDto> getAll() {
		return devoirService.getAllDevoir();
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public DevoirDto getById(@PathVariable("id") long id) {
		return devoirService.getById(id);
	}

	@GetMapping(value = "/{page}/{size}", produces = "application/json")
	public @ResponseBody List<DevoirDto> getAllByPage(@PathVariable("page") int page,
			@PathVariable(value = "size") int size) {
		return devoirService.getAllByPage(page, size, "");
	}
	
	@GetMapping(value = "/{page}/{size}/{search}", produces = "application/json")
 	public @ResponseBody List<DevoirDto> getAllByPage(@PathVariable("page") int page,
 			@PathVariable(value = "size") int size, @PathVariable(value = "search", required = false) Optional<String> search) {
 		if(search.isPresent())
 			return devoirService.getAllByPage(page, size, search.get());
 		else
 			return devoirService.getAllByPage(page, size, "");
 	}

    
    @GetMapping(value = "/count/{search}", produces = "application/json")
	public CountDto count(@PathVariable(value = "search", required = false) Optional<String> search) {
		if(search.isPresent())
			return devoirService.count(search.get());
		else
			return devoirService.count("");
	}
	
	@GetMapping(value = "/count", produces = "application/json")
	public CountDto count() {
			return devoirService.count();
	}

	// ##################################################
	// # POST #
	// ##################################################

	@PostMapping(consumes = "application/json", produces = "application/json")
	public DevoirDto save(@RequestBody DevoirDto dDto) {
		return devoirService.saveOrUpdate(dDto);
	}

	// ##################################################
	// # DELETE #
	// ##################################################

	@DeleteMapping(value = "/{id}", produces = "text/plain")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") long id) {
		try {
			devoirService.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression effectuée");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("suppression non réalisée");
		}

	}

	// ##################################################
	// # PUT #
	// ##################################################

	@PutMapping(consumes = "application/json", produces = "application/json")
	public DevoirDto update(@RequestBody DevoirDto dDto) {
		return devoirService.saveOrUpdate(dDto);
	}

}
