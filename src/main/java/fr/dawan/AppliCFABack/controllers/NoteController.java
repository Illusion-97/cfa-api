package fr.dawan.AppliCFABack.controllers;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.NoteDto;
import fr.dawan.AppliCFABack.dto.NoteDtoToSave;
import fr.dawan.AppliCFABack.dto.customdtos.NoteControleContinuDto;
import fr.dawan.AppliCFABack.services.EmailService;
import fr.dawan.AppliCFABack.services.NoteService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

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
    @GetMapping(value = "/examen/{id}", produces = "application/json")
	public @ResponseBody List<NoteDto> getAllByExamenId(@PathVariable("id") long id) {
		return noteService.getAllByExamenId(id);
	}
    @GetMapping(value = "/promotion-examen/{idPromotion}/{idExamen}" ,produces = "application/json")
    public List<NoteDto> getAllByPromotionId(@PathVariable("idPromotion") long idPromotion , @PathVariable("idExamen") long idExamen){
    	return noteService.getAllByPromotionIdAndExamenId(idPromotion,idExamen);
    }
    @GetMapping(value = {"/intervention-examen/{idIntervention}/{idExamen}/{search}" ,"/intervention-examen/{idIntervention}/{idExamen}" } ,produces = "application/json")
    public List<NoteDto> getAllByInterventionId(@PathVariable("idIntervention") long idIntervention, @PathVariable("idExamen") long idExamen
    		,@PathVariable("search") Optional<String> search){
    	
    	return noteService.getAllByInterventionIdAndExamenId(idIntervention,idExamen, search.isPresent()?search.get():"");
    }
	// ##################################################
	// # POST #
	// ##################################################

	@PostMapping(consumes = "application/json", produces = "application/json")
	public NoteDtoToSave save(@RequestBody NoteDtoToSave nDto) throws NotFoundException {
		noteService.notificationMail(nDto);
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
	public NoteDtoToSave update(@RequestBody NoteDtoToSave nDto) throws NotFoundException {
		noteService.notificationMail(nDto);
		return noteService.saveOrUpdate(nDto);
	}

	/**
	 * @param id de l'étudiant
	 * @return dans un get, le service qui va récupérer toutes les informations nécessaires pour remplir la section Contrôles Continus
	 * de l'espace étudiant partie front
	 */
	@GetMapping(value = "/note-etudiant/{id}", produces = "application/json")
	public Map<Set<String>, List<NoteControleContinuDto>> getNotesByIdEtudiant(@PathVariable("id") long id) {
		return noteService.getNotesByIdEtudiant(id);
	}

	/**
	 * @param id de l'étudiant
	 * @return dans un get, le service qui va récupérer toutes les informations nécessaires pour remplir la section Contrôles Continus
	 * de l'espace étudiant partie front
	 */
	@GetMapping(value = "/note-etudiant-list/{id}", produces = "application/json")
	public List<NoteControleContinuDto> getNotesByIdEtudiantNoTitle(@PathVariable("id") long id) {
		return noteService.getNotesByIdEtudiantNoTitle(id);
	}

}
