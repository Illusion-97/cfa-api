package fr.dawan.AppliCFABack.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.AppliCFABack.dto.EvaluationFormationDto;
import fr.dawan.AppliCFABack.services.EvaluationFormationService;

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

}
