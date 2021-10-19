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

import fr.dawan.AppliCFABack.dto.DossierProjetDto;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.services.DossierProjetService;
import fr.dawan.AppliCFABack.services.EtudiantService;

@RestController
@RequestMapping("/dossierProjet")
public class DossierProjetController {

	@Autowired
	DossierProjetService dossierProService;
	@Autowired
	EtudiantService etudiantService;

	@GetMapping(produces = "application/json")
	public List<DossierProjetDto> getAll() {
		return dossierProService.getAll();
	}
	
	@GetMapping(value = "/{id}",produces = "application/json")
	public DossierProjetDto getById(@PathVariable("id") long id) {
		return dossierProService.getById(id);
	}
	@GetMapping(value = "/etudiant/{id}",produces = "application/json")
	public List<DossierProjetDto> getByIdEtudiant(@PathVariable("id") long id) {
		return dossierProService.getByIdEtudiant(id);
	}
	
	@GetMapping(value = "/{page}/{size}", produces = "application/json")
	public @ResponseBody List<DossierProjetDto> getAllByPage(@PathVariable("page") int page,
			@PathVariable(value = "size") int size) {
		return dossierProService.getAllByPage(page, size, "");
	}
	
	@GetMapping(value = "/{page}/{size}/{search}", produces = "application/json")
 	public @ResponseBody List<DossierProjetDto> getAllByPage(@PathVariable("page") int page,
 			@PathVariable(value = "size") int size, @PathVariable(value = "search", required = false) Optional<String> search) {
 		if(search.isPresent())
 			return dossierProService.getAllByPage(page, size, search.get());
 		else
 			return dossierProService.getAllByPage(page, size, "");
 	}
	
	@PostMapping(value = "/save/{id}" ,consumes = "application/json", produces = "application/json")
	public DossierProjetDto save(@RequestBody DossierProjetDto dpDto, @PathVariable("id") long id) {
		DossierProjetDto dpDto1 = dossierProService.getByName(dpDto.getNom());
		if (dpDto1!= null) {
			return null;
		}
		DossierProjetDto dp = dossierProService.saveOrUpdate(dpDto);
		EtudiantDto eDto = etudiantService.getById(id);
		eDto.getDossierProjet().add(dp);
		etudiantService.saveOrUpdate(eDto);
		return dp;
	}
	
	
	@DeleteMapping(value = "/{idEtudiant}/delete/{id}", produces = "text/plain")
	public ResponseEntity<?> deleteById(@PathVariable("idEtudiant") long idEtudiant, @PathVariable("id") long id) {
		try {
			EtudiantDto eDto = etudiantService.getById(idEtudiant);
			for(int i=0;i<eDto.getDossierProjet().size();i++) {
				if (eDto.getDossierProjet().get(i).getId()==id) {
					eDto.getDossierProjet().remove(i);
					
				}
			}
			etudiantService.saveOrUpdate(eDto);
			dossierProService.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression effectuée");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("suppression non réalisée");
		}

	}
	
	
	@PutMapping(value = "/save/{id}" ,consumes = "application/json", produces = "application/json")
	public DossierProjetDto update(@RequestBody DossierProjetDto dpDto, @PathVariable("id") long id) {
		DossierProjetDto dpDto1 = dossierProService.getByName(dpDto.getNom());
		if (dpDto1!= null) {
			return null;
		}
		DossierProjetDto dp = dossierProService.saveOrUpdate(dpDto);
		EtudiantDto eDto = etudiantService.getById(id);
		eDto.getDossierProjet().add(dp);
		etudiantService.saveOrUpdate(eDto);
		return dp;
	}
}
