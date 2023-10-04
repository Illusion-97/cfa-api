package fr.dawan.AppliCFABack.controllers;

import fr.dawan.AppliCFABack.dto.LivretEvaluationDto;
import fr.dawan.AppliCFABack.dto.customdtos.EtudiantLivretEvaluationDto;
import fr.dawan.AppliCFABack.services.GenericService;
import fr.dawan.AppliCFABack.services.LivretEvaluationService;
import fr.dawan.AppliCFABack.tools.SaveInvalidException;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
/**
 * @author Feres BG.
 * @see fr.dawan.appliCFABack.service
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 */

@RestController
@RequestMapping("/livretEvaluation")
public class LivretEvaluationController extends GenericController<LivretEvaluationDto> {
	@Autowired LivretEvaluationService livretEvaluationService;
	public LivretEvaluationController(GenericService<LivretEvaluationDto> service) {
		super(service);
	}

	@Override
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<LivretEvaluationDto> save(@RequestBody LivretEvaluationDto livret) throws NotFoundException, SaveInvalidException {
		//On save le livret avant l'envoi de mail
		LivretEvaluationDto livetValidation = service.saveOrUpdate(livret);
		livretEvaluationService.notificationMail(livret);
		return ResponseEntity.status(HttpStatus.CREATED).body(livetValidation);
	}
	@GetMapping(value = "/etudiant/{id}", produces = "application/json")
	public List<LivretEvaluationDto> getAllByEtudiantId(@PathVariable("id") long id) {
		return ((LivretEvaluationService) service).getByEtudiantId(id);
	}
	@GetMapping(value = "/{idEtudiant}/{idCursus}", produces = "application/json")
	public LivretEvaluationDto findByIdEtudiantAndCursus(@PathVariable("idEtudiant") long idEtudiant,@PathVariable("idCursus") long idCursus) {
		return ((LivretEvaluationService) service).getByIdEtudiantAndIdCurcus(idEtudiant,idCursus);
	}
//	@GetMapping(value = "/generer/{idEtudiant}/{idCursus}", produces = "application/octet-stream")
//	public ResponseEntity<Resource> getLivretEval(@PathVariable("idEtudiant") long idEtudiant,
//			@PathVariable("idCursus") long idCursus) throws Exception {
//
//		
//		String outpoutPath = ((LivretEvaluationService) service).getLivretEvaluation(idEtudiant, idCursus);
//		File f = new File(outpoutPath);
//
//		Path path = Paths.get(f.getAbsolutePath());
//		ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
//
//		// Pour afficher un boite de téléchargement dans une réponse web au lieu de
//		// changer de page, nous devons
//		// spécifier un header : Content-Disposition, attachment;filename=app.log
//		HttpHeaders headers = new HttpHeaders();
//		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=app.log");
//		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
//		headers.add("Pragma", "no-cache");
//		headers.add("Expires", "0");
//		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=app.log");
//
//		return ResponseEntity.ok().headers(headers).contentLength(f.length())
//				.contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
//	}
	
	/**
	 * 	Génère  le livret d'évaluation 
	 * 
	 * @param idEtudiant identifiant de l'etudiant
	 * @param idCursus identifiant du cursus
	 * @return le livret d'évaluation convertie en base 64
	 * @throws Exception
	 */
	@GetMapping(value = "/generer/{idEtudiant}/{idCursus}", produces = "text/plain")
	public ResponseEntity<String> getLivretEval(@PathVariable("idEtudiant") long idEtudiant,
			@PathVariable("idCursus") long idCursus) throws Exception {

		String outpoutPath = ((LivretEvaluationService) service).getLivretEvaluation(idEtudiant, idCursus);
		File f = new File(outpoutPath);
		
		Path path = Paths.get(f.getAbsolutePath());
		byte[] bytes =  Files.readAllBytes(path);
		String base64 = Base64.getEncoder().encodeToString(bytes);

		return ResponseEntity.ok().body(base64);
	}

	@GetMapping(value = "livret-etudiant/{id}", produces = "application/json")
	public List<EtudiantLivretEvaluationDto> getlivretEtudiant(@PathVariable("id") long id) {
		return ((LivretEvaluationService) service).getLivretEtudiant(id);
	}


}
