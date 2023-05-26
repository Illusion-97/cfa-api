package fr.dawan.AppliCFABack.controllers;

import fr.dawan.AppliCFABack.dto.MaitreApprentissageDto;
import fr.dawan.AppliCFABack.services.MaitreApprentissageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maitreApprentissages")
public class MaitreApprentissageController {
	
	@Autowired
	MaitreApprentissageService maitreApprentissageService;

	// ##################################################
	// # GET #
	// ##################################################

	@GetMapping(produces = "application/json")
	public List<MaitreApprentissageDto> getAll() {
		return maitreApprentissageService.getAllMaitreApprentissage();
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public MaitreApprentissageDto getById(@PathVariable("id") long id) {
		return maitreApprentissageService.getById(id);
	}

	/**
	 * 
	 * @param page
	 * @param size
	 * @return all maitre apprentissage by page
	 */
	@GetMapping(value = "/{page}/{size}", produces = "application/json")
	public @ResponseBody List<MaitreApprentissageDto> getAllByPage(@PathVariable("page") int page,
			@PathVariable(value = "size") int size) {
		return maitreApprentissageService.getAllMaitreApprentissage(page, size);
	}
	
	@GetMapping(value="/etudiant/{id}", produces = "application/json")
	public MaitreApprentissageDto getByEtudiantId(@PathVariable("id") long id) {
		return maitreApprentissageService.getMaitreApprentissageByEtudiantId(id);
	}

	// ##################################################
	// # POST #
	// ##################################################

	@PostMapping(consumes = "application/json", produces = "application/json")
	public MaitreApprentissageDto save(@RequestBody MaitreApprentissageDto maDto) {
		return maitreApprentissageService.saveOrUpdate(maDto);
	}

	// ##################################################
	// # DELETE #
	// ##################################################

	@DeleteMapping(value = "/{id}", produces = "text/plain")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") long id) {
		try {
			maitreApprentissageService.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression effectuée");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("suppression non réalisée");
		}

	}

	// ##################################################
	// # PUT #
	// ##################################################

	@PutMapping(consumes = "application/json", produces = "application/json")
	public MaitreApprentissageDto update(@RequestBody MaitreApprentissageDto maDto) {
		return maitreApprentissageService.saveOrUpdate(maDto);
	}

}
