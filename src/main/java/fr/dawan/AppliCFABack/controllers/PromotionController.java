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
import fr.dawan.AppliCFABack.dto.PromotionDto;
import fr.dawan.AppliCFABack.dto.UtilisateurDto;
import fr.dawan.AppliCFABack.services.PromotionService;

@RestController
@RequestMapping("/promotions")
public class PromotionController {

	
	@Autowired
	private PromotionService promoService;
	
	@GetMapping(produces = "application/json")
	public List<PromotionDto> getAll() {
		return promoService.getAll();
	}
	
	@GetMapping(value = "/{page}/{size}", produces = "application/json")
 	public @ResponseBody List<PromotionDto> getAllByPage(@PathVariable("page") int page, @PathVariable(value = "size") int size) {
 		return promoService.getAllPromotions(page, size, "");
 	}
 	
 	@GetMapping(value = "/{page}/{size}/{search}", produces = "application/json")
 	public @ResponseBody List<PromotionDto> getAllByPage(@PathVariable("page") int page,
 			@PathVariable(value = "size") int size, @PathVariable(value = "search", required = false) Optional<String> search) {
 		if(search.isPresent())
 			return promoService.getAllPromotions(page, size, search.get());
 		else
 			return promoService.getAllPromotions(page, size, "");
 	}
	
	@GetMapping(value = "/{id}",produces = "application/json")
	public PromotionDto getById(@PathVariable("id") long id) {
		return promoService.getById(id);
	}
	
	@GetMapping(value = "/count", produces = "application/json")
	public CountDto count() {
		return promoService.count("");
	}
	
	@GetMapping(value = "/count/{search}", produces = "application/json")
	public CountDto count(@PathVariable(value = "search", required = false) Optional<String> search) {
		if(search.isPresent())
			return promoService.count(search.get());
		else
			return promoService.count("");
	}
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public PromotionDto save(@RequestBody PromotionDto pDto) {
		return promoService.saveOrUpdate(pDto);
	}
	
	@DeleteMapping(value = "/{id}", produces = "text/plain")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") long id) {
		try {
			promoService.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression effectuée");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("suppression non réalisée");
		}
	}
	
	@PutMapping(consumes = "application/json", produces = "application/json")
	public PromotionDto update(@RequestBody PromotionDto pDto) {
		return promoService.saveOrUpdate(pDto);
	}
	
	
	@GetMapping(value = "/{id}/referent",produces = "application/json")
	public UtilisateurDto getReferentById(@PathVariable("id") long id) {
		return promoService.getReferentById(id);
	}
	
	@GetMapping(value = "/{id}/etudiants",produces = "application/json")
	public List<EtudiantDto> getEtudiantsById(@PathVariable("id") long id) {
		return promoService.getEtudiantsById(id);
	}
}
