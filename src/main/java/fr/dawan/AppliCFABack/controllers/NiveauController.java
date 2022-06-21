package fr.dawan.AppliCFABack.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.AppliCFABack.dto.NiveauDto;
import fr.dawan.AppliCFABack.services.NiveauService;

@RestController
@RequestMapping("/niveaux")
public class NiveauController {

	@Autowired
	NiveauService niveauService;
	
	@GetMapping(produces = "application/json")
	public List<NiveauDto> getAllNiveaux(){
		
		return niveauService.getAllNiveaux();
		
	}
	
}
