package fr.dawan.AppliCFABack.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.dawan.AppliCFABack.dto.DossierProfessionnelDto;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprofessionnel.CursusDossierProDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprofessionnel.DossierProEtudiantDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprofessionnel.GetDossierProDto;
import fr.dawan.AppliCFABack.services.*;
import fr.dawan.AppliCFABack.tools.CursusNotFoundException;
import fr.dawan.AppliCFABack.tools.EtudiantNotFoundException;
import io.micrometer.core.lang.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
	AnnexeService annexeService;
	
	@Autowired
	EtudiantService etudiantService;
	
	@Autowired
	CursusService cursusService;
	
	@Autowired
	FilesService fileService;

	@Autowired
	private ObjectMapper objMap;

	@Value("${app.storagefolder2}")
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
	
	@PostMapping(value = "/save/etudiant/{id}", consumes = "multipart/form-data", produces = "application/json")
	public DossierProEtudiantDto saveDossierProfessionnel(@RequestParam("dossierProfessionnel") String dpDto, @PathVariable("id") long id,
	        @RequestParam("pieceJointe") List<MultipartFile> files) throws JsonMappingException, JsonProcessingException {
	    String path = storageFolder2 + "DossierProfessionnel" + "/";
	    fileService.createDirectory(path);
	    DossierProEtudiantDto dpEtDto = objMap.readValue(dpDto, DossierProEtudiantDto.class);
	    DossierProEtudiantDto dpE = dossierProService.saveOrUpdateDossierProfessionnel(dpEtDto, id, files);
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
		 String path = storageFolder2 + "DossierProfessionnel" + "/";
		 fileService.createDirectory(path);
		 DossierProEtudiantDto dpEtDto = objMap.readValue(dpDto, DossierProEtudiantDto.class);
		return dossierProService.saveOrUpdateDossierProfessionnel(dpEtDto, id, file);
	}
	
	@GetMapping(value = "/dossier-professionnel/{dossierId}", produces = "application/pdf")
	public ResponseEntity<Resource> generateDossierProPdf(@PathVariable long dossierId) throws Exception {
	    String outputpdfPath = dossierProService.generateDossierProPdf(dossierId);

	    File f = new File(outputpdfPath);
	    Path path = Paths.get(f.getAbsolutePath());
	    ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
	    HttpHeaders headers = new HttpHeaders();
	    headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=dossierEtudiant" + dossierId + "dp" + ".pdf");
	    headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
	    headers.add("Pragma", "no-cache");
	    headers.add("Expires", "0");

	    return ResponseEntity.ok().headers(headers).contentLength(f.length()).contentType(MediaType.APPLICATION_PDF).body(resource);
	}

	
	@DeleteMapping("/annexes/{annexeId}")
    public ResponseEntity<String> deleteAnnexe(@PathVariable Long annexeId) {
        boolean isDeleted = annexeService.deleteAnnexe(annexeId);
        
        if (isDeleted) {
            return new ResponseEntity<>("Annexe deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Annexe not found", HttpStatus.NOT_FOUND);
        }
    }
	
	
	@PostMapping(value = "/upload/{etudiantId}/{cursusId}", consumes = "multipart/form-data", produces = "application/json")
	public ResponseEntity<String> handleFileUpload(@PathVariable("etudiantId") long etudiantId, @PathVariable("cursusId") long cursusId, @RequestParam("fileImport") MultipartFile file, @RequestParam("nom") String nom ) throws IOException, EtudiantNotFoundException, CursusNotFoundException {
	    String message = "";
	    List<MultipartFile> files = new ArrayList<>();
	    files.add(file);
	  
	    try {    
	    
	        byte[] bytes = file.getBytes();
	        Path path = Paths.get(storageFolder2 + "DossierProfessionnel" + "/" + file.getOriginalFilename());
	        Files.write(path, bytes);

	        EtudiantDto etudiant = new EtudiantDto();	      
	        etudiant.setId(etudiantId);
	        
	        CursusDossierProDto cursus =  new CursusDossierProDto();
	        cursus.setId(cursusId);
	       
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

	@DeleteMapping(value = "/deleteFile/{id}", produces = "application/json")
	public ResponseEntity<DossierProEtudiantDto> deletefile( @PathVariable("id") Long id, @RequestParam("fileImport") String file) throws Exception{
		DossierProEtudiantDto dpDto = dossierProService.deleteFileImportById(id, file);
		return ResponseEntity.status(HttpStatus.OK).body(dpDto);
	}
	
	@PostMapping(value = "/uploadFile/{dossierId}", consumes = "multipart/form-data", produces = "application/json")
	public ResponseEntity<DossierProEtudiantDto> saveFileImport(@Nullable @RequestParam("fileImport")MultipartFile fileImport,
															 @PathVariable("dossierId") Long id) throws IOException {
		DossierProEtudiantDto dpDto = dossierProService.saveFileImport(fileImport, id);
		return ResponseEntity.status(HttpStatus.CREATED).body(dpDto);
	}
	
}
