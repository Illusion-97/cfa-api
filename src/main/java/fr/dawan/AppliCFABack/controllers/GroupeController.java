package fr.dawan.AppliCFABack.controllers;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.GroupeEtudiantDto;
import fr.dawan.AppliCFABack.services.GroupeEtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/groupeEtudiants")
public class GroupeController {

	@Autowired
	GroupeEtudiantService groupeEtudiantService;
	
	private static final Logger logger = Logger.getGlobal();

	// ##################################################
	// # GET #
	// ##################################################

	@GetMapping(produces = "application/json")
	public List<GroupeEtudiantDto> getAll() {
		logger.info("Controller getAll");
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
    	logger.log(Level.INFO, "getEtudiantsByGroupeId : ", id);
		return groupeEtudiantService.getEtudiantsByGroupeId(id);
	}

	// ##################################################
	// # POST #
	// ##################################################

	@PostMapping(consumes = "application/json", produces = "application/json")
	public GroupeEtudiantDto save(@RequestBody GroupeEtudiantDto gDto) {
		logger.info("Controller @PostMapping");
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
		logger.info("Controller @PutMapping");
		return groupeEtudiantService.saveOrUpdate(eDto);
	}

}
