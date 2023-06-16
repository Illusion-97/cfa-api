package fr.dawan.AppliCFABack.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import fr.dawan.AppliCFABack.dto.customdtos.dossierprofessionnel.GetDossierProDto;
import fr.dawan.AppliCFABack.entities.DossierProfessionnel;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.ExperienceProfessionnelle;

import org.modelmapper.internal.util.Lists;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.dawan.AppliCFABack.dto.DossierProfessionnelDto;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprofessionnel.DossierProEtudiantDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprofessionnel.GetDossierProDto;
import fr.dawan.AppliCFABack.services.DossierProfessionnelService;
import fr.dawan.AppliCFABack.services.EtudiantService;
import fr.dawan.AppliCFABack.services.FilesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
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
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.dawan.AppliCFABack.dto.CursusDto;
import fr.dawan.AppliCFABack.dto.DossierProfessionnelDto;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.ExperienceProfessionnelleDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprofessionnel.CursusDossierProDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprofessionnel.DossierProEtudiantDto;
import fr.dawan.AppliCFABack.services.CursusService;
import fr.dawan.AppliCFABack.services.DossierProfessionnelService;
import fr.dawan.AppliCFABack.services.EtudiantService;
import fr.dawan.AppliCFABack.services.FilesService;
import fr.dawan.AppliCFABack.tools.CursusNotFoundException;
import fr.dawan.AppliCFABack.tools.EtudiantNotFoundException;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/dossierProfessionnel")
@CrossOrigin(origins = "*")
public class DossierProfessionnelController {

	@Autowired
	DossierProfessionnelService dossierProService;
	
	@Autowired
	EtudiantService etudiantService;
	
	@Autowired
	CursusService cursusService;

	@Value("${app.storagefolder}")
	private String storageFolder;
	
	@Autowired
	FilesService fileService;

	@Autowired
	private ObjectMapper objMap;

	 @Value("src/main/resources/files/")
	    private String storageFolder2;

