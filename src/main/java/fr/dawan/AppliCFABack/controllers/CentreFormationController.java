package fr.dawan.AppliCFABack.controllers;

import fr.dawan.AppliCFABack.dto.CentreFormationDto;
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.services.CentreFormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/centreFormations")
public class CentreFormationController {

	@Autowired
	CentreFormationService centreFormationService;

	// ##################################################
	// # GET #
	// ##################################################

	@GetMapping(produces = "application/json")
	public List<CentreFormationDto> getAll() {
		return centreFormationService.getAllCentreFormation();
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public CentreFormationDto getById(@PathVariable("id") long id) {
		return centreFormationService.getById(id);
	}

	/**
	 * 
	 * @param page
	 * @param size
	 * @return all centre formation by page
	 */
	@GetMapping(value = "/{page}/{size}", produces = "application/json")
	public @ResponseBody List<CentreFormationDto> getAllByPage(@PathVariable("page") int page,
			@PathVariable(value = "size") int size) {
		return centreFormationService.getAllCentreFormation(page, size);
	}
	
	@GetMapping(value = "/{page}/{size}/{search}", produces = "application/json")
 	public @ResponseBody List<CentreFormationDto> getAllByPage(@PathVariable("page") int page,
 			@PathVariable(value = "size") int size, @PathVariable(value = "search", required = false) Optional<String> search) {
 		if(search.isPresent())
 			return centreFormationService.getAllCentreFormations(page, size, search.get());
 		else
 			return centreFormationService.getAllCentreFormations(page, size, "");
 	}
	
	
	@GetMapping(value = "/count", produces = "application/json")
	public CountDto count() {
		return centreFormationService.count("");
	}
	
	@GetMapping(value = "/count/{search}", produces = "application/json")
	public CountDto count(@PathVariable(value = "search", required = false) Optional<String> search) {
		if(search.isPresent())
			return centreFormationService.count(search.get());
		else
			return centreFormationService.count("");
	}
	

	// ##################################################
	// # POST #
	// ##################################################

	@PostMapping(consumes = "application/json", produces = "application/json")
	public CentreFormationDto save(@RequestBody CentreFormationDto cfDto) {
		return centreFormationService.saveOrUpdate(cfDto);
	}

	// ##################################################
	// # DELETE #
	// ##################################################

	@DeleteMapping(value = "/{id}", produces = "text/plain")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") long id) {
		try {
			centreFormationService.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression effectuée");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("suppression non réalisée");
		}

	}

	// ##################################################
	// # PUT #
	// ##################################################

	@PutMapping(consumes = "application/json", produces = "application/json")
	public CentreFormationDto update(@RequestBody CentreFormationDto cfDto) {
		return centreFormationService.saveOrUpdate(cfDto);
	}
	
	// ##################################################
	// # FETCH Dawan webservice #
	// ##################################################
	@GetMapping(value = "/dg2", produces = "application/json")
	public ResponseEntity<String> fetchAllDG2(@RequestHeader Map<String, String> headers) {
			String userDG2 = headers.get("x-auth-token");
			String[] splitUserDG2String = userDG2.split(":");

			try {
				centreFormationService.fetchAllDG2CentreFormation(splitUserDG2String[0], splitUserDG2String[1]);
				return ResponseEntity.status(HttpStatus.OK).body("Succeed to fetch data from the webservice DG2");
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Error while fetching data from the webservice DG2");
			}
		}


}
