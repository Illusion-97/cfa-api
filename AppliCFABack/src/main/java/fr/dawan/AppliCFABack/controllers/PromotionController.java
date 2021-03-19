package fr.dawan.AppliCFABack.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.AppliCFABack.dto.PromotionDto;
import fr.dawan.AppliCFABack.services.PromotionService;

@RestController
@RequestMapping("/AppliCFABack/promotions")
public class PromotionController {

	
	@Autowired
	private PromotionService promoService;
	
	@GetMapping(produces = "application/json")
	public List<PromotionDto> getAll() {
		return promoService.getAll();
	}
	
	@GetMapping(value = "/{id}",produces = "application/json")
	public PromotionDto getById(@PathVariable("id") long id) {
		return promoService.getById(id);
	}
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public PromotionDto save(@RequestBody PromotionDto pDto) {
		return promoService.saveOrUpdate(pDto);
	}
	
}
