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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.AppliCFABack.dto.AdresseDto;
import fr.dawan.AppliCFABack.dto.CongeDto;
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.JourneePlanningDto;
import fr.dawan.AppliCFABack.dto.UtilisateurDto;
import fr.dawan.AppliCFABack.services.UtilisateurService;

@RestController
@RequestMapping("/AppliCFABack/utilisateurs")
public class UtilisateurController {
	@Autowired
	private UtilisateurService utilisateurService;

	@DeleteMapping(value = "/{id}", produces = "text/plain")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") long id) {
		try {
			utilisateurService.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression effectuée");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("suppression non réalisée");
		}

	}

	// GET: /AppliCFABack/utilisateurs
	@GetMapping(produces = { "application/json", "application/xml" })
	public List<UtilisateurDto> getAll() {
		return utilisateurService.getAll();
	}

	@GetMapping(value = "/{page}/{size}", produces = "application/json")
	public @ResponseBody List<UtilisateurDto> getAllByPage(@PathVariable("page") int page,
			@PathVariable(value = "size") int size) {
		return utilisateurService.getAllUtilisateurs(page, size, "");
	}

	@GetMapping(value = "/{page}/{size}/{search}", produces = "application/json")
	public @ResponseBody List<UtilisateurDto> getAllByPage(@PathVariable("page") int page,
			@PathVariable(value = "size") int size,
			@PathVariable(value = "search", required = false) Optional<String> search) {
		if (search.isPresent())
			return utilisateurService.getAllUtilisateurs(page, size, search.get());
		else
			return utilisateurService.getAllUtilisateurs(page, size, "");
	}

	@GetMapping(value = "/count", produces = "application/json")
	public CountDto count() {
		return utilisateurService.count("");
	}

	@GetMapping(value = "/count/{search}", produces = "application/json")
	public CountDto count(@PathVariable(value = "search", required = false) Optional<String> search) {
		if (search.isPresent())
			return utilisateurService.count(search.get());
		else
			return utilisateurService.count("");
	}

	// GET: /AppliCFABack/utilisateurs/with-object
	@GetMapping(produces = { "application/json", "application/xml" }, value = "/with-object")
	public List<UtilisateurDto> getAllWithObject() {
		return utilisateurService.getAllWithObject();
	}

	// GET: /AppliCFABack/utilisateurs/{id}
	@GetMapping(value = "/{id}", produces = "application/json")
	public UtilisateurDto getById(@PathVariable("id") long id) {
		return utilisateurService.getById(id);
	}

	// GET: /AppliCFABack/utilisateurs/{login}
	@GetMapping(value = "/email={login}", produces = "application/json")
	public UtilisateurDto getByLogin(@PathVariable("login") String login) {
		return utilisateurService.findByEmail(login);
	}

	// GET: /AppliCFABack/utilisateurs/with-object
	@GetMapping(value = "/{id}/with-object", produces = { "application/json", "application/xml" })
	public UtilisateurDto getByIdWithObject(@PathVariable("id") long id) {
		return utilisateurService.getByIdWithObject(id);
	}

	// GET: /AppliCFABack/utilisateurs/user?name=XXXX
	@GetMapping(value = "/user", produces = "application/json")
	public ResponseEntity<?> getName(@RequestParam("name") String name) {
		UtilisateurDto user = utilisateurService.getName(name);
		return new ResponseEntity<UtilisateurDto>(user, HttpStatus.OK);
	}

	// POST: /AppliCFABack/utilisateurs
	@PostMapping(consumes = "application/json", produces = "application/json")
	public UtilisateurDto insert(@RequestBody UtilisateurDto uDto) {
		return utilisateurService.insertUpdate(uDto);
	}

	// PUT: /AppliCFABack/utilisateurs
	@PutMapping(consumes = "application/json", produces = "application/json")
	public UtilisateurDto update(@RequestBody UtilisateurDto uDto) {
		return utilisateurService.insertUpdate(uDto);
	}

	// GET: /AppliCFABack/utlisateur/adresse?ville=XXXX
	@GetMapping(value = "/adresse", produces = "application/json")
	public List<UtilisateurDto> findByAdresse(@RequestParam("ville") String ville) {
		return utilisateurService.findByAdresse(ville);
	}

	@GetMapping(value = "/entreprise", produces = "application/json")
	public List<UtilisateurDto> findByEntreprise(@RequestParam("id") long id) {
		return utilisateurService.findByEntreprise(id);
	}

	@GetMapping(value = "/{id}/planning", produces = "application/json")
	public List<JourneePlanningDto> getAllJourneePlanningByIdUtilisateur(@PathVariable("id") long id) {
		return utilisateurService.getAllJourneePlanningByIdUtilisateur(id);
	}

	@GetMapping(value = "/{id}/conges", produces = "application/json")
	public List<CongeDto> getAllCongesByIdUtilisateur(@PathVariable("id") long id) {
		return utilisateurService.getAllCongesByIdUtilisateur(id);
	}

	/*
	 * On récupère l'adresse de l'étudiant à partir de son id en passant par
	 * Personne
	 */
	@GetMapping(value = "/{id}/adresse", produces = "application/json")
	public AdresseDto getAdresseByIdUtilisateur(@PathVariable("id") long id) {
		return utilisateurService.getAdresseByIdUtilisateur(id);
	}
}
