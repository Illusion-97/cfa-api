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

import fr.dawan.AppliCFABack.dto.CompetenceProfessionnelleDto;
import fr.dawan.AppliCFABack.services.CompetenceProfessionnelleService;


@RestController
@RequestMapping("/competenceProfessionnelles")
public class CompetenceProfessionnelleController {

	@Autowired
	private CompetenceProfessionnelleService competenceProfessionnelleService;
	
	@GetMapping(produces = "application/json")
	List<CompetenceProfessionnelleDto> getAll(){
		return competenceProfessionnelleService.getAllCompetenceProfessionnelle();
	}
	
	@GetMapping(value = "/{id}", produces ="application/json" )
	CompetenceProfessionnelleDto getById(@PathVariable("id") long id) {
		return competenceProfessionnelleService.getById(id);
	}
	
	@PostMapping(consumes = "application/json",produces = "application/json")
	ResponseEntity<CompetenceProfessionnelleDto> save( @RequestBody CompetenceProfessionnelleDto actDto) {
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(competenceProfessionnelleService.saveOrUpdate(actDto));
	}

	@PutMapping(consumes ="application/json", produces = "application/json" )
	CompetenceProfessionnelleDto update(@RequestBody CompetenceProfessionnelleDto cptDto) {
		return competenceProfessionnelleService.saveOrUpdate(cptDto);
	}
	@DeleteMapping(value = "/{id}",produces ="text/plain" )
	ResponseEntity<String> delete(@PathVariable("id") long id){
		try {
			competenceProfessionnelleService.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression effectuée");
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression non réalisée");
		}
		
	}
	
}
