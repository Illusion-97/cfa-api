package fr.dawan.AppliCFABack.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.AppliCFABack.dto.CoursDto;
import fr.dawan.AppliCFABack.entities.Cours;
import fr.dawan.AppliCFABack.services.CoursService;

@RestController
@RequestMapping("/AppliCFABack/cours")
public class CoursController {

	@Autowired
	CoursService coursService;
	
	@GetMapping(produces = "application/json")
	public List<CoursDto> getAll() {
		return coursService.getAll();
	}
	@GetMapping(value = "/{id}" ,produces = "application/json")
	public CoursDto getById(@PathVariable("id") long id) {
		return coursService.getById(id);
	}
	
}
