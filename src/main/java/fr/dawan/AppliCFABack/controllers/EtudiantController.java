package fr.dawan.AppliCFABack.controllers;

import fr.dawan.AppliCFABack.dto.*;
import fr.dawan.AppliCFABack.dto.customdtos.AccueilEtudiantDto;
import fr.dawan.AppliCFABack.dto.customdtos.EtudiantAbsencesDevoirsDto;
import fr.dawan.AppliCFABack.services.EmailService;
import fr.dawan.AppliCFABack.services.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/etudiants")
public class EtudiantController {

	@Autowired
	EtudiantService etudiantService;
	@Autowired
	EmailService emailService;
	private static Logger logger = Logger.getGlobal();

	// ##################################################
	// # CRUD #
	// ##################################################

	/*
	 * On récupère tous les étudiants présents dans la bdd
	 */
	@GetMapping(produces = "application/json")
	public List<EtudiantDto> getAll() {
		return etudiantService.getAll();
	}

	@GetMapping(produces = "application/json", value = "/{page}/{size}")
	public List<EtudiantDto> getAllByPage(@PathVariable("page") int page, @PathVariable("size") int size) {
		return etudiantService.getAllByPage(page, size, "");

	}

	@GetMapping(produces = "application/json", value = "/{page}/{size}/{search}")
	public List<EtudiantDto> getAllByPage(@PathVariable("page") int page, @PathVariable(value = "size") int size,
			@PathVariable(value = "search", required = false) Optional<String> search) {
		if (search.isPresent())
			return etudiantService.getAllByPage(page, size, search.get());
		else
			return etudiantService.getAllByPage(page, size, "");
	}

	/*
	 * On récupère l'étudiant avec l'id passé en paramètre
	 */
	@GetMapping(value = "/{id}", produces = "application/json")
	public EtudiantDto getById(@PathVariable("id") long id) {
		return etudiantService.getById(id);
	}

	/*
	 * On sauvegarde l'étudiant en paramètre dans la bdd
	 */
	@PostMapping(consumes = "application/json", produces = "application/json")
	public EtudiantDto save(@RequestBody EtudiantDto eDto) {
		logger.info("Controller @@PostMapping");
		return etudiantService.saveOrUpdate(eDto);
	}

