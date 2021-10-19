package fr.dawan.AppliCFABack.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import fr.dawan.AppliCFABack.dto.CursusDto;
import fr.dawan.AppliCFABack.dto.FormationDto;
import fr.dawan.AppliCFABack.dto.InterventionDto;
import fr.dawan.AppliCFABack.dto.PromotionDto;
import fr.dawan.AppliCFABack.services.CursusService;
import fr.dawan.AppliCFABack.services.FormationService;
import fr.dawan.AppliCFABack.services.PromotionService;

@RestController
@RequestMapping("/formations")
public class FormationController {

	@Autowired
	FormationService formationService;

	@Autowired
	PromotionService promotionService;
	
	@Autowired
	CursusService cursusService;

	// ##################################################
	// # GET #
	// ##################################################

	@GetMapping(produces = "application/json")
	public List<FormationDto> getAll() {
		return formationService.getAllFormation();
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public FormationDto getById(@PathVariable("id") long id) {
		return formationService.getById(id);
	}

	@GetMapping(value = "/{page}/{size}", produces = "application/json")
	public @ResponseBody List<FormationDto> getAllByPage(@PathVariable("page") int page,
			@PathVariable(value = "size") int size) {
		return formationService.getAllByPage(page, size, "");
	}

	@GetMapping(value = "/{page}/{size}/{search}", produces = "application/json")
	public @ResponseBody List<FormationDto> getAllByPage(@PathVariable("page") int page,
			@PathVariable(value = "size") int size,
			@PathVariable(value = "search", required = false) Optional<String> search) {
		if (search.isPresent())
			return formationService.getAllByPage(page, size, search.get());
		else
			return formationService.getAllByPage(page, size, "");
	}

	@GetMapping(value = "/{id}/interventions", produces = "application/json")
	public List<InterventionDto> findAllInterventionByFormationId(@PathVariable("id") long id) {

		return formationService.findAllByFormationId(id);
	}

	@GetMapping(value = "/count", produces = "application/json")
	public CountDto count() {
		return formationService.count("");
	}

	@GetMapping(value = "/count/{search}", produces = "application/json")
	public CountDto count(@PathVariable(value = "search", required = false) Optional<String> search) {
		if (search.isPresent())
			return formationService.count(search.get());
		else
			return formationService.count("");
	}

	@GetMapping(value = "/getFormationByPromoId/{id}", produces = "application/json")
	public List<FormationDto> getFormationByEtudidantId(@PathVariable(value = "id") long id) {

		PromotionDto promotion = promotionService.getById(id);
		List<InterventionDto> lstintervention = promotion.getInterventionsDto();
		List<FormationDto> lstFormation = new ArrayList<FormationDto>();
		for (InterventionDto i : lstintervention) {
			lstFormation.add(i.getFormationDto());
		}
		return lstFormation;

	}
	
	@GetMapping(value = "/getFormationByCursusId/{id}", produces = "application/json")
	public List<FormationDto> getFormationByCursusId(@PathVariable(value = "id") long id) {

		CursusDto cDto = cursusService.getById(id);
		List<FormationDto> lstFormation = new ArrayList<FormationDto>();

		for (FormationDto formationDto : cDto.getFormationsDto()) {
			lstFormation.add(formationDto);
		}
		return lstFormation;

	}

	// ##################################################
	// # POST #
	// ##################################################
	@CrossOrigin(origins = "*")
	@PostMapping(consumes = "application/json", produces = "application/json")
	public FormationDto save(@RequestBody FormationDto fDto) {
		return formationService.saveOrUpdate(fDto);
	}

	// ##################################################
	// # DELETE #
	// ##################################################

	@DeleteMapping(value = "/{id}", produces = "text/plain")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") long id) {
		try {
			formationService.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression effectuée");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("suppression non réalisée");
		}

	}

	// ##################################################
	// # PUT #
	// ##################################################

	@PutMapping(consumes = "application/json", produces = "application/json")
	public FormationDto update(@RequestBody FormationDto fDto) {
		return formationService.saveOrUpdate(fDto);
	}
}
