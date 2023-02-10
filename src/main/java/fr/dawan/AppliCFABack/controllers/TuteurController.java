package fr.dawan.AppliCFABack.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.services.TuteurService;

@RestController
@RequestMapping("/tuteur")
public class TuteurController {
	
	
	
	/*@GetMapping(produces = "application/json", value = "/{id}")
	public List<EtudiantDto> getEtudiantByTuteurId(@PathVariable("id") long id)
		{
		return tuteurService.getEtudiantByIdTuteur(id);
	}
	
	@GetMapping(produces = "application/json", value = "/{id}/{page}/{size}/{search}")
	public List<EtudiantDto> getEtudiantByKeyword(@PathVariable("page") int page, 
			@PathVariable("size") int size, @PathVariable("search") String search)
		 {
		return tuteurService.getAllEtudiantPerPage( page, size,search);
	   }
	
	}*/
}

