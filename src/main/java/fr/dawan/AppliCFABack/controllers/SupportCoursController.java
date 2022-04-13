package fr.dawan.AppliCFABack.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.dawan.AppliCFABack.dto.SupportCoursDto;
import fr.dawan.AppliCFABack.services.SupportCoursService;

@Controller
@RequestMapping("/supportsCours")
public class SupportCoursController extends GenericController<SupportCoursDto> {

	public SupportCoursController(SupportCoursService service) {
		super(service);
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping(produces = "application/json")
	public List<SupportCoursDto> getAll(){
		return ((SupportCoursService) service).getAll();
	}
}
