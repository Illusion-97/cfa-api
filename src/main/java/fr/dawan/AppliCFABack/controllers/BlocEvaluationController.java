package fr.dawan.AppliCFABack.controllers;

import fr.dawan.AppliCFABack.dto.BlocEvaluationDto;
import fr.dawan.AppliCFABack.services.BlocEvaluationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/blocEvaluations")
public class BlocEvaluationController extends GenericController<BlocEvaluationDto> {

	protected BlocEvaluationController(BlocEvaluationService service) {
		super(service);
	}
	@GetMapping(value = "/{idAt}/{idLivretEval}", produces = "application/json")
	public BlocEvaluationDto findByIdAtAndIdLivretEval(@PathVariable("idAt") long idAt,@PathVariable("idLivretEval") long idLivretEval) {
		return ((BlocEvaluationService) service).finddByActiviteTypeIdAndlivretEvaluationId(idAt, idLivretEval);
	}


}
