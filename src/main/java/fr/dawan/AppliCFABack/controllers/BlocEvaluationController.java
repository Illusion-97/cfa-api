package fr.dawan.AppliCFABack.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.AppliCFABack.dto.BlocEvaluationDto;
import fr.dawan.AppliCFABack.services.GenericService;
@RestController
@RequestMapping("/blocEvaluations")
public class BlocEvaluationController extends GenericController<BlocEvaluationDto> {

	protected BlocEvaluationController(GenericService<BlocEvaluationDto> service) {
		super(service);
	}

}
