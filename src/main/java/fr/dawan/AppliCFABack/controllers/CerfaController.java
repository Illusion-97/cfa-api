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

import fr.dawan.AppliCFABack.dto.CerfaDto;
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.FichePosteDto;
import fr.dawan.AppliCFABack.services.CerfaService;

@RestController
@RequestMapping("/cerfa")
public class CerfaController {
	
	@Autowired
	private CerfaService cerfaService;

	public void setCerfaService(CerfaService cerfaService){
		this.cerfaService = cerfaService;
	}
	
	@GetMapping(produces = "application/json")
	public List<CerfaDto> getAll() {
		return cerfaService.getAll();
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public CerfaDto getById(@PathVariable("id") long id) {
		return cerfaService.getById(id);
	}

	
	@GetMapping(value = "/{page}/{size}", produces = "application/json")
	public @ResponseBody List<CerfaDto> getAllByPage(@PathVariable("page") int page,
			@PathVariable(value = "size") int size) {
		return cerfaService.getAllByPage(page, size, "");
	}
	
	@GetMapping(value = "/{page}/{size}/{search}", produces = "application/json")
 	public @ResponseBody List<CerfaDto> getAllByPage(@PathVariable("page") int page,
 			@PathVariable(value = "size") int size, @PathVariable(value = "search", required = false) Optional<String> search) {
 		if(search.isPresent())
 			return cerfaService.getAllByPage(page, size, search.get());
 		else
 			return cerfaService.getAllByPage(page, size, "");
 	}

		
	@GetMapping(value = "/count", produces = "application/json")
	public CountDto count() {
		return cerfaService.count("");
	}
    
    @GetMapping(value = "/count/{search}", produces = "application/json")
	public CountDto count(@PathVariable(value = "search", required = false) Optional<String> search) {
		if(search.isPresent())
			return cerfaService.count(search.get());
		else
			return cerfaService.count("");
	}
    @GetMapping(value = "/etudiant/{id}", produces = "application/json")
 	public CerfaDto getByIdEtudiant(@PathVariable("id") long id) {
 		return cerfaService.getByIdEtudiant(id);
 	} 
    	// ##################################################
 		// # 					POST 						#
 		// ##################################################
 		
 		@PostMapping(consumes = "application/json", produces = "application/json")
 		public CerfaDto save(@RequestBody CerfaDto cDto) {
 			return cerfaService.saveOrUpdate(cDto);
 		}

 		// ##################################################
 		// # 					DELETE 						#
 		// ##################################################

 		@DeleteMapping(value = "/{id}", produces = "text/plain")
 		public ResponseEntity<?> deleteById(@PathVariable(value = "id") long id) {
 			try {
 				cerfaService.deleteById(id);
 				return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression effectuée");
 			} catch (Exception e) {
 				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("suppression non réalisée");
 			}

 		}
 		
 		// ##################################################
 		// # 					PUT 						#
 		// ##################################################
 		
 		@PutMapping(consumes = "application/json", produces = "application/json")
 		public CerfaDto update(@RequestBody CerfaDto cDto) {
 			return cerfaService.saveOrUpdate(cDto);
 		}

}
