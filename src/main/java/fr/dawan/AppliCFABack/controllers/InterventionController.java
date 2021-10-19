package fr.dawan.AppliCFABack.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DevoirDto;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.FormateurDto;
import fr.dawan.AppliCFABack.dto.InterventionDto;
import fr.dawan.AppliCFABack.dto.PromotionDto;
import fr.dawan.AppliCFABack.services.EtudiantService;
import fr.dawan.AppliCFABack.services.FilesService;
import fr.dawan.AppliCFABack.services.InterventionService;
import org.springframework.core.io.ByteArrayResource;

@RestController
@RequestMapping("/interventions")
public class InterventionController {

	@Autowired
	InterventionService interventionService;
	@Autowired
	EtudiantService etudiantService;
	@Autowired
	FilesService filesService;

	// ##################################################
	// # GET #
	// ##################################################

	@GetMapping(produces = "application/json")
	public List<InterventionDto> getAll() {
		return interventionService.getAllIntervention();
	}

//	@GetMapping(value = "/{id}", produces = "application/json")
//	public InterventionDto getById(@PathVariable("id") long id) {
//		return interventionService.getById(id);
//	}
	
	// GET : /AppliCFABack/interventions/{id}
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<?> getById(@PathVariable("id") long id) {
		HttpStatus status = ResponseEntity.status(HttpStatus.OK).build().getStatusCode();
		ResponseEntity<?> response = ResponseEntity.status(status).header("Status", status.toString())
				.body(interventionService.getById(id));
		return response;
	}

	// GET : /AppliCFABack/interventions/{page}/{size}
	@GetMapping(value = "/{page}/{size}", produces = "application/json")
	public @ResponseBody List<InterventionDto> getAllByPage(@PathVariable("page") int page,
			@PathVariable(value = "size") int size) {
		return interventionService.getAllByPage(page, size, "");
	}
	
	// GET : /AppliCFABack/interventions/{page}/{size}/{search}
	@GetMapping(value = "/{page}/{size}/{search}", produces = "application/json")
	public @ResponseBody List<InterventionDto> getAllByPage(@PathVariable("page") int page,
			@PathVariable(value = "size") int size,
			@PathVariable(value = "search", required = false) Optional<String> search) {
		if (search.isPresent())
			return interventionService.getAllByPage(page, size, search.get());
		else
			return interventionService.getAllByPage(page, size, "");
	}

	@GetMapping(value = "/count", produces = "application/json")
	public CountDto count() {
		return interventionService.count("");
	}

	@GetMapping(value = "/count/{search}", produces = "application/json")
	public CountDto count(@PathVariable(value = "search", required = false) Optional<String> search) {
		if (search.isPresent())
			return interventionService.count(search.get());
		else
			return interventionService.count("");
	}
	
	@GetMapping(value = "/{id}/etudiants-promotion", produces = "application/json")
	public List<EtudiantDto> findAllByPromotionInterventionsId(@PathVariable("id") long id) {
		return interventionService.findAllByPromotionInterventionsId(id);
	}

	@GetMapping(value = "/{id}/promotions", produces = "application/json")
	public List<PromotionDto> findAllPromotionsByInterventionId(@PathVariable("id") long id) {
		return interventionService.findPromotionsByInterventionId(id);
	}

	@GetMapping(value = "/{id}/devoirs", produces = "application/json")
	public List<DevoirDto> findAllDevoirsByInterventionId(@PathVariable("id") long id) {
		return interventionService.findDevoirsByInterventionId(id);
	}

	@GetMapping(value = "/{id}/formateurs", produces = "application/json")
	public List<FormateurDto> findAllFormateursByInterventionId(@PathVariable("id") long id) {
		return interventionService.findFormateursByInterventionsId(id);
	}

	// ##################################################
	// # POST #
	// ##################################################
	@PostMapping(consumes = "application/json", produces = "application/json")
	public InterventionDto save(@RequestBody InterventionDto iDto) {
		return interventionService.saveOrUpdate(iDto);
	}

	// ##################################################
	// # DELETE #
	// ##################################################

	@DeleteMapping(value = "/{id}", produces = "text/plain")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") long id) {
		try {
			interventionService.deleteById(id);
//			int status = ResponseEntity.status(HttpStatus.ACCEPTED).build().getStatusCodeValue();
			HttpStatus status = ResponseEntity.status(HttpStatus.ACCEPTED).build().getStatusCode();
			ResponseEntity<?> response = ResponseEntity.status(status).header("Status", status.toString()).build();
			return response;
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
		}

	}

	// ##################################################
	// # PUT #
	// ##################################################
	@PutMapping(consumes = "application/json", produces = "application/json")
	public InterventionDto update(@RequestBody InterventionDto iDto) {
		return interventionService.saveOrUpdate(iDto);
	}
	
	@GetMapping(value = "/{id}/supports", produces = "application/json")
	public String[] findAllSupportByInterventionId(@PathVariable("id") long id) {
		String path = "interventions/" + id;
		return filesService.getAllNamesByDirectory(path);
	}
}
