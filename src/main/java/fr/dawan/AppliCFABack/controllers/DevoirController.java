package fr.dawan.AppliCFABack.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.AppliCFABack.dto.DevoirDto;
import fr.dawan.AppliCFABack.services.DevoirService;

@RestController
@RequestMapping("/devoirs")
public class DevoirController extends GenericController<DevoirDto> {

	
	@Autowired
	public DevoirController(DevoirService service) {
		super(service);
	}


	@GetMapping(produces = "application/json")
	public List<DevoirDto> getAll() {
		return ((DevoirService) service).getAllDevoir();
	}


	@GetMapping(value = "/{page}/{size}/{search}", produces = "application/json")
 	public @ResponseBody List<DevoirDto> getAllByPage(@PathVariable("page") int page,
 			@PathVariable(value = "size") int size, @PathVariable(value = "search", required = false) Optional<String> search) {
 		if(search.isPresent())
 			return ((DevoirService) service).getAllByPage(page, size, search.get());
 		else
 			return ((DevoirService) service).getAllByPage(page, size, "");
 	}

 

}
