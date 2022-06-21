package fr.dawan.AppliCFABack.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.ExamenDto;
import fr.dawan.AppliCFABack.dto.ExamenDtoSave;
import fr.dawan.AppliCFABack.dto.LivretEvaluationDto;
import fr.dawan.AppliCFABack.services.ExamenService;
import fr.dawan.AppliCFABack.services.FileService;

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
 			@PathVariable(value = "size") int size, @PathVariable(value = "search", required = false) Optional<String> search) {
 		if(search.isPresent())
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
		if(search.isPresent())
			return examenService.count(search.get());
		else
			return examenService.count("");
	}
    
	@GetMapping(value = "/interventions/{id}", produces = "application/json")
	public List<ExamenDto> findExamensByInterventionId (@PathVariable(value = "id") long id){
		return examenService.findExamensByInterventionId(id);
	}

	@GetMapping(value = "/file/{idExamen}")
	public ResponseEntity<Resource> getFileExamen(@PathVariable("idExamen") long idExamen){
		ExamenDto exaDto = examenService.getById(idExamen);
		
		try {
			Resource file =  fileSevice.download(exaDto.getPieceJointe());
			
			Path path = file.getFile().toPath();
			
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(path))
					.header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+file.getFilename()+"\"").body(file);
					
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	// ##################################################
	// # POST #
	// ##################################################


	@PostMapping(consumes = "multipart/form-data", produces = "application/json")
	public ResponseEntity<ExamenDtoSave> save(@RequestParam("examen") String examStr , @RequestPart("file") MultipartFile file)throws Exception {
		
		File f = new File(storageFolder + "/examens/" + file.getOriginalFilename());
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f));
		bos.write(file.getBytes());
		bos.close();
		ExamenDtoSave examenDto	= objectMapper.readValue(examStr, ExamenDtoSave.class)	;
		 examenDto.setPieceJointe(file.getOriginalFilename());
		 ExamenDtoSave result = examenService.saveOrUpdate(examenDto);
		 return ResponseEntity
				 .status(HttpStatus.CREATED)
				 .body(result);
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
	public ExamenDtoSave update(@RequestBody ExamenDtoSave eDto) throws Exception {
		return examenService.saveOrUpdate(eDto);
	}

	@GetMapping(value = "/livret-evaluation/{id}", produces = "application/json")
	public List<LivretEvaluationDto> getLivretEvaluation(@PathVariable("id") long id) {
		return examenService.getLivretEvaluation(id);
	}

}
