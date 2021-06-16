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
	
	@GetMapping(value = "/{page}/{size}", produces = "application/json")
	public @ResponseBody List<CursusDto> getAllByPage(@PathVariable("page") int page,
			@PathVariable(value = "size") int size) {
		return cursusService.getAllByPage(page, size, "");
	}
	
	@GetMapping(value = "/{page}/{size}/{search}", produces = "application/json")
 	public @ResponseBody List<CursusDto> getAllByPage(@PathVariable("page") int page,
 			@PathVariable(value = "size") int size, @PathVariable(value = "search", required = false) Optional<String> search) {
 		if(search.isPresent())
 			return cursusService.getAllByPage(page, size, search.get());
 		else
 			return cursusService.getAllByPage(page, size, "");
 	}

		
	@GetMapping(value = "/count", produces = "application/json")
	public CountDto count() {
		return cursusService.count("");
	}
    
    @GetMapping(value = "/count/{search}", produces = "application/json")
	public CountDto count(@PathVariable(value = "search", required = false) Optional<String> search) {
		if(search.isPresent())
			return cursusService.count(search.get());
		else
			return cursusService.count("");
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
