package fr.dawan.AppliCFABack.controllers;

import fr.dawan.AppliCFABack.controllers.generic.GenericController;
import fr.dawan.AppliCFABack.dto.ValidationDto;
import fr.dawan.AppliCFABack.services.generic.GenericService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("validation")
public class ValidationController extends GenericController<ValidationDto> {

	public ValidationController(GenericService<ValidationDto> service) {
		super(service);

	}

}
