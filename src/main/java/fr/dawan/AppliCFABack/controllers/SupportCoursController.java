package fr.dawan.AppliCFABack.controllers;

import fr.dawan.AppliCFABack.controllers.generic.GenericController;
import fr.dawan.AppliCFABack.dto.SupportCoursDto;
import fr.dawan.AppliCFABack.services.SupportCoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Valentin C, Feres BG.
 * @see fr.dawan.appliCFABack.service
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return Controller de la classe activité type
 */
@RestController
@RequestMapping("/supportCours")
public class SupportCoursController extends GenericController<SupportCoursDto> {

	@Autowired
	public SupportCoursController(SupportCoursService service) {
		super(service);
	}

	/**
	 * 
	 * @return la liste de tous les supports de cours
	 */
	@GetMapping(produces = "application/json")
	public List<SupportCoursDto> getAll() {
		return ((SupportCoursService) service).getAll();
	}

}
