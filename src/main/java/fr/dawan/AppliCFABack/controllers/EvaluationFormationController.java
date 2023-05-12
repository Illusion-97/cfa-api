package fr.dawan.AppliCFABack.controllers;

import fr.dawan.AppliCFABack.dto.EvaluationFormationDto;
import fr.dawan.AppliCFABack.services.EvaluationFormationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/evaluationsFormations")

public class EvaluationFormationController extends GenericController<EvaluationFormationDto> {

	public EvaluationFormationController(EvaluationFormationService service) {
		super(service);
		
	}
	@GetMapping(value = "/{idPrmotion}/{idActiviteType}")
	public List<EvaluationFormationDto> getByPromotionAndActiviteType(@PathVariable("idPrmotion") long idPrmotion, @PathVariable("idActiviteType") long idActiviteType){
		return ((EvaluationFormationService)service).getByPromotionIdAndActiviteTypeId(idPrmotion, idActiviteType);
		
	}
	@GetMapping(value = "intervention/{idIntervention}")
	public List<EvaluationFormationDto> getByInterventionId(@PathVariable("idIntervention") long idIntervention){
		return ((EvaluationFormationService)service).getByInterventionId(idIntervention);
		
	}

}
