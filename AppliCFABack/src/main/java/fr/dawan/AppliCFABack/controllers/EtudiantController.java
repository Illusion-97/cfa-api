package fr.dawan.AppliCFABack.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/AppliCFABack/etudiants")
public class EtudiantController {

//	@Autowired
//	EtudiantService etudiantService;
//
//	// ##################################################
//	// # 					CRUD 						#
//	// ##################################################
//
//	/*
//	 * On récupère tous les étudiants présents dans la bdd
//	 */
//	@GetMapping(produces = "application/json")
//	public List<EtudiantDto> getAll() {
//		return etudiantService.getAll();
//	}
//
//	/*
//	 * On récupère l'étudiant avec l'id passé en paramètre
//	 */
//	@GetMapping(value = "/{id}", produces = "application/json")
//	public EtudiantDto getById(@PathVariable("id") long id) {
//		return etudiantService.getById(id);
//	}
//	
//	/*
//	 * On sauvegarde l'étudiant en paramètre dans la bdd
//	 */
//	@PostMapping(consumes = "application/json", produces = "application/json")
//	public EtudiantDto save(@RequestBody EtudiantDto eDto) {
//		return etudiantService.saveOrUpdate(eDto);
//	}
//	
//	/*
//	 * On supprime l'étudiant avec l'id passé en paramètre
//	 */
//	@DeleteMapping(value = "/{id}", produces = "text/plain")
//	public ResponseEntity<?> deleteById(@PathVariable(value = "id") long id) {
//		try {
//			etudiantService.deleteById(id);
//			return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression effectuée");
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("suppression non réalisée");
//		}
//
//	}
//	
//	/*
//	 * On update l'étudiant passé en paramètre
//	 */
//	@PutMapping(consumes = "application/json", produces = "application/json")
//	public EtudiantDto update(@RequestBody EtudiantDto eDto) {
//		return etudiantService.saveOrUpdate(eDto);
//	}
//		
//	// ##################################################
//	// # 			  Get : 1er Niveau 					#
//	// ##################################################
//	
//	/*
//	 * On récupère les informations personnelles de l'étudiant : nom etc à partir de son id
//	 */
//	@GetMapping(value = "/{id}/personne", produces = "application/json")
//	public List<PersonneDto> getPersonneByIdEtudiant(@PathVariable("id") long id){
//		return etudiantService.getPersonneByIdEtudiant(id);
//	}
//	
//	/*
//	 * On récupère l'entreprise de l'étudiant à partir de son id
//	 */
//	@GetMapping(value = "/{id}/entreprise", produces = "application/json")
//	public List<EntrepriseDto> getEntrepriseByIdEtudiant(@PathVariable("id") long id){
//		return etudiantService.getEntrepriseByIdEtudiant(id);
//	}
//	
//	/*
//	 * On récupère toutes les promotions auxquelles l'étudiant est inscrit à partir de son id
//	 */
//	@GetMapping(value = "/{id}/promotions", produces = "application/json")
//	public List<PromotionDto> getPromotionsByIdEtudiant(@PathVariable("id") long id){
//		return etudiantService.getPromotionsByIdEtudiant(id);
//	}
//	
//	/*
//	 * On récupère les notes de l'étudiants à partir de son id
//	 */
//	@GetMapping(value = "/{id}/notes", produces = "application/json")
//	public List<NoteDto> getNotesByIdEtudiant(@PathVariable("id") long id){
//		return etudiantService.getNotesByIdEtudiant(id);
//	}
//	
//	/*
//	 * On récupère les groupes dont l'étudiant fait parti à partir de son id
//	 */
//	@GetMapping(value = "/{id}/groupes", produces = "application/json")
//	public List<GroupeDto> getGroupesByIdEtudiant(@PathVariable("id") long id){
//		return etudiantService.getGroupesByIdEtudiant(id);
//	}
//	
//	/*
//	 * On récupère les absences de l'étudiant à partir de son id
//	 */
//	@GetMapping(value = "/{id}/absences", produces = "application/json")
//	public List<AbsenceDto> getAbsencesByIdEtudiant(@PathVariable("id") long id){
//		return etudiantService.getAbsencesByIdEtudiant(id);
//	}
//	
//	// ##################################################
//	// # 			 Get : 2eme Niveau 					#
//	// ##################################################
//	
//	/*
//	 * On récupère les cours de l'étudiant à parti de son id en passant pas sa promotion
//	 */
//	@GetMapping(value = "/{id}/cours", produces = "application/json")
//	public List<ProgrammeCoursDto> getProgrammeCoursByIdEtudiant(@PathVariable("id") long id){
//		return etudiantService.getProgrammeCoursByIdEtudiant(id);
//	}
//	
//	/*
//	 * On récupère les projets de l'étudiant à parti de son id en passant pas ses groupes
//	 */
//	@GetMapping(value = "/{id}/projets", produces = "application/json")
//	public List<ProjetDto> getProjetByIdEtudiant(@PathVariable("id") long id){
//		return etudiantService.getProjetByIdEtudiant(id);
//	}
//	
//	/*
//	 * On récupère l'adresse de l'étudiant à partir de son id en passant par Personne
//	 */
//	@GetMapping(value = "/{id}/adresse", produces = "application/json")
//	public List<AdresseDto> getAdresseByIdEtudiant(@PathVariable("id") long id){
//		return etudiantService.getAdresseByIdEtudiant(id);
//	}
//	
//	// ##################################################
//	// # 			 Get : 3eme Niveau 					#
//	// ##################################################
//	
//	/*
//	 * On récupère les formateurs de l'étudiant passant par : etudiant.promotions.cours.formateurs à partir de son id
//	 */
//	@GetMapping(value = "/{id}/formateurs", produces = "application/json")
//	public List<FormateurDto> getFormateursByIdEtudiant(@PathVariable("id") long id){
//		return etudiantService.getFormateursByIdEtudiant(id);
//	}
//	
//	/*
//	 * On récupère les devoirs de l'étudiant passant par : etudiant.promotions.cours.devoirs à partir de son id
//	 */
//	@GetMapping(value = "/{id}/devoirs", produces = "application/json")
//	public List<DevoirDto> getDevoirsByIdEtudiant(@PathVariable("id") long id){
//		return etudiantService.getDevoirsByIdEtudiant(id);
//	}
//	
//	/*
//	 * On récupère les examens de l'étudiant passant par : etudiant.promotions.cours.examens à partir de son id
//	 */
//	@GetMapping(value = "/{id}/examens", produces = "application/json")
//	public List<ExamenDto> getExamensByIdEtudiant(@PathVariable("id") long id){
//		return etudiantService.getExamensByIdEtudiant(id);
//	}
//	
	
}
