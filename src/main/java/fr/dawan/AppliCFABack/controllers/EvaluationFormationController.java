package fr.dawan.AppliCFABack.controllers;

import fr.dawan.AppliCFABack.dto.EvaluationFormationDto;
import fr.dawan.AppliCFABack.services.EvaluationFormationService;
import fr.dawan.AppliCFABack.tools.SaveInvalidException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/evaluationsFormations")

public class EvaluationFormationController extends GenericController<EvaluationFormationDto> {
	
	@Autowired
	EvaluationFormationService evaluationFormationService;

	public EvaluationFormationController(EvaluationFormationService service) {
		super(service);

		
	}
	@GetMapping(value = "/{idPrmotion}/{idActiviteType}")
	public List<EvaluationFormationDto> getByPromotionAndActiviteType(@PathVariable("idPrmotion") long idPrmotion, @PathVariable("idActiviteType") long idActiviteType){
		return ((EvaluationFormationService)service).getByPromotionIdAndActiviteTypeId(idPrmotion, idActiviteType);
		
	}
	@GetMapping(value = "/intervention/{idIntervention}")
	public List<EvaluationFormationDto> getByInterventionId(@PathVariable("idIntervention") long idIntervention){
		return ((EvaluationFormationService)service).getByInterventionId(idIntervention);
		
	}
	
	@PutMapping(value="/update", consumes = "application/json", produces = "application/json")
	public EvaluationFormationDto updateEvaluationFormation(@RequestBody EvaluationFormationDto evaluationFormationDto)
	        throws SaveInvalidException {
	    return evaluationFormationService.update(evaluationFormationDto);
	}
	
	@GetMapping(value = "/get/{id}")
	public ResponseEntity<EvaluationFormationDto> getById(@PathVariable Long id) {
		EvaluationFormationDto evaluationFormationDto = evaluationFormationService.getById(id);
		    
		if (evaluationFormationDto != null) {
		     return ResponseEntity.ok(evaluationFormationDto);
		} else {
		     return ResponseEntity.notFound().build();
		}
	}

}
