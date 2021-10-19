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

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.NoteDto;
import fr.dawan.AppliCFABack.services.NoteService;

@RestController
@RequestMapping("/notes")
public class NoteController {

	@Autowired
	NoteService noteService;

	// ##################################################
	// # GET #
	// ##################################################

	@GetMapping(produces = "application/json")
	public List<NoteDto> getAll() {
		return noteService.getAllNote();
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public NoteDto getById(@PathVariable("id") long id) {
		return noteService.getById(id);
	}

	@GetMapping(value = "/{page}/{size}", produces = "application/json")
	public @ResponseBody List<NoteDto> getAllByPage(@PathVariable("page") int page,
			@PathVariable(value = "size") int size) {
		return noteService.getAllByPage(page, size, "");
	}
	
	@GetMapping(value = "/{page}/{size}/{search}", produces = "application/json")
 	public @ResponseBody List<NoteDto> getAllByPage(@PathVariable("page") int page,
 			@PathVariable(value = "size") int size, @PathVariable(value = "search", required = false) Optional<String> search) {
 		if(search.isPresent())
 			return noteService.getAllByPage(page, size, search.get());
 		else
 			return noteService.getAllByPage(page, size, "");
 	}
		
	@GetMapping(value = "/count", produces = "application/json")
	public CountDto count() {
		return noteService.count("");
	}
    
    @GetMapping(value = "/count/{search}", produces = "application/json")
	public CountDto count(@PathVariable(value = "search", required = false) Optional<String> search) {
		if(search.isPresent())
			return noteService.count(search.get());
		else
			return noteService.count("");
	}
	
    @GetMapping(value = "/etudiant/{id}", produces = "application/json")
	public @ResponseBody List<NoteDto> getAllByIdEtudiant(@PathVariable("id") long id) {
		return noteService.getAllByIdEtudiant(id);
	}
    
	// ##################################################
	// # POST #
	// ##################################################

	@PostMapping(consumes = "application/json", produces = "application/json")
	public NoteDto save(@RequestBody NoteDto nDto) {
		return noteService.saveOrUpdate(nDto);
	}

	// ##################################################
	// # DELETE #
	// ##################################################

	@DeleteMapping(value = "/{id}", produces = "text/plain")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") long id) {
		try {
			noteService.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression effectuée");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("suppression non réalisée");
		}

	}

	// ##################################################
	// # PUT #
	// ##################################################

	@PutMapping(consumes = "application/json", produces = "application/json")
	public NoteDto update(@RequestBody NoteDto nDto) {
		return noteService.saveOrUpdate(nDto);
	}

}
