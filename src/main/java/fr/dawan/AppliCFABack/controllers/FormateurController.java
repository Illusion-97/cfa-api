package fr.dawan.AppliCFABack.controllers;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.FormateurDto;
import fr.dawan.AppliCFABack.dto.InterventionDto;
import fr.dawan.AppliCFABack.services.FormateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/formateurs")
public class FormateurController {
	@Autowired
	FormateurService formateurService;

	@GetMapping(produces = "application/json")
	public List<FormateurDto> getAll() {
		return formateurService.getAll();
	}
	/**
	 * 
	 * @param page
	 * @param size
	 * @return all formateur by page 
	 * 
	 */
	@GetMapping(produces = "application/json", value = "/{page}/{size}")
	public @ResponseBody List<FormateurDto> getAll(@PathVariable("page") int page, @PathVariable("size") int size) {
		return formateurService.getAllByPage(page, size);
	}

	/**
	 * 
	 * @param page
	 * @param size
	 * @param search
	 * @return all formateur by page + search
	 * 
	 */
	@GetMapping(produces = "application/json", value = "/{page}/{size}/{search}")
	public @ResponseBody List<FormateurDto> getAll(@PathVariable("page") int page, @PathVariable("size") int size,
			@PathVariable("search") Optional<String> search) {
		if (search.isPresent())
			return formateurService.getAllByPageWithKeyword(page, size, search.get());
		return formateurService.getAllByPage(page, size);
	}


	/**
	 * 
	 * @return getAllWithObject
	 */
	@GetMapping(value = "/with-object", produces = "application/json")
	public List<FormateurDto> getAllWithObject() {
		return formateurService.getAllWithObject();
	}

	/**
	 * 
	 * @param id
	 * @return formateur by id
	 */
	@GetMapping(produces = "application/json", value = "/{id}")
	public FormateurDto getById(@PathVariable("id") long id) {
		return formateurService.getById(id);
	}

	/**
	 * 
	 * @param fDto formateurDto
	 * @return saveOrUpdate formateur
	 */
	@PostMapping(consumes = "application/json", produces = "application/json")
	public FormateurDto save(@RequestBody FormateurDto fDto) {
		return formateurService.saveOrUpdate(fDto);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/{id}", produces = "text/plain")
	public ResponseEntity<?> deleteById(@PathVariable("id") long id) {
		try {
			formateurService.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression effectuée");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("suppression non réalisée");
		}
	}

	/**
	 * 
	 * @param fDto
	 * @return
	 */
	@PutMapping(consumes = "application/json", produces = "application/json")
	public FormateurDto update(@RequestBody FormateurDto fDto) {
		return formateurService.saveOrUpdate(fDto);
	}

	/**
	 * 
	 * @return
	 */
	@GetMapping(value = "/count")
	public CountDto count() {
		return formateurService.count("");
	}

	/**
	 * 
	 * @param search
	 * @return
	 */
	@GetMapping(value = "/count/{search}")
	public CountDto count(@PathVariable("search") Optional<String> search) {
		if (search.isPresent())
			return formateurService.count(search.get());
		return formateurService.count("");
	}

	/** ++++++++++++++ INTERVENTION FORMATEUR ++++++++++++++ **/
	/**
	 * 
	 * @param id
	 * @param page
	 * @param size
	 * @param search
	 * @return
	 */
	@GetMapping(produces = "application/json", value = "/{id}/interventions/{page}/{size}/{search}")
	public List<InterventionDto> getInterventionsByFormateurIdByKeyword(@PathVariable("id") long id,
			@PathVariable("page") int page, @PathVariable("size") int size,
			@PathVariable("search") Optional<String> search) {
		if (search.isPresent())
			return formateurService.getAllInterventionsByFormateurIdPerPageByKeyword(id, page, size, search.get());
		return formateurService.getAllInterventionsByFormateurIdPerPage(id, page, size);
	}

	/**
	 * 
	 * @param id
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping(produces = "application/json", value = "/{id}/interventions/{page}/{size}")
	public List<InterventionDto> getInterventionsByFormateurId(@PathVariable("id") long id,
			@PathVariable("page") int page, @PathVariable("size") int size) {
		return formateurService.getAllInterventionsByFormateurIdPerPage(id, page, size);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}/interventions/count")
	public CountDto countByFormateurId(@PathVariable("id") long id) {
		return formateurService.countInterventionById(id);
	}

	/**
	 * 
	 * @param id
	 * @param search
	 * @return
	 */
	@GetMapping(value = "/{id}/interventions/count/{search}")
	public CountDto countByFormateurId(@PathVariable("id") long id, @PathVariable("search") Optional<String> search) {
		if (search.isPresent())
			return formateurService.countInterventionById(id, search.get());
		return formateurService.countInterventionById(id);
	}

}
