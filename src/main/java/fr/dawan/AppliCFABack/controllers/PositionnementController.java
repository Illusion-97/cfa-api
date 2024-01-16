package fr.dawan.AppliCFABack.controllers;

import fr.dawan.AppliCFABack.controllers.generic.GenericController;
import fr.dawan.AppliCFABack.dto.PositionnementDto;
import fr.dawan.AppliCFABack.services.PositionnementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/positionnements")
public class PositionnementController extends GenericController<PositionnementDto> {

	
	@Autowired
	public PositionnementController(PositionnementService service) {
		super(service);
	}


	@GetMapping(value = "/promotion/{idPromotion}" ,produces = "application/json")
	public List<PositionnementDto> getAllByIdPromotion(@PathVariable("idPromotion") long idPromotion) {
		return ((PositionnementService) service).getAllByPromotionId(idPromotion);
	}

	@GetMapping(value = "/intervention/{idIntervention}" ,produces = "application/json")
	public List<PositionnementDto> getAllByIdIntervention(@PathVariable("idIntervention") long idIntervention) {
		return ((PositionnementService) service).getAllByInterventionId(idIntervention);
	}


 

}
