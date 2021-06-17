package fr.dawan.AppliCFABack.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.AppliCFABack.dto.AdresseDto;
import fr.dawan.AppliCFABack.services.AdresseService;

@RestController
@RequestMapping("/AppliCFABack/adresses")
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
