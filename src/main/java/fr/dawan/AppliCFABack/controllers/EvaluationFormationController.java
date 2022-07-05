package fr.dawan.AppliCFABack.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.AppliCFABack.dto.EvaluationFormationDto;
import fr.dawan.AppliCFABack.services.EvaluationFormationService;

@RestController
@RequestMapping("/evaluationsFormations")

public class EvaluationFormationController extends GenericController<EvaluationFormationDto> {

	public EvaluationFormationController(EvaluationFormationService service) {
		super(service);
		// TODO Auto-generated constructor stub
	}

}
