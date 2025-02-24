package fr.dawan.AppliCFABack.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import fr.dawan.AppliCFABack.services.dg2Imports.DG2ImportUsers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fr.dawan.AppliCFABack.dto.AdresseDto;
import fr.dawan.AppliCFABack.dto.CongeDto;
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.JourneePlanningDto;
import fr.dawan.AppliCFABack.dto.UtilisateurDto;
import fr.dawan.AppliCFABack.services.UtilisateurService;
import fr.dawan.AppliCFABack.tools.SaveInvalidException;
import javassist.NotFoundException;

@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {

	private final UtilisateurService utilisateurService;


	private final DG2ImportUsers dg2ImportUsers;

	private static final Logger logger = LoggerFactory.getLogger(UtilisateurController.class);

	public UtilisateurController(
			@Autowired UtilisateurService utilisateurService,
			@Autowired DG2ImportUsers dg2ImportUsers

	) {
		this.utilisateurService = utilisateurService;
		this.dg2ImportUsers = dg2ImportUsers;
	}

	@DeleteMapping(value = "/{id}", produces = "text/plain")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") long id) {
		try {
			utilisateurService.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression effectuée");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("suppression non réalisée");
		}

	}

	@GetMapping(value = "/{page}/{size}", produces = "application/json")
	public @ResponseBody List<UtilisateurDto> getAllByPage(@PathVariable("page") int page,
			@PathVariable(value = "size") int size, @RequestParam(required = false) Optional<String> role,
			@RequestParam(defaultValue = "", required = false) Optional<String> search) {

		if (role.isPresent() && search.isPresent())
			return utilisateurService.findAllByRoleByPage(page, size, role.get(), search.get());
		else
			return utilisateurService.getAllUtilisateurs(page, size, search.get());
	}

	@GetMapping(value = "/count", produces = "application/json")
	public CountDto count(@RequestParam(required = false) Optional<String> role,
			@RequestParam(defaultValue = "", required = false) Optional<String> search) {
		if (role.isPresent() && search.isPresent())
			return utilisateurService.countByRole(role.get(), search.get());
		else
			return utilisateurService.count(search.get());
	}

	/**
	 *
	 * @param id
	 * @return recuperation des user pat id
	 */
	@GetMapping(value = "/{id}", produces = "application/json")
	public UtilisateurDto getById(@PathVariable("id") long id) {
		return utilisateurService.getById(id);
	}

	/**
	 *
	 * @return
	 */
	@GetMapping(produces = { "application/json", "application/xml" }, value = "/with-object")
	public List<UtilisateurDto> getAllWithObject() {
		return utilisateurService.getAllWithObject();
	}

	/**
	 *
	 * @return recuperation de tous les users
	 */
	@GetMapping(produces = { "application/json", "application/xml" })
	public List<UtilisateurDto> getAll() {
		return utilisateurService.getAll();
	}

	/**
	 *
	 * @param login
	 * @return recherche par email
	 */
	@GetMapping(value = "/email={login}", produces = "application/json")
	public UtilisateurDto getByLogin(@PathVariable("login") String login) {
		return utilisateurService.findByEmail(login);
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}/with-object", produces = { "application/json", "application/xml" })
	public UtilisateurDto getByIdWithObject(@PathVariable("id") long id) {
		return utilisateurService.getByIdWithObject(id);
	}

	/**
	 *
	 * @param name
	 * @return ResponseEntity
	 */
	@GetMapping(value = "/user", produces = "application/json")
	public ResponseEntity<?> getName(@RequestParam("name") String name) {
		UtilisateurDto user = utilisateurService.getName(name);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	/**
	 *
	 * @param uDto
	 * @return ResponseEntity
	 */
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> insert(@RequestBody UtilisateurDto uDto) {
		try {
			return ResponseEntity.ok(utilisateurService.insertUpdate(uDto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@PostMapping(value = "/tuteur", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> insertTuteur(@RequestBody UtilisateurDto uDto) {
		try {
			return ResponseEntity.ok(utilisateurService.insertTuteur(uDto));
		} catch (SaveInvalidException e) {
			// L'email existe déjà en base
			logger.error("Un utilisateur utilise déjà cette adresse mail login", e);
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		} catch (Exception e) {
			logger.error("Une erreur s'est produite lors de l'insertion du tuteur.", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Une erreur est survenue lors de l'insertion du tuteur.");
		}
	}

	@PutMapping(value = "/tuteur", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> updateTuteur(@RequestBody UtilisateurDto uDto) throws SaveInvalidException {
		try {
			return ResponseEntity.ok(utilisateurService.updateTuteur(uDto));
		} catch (Exception e) {
			throw new SaveInvalidException(e.getMessage());
		}
	}

	/**
	 *
	 * @param uDto
	 * @return mise à jour des user
	 * @throws Exception
	 */
	@PutMapping(consumes = "application/json", produces = "application/json")
	public UtilisateurDto update(@RequestBody UtilisateurDto uDto) throws SaveInvalidException {
		return utilisateurService.insertUpdate(uDto);
	}

	/**
	 *
	 * @param ville
	 * @return recjerche par adresse(ville)
	 */
	@GetMapping(value = "/adresse", produces = "application/json")
	public List<UtilisateurDto> findByAdresse(@RequestParam("ville") String ville) {
		return utilisateurService.findByAdresse(ville);
	}

//	@GetMapping(value = "/entreprise", produces = "application/json")
//	public List<UtilisateurDto> findByEntreprise(@RequestParam("id") long id) {
//		return utilisateurService.findByEntreprise(id);
//	}

	@GetMapping(value = "/roles/{idRole}", produces = "application/json")
	public List<UtilisateurDto> findByRole(@PathVariable("role") long idRole) {
		return utilisateurService.findByRole(idRole);
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

	@GetMapping(value = "/{id}/isReferent", produces = "application/json")
	public Boolean isReferent(@PathVariable("id") long id) {
		return utilisateurService.isReferent(id);
	}

	@PostMapping(value = "{id}/upload-file", consumes = "multipart/form-data")
	public ResponseEntity<?> uploadFile(@PathVariable("id") long id, @RequestParam("file") MultipartFile file) {
		try {
			utilisateurService.uploadFile(file, id);
			return ResponseEntity.status(HttpStatus.OK).body("Import du fichier réussi");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping(value = "/dg2", produces = "application/json")
	public ResponseEntity<String> fetchAllDG2(@RequestHeader Map<String, String> headers) {
		String userDG2 = headers.get("x-auth-token");
		String[] splitUserDG2String = userDG2.split(":");

		try {
			this.dg2ImportUsers.fetchAllDG2Employees(splitUserDG2String[0], splitUserDG2String[1]);
			return ResponseEntity.status(HttpStatus.OK).body("Succeed to fetch data from the webservice DG2");
		} catch (Exception e) {
			logger.error("Exception : ", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error while fetching data from the webservice DG2");
		}
	}

	@PutMapping("/{utilisateurId}/roles")
	public ResponseEntity<String> modifierRolesUtilisateur(@PathVariable long utilisateurId,
			@RequestBody List<Long> nouveauRolesIds) throws NotFoundException {
		utilisateurService.modifierRolesUtilisateur(utilisateurId, nouveauRolesIds);
		return ResponseEntity.ok("Rôles de l'utilisateur modifiés avec succès");
	}

}
