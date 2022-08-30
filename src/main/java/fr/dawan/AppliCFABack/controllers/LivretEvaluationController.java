package fr.dawan.AppliCFABack.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.AppliCFABack.dto.LivretEvaluationDto;
import fr.dawan.AppliCFABack.services.GenericService;
import fr.dawan.AppliCFABack.services.LivretEvaluationService;

@RestController
@RequestMapping("livretEvaluation")
public class LivretEvaluationController extends GenericController<LivretEvaluationDto>{
	
	public LivretEvaluationController(GenericService<LivretEvaluationDto> service) {
		super(service);
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping(value = "/etudiant/{id}", produces = "application/json")
	public List<LivretEvaluationDto> getAllByEtudiantId(@PathVariable("id") long id){
		return ((LivretEvaluationService) service).getByEtudiantId(id);
	}
	

}
