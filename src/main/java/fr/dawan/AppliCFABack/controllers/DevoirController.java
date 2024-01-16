package fr.dawan.AppliCFABack.controllers;

import fr.dawan.AppliCFABack.controllers.generic.GenericController;
import fr.dawan.AppliCFABack.dto.DevoirDto;
import fr.dawan.AppliCFABack.services.DevoirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

	@GetMapping(value= "/intervention/{interventionId}", produces = "application/json")
	public List<DevoirDto> getAllByInterventionId(@PathVariable("interventionId") long id){
		return ((DevoirService) service).getAllByInterventionId(id);
	}


	@Override	@PutMapping(consumes="application/json", produces="application/json")
	public DevoirDto update(@RequestBody DevoirDto dDto) throws Exception {
		return service.saveOrUpdate(dDto);
	}

	@DeleteMapping(value="/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") long id){
		service.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("Le devoir " +id+ " a bien été supprimé");
	}



}
