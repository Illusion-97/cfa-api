package fr.dawan.AppliCFABack.controllers;

import fr.dawan.AppliCFABack.dto.AdresseDto;
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.services.AdresseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/adresses")
public class AdresseController {
	
	@Autowired
	AdresseService adresseService;
	
		// ##################################################
		// # 					GET 						#
		// ##################################################

		@GetMapping(produces = "application/json")
		public List<AdresseDto> getAllAdresse() {
			return adresseService.getAllAdresse();
		}
		@GetMapping(value = "/{id}", produces = "application/json")
		public AdresseDto getById(@PathVariable("id") long id) {
			return adresseService.getById(id);
		}
		
		@GetMapping(value = "/{page}/{size}", produces = "application/json")
		public @ResponseBody List<AdresseDto> getAllByPage(@PathVariable("page") int page,
				@PathVariable(value = "size") int size) {
			return adresseService.getAllByPage(page, size, "");
		}
		
		@GetMapping(value = "/{page}/{size}/{search}", produces = "application/json")
	 	public @ResponseBody List<AdresseDto> getAllByPage(@PathVariable("page") int page,
	 			@PathVariable(value = "size") int size, @PathVariable(value = "search", required = false) Optional<String> search) {
	 		if(search.isPresent())
	 			return adresseService.getAllAdresses(page, size, search.get());
	 		else
	 			return adresseService.getAllAdresses(page, size, "");
		}
	 	
		
		@GetMapping(value = "/count", produces = "application/json")
		public CountDto count() {
			return adresseService.count("");
		}
		
		@GetMapping(value = "/count/{search}", produces = "application/json")
		public CountDto count(@PathVariable(value = "search", required = false) Optional<String> search) {
			if(search.isPresent())
				return adresseService.count(search.get());
			else
				return adresseService.count("");
		}
		
		// ##################################################
		// # 					POST 						#
		// ##################################################
		
		@PostMapping(consumes = "application/json", produces = "application/json")
		public AdresseDto save(@RequestBody AdresseDto aDto) {
			return adresseService.saveOrUpdate(aDto);
		}

		// ##################################################
		// # 					DELETE 						#
		// ##################################################

		@DeleteMapping(value = "/{id}", produces = "text/plain")
		public ResponseEntity<?> deleteById(@PathVariable(value = "id") long id) {
			try {
				adresseService.deleteById(id);
				return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression effectuée");
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("suppression non réalisée");
			}

		}
		
		// ##################################################
		// # 					PUT 						#
		// ##################################################
		
		@PutMapping(consumes = "application/json", produces = "application/json")
		public AdresseDto update(@RequestBody AdresseDto aDto) {
			return adresseService.saveOrUpdate(aDto);
		}

}
