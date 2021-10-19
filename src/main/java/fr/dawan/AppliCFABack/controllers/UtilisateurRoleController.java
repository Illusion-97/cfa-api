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
import fr.dawan.AppliCFABack.dto.UtilisateurRoleDto;
import fr.dawan.AppliCFABack.services.UtilisateurRoleService;

@RestController
@RequestMapping("/utilisateursRoles")
public class UtilisateurRoleController {
	
	@Autowired
	UtilisateurRoleService utilisateurRoleService;

	// ##################################################
	// # GET #
	// ##################################################

	@GetMapping(produces = "application/json")
	public List<UtilisateurRoleDto> getAll() {
		return utilisateurRoleService.getAllUtilisateurRole();
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public UtilisateurRoleDto getById(@PathVariable("id") long id) {
		return utilisateurRoleService.getById(id);
	}

	@GetMapping(value = "/{page}/{size}", produces = "application/json")
	public @ResponseBody List<UtilisateurRoleDto> getAllByPage(@PathVariable("page") int page,
			@PathVariable(value = "size") int size) {
		return utilisateurRoleService.getAllByPage(page, size, "");
	}
	
	@GetMapping(value = "/{page}/{size}/{search}", produces = "application/json")
 	public @ResponseBody List<UtilisateurRoleDto> getAllByPage(@PathVariable("page") int page,
 			@PathVariable(value = "size") int size, @PathVariable(value = "search", required = false) Optional<String> search) {
 		if(search.isPresent())
 			return utilisateurRoleService.getAllByPage(page, size, search.get());
 		else
 			return utilisateurRoleService.getAllByPage(page, size, "");
 	}

		
	@GetMapping(value = "/count", produces = "application/json")
	public CountDto count() {
		return utilisateurRoleService.count("");
	}
    
    @GetMapping(value = "/count/{search}", produces = "application/json")
	public CountDto count(@PathVariable(value = "search", required = false) Optional<String> search) {
		if(search.isPresent())
			return utilisateurRoleService.count(search.get());
		else
			return utilisateurRoleService.count("");
	}


	// ##################################################
	// # POST #
	// ##################################################

	@PostMapping(consumes = "application/json", produces = "application/json")
	public UtilisateurRoleDto save(@RequestBody UtilisateurRoleDto uDto) {
		return utilisateurRoleService.saveOrUpdate(uDto);
	}

	// ##################################################
	// # DELETE #
	// ##################################################

	@DeleteMapping(value = "/{id}", produces = "text/plain")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") long id) {
		try {
			utilisateurRoleService.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression effectuée");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("suppression non réalisée");
		}

	}

	// ##################################################
	// # PUT #
	// ##################################################

	@PutMapping(consumes = "application/json", produces = "application/json")
	public UtilisateurRoleDto update(@RequestBody UtilisateurRoleDto uDto) {
		return utilisateurRoleService.saveOrUpdate(uDto);
	}

}
