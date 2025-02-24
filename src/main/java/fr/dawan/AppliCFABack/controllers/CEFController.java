package fr.dawan.AppliCFABack.controllers;

import fr.dawan.AppliCFABack.dto.CEFDto;
import fr.dawan.AppliCFABack.services.CEFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cefs")
public class CEFController {

	@Autowired
	CEFService cefService;

	// ##################################################
	// # GET #
	// ##################################################

	@GetMapping(produces = "application/json")
	public List<CEFDto> getAll() {
		return cefService.getAllCef();
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public CEFDto getById(@PathVariable("id") long id) {
		return cefService.getById(id);
	}

	/**
	 * 
	 * @param page
	 * @param size
	 * @return all cef by page
	 */
	@GetMapping(value = "/{page}/{size}", produces = "application/json")
	public @ResponseBody List<CEFDto> getAllByPage(@PathVariable("page") int page,
			@PathVariable(value = "size") int size) {
		return cefService.getAllCef(page, size);
	}

	// ##################################################
	// # POST #
	// ##################################################

	@PostMapping(consumes = "application/json", produces = "application/json")
	public CEFDto save(@RequestBody CEFDto cDto) {
		return cefService.saveOrUpdate(cDto);
	}

	// ##################################################
	// # DELETE #
	// ##################################################

	@DeleteMapping(value = "/{id}", produces = "text/plain")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") long id) {
		try {
			cefService.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression effectuée");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("suppression non réalisée");
		}

	}

	// ##################################################
	// # PUT #
	// ##################################################

	@PutMapping(consumes = "application/json", produces = "application/json")
	public CEFDto update(@RequestBody CEFDto cDto) {
		return cefService.saveOrUpdate(cDto);
	}

}
