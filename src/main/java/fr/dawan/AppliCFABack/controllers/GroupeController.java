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
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.GroupeEtudiantDto;
import fr.dawan.AppliCFABack.services.GroupeEtudiantService;

@RestController
@RequestMapping("/groupeEtudiants")
public class GroupeController {

	@Autowired
	GroupeEtudiantService groupeEtudiantService;

	// ##################################################
	// # GET #
	// ##################################################

	@GetMapping(produces = "application/json")
	public List<GroupeEtudiantDto> getAll() {
		System.out.println("Controller getAll");
		return groupeEtudiantService.getAllGroupeEtudiant();
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public GroupeEtudiantDto getById(@PathVariable("id") long id) {
		return groupeEtudiantService.getById(id);
	}

	@GetMapping(value = "/{page}/{size}", produces = "application/json")
	public @ResponseBody List<GroupeEtudiantDto> getAllByPage(@PathVariable("page") int page,
			@PathVariable(value = "size") int size) {
		return groupeEtudiantService.getAllByPage(page, size, "");
	}
	
	@GetMapping(value = "/{page}/{size}/{search}", produces = "application/json")
 	public @ResponseBody List<GroupeEtudiantDto> getAllByPage(@PathVariable("page") int page,
 			@PathVariable(value = "size") int size, @PathVariable(value = "search", required = false) Optional<String> search) {
 		if(search.isPresent())
 			return groupeEtudiantService.getAllByPage(page, size, search.get());
 		else
 			return groupeEtudiantService.getAllByPage(page, size, "");
 	}

		
	@GetMapping(value = "/count", produces = "application/json")
	public CountDto count() {
		return groupeEtudiantService.count("");
	}
    
    @GetMapping(value = "/count/{search}", produces = "application/json")
	public CountDto count(@PathVariable(value = "search", required = false) Optional<String> search) {
		if(search.isPresent())
			return groupeEtudiantService.count(search.get());
		else
			return groupeEtudiantService.count("");
	}
    
    @GetMapping(value = "/{id}/etudiants", produces = "application/json")
	public List<EtudiantDto> getEtudiantsByGroupeId(@PathVariable("id") long id) {
    	System.out.println("getEtudiantsByGroupeId : " + id);
		return groupeEtudiantService.getEtudiantsByGroupeId(id);
	}

	// ##################################################
	// # POST #
	// ##################################################

	@PostMapping(consumes = "application/json", produces = "application/json")
	public GroupeEtudiantDto save(@RequestBody GroupeEtudiantDto gDto) {
		System.out.println("Controller @PostMapping");
		return groupeEtudiantService.saveOrUpdate(gDto);
	}

	// ##################################################
	// # DELETE #
	// ##################################################

	@DeleteMapping(value = "/{id}", produces = "text/plain")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") long id) {
		try {
			groupeEtudiantService.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression effectuée");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("suppression non réalisée");
		}

	}

	// ##################################################
	// # PUT #
	// ##################################################

	@PutMapping(consumes = "application/json", produces = "application/json")
	public GroupeEtudiantDto update(@RequestBody GroupeEtudiantDto eDto) {
		System.out.println("Controller @PutMapping");
		return groupeEtudiantService.saveOrUpdate(eDto);
	}

}
