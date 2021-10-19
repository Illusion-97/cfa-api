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

import fr.dawan.AppliCFABack.dto.AbsenceDto;
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.services.AbsenceService;

@RestController
@RequestMapping("/absences")
public class AbsenceController {

	@Autowired
	AbsenceService absenceService;

	// ##################################################
	// # GET #
	// ##################################################

	@GetMapping(produces = "application/json")
	public List<AbsenceDto> getAll() {
		return absenceService.getAllAbsence();
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public AbsenceDto getById(@PathVariable("id") long id) {
		return absenceService.getById(id);
	}

	// /AppliCFABack/groupeEtudiants/{page}/{size}/{search}
	@GetMapping(value = "/{page}/{size}/{search}", produces = "application/json")
	public @ResponseBody List<AbsenceDto> getAllByPage(@PathVariable("page") int page,
			@PathVariable(value = "size") int size, @PathVariable("search") Optional<String> search) {
		if (search.isPresent())
			return absenceService.getAllAbsence(page, size, search.get());

		return absenceService.getAllAbsence(page, size, "");
	}

	// /AppliCFABack/groupeEtudiants/{page}/{size}
	@GetMapping(value = "/{page}/{size}", produces = "application/json")
	public @ResponseBody List<AbsenceDto> getAllByPage(@PathVariable("page") int page,
			@PathVariable(value = "size") int size) {
		return absenceService.getAllAbsence(page, size, "");
	}

	@GetMapping(value = "/count", produces = "application/json")
	public CountDto count() {
		return absenceService.count("");
	}
	
	 @GetMapping(value = "/etudiant/{id}", produces = "application/json")
		public @ResponseBody List<AbsenceDto> getAllByIdEtudiant(@PathVariable("id") long id) {
			return absenceService.getAllByIdEtudiant(id);
		}

	@GetMapping(value = "/count/{search}", produces = "application/json")
	public CountDto count(@PathVariable("search") Optional<String> search) {
		if (search.isPresent())
			return absenceService.count(search.get());
		return absenceService.count("");
	}
	// ##################################################
	// # POST #
	// ##################################################

	@PostMapping(consumes = "application/json", produces = "application/json")
	public AbsenceDto save(@RequestBody AbsenceDto aDto) {
		return absenceService.saveOrUpdate(aDto);
	}

	// ##################################################
	// # DELETE #
	// ##################################################

	@DeleteMapping(value = "/{id}", produces = "text/plain")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") long id) {
		try {
			absenceService.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression effectuée");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("suppression non réalisée");
		}

	}

	// ##################################################
	// # PUT #
	// ##################################################

	@PutMapping(consumes = "application/json", produces = "application/json")
	public AbsenceDto update(@RequestBody AbsenceDto aDto) {
		return absenceService.saveOrUpdate(aDto);
	}

//	@GetMapping(value = "/promotion/{id}/absences", produces = "application/json")
//	public List<AbsenceDto> getAbsenceByPromotionId(@PathVariable("id") long id) {
//		return absenceService.getAbsenceByReferentId(id);
//	}
}
