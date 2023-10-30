package fr.dawan.AppliCFABack.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.SoutenanceDto;
import fr.dawan.AppliCFABack.services.SoutenanceService;
import fr.dawan.AppliCFABack.tools.SaveInvalidException;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/soutenance")
public class SoutenanceController {

	@Autowired
	SoutenanceService soutenanceService;
	
	@GetMapping(produces = "application/json", value = "/{id}")
	public SoutenanceDto getById(@PathVariable("id") long id) {
		return soutenanceService.getById(id);		
	}
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public SoutenanceDto save(@RequestBody SoutenanceDto soutenanceDto) {
		return soutenanceService.save(soutenanceDto);
	}
	
	@PutMapping(consumes = "application/json", produces = "application/json")
	public SoutenanceDto update(@RequestBody SoutenanceDto soutenanceDto) throws SaveInvalidException {
		return soutenanceService.saveOrUpdate(soutenanceDto);
	}
	
	@DeleteMapping(produces = "application/json", value = "/{id}")
	public void delete(@PathVariable("id") long id) {
		soutenanceService.delete(id);
	}
	
	@GetMapping(produces = "application/json", value = "/count/{id}")
	public CountDto countByIdPromotion(@PathVariable("id") long id) {
		return soutenanceService.count(String.valueOf(id));
	}
	
	@GetMapping(produces = "application/json", value = "/promotion/{id}")
	public List<SoutenanceDto> getByPromotionId(@PathVariable("id") long id) {
		return soutenanceService.getByPromotionId(id);
	}
	
	@GetMapping(produces = "application/json", value = "/promotion/{id}/{page}/{size}")
	public List<SoutenanceDto> getPageByPromotionId(
			@PathVariable("id") long id, 
			@PathVariable("page") int page,
			@PathVariable("size") int size) {		
		return soutenanceService.getPageByPromotionId(id, page, size);
	}
}