	/*
	 * On supprime l'étudiant avec l'id passé en paramètre
	 */
	@DeleteMapping(value = "/{id}", produces = "text/plain")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") long id) {
		try {
			etudiantService.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression effectuée");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("suppression non réalisée");
		}

	}

	@GetMapping(value = "/count", produces = "application/json")
	public CountDto count() {
		return etudiantService.count("");
	}

	@GetMapping(value = "/count/{search}", produces = "application/json")
	public CountDto count(@PathVariable(value = "search", required = false) Optional<String> search) {
		if (search.isPresent())
			return etudiantService.count(search.get());
		else
			return etudiantService.count("");
	}

	/*
	 * On update l'étudiant passé en paramètre
	 */
	@PutMapping(consumes = "application/json", produces = "application/json")
	public EtudiantDto update(@RequestBody EtudiantDto eDto) {
		logger.info("Controller @PutMapping");
		return etudiantService.saveOrUpdate(eDto);
	}

	// ##################################################
	// # Get : 1er Niveau #
	// ##################################################


	/*
	 * On récupère toutes les promotions auxquelles l'étudiant est inscrit à partir
	 * de son id
	 */
	@GetMapping(value = "/{id}/promotions", produces = "application/json")
	public List<PromotionDto> getPromotionsByIdEtudiant(@PathVariable("id") long id) {
		return etudiantService.getPromotionsByIdEtudiant(id);
	}

	/*
	 * On récupère les groupes dont l'étudiant fait parti à partir de son id
	 */
	@GetMapping(value = "/{id}/groupes", produces = "application/json")
	public List<GroupeEtudiantDto> getGroupesByIdEtudiant(@PathVariable("id") long id) {
		return etudiantService.getGroupesByIdEtudiant(id);
	}

	@GetMapping(value = "/{id}/projets", produces = "application/json")
	public List<ProjetDto> getProjetByIdEtudiant(@PathVariable("id") long id){
		return etudiantService.getProjetByIdEtudiant(id);
	}
	/*
	 * On récupère l'adresse de l'étudiant à partir de son id en passant par
	 * Personne
	 */
	@GetMapping(value = "/{id}/adresse", produces = "application/json")
	public AdresseDto getAdresseByIdEtudiant(@PathVariable("id") long id) {
		return etudiantService.getAdresseByIdEtudiant(id);
	}

	/***
	 * 
	 * @param idIntervention : identifiant de l'intervention
	 * @return List de EtudiantAbsencesDevoirsDto
	 */
	@GetMapping(value = {"/{idIntervention}/etudiants-abcences-devoirs/{search}" ,"/{idIntervention}/etudiants-abcences-devoirs" })
	public List<EtudiantAbsencesDevoirsDto> getAllByInterventionId(@PathVariable("idIntervention") long idIntervention,
			@PathVariable(value = "search", required = false) Optional<String> search) {
		if (search.isPresent()) {
			return etudiantService.getEtudiantsByInterventionId(idIntervention,search.get());
		}
		return etudiantService.getEtudiantsByInterventionId(idIntervention,"");
	}
	// ##################################################
	// # Get : 2eme Niveau #
	// ##################################################

	/*
	 * On récupère les notes de l'étudiants à partir de son id
	 */
	@GetMapping(value = "/{id}/notes/{page}/{size}", produces = "application/json")
	public List<NoteDto> getNotesByIdEtudiant(@PathVariable("id") long id, @PathVariable("page") int page,
			@PathVariable(value = "size") int size) {
		return etudiantService.getNotesByIdEtudiant(id, page, size);
	}

	@GetMapping(value = "/{id}/devoirs/{page}/{size}", produces = "application/json")
	public List<DevoirDto> getDevoirsByIdEtudiant(@PathVariable("id") long id, @PathVariable("page") int page,
			@PathVariable(value = "size") int size) {
		return etudiantService.getDevoirsByIdEtudiant(id, page, size);
	}


    // ##################################################
    // # Get : 3eme Niveau #
    // ##################################################

    /*
     * On récupère les cours de l'étudiant à parti de son id en passant pas sa
     * promotion
     */
    @GetMapping(value = "/{id}/interventions", produces = "application/json")
    public List<InterventionDto> getIntervenionByIdEtudiant(@PathVariable("id") long id) {
        return etudiantService.getInterventionByIdEtudiant(id);
    }



    // ##################################################
    // # FETCH Dawan webservice #
    // ##################################################

    @GetMapping(value = {"dg2","/dg2/{idPromotionDg2}"}, produces = "application/json")
    public ResponseEntity<String> fetchAllEtudiantDG2(@RequestHeader Map<String, String> headers, @PathVariable(value = "idPromotionDg2", required = false) Optional<Long> idPromotionDg2) {
        String userDG2 = headers.get("x-auth-token");
        String[] splitUserDG2String = userDG2.split(":");

        try {
            if(idPromotionDg2.isPresent()){
                etudiantService.fetchAllEtudiantDG2ByIdPromotion(splitUserDG2String[0], splitUserDG2String[1], idPromotionDg2.get());
            }
            else {
				etudiantService.fetchAllEtudiantDG2(splitUserDG2String[0], splitUserDG2String[1]);
			}
            return ResponseEntity.status(HttpStatus.OK).body("Succeed to fetch data from the webservice DG2");
        } catch (Exception e) {
        	logger.log(Level.SEVERE,"ERROR", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body
                    ("Error while fetching data from the webservice DG2");
        }
    }

	/**
	 * méthode de récupération des données nécessaires pour la section Accueil de l'espace étudiant
	 * on convertit un objet Etudiant en AccueilEtudiantDto
	 */
	@GetMapping(value = "accueil-etudiant/{id}", produces = "application/json")
	public AccueilEtudiantDto getAccueilEtudiant(@PathVariable("id") long id) {
		return etudiantService.getAccueilEtudiant(id);
	}
	
	@GetMapping(value = {"/promotion/{id}/{page}/{size}/{search}", "/promotion/{id}/{page}/{size}"}, produces = "application/json")
	public List<EtudiantDto> GetEtudiantByPromotion(
			@PathVariable("id") long id,
			@PathVariable("page") int page,
			@PathVariable("size") int size,
			@PathVariable(value = "search", required = false) Optional<String> search) {
		if (search.isPresent()) {			
			return etudiantService.getEtudiantByPromotion(id, page, size, search.get());
		}else {
			return etudiantService.getEtudiantByPromotion(id, page, size, "");

		}
	}

	@GetMapping(value = {"/countEtudiantByPromotion/{id}/{search}", "/countEtudiantByPromotion/{id}" }, produces = "application/json")
	public CountDto countEtudiantByPromotion(@PathVariable("id") long id,
			@PathVariable(value = "search", required = false) Optional<String> search) {
		if (search.isPresent()) {			
			return etudiantService.countEtudiantByPromotion(id, search.get());
		}else {
			return etudiantService.countEtudiantByPromotion(id, "");
			
		}
	}

	@PostMapping(value = "/mail")
	public ResponseEntity<String> sendEmailToFormateur(@RequestParam String from,@RequestParam String to , @RequestParam String header,
													   @RequestParam String msg){
			emailService.sendMailUser1ToUser2(from, to, header, msg);
			return ResponseEntity.status(HttpStatus.OK).build();

	}
}
