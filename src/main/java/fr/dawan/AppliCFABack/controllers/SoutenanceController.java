package fr.dawan.AppliCFABack.controllers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.SoutenanceDto;
import fr.dawan.AppliCFABack.services.SoutenanceService;
import fr.dawan.AppliCFABack.tools.SaveInvalidException;

@RestController
@RequestMapping("/soutenance")
public class SoutenanceController {

	@Autowired
	SoutenanceService soutenanceService;
	
	@GetMapping(produces = "application/json", value = "/{id}")
	public SoutenanceDto getById(@PathVariable("id") long id) {
		return soutenanceService.getById(id);		
	}
	
	@GetMapping(produces = "application/json", value = "")
	public List<SoutenanceDto> getAll() {
		return soutenanceService.getAll();
	}
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public SoutenanceDto save(@RequestBody SoutenanceDto soutenanceDto) throws SaveInvalidException {
		return soutenanceService.saveOrUpdate(soutenanceDto);
	}
	
	@PutMapping(consumes = "application/json", produces = "application/json")
	public SoutenanceDto update(@RequestBody SoutenanceDto soutenanceDto) throws SaveInvalidException {
		return soutenanceService.saveOrUpdate(soutenanceDto);
	}
	
	@DeleteMapping(produces = "application/json", value = "/{id}")
	public void delete(@PathVariable("id") long id) {
		soutenanceService.delete(id);
	}
	
	@GetMapping(produces = "application/json", value = "/count/promotion/{id}")
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
	
	@GetMapping(produces = "text/plain", value = "/generer/{promotion}/{id}")
	public ResponseEntity<String> genererLstSoutenance(
			@PathVariable("promotion") String promotion,
			@PathVariable("id") long idPromotion) throws Exception {
		String outpoutPath = (soutenanceService.genererLstSoutenance(promotion, idPromotion));
		File f = new File(outpoutPath);
		Path path = Paths.get(f.getAbsolutePath());
		byte[] bytes =  Files.readAllBytes(path);
		String base64 = Base64.getEncoder().encodeToString(bytes);

		return ResponseEntity.ok().body(base64);
	}
	
}
