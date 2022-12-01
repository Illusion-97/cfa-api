package fr.dawan.AppliCFABack.controllers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import fr.dawan.AppliCFABack.dto.customdtos.EtudiantLivretEvaluationDto;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.AppliCFABack.dto.LivretEvaluationDto;
import fr.dawan.AppliCFABack.services.GenericService;
import fr.dawan.AppliCFABack.services.LivretEvaluationService;

@RestController
@RequestMapping("livretEvaluation")
public class LivretEvaluationController extends GenericController<LivretEvaluationDto> {

	public LivretEvaluationController(GenericService<LivretEvaluationDto> service) {
		super(service);
	}

	@GetMapping(value = "/etudiant/{id}", produces = "application/json")
	public List<LivretEvaluationDto> getAllByEtudiantId(@PathVariable("id") long id) {
		return ((LivretEvaluationService) service).getByEtudiantId(id);
	}

	@GetMapping(value = "/generer/{idEtudiant}/{idCursus}", produces = "application/octet-stream")
	public ResponseEntity<Resource> getGrillePositionnement(@PathVariable("idEtudiant") long idEtudiant,
			@PathVariable("idCursus") long idCursus) throws Exception {

		String outpoutPath = ((LivretEvaluationService) service).getLivretEvaluation(idEtudiant, idCursus);
		File f = new File(outpoutPath);

		Path path = Paths.get(f.getAbsolutePath());
		ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

		// Pour afficher un boite de téléchargement dans une réponse web au lieu de
		// changer de page, nous devons
		// spécifier un header : Content-Disposition, attachment;filename=app.log
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=app.log");
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=app.log");

		return ResponseEntity.ok().headers(headers).contentLength(f.length())
				.contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
	}

	@GetMapping(value = "livret-etudiant/{id}", produces = "application/json")
	public EtudiantLivretEvaluationDto getlivretEtudiant(@PathVariable("id") long id) {
		return ((LivretEvaluationService) service).getLivretEtudiant(id);
	}

}
