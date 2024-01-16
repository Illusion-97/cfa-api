package fr.dawan.AppliCFABack.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.dawan.AppliCFABack.dto.AbsenceDto;
import fr.dawan.AppliCFABack.services.AbsenceService;
import fr.dawan.AppliCFABack.services.EtudiantService;
import fr.dawan.AppliCFABack.services.FileService;
import fr.dawan.AppliCFABack.services.FilesService;
import fr.dawan.AppliCFABack.tools.SaveInvalidException;

/**
 * @author Valentin C, Feres BG.
 * @see fr.dawan.appliCFABack.service
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return Controller de la classe Absence
 */
@RestController
@RequestMapping("/absences")
public class AbsenceController extends GenericController<AbsenceDto> {
	
	@Value("${app.storagefolder}")
	private String storageFolder;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	public AbsenceController(AbsenceService service) {
		super(service);
	}
	
	@Autowired
	private EtudiantService etudiantService;
	
	@Autowired
	FileService fileSevice; 
	
	@Autowired
	FilesService filesService;
	
	private static final Logger logger = Logger.getGlobal();
	
	/**
	 * 
	 * @param id de l'intervention concernée
	 * @return les absences correspondants a l'intervention concernée
	 */
	@GetMapping(value = "/intervention/{id}", produces = "application/json")
	public List<AbsenceDto> getAllByInterventionId(@PathVariable("id") long id){
		return ((AbsenceService) service).getAllByInterventionId(id);
	}
	
	/**
	 * 
	 * @param id de l'étudiant concerné
	 * @return toutes les absences de l'étudiant concerné
	 */
	@GetMapping(value = "/etudiant/id/{id}", produces = "application/json")
	public List<AbsenceDto> getAllByEtudiantId(@PathVariable("id") long id){
		return ((AbsenceService) service).getAllByEtudiantId(id);
	}
	
	/**
	 * 
	 * @param search champs de recherche du nom ou prénom
	 * @return toutes les occurences contenant le champs de recherche
	 */
	@GetMapping(value = "/etudiant/search/{search}", produces = "application/json")
	public List<AbsenceDto> getAllByNomPrenomContaining(@PathVariable("search") String search){
		return ((AbsenceService) service).getAllByNomPrenomContaining(search);
	}
	
	@GetMapping(value = "/justificatif/{idAbsence}")
	public ResponseEntity<String> getJustificatifByAbsenceId(@PathVariable("idAbsence") long idAbsence) throws Exception {
				
		String outpoutPath = ((AbsenceService) service).getJustificatifByAbsenceId(idAbsence);
		File f = new File(outpoutPath);
		Path path = Paths.get(f.getAbsolutePath());
		byte[] bytes =  Files.readAllBytes(path	);
		String base64 = Base64.getEncoder().encodeToString(bytes);
		
		return ResponseEntity.ok().body(base64);
	}
	
//	@PutMapping(consumes="application/json", produces="application/json")
//	public AbsenceDto update(@RequestBody AbsenceDto aDto) throws SaveInvalidException{
//		return ((AbsenceService) service).saveOrUpdate(aDto);
//	}
//	
	
	@PutMapping(consumes="multipart/form-data", produces="application/json")
	public AbsenceDto update(@RequestParam("absence") String absStr, 
			@RequestParam("fileJustificatif") MultipartFile file) throws SaveInvalidException, IOException{
		
		
		//A changer : bien rentrer le nom du fichier
		File f = new File(storageFolder + "/justificatifAbsenceEtudiant/" + file.getOriginalFilename());
		
		try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f))) {
	        bos.write(file.getBytes());
	    }
		
		AbsenceDto absDto = objectMapper.readValue(absStr, AbsenceDto.class);
		
		return service.saveOrUpdate(absDto);
	}

	
	@PostMapping(value="/envoi-justificatif" ,consumes="multipart/form-data", produces="application/json")
	public AbsenceDto postAbsence(@RequestParam(name = "absence") String absStr, 
			@RequestParam("fileJustificatif") MultipartFile file) throws SaveInvalidException, IOException{
		
		//A changer : bien rentrer le nom du fichier
		String pathJustificatif = storageFolder + "/justificatifAbsenceEtudiant/" + file.getOriginalFilename();
		File f = new File(pathJustificatif);
		
		try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f))) {
	        bos.write(file.getBytes());
	    }
				
		AbsenceDto absDto = objectMapper.readValue(absStr, AbsenceDto.class);
		absDto.setJustificatif(pathJustificatif);		
		return service.saveOrUpdate(absDto);
	}
	

	// ##################################################
	// # GET #
	// ##################################################

//	@GetMapping(produces = "application/json")
//	public List<AbsenceDto> getAll() {
//		return absenceService.getAllAbsence();
//	}
//

//
//	// /AppliCFABack/groupeEtudiants/{page}/{size}/{search}
//	@GetMapping(value = "/{page}/{size}/{search}", produces = "application/json")
//	public @ResponseBody List<AbsenceDto> getAllByPage(@PathVariable("page") int page,
//			@PathVariable(value = "size") int size, @PathVariable("search") Optional<String> search) {
//		if (search.isPresent())
//			return absenceService.getAllAbsence(page, size, search.get());
//
//		return absenceService.getAllAbsence(page, size, "");
//	}
//
//	// /AppliCFABack/groupeEtudiants/{page}/{size}
//	@GetMapping(value = "/{page}/{size}", produces = "application/json")
//	public @ResponseBody List<AbsenceDto> getAllByPage(@PathVariable("page") int page,
//			@PathVariable(value = "size") int size) {
//		return absenceService.getAllAbsence(page, size, "");
//	}
//
//	@GetMapping(value = "/count/{search}", produces = "application/json")
//	public CountDto count(@PathVariable("search") Optional<String> search) {
//		if (search.isPresent())
//			return absenceService.count(search.get());
//		return absenceService.count("");
//	}
	
//	@GetMapping(value = "/promotion/{id}/absences", produces = "application/json")
//	public List<AbsenceDto> getAbsenceByPromotionId(@PathVariable("id") long id) {
//		return absenceService.getAbsenceByReferentId(id);
//	}
}
