package fr.dawan.AppliCFABack.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.ExamenDto;
import fr.dawan.AppliCFABack.dto.ExamenDtoSave;
import fr.dawan.AppliCFABack.dto.customdtos.LivretEvaluationDto;
import fr.dawan.AppliCFABack.services.ExamenService;
import fr.dawan.AppliCFABack.services.FileService;
import fr.dawan.AppliCFABack.tools.SaveInvalidException;

@RestController
@RequestMapping("/examens")
public class ExamenController {

	@Value("${app.storagefolder}")
	private String storageFolder;

	@Autowired
	ExamenService examenService;

	@Autowired
	FileService fileSevice;

	@Autowired
	private ObjectMapper objectMapper;
	
	private static Logger logger = Logger.getGlobal();

	// ##################################################
	// # GET #
	// ##################################################

	@GetMapping(produces = "application/json")
	public List<ExamenDto> getAll() {
		return examenService.getAllExamen();
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public ExamenDto getById(@PathVariable("id") long id) {
		return examenService.getById(id);
	}

	@GetMapping(value = "/{page}/{size}", produces = "application/json")
	public @ResponseBody List<ExamenDto> getAllByPage(@PathVariable("page") int page,
			@PathVariable(value = "size") int size) {
		return examenService.getAllByPage(page, size, "");
	}

	@GetMapping(value = "/{page}/{size}/{search}", produces = "application/json")
	public @ResponseBody List<ExamenDto> getAllByPage(@PathVariable("page") int page,
			@PathVariable(value = "size") int size,
			@PathVariable(value = "search", required = false) Optional<String> search) {
		if (search.isPresent())
			return examenService.getAllByPage(page, size, search.get());
		else
			return examenService.getAllByPage(page, size, "");
	}

	@GetMapping(value = "/count", produces = "application/json")
	public CountDto count() {
		return examenService.count("");
	}

	@GetMapping(value = "/count/{search}", produces = "application/json")
	public CountDto count(@PathVariable(value = "search", required = false) Optional<String> search) {
		if (search.isPresent())
			return examenService.count(search.get());
		else
			return examenService.count("");
	}

	@GetMapping(value = "/interventions/{id}", produces = "application/json")
	public List<ExamenDto> findExamensByInterventionId(@PathVariable(value = "id") long id) {
		return examenService.findExamensByInterventionId(id);
	}

	@GetMapping(value = "/file/{idExamen}")
	public ResponseEntity<Resource> getFileExamen(@PathVariable("idExamen") long idExamen) {
		ExamenDto exaDto = examenService.getById(idExamen);

		try {
			Resource file = fileSevice.download(exaDto.getPieceJointe(),"examens");

			Path path = file.getFile().toPath();

			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(path))
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
					.body(file);

		} catch (Exception e) {
			logger.log(Level.WARNING, "Failed Response Entity ? file ? path", e);
			return null;
		}

	}

	// ##################################################
	// # POST #
	// ##################################################

	@PostMapping(consumes = "multipart/form-data", produces = "application/json")
	public ResponseEntity<ExamenDtoSave> save(@RequestParam("examen") String examStr,
			@RequestPart("file") MultipartFile file) throws SaveInvalidException, IOException {

		File f = new File(storageFolder + "/examens/" + file.getOriginalFilename());
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f));
		bos.write(file.getBytes());
		bos.close();
		ExamenDtoSave examenDto = objectMapper.readValue(examStr, ExamenDtoSave.class);
		examenDto.setPieceJointe(file.getOriginalFilename());
		ExamenDtoSave result = examenService.saveOrUpdate(examenDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

	// ##################################################
	// # DELETE #
	// ##################################################

	@DeleteMapping(value = "/{id}", produces = "text/plain")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") long id) {
		try {
			examenService.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression effectuée");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("suppression non réalisée");
		}

	}

	// ##################################################
	// # PUT #
	// ##################################################

	@PutMapping(consumes = "application/json", produces = "application/json")
	public ExamenDtoSave update(@RequestBody ExamenDtoSave eDto) throws SaveInvalidException {
		return examenService.saveOrUpdate(eDto);
	}

	/**
	 * @param id de l'étudiant
	 * @return dans un get, le service qui va chercher toutes les données
	 *         nécessaires pour remplir la section livret Evaluation du front partie
	 *         étudiant
	 */
	@GetMapping(value = "/livret-evaluation/{id}", produces = "application/json")
	public List<LivretEvaluationDto> getLivretEvaluation(@PathVariable("id") long id) {
		return examenService.getLivretEvaluation(id);
	}

	@GetMapping(value = "/bulletin-etudiant/{etudiantId}/{promotionId}", produces = "application/octet-stream")
	public ResponseEntity<Resource> generateBulletinByStudentAndPromo(@PathVariable("etudiantId") long etudiantId, @PathVariable("promotionId") long promotionId) throws Exception {
		String outputhPdfPath = examenService.generateBulletinPdfByStudentAndPromo(etudiantId, promotionId);

		File f = new File(outputhPdfPath);
		Path path = Paths.get(f.getAbsolutePath());
		ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=bulletinEtudiant" + etudiantId + "-promo" + promotionId + ".pdf");
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");

		return ResponseEntity.ok().headers(headers).contentLength(f.length())
				.contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
	}

}
