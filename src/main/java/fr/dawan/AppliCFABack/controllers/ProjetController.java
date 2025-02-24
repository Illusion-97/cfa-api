package fr.dawan.AppliCFABack.controllers;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.GroupeEtudiantDto;
import fr.dawan.AppliCFABack.dto.ProjetDto;
import fr.dawan.AppliCFABack.services.EtudiantService;
import fr.dawan.AppliCFABack.services.ProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projets")
public class ProjetController {

	@Autowired
	ProjetService projetService;
	
	@Autowired
	EtudiantService etudiantService;

	// ##################################################
	// # GET #
	// ##################################################

	@GetMapping(produces = "application/json")
	public List<ProjetDto> getAll() {
		return projetService.getAllProjet();
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public ProjetDto getById(@PathVariable("id") long id) {
		return projetService.getById(id);
	}

	@GetMapping(value = "/{page}/{size}", produces = "application/json")
	public @ResponseBody List<ProjetDto> getAllByPage(@PathVariable("page") int page,
			@PathVariable(value = "size") int size) {
		return projetService.getAllByPage(page, size, "");
	}
	
	@GetMapping(value = "/{page}/{size}/{search}", produces = "application/json")
 	public @ResponseBody List<ProjetDto> getAllByPage(@PathVariable("page") int page,
 			@PathVariable(value = "size") int size, @PathVariable(value = "search", required = false) Optional<String> search) {
 		if(search.isPresent())
 			return projetService.getAllByPage(page, size, search.get());
 		else
 			return projetService.getAllByPage(page, size, "");
 	}

		
	@GetMapping(value = "/count", produces = "application/json")
	public CountDto count() {
		return projetService.count("");
	}
    
    @GetMapping(value = "/count/{search}", produces = "application/json")
	public CountDto count(@PathVariable(value = "search", required = false) Optional<String> search) {
		if(search.isPresent())
			return projetService.count(search.get());
		else
			return projetService.count("");
	}
    
    
    @GetMapping(value = "/groupes/{id}", produces = "application/json")
	public List<ProjetDto> getByGroupeId(@PathVariable("id") long id) {
    	return projetService.getByGroupeId(id);
	}
    @GetMapping(value = "/etudiant/{id}", produces = "application/json")
   	public List<ProjetDto> getByIdEtudiant(@PathVariable("id") long id) {
       EtudiantDto eDto = etudiantService.getById(id);
       List<ProjetDto> lstProjetDto = projetService.getAllProjet();
       List<GroupeEtudiantDto> gpeDto = eDto.getGroupesDto();
       List<ProjetDto> lstProjetDtoEtudiant = new ArrayList<>();
       for (GroupeEtudiantDto groupeEtudiantDto : gpeDto) {
    	  for (ProjetDto projetDto : lstProjetDto) {
    		  if (projetDto.getGroupeId()==groupeEtudiantDto.getId()) {
				lstProjetDtoEtudiant.add(projetDto);
			}

		}
	}
       	return lstProjetDtoEtudiant;
   	}
    
	// ##################################################
	// # POST #
	// ##################################################

	@PostMapping(consumes = "application/json", produces = "application/json")
	public ProjetDto save(@RequestBody ProjetDto pDto) {
		return projetService.saveOrUpdate(pDto);
	}

	// ##################################################
	// # DELETE #
	// ##################################################

	@DeleteMapping(value = "/{id}", produces = "text/plain")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") long id) {
		try {
			projetService.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression effectuée");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("suppression non réalisée");
		}

	}

	// ##################################################
	// # PUT #
	// ##################################################

	@PutMapping(consumes = "application/json", produces = "application/json")
	public ProjetDto update(@RequestBody ProjetDto pDto) {
		return projetService.saveOrUpdate(pDto);
	}
}
