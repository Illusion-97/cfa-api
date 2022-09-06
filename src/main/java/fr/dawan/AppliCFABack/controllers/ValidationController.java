package fr.dawan.AppliCFABack.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.AppliCFABack.dto.ValidationDto;
import fr.dawan.AppliCFABack.services.GenericService;

@RestController
@RequestMapping("validation")
public class ValidationController extends GenericController<ValidationDto>{

	public ValidationController(GenericService<ValidationDto> service) {
		super(service);

	}

}
