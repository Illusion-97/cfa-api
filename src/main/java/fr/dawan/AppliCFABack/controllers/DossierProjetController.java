package fr.dawan.AppliCFABack.controllers;

import fr.dawan.AppliCFABack.dto.DossierProjetDto;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.services.DossierProjetService;
import fr.dawan.AppliCFABack.services.EmailService;
import fr.dawan.AppliCFABack.services.EtudiantService;
import fr.dawan.AppliCFABack.services.FilesService;
import io.micrometer.core.lang.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/dossierProjet")
public class DossierProjetController {
	
	@Autowired
	DossierProjetService dossierProService;
	@Autowired
	EtudiantService etudiantService;
	@Autowired
	FilesService fileService;
	@Autowired
	private EmailService emailService;
	
	@Value("${app.storagefolder}")
	private String storageFolder;

	@GetMapping(produces = "application/json")
	public List<DossierProjetDto> getAll() {
		return dossierProService.getAll();
	}
	
	@GetMapping(value = "/{id}",produces = "application/json")
	public DossierProjetDto getById(@PathVariable("id") long id) {
		return dossierProService.getById(id);
	}
	@GetMapping(value = "/etudiant/{id}",produces = "application/json")
	public List<DossierProjetDto> getByIdEtudiant(@PathVariable("id") long id) {
		return dossierProService.getByIdEtudiant(id);
	}
	
	@GetMapping(value = "/{page}/{size}", produces = "application/json")
	public @ResponseBody List<DossierProjetDto> getAllByPage(@PathVariable("page") int page,
			@PathVariable(value = "size") int size) {
		return dossierProService.getAllByPage(page, size, "");
	}
	
	@GetMapping(value = "/{page}/{size}/{search}", produces = "application/json")
 	public @ResponseBody List<DossierProjetDto> getAllByPage(@PathVariable("page") int page,
 			@PathVariable(value = "size") int size, @PathVariable(value = "search", required = false) Optional<String> search) {
 		if(search.isPresent())
 			return dossierProService.getAllByPage(page, size, search.get());
 		else
 			return dossierProService.getAllByPage(page, size, "");
 	}
	@GetMapping(value = "/generer/{idDossierProjet}", produces = "text/plain")
	public ResponseEntity<String> genererDossierProj(
			@PathVariable("idDossierProjet") long idDossierProjet) throws Exception {

		String outpoutPath = (dossierProService.genererDossierProjet(idDossierProjet));
		File f = new File(outpoutPath);
		
		Path path = Paths.get(f.getAbsolutePath());
		byte[] bytes =  Files.readAllBytes(path);
		String base64 = Base64.getEncoder().encodeToString(bytes);

		return ResponseEntity.ok().body(base64);
	}
	@DeleteMapping(value = "/{idEtudiant}/delete/{id}", produces = "text/plain")
	public ResponseEntity<?> deleteById(@PathVariable("idEtudiant") long idEtudiant, @PathVariable("id") long id) {
		try {
			EtudiantDto eDto = etudiantService.getById(idEtudiant);
			for(int i=0;i<eDto.getDossierProjet().size();i++) {
				if (eDto.getDossierProjet().get(i).getId()==id) {
					eDto.getDossierProjet().remove(i);
					
				}
			}
			etudiantService.saveOrUpdate(eDto);
			dossierProService.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression effectuée");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("suppression non réalisée");
		}

	}

	@PutMapping(value = "/update", produces = "application/json")
	public ResponseEntity<DossierProjetDto> updateDossierProjet(
			@RequestBody DossierProjetDto dpDto){
		DossierProjetDto dpEtuDto = dossierProService.saveOrUpdate(dpDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(dpEtuDto);
	}

	@PostMapping(value = "/save", produces = "application/json")
    public ResponseEntity<DossierProjetDto> saveDossierProjet(
    		@RequestBody DossierProjetDto dpDto){
		DossierProjetDto dpEtuDto = dossierProService.saveOrUpdate(dpDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(dpEtuDto);
    }

	@PostMapping(value = "/save-import/{dpid}", consumes = "multipart/form-data", produces = "application/json")
	public ResponseEntity<DossierProjetDto> saveImport(@Nullable @RequestParam("import")MultipartFile file,
															 @PathVariable("dpid") Long id) throws IOException {
		//Chemin a changer selon les directives
		//String path = storageFolder + "DossierProjet" + "/" ;
		//fileService.createDirectory(path);
		DossierProjetDto dpDto = dossierProService.importDossierProjet(file, id);
		return ResponseEntity.status(HttpStatus.CREATED).body(dpDto);
	}

	@PostMapping(value = "/save-annexe/{dpid}", consumes = "multipart/form-data", produces = "application/json")
	public ResponseEntity<DossierProjetDto> saveAnnexe(@RequestParam("pieceJointe") List<MultipartFile> files,
															 @PathVariable("dpid") Long id) throws IOException {
		//Chemin a changer selon les directives
		//String path = storageFolder + "DossierProjet" + "/" ;
		//fileService.createDirectory(path);
		DossierProjetDto dpDto =  dossierProService.saveAnnexesDossierProjet(files, id);
		return ResponseEntity.status(HttpStatus.CREATED).body(dpDto);
	}

	@PutMapping(value = "/update-annexe/{dpid}", consumes = "multipart/form-data", produces = "application/json")
	public ResponseEntity<DossierProjetDto> updateAnnexe(@RequestParam("pieceJointe") List<MultipartFile> files,
													   @PathVariable("dpid") Long id) throws IOException {
		//Chemin a changer selon les directives
		//String path = storageFolder + "DossierProjet" + "/" ;
		//fileService.createDirectory(path);
		DossierProjetDto dpDto =  dossierProService.saveAnnexesDossierProjet(files, id);
		return ResponseEntity.status(HttpStatus.CREATED).body(dpDto);
	}
	//DossierProjetEtudiantDto created = dossierProService.saveOrUpdateDossierProjet(dpEtuDto, id, files, file);
	@DeleteMapping(value = "/{id}", produces = "text/plain")
	public ResponseEntity<DossierProjetDto> deletefile(@RequestParam("file")String file, @PathVariable("id") Long id){
		DossierProjetDto dpDto = dossierProService.deleteFile(file, id);
		return ResponseEntity.status(HttpStatus.OK).body(dpDto);
	}
}
