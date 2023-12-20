package fr.dawan.AppliCFABack.controllers;

import fr.dawan.AppliCFABack.dto.*;
import fr.dawan.AppliCFABack.services.EtudiantService;
import fr.dawan.AppliCFABack.services.FilesService;
import fr.dawan.AppliCFABack.services.InterventionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/interventions")
public class InterventionController {

	@Autowired
	InterventionService interventionService;
	@Autowired
	EtudiantService etudiantService;
	@Autowired
	FilesService filesService;
	
	private static final Logger logger = LoggerFactory.getLogger(InterventionController.class);

	// ##################################################
	// # GET #
	// ##################################################

	@GetMapping(produces = "application/json")
	public List<InterventionDto> getAll() {
		return interventionService.getAllIntervention();
	}
	
	/**
	 * 
	 * @param id
	 * @return ResponseEntity
	 */
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<?> getById(@PathVariable("id") long id) {
		HttpStatus status = ResponseEntity.status(HttpStatus.OK).build().getStatusCode();
		return ResponseEntity.status(status).header("Status", status.toString())
				.body(interventionService.getById(id));
	}

	/**
	 * 
	 * @param page
	 * @param size
	 * @return liste des interventions avec pagination
	 */
	@GetMapping(value = "/{page}/{size}", produces = "application/json")
	public @ResponseBody List<InterventionDto> getAllByPage(@PathVariable("page") int page,
			@PathVariable(value = "size") int size) {
		return interventionService.getAllByPage(page, size, "","");
	}
	
	/**
	 * 
	 * @param page
	 * @param size
	 * @param search
	 * @return liste des interventiosn avec pagination + search
	 */
	@GetMapping(value = "/{page}/{size}/{search}", produces = "application/json")
	public @ResponseBody List<InterventionDto> getAllByPage(@PathVariable("page") int page,
			@PathVariable(value = "size") int size,
			@PathVariable(value = "search", required = false) Optional<String> search) {
		if (search.isPresent())
			return interventionService.getAllByPage(page, size,"", search.get());
		else
			return interventionService.getAllByPage(page, size,"", "");
	}

	/**
	 *
	 * @param page
	 * @param size
	 * @param search
	 * @return liste des interventiosn avec pagination + search
	 */
	@GetMapping(value = "/sort/{page}/{size}/", produces = "application/json")
	public @ResponseBody List<InterventionDto> getAllByPage(@PathVariable("page") int page,
															@PathVariable(value = "size") int size,
															@RequestParam(value = "sort", required = false) String sort) {
			return interventionService.getAllByPage(page, size,sort, "");
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
		return interventionService.findAllEtudiantsByPromotionInterventionsId(id);
	}

	@GetMapping(value = "/{id}/promotions", produces = "application/json")
	public List<PromotionDto> findAllPromotionsByInterventionId(@PathVariable("id") long id) {
		return interventionService.findPromotionsByInterventionId(id);
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
			HttpStatus status = ResponseEntity.status(HttpStatus.ACCEPTED).build().getStatusCode();
			return ResponseEntity.status(status).header("Status", status.toString()).build();
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
	
	// ##################################################
	// # FETCH Dawan webservice #
	// ##################################################
	@GetMapping(value = {"/dg2", "/dg2/{idPromotion}"}, produces="application/json")
	public ResponseEntity<String> fetchAllDG2(@RequestHeader Map<String, String> headers, @PathVariable(value = "idPromotion" ,required = false) Optional<Long> idPromotion ){
		String userDG2 = headers.get("x-auth-token");
		String[] splitUser = userDG2.split(":");
		
		try {
			//int nb = 0;
			if (idPromotion.isPresent()) {
				interventionService.fetchDGInterventions(splitUser[0], splitUser[1],idPromotion.get());
			}
			else {
				interventionService.fetchDGInterventions(splitUser[0], splitUser[1]);
			}
			return ResponseEntity.status(HttpStatus.OK).body("Succeed to fetch data from the webservice DG2. Interventions updated");
		} catch (Exception e) {
			logger.error("Exception", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error while fetching data from the webservice DG2");
		}
	}
	
	@GetMapping(value = {"/promotion/{id}/{page}/{size}/{search}", "/promotion/{id}/{page}/{size}"}, produces = "application/json")
	public List<InterventionDto> findInterventionByPromotionId(
			@PathVariable("id") long id,
			@PathVariable("page") int page,
			@PathVariable("size") int size,
			@PathVariable(value = "search", required = false) Optional<String> search) {
		if (search.isPresent()) {			
			return interventionService.findInterventionByPromotionId(id, page, size, search.get());
		}else {
			return interventionService.findInterventionByPromotionId(id, page, size, "");
		}
	}
	
	@GetMapping(value = {"/countInterventionByPromotion/{id}/{search}", "/countInterventionByPromotion/{id}" }, produces = "application/json")
	public CountDto countInterventionByPromotionId(@PathVariable("id") long id,
			@PathVariable(value = "search", required = false) Optional<String> search) {
		if (search.isPresent()) {			
			return interventionService.countInterventionByPromotionId(id, search.get());
		}else {
			return interventionService.countInterventionByPromotionId(id, "");
		}
	}
}
