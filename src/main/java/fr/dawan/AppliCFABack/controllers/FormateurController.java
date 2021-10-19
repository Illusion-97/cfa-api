package fr.dawan.AppliCFABack.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.FormateurDto;
import fr.dawan.AppliCFABack.dto.InterventionDto;
import fr.dawan.AppliCFABack.services.FormateurService;

@RestController
@RequestMapping("/formateurs")
public class FormateurController {
	@Autowired
	FormateurService formateurService;

	@GetMapping(produces = "application/json")
	public List<FormateurDto> getAll() {
		return formateurService.getAll();
	}

	// GET : /AppliCFABack/formateurs/{page}/{size}
	@GetMapping(produces = "application/json", value = "/{page}/{size}")
	public @ResponseBody List<FormateurDto> getAll(@PathVariable("page") int page, @PathVariable("size") int size) {
		return formateurService.getAllByPage(page, size);
	}

	// GET : /AppliCFABack/formateurs/{page}/{size}/{search}
	@GetMapping(produces = "application/json", value = "/{page}/{size}/{search}")
	public @ResponseBody List<FormateurDto> getAll(@PathVariable("page") int page, @PathVariable("size") int size,
			@PathVariable("search") Optional<String> search) {
		if (search.isPresent())
			return formateurService.getAllByPageWithKeyword(page, size, search.get());
		return formateurService.getAllByPage(page, size);
	}

	// GET : /AppliCFABack/formateurs/with-object
	@GetMapping(value = "/with-object", produces = "application/json")
	public List<FormateurDto> getAllWithObject() {
		return formateurService.getAllWithObject();
	}

	// GET : /AppliCFABack/formateurs/{id}
	@GetMapping(produces = "application/json", value = "/{id}")
	public FormateurDto getById(@PathVariable("id") long id) {
		return formateurService.getById(id);
	}

	// POST : /AppliCFABack/formateurs
	@PostMapping(consumes = "application/json", produces = "application/json")
	public FormateurDto save(@RequestBody FormateurDto fDto) {
		return formateurService.saveOrUpdate(fDto);
	}

	// DELETE : /AppliCFABack/formateurs/{id}
	@DeleteMapping(value = "/{id}", produces = "text/plain")
	public ResponseEntity<?> deleteById(@PathVariable("id") long id) {
		try {
			formateurService.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression effectuée");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("suppression non réalisée");
		}
	}

	// PUT : /AppliCFABack/formateurs
	@PutMapping(consumes = "application/json", produces = "application/json")
	public FormateurDto update(@RequestBody FormateurDto fDto) {
		return formateurService.saveOrUpdate(fDto);
	}

	// GET : /AppliCFABack/formateurs/count
	@GetMapping(value = "/count")
	public CountDto count() {
		return formateurService.count("");
	}

	// GET : /AppliCFABack/formateurs/count/{search}
	@GetMapping(value = "/count/{search}")
	public CountDto count(@PathVariable("search") Optional<String> search) {
		if (search.isPresent())
			return formateurService.count(search.get());
		return formateurService.count("");
	}

	/** ++++++++++++++ INTERVENTION FORMATEUR ++++++++++++++ **/
	// GET : /AppliCFABack/formateurs/{id}/interventions/{page}/{size}/{search}
	@GetMapping(produces = "application/json", value = "/{id}/interventions/{page}/{size}/{search}")
	public List<InterventionDto> getInterventionsByFormateurIdByKeyword(@PathVariable("id") long id,
			@PathVariable("page") int page, @PathVariable("size") int size,
			@PathVariable("search") Optional<String> search) {
		if (search.isPresent())
			return formateurService.getAllInterventionsByFormateurIdPerPageByKeyword(id, page, size, search.get());
		return formateurService.getAllInterventionsByFormateurIdPerPage(id, page, size);
	}

	// GET : /AppliCFABack/formateurs/{id}/interventions/{page}/{size}
	@GetMapping(produces = "application/json", value = "/{id}/interventions/{page}/{size}")
	public List<InterventionDto> getInterventionsByFormateurId(@PathVariable("id") long id,
			@PathVariable("page") int page, @PathVariable("size") int size) {
		return formateurService.getAllInterventionsByFormateurIdPerPage(id, page, size);
	}

	// GET : /AppliCFABack/formateurs/{id}/interventions/count
	@GetMapping(value = "/{id}/interventions/count")
	public CountDto countByFormateurId(@PathVariable("id") long id) {
		return formateurService.countInterventionById(id);
	}

	// GET : /AppliCFABack/formateurs/{id}/interventions/count/{search}
	@GetMapping(value = "/{id}/interventions/count/{search}")
	public CountDto countByFormateurId(@PathVariable("id") long id, @PathVariable("search") Optional<String> search) {
		if (search.isPresent())
			return formateurService.countInterventionById(id, search.get());
		return formateurService.countInterventionById(id);
	}

}
