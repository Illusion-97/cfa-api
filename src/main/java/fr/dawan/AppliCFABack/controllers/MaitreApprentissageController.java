package fr.dawan.AppliCFABack.controllers;

import java.util.List;

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

import fr.dawan.AppliCFABack.dto.MaitreApprentissageDto;
import fr.dawan.AppliCFABack.services.MaitreApprentissageService;

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

	// /AppliCFABack/groupeEtudiants/{page}/{size}
	@GetMapping(value = "/{page}/{size}", produces = "application/json")
	public @ResponseBody List<MaitreApprentissageDto> getAllByPage(@PathVariable("page") int page,
			@PathVariable(value = "size") int size) {
		return maitreApprentissageService.getAllMaitreApprentissage(page, size);
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