	@GetMapping(produces = "application/json")
	public List<DossierProfessionnelDto> getAll() {
		return dossierProService.getAll();
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public DossierProfessionnelDto getById(@PathVariable("id") long id) {
		return dossierProService.getById(id);
	}

	@GetMapping(value = "/etudiant/{id}", produces = "application/json")
	public List<DossierProfessionnelDto> getByIdEtudiant(@PathVariable("id") long id) {
		return dossierProService.getByIdEtudiant(id);
	}

	@GetMapping(value = "/{page}/{size}", produces = "application/json")
	public @ResponseBody List<DossierProfessionnelDto> getAllByPage(@PathVariable("page") int page,
			@PathVariable(value = "size") int size) {
		return dossierProService.getAllByPage(page, size, "");
	}

	@GetMapping(value = "/{page}/{size}/{search}", produces = "application/json")
	public @ResponseBody List<DossierProfessionnelDto> getAllByPage(@PathVariable("page") int page,
			@PathVariable(value = "size") int size,
			@PathVariable(value = "search", required = false) Optional<String> search) {
		if (search.isPresent())
			return dossierProService.getAllByPage(page, size, search.get());
		else
			return dossierProService.getAllByPage(page, size, "");
	}

	@PostMapping(value = "/save/{id}", consumes = "application/json", produces = "application/json")
	public DossierProfessionnelDto saveOrUpdate(@PathVariable("id") long id, @RequestBody DossierProfessionnelDto dpDto) {
		DossierProfessionnelDto dpDto1 = dossierProService.getByName(dpDto.getNom());
		/*if (dpDto1 != null) {
			return null;
		}*/
		DossierProfessionnelDto dp = dossierProService.saveOrUpdate(dpDto);
		EtudiantDto eDto = etudiantService.getById(id);
		eDto.getDossierProfessionnel().add(dp);
		etudiantService.saveOrUpdate(eDto);
		return dp;
	}

	@DeleteMapping(value = "/{idEtudiant}/delete/{id}", produces = "text/plain")
	public ResponseEntity<?> deleteById(@PathVariable("idEtudiant") long idEtudiant,
			@PathVariable(value = "id") long id) {
		try {
			EtudiantDto eDto = etudiantService.getById(idEtudiant);
			for (int i = 0; i < eDto.getDossierProfessionnel().size(); i++) {
				if (eDto.getDossierProfessionnel().get(i).getId() == id) {
					eDto.getDossierProfessionnel().remove(i);

				}
			}
			etudiantService.saveOrUpdate(eDto);
			dossierProService.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression effectuée");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("suppression non réalisée");
		}

	}
	
	@PutMapping(value = "/update/{id}", consumes = "application/json", produces = "application/json")
	public DossierProfessionnelDto update(@RequestBody DossierProfessionnelDto dpDto, @PathVariable("id") long id) {
		DossierProfessionnelDto dpDto1 = dossierProService.getByName(dpDto.getNom());
		if (dpDto1 != null) {
			return null;
		}
		DossierProfessionnelDto dp = dossierProService.saveOrUpdate(dpDto);
		EtudiantDto eDto = etudiantService.getById(id);
		eDto.getDossierProfessionnel().add(dp);
		etudiantService.saveOrUpdate(eDto);
		return dp;
	}


	
	@PostMapping(value = "/save/etudiant/{id}", consumes = "multipart/form-data", produces = "application/json")
	public DossierProEtudiantDto saveDossierProfessionnel(@RequestParam("dossierProfessionnel") String dpDto, @PathVariable("id") long id ,
			@RequestParam("pieceJointe") List<MultipartFile> file) throws JsonMappingException, JsonProcessingException {
		String path = storageFolder + "DossierProfessionnel" + "/";
		 fileService.createDirectory(path);
		 DossierProEtudiantDto dpEtDto = objMap.readValue(dpDto, DossierProEtudiantDto.class);
		DossierProEtudiantDto dpE = dossierProService.saveOrUpdateDossierProfessionnel(dpEtDto, id, file);
		return dpE;
	}

	@GetMapping(value = "/etudiant",produces = "application/json")
	public List<DossierProEtudiantDto> getAllDossierProfessionnel() {
		return dossierProService.getAllDossierProfessionnel();
	}

	@GetMapping(value = "/cursus/etudiant/{id}", produces = "application/json")
	public GetDossierProDto getAllDossierProfessionnelByEtudiantAndByCursus(@PathVariable("id") long id) {
		return dossierProService.getAllDossierProfessionnelByEtudiant(id);
	}

	@PutMapping(value = "/update/etudiant/{id}", consumes = "multipart/form-data", produces = "application/json")
	public DossierProEtudiantDto updateDossierProfessionnel(@PathVariable("id") long id, @RequestParam("dossierProfessionnel") String dpDto,
			@RequestParam("pieceJointe") List<MultipartFile> file) throws JsonMappingException, JsonProcessingException {
		 String path = storageFolder + "DossierProfessionnel" + "/";
		 fileService.createDirectory(path);
		 DossierProEtudiantDto dpEtDto = objMap.readValue(dpDto, DossierProEtudiantDto.class);
		return dossierProService.saveOrUpdateDossierProfessionnel(dpEtDto, id, file);
	}

	@GetMapping(value="/dossier-professionnel/{etudiantId}/{promotionId}", produces="application/octet-stream")
	public ResponseEntity<Resource> generateDossierProByStudentAndPromo(@PathVariable("etudiantId") long etudiantId, @PathVariable("promotionId") long promotionId) throws Exception {
		String outputpdfPath = dossierProService.generateDossierProByStudentAndPromo(etudiantId, promotionId);

		File f = new File(outputpdfPath);
		Path path = Paths.get(f.getAbsolutePath());
		ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=dossierEtudiant" + etudiantId + "-promo" + promotionId + ".pdf");
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires","0");

		return ResponseEntity.ok().headers(headers).contentLength(f.length()).contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
	}
	
	@GetMapping(value = "/generer/{idDossierPro}", produces = "text/plain")
	public ResponseEntity<String> genererDossierProfessionnel(
			@PathVariable("idDossierPro") long idDossierPro) throws Exception {

		String outpoutPath = (dossierProService.genererDossierProfessionnel(idDossierPro));
		File f = new File(outpoutPath);
		
		Path path = Paths.get(f.getAbsolutePath());
		byte[] bytes =  Files.readAllBytes(path);
		String base64 = Base64.getEncoder().encodeToString(bytes);

		return ResponseEntity.ok().body(base64);
	}
	
	
	@PostMapping(value = "/upload/{etudiantId}/{cursusId}", consumes = "multipart/form-data", produces = "application/json")
	public ResponseEntity<String> handleFileUpload(@PathVariable("etudiantId") long etudiantId, @PathVariable("cursusId") long cursusId, @RequestParam("fileImport") MultipartFile file, @RequestParam("nom") String nom ) throws IOException, EtudiantNotFoundException, CursusNotFoundException {
	    String message = "";
	    List<MultipartFile> files = new ArrayList<>();
	    files.add(file);
	  
	    try {    
	    
	        // Enregistrer le fichier sur le serveur
	        byte[] bytes = file.getBytes();
	        Path path = Paths.get(storageFolder2 + "DossierProfessionnel" + "/" + file.getOriginalFilename());
	        Files.write(path, bytes);

	        // Enregistrer le fichier dans la base de données avec etudiantId
	        EtudiantDto etudiant = new EtudiantDto();	      
	        etudiant.setId(etudiantId);
	        
	        CursusDossierProDto cursus =  new CursusDossierProDto();
	        cursus.setId(cursusId);
	       

	        // Enregistrer le fichier importé pour l'étudiant dans la base de données
	        DossierProEtudiantDto dossier = new DossierProEtudiantDto();
	        dossier.setNom(nom);
	        dossier.setCursusDto(cursus);     
	        dossier.setExperienceProfessionnelleDtos(new ArrayList<>());
	        dossier.setAnnexeDtos(new ArrayList<>());
	        dossier.setFacultatifDto(new ArrayList<>());
	        dossier.setFileImport(file.getOriginalFilename());
	        
	        dossierProService.saveOrUpdateDossierProfessionnel(dossier, etudiantId, files);

	        message = "Le fichier " + file.getOriginalFilename() + " a été téléchargé avec succès   !";
	        return ResponseEntity.status(HttpStatus.OK).body(message);
	    } catch (IOException e) {
	        message = "Impossible de télécharger le fichier " + file.getOriginalFilename() + " !";
	        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
	    }
	}

	
	

}
