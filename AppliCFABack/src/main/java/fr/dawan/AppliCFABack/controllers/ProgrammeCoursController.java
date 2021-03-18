package fr.dawan.AppliCFABack.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.AppliCFABack.dto.ProgrammeCoursDto;
import fr.dawan.AppliCFABack.services.ProgrammeCoursService;

@RestController
@RequestMapping("/AppliCFABack/programmecours")

public class ProgrammeCoursController {

	@Autowired
	ProgrammeCoursService pgService;
	
	@GetMapping(produces = "application/json")
	public List<ProgrammeCoursDto> getAll() {
		return pgService.getAll();
	}
	@GetMapping(value = "/{id}" ,produces = "application/json")
	public ProgrammeCoursDto getById(@PathVariable("id") long id) {
		return pgService.getById(id);
	}
	
}
