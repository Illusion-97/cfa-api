package fr.dawan.AppliCFABack.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.dawan.AppliCFABack.controllers.generic.GenericController;
import fr.dawan.AppliCFABack.dto.DossierProfessionnelDto;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprofessionnel.CursusDossierProDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprofessionnel.DossierProEtudiantDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprofessionnel.GetDossierProDto;
import fr.dawan.AppliCFABack.services.*;
import fr.dawan.AppliCFABack.tools.CursusNotFoundException;
import fr.dawan.AppliCFABack.tools.DossierProfessionnelException;
import fr.dawan.AppliCFABack.tools.EtudiantNotFoundException;
import freemarker.template.TemplateException;
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
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/dossierProfessionnel")
@CrossOrigin(origins = "*")
public class DossierProfessionnelController extends GenericController<DossierProfessionnelDto> {

	protected DossierProfessionnelController(DossierProfessionnelService service) {
		super(service);
	}

	@Autowired
	DossierProfessionnelService dossierProService;
	
	@Autowired
	AnnexeService annexeService;
	
	@Autowired
	FacultatifService facultatifService;
	
	@Autowired
	EtudiantService etudiantService;
	
	@Autowired
	CursusService cursusService;
	
	@Autowired
	FilesService fileService;

	@Autowired
	private ObjectMapper objMap;

	@Value("src/main/resources/pictures/")
	private String storageFolder2;

	@GetMapping(produces = "application/json")
	public List<DossierProfessionnelDto> getAll() {
		return dossierProService.getAll();
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

	
	@PostMapping(value = "/save/etudiant/{id}", consumes = "multipart/form-data", produces = "application/json")
	public ResponseEntity<DossierProEtudiantDto> saveDossierProfessionnel(@RequestParam("dossierProfessionnel") String dpDto, @PathVariable("id") long id,
	        @RequestParam("pieceJointe") List<MultipartFile> files) throws TemplateException, DossierProfessionnelException, IOException  {
	    String path = storageFolder2 + "DossierProfessionnel" + "/";
	    fileService.createDirectory(path);
	    DossierProEtudiantDto dpEtDto = objMap.readValue(dpDto, DossierProEtudiantDto.class);
	    DossierProEtudiantDto dpE = dossierProService.saveOrUpdateDossierProfessionnel(dpEtDto, id, files);
	    dossierProService.emailTuteurDossierProfessionnelle(dpEtDto, id);
	    return ResponseEntity.status(HttpStatus.CREATED).body(dpE);
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
	public ResponseEntity<DossierProEtudiantDto> updateDossierProfessionnel(@RequestParam("dossierProfessionnel") String dpDto, @PathVariable("id") long id,
	        @RequestParam("pieceJointe") List<MultipartFile> files) throws TemplateException, DossierProfessionnelException, IOException  {
	    String path = storageFolder2 + "DossierProfessionnel" + "/";
	    fileService.createDirectory(path);
	    DossierProEtudiantDto dpEtDto = objMap.readValue(dpDto, DossierProEtudiantDto.class);
	    DossierProEtudiantDto dpE = dossierProService.saveOrUpdateDossierProfessionnel(dpEtDto, id, files);
	    dossierProService.emailTuteurDossierProfessionnelle(dpEtDto, id);
	    return ResponseEntity.status(HttpStatus.CREATED).body(dpE);
	}

	
	@GetMapping(value = "/dossier-professionnel/{dossierId}", produces = "application/pdf")
	public ResponseEntity<Resource> generateDossierProPdf(@PathVariable long dossierId) throws Exception {
	    String outputpdfPath = dossierProService.generateDossierProPdf(dossierId);

	    File f = new File(outputpdfPath);
	    Path path = Paths.get(f.getAbsolutePath());
	    ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
	    HttpHeaders headers = new HttpHeaders();
	    headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=dossierEtudiant" + dossierId + "dp" + ".pdf");
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

	@DeleteMapping(value = "/deleteFile/{id}", produces = "text/plain")
	public ResponseEntity<DossierProEtudiantDto> deletefile( @PathVariable("id") Long id, @RequestParam("fileImport") String file){
		DossierProEtudiantDto dpDto = dossierProService.deleteFileImportById(id, file);
		return ResponseEntity.status(HttpStatus.OK).body(dpDto);
	}
	
	@PostMapping(value = "/saveFile/{dossierId}", consumes = "multipart/form-data", produces = "application/json")
	public ResponseEntity<DossierProEtudiantDto> saveFileImport(@RequestParam("fileImport")MultipartFile fileImport,
															 @PathVariable("dossierId") long id) throws IOException, DossierProfessionnelException {
		DossierProEtudiantDto dpDto = dossierProService.saveFileImport(fileImport, id);
		return ResponseEntity.status(HttpStatus.CREATED).body(dpDto);
	}
	
	@DeleteMapping("/facultatif/{facultatifId}")
	public ResponseEntity<String> deleteFacultatif(@PathVariable long facultatifId) {
		try {
			facultatifService.deleteById(facultatifId);
			return new ResponseEntity<>("Facultatif supprimé avec succès.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Erreur lors de la suppression du facultatif : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
