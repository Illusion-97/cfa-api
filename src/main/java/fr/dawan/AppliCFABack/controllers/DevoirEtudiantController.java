package fr.dawan.AppliCFABack.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.dawan.AppliCFABack.dto.DevoirEtudiantDto;
import fr.dawan.AppliCFABack.services.DevoirEtudiantService;

@RestController
@RequestMapping("/devoirsEtudiants")
public class DevoirEtudiantController extends GenericController<DevoirEtudiantDto> {

	@Value("${app.storagefolder}")
	private String storageFolder;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	public DevoirEtudiantController(DevoirEtudiantService service) {
		super(service);
	}

	@GetMapping(value = "/etudiant/{id}", produces = "application/json")
	public List<DevoirEtudiantDto> getAllByEtudiantId(@PathVariable("id") long id) {
		return ((DevoirEtudiantService) service).getAllByEtudiantId(id);
	}

	@GetMapping(value = "/devoir/{id}", produces = "application/json")
	public List<DevoirEtudiantDto> getAllByDevoirId(@PathVariable("id") long id) {
		return ((DevoirEtudiantService) service).getAllByDevoirId(id);
	}

	@PostMapping(consumes = "multipart/form-data", produces = "application/json")
	ResponseEntity<DevoirEtudiantDto> save(@RequestParam("devoirEtudiant") String DevoirStr, @RequestParam("file") MultipartFile file) throws Exception {
		File f = new File(storageFolder + "/devoirsEtudiants/" + file.getOriginalFilename());
		try (BufferedOutputStream  bos= new BufferedOutputStream(new FileOutputStream(f))){
			bos.write(file.getBytes());
		}
		DevoirEtudiantDto  dEDto = objectMapper.readValue(DevoirStr, DevoirEtudiantDto.class);
		dEDto.setPieceJointe(file.getOriginalFilename());
		dEDto = ((DevoirEtudiantService) service).saveOrUpdate(dEDto);
		return ResponseEntity.accepted().body(dEDto);
	}


    

}
