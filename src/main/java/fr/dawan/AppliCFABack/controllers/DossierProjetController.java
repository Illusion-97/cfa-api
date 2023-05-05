package fr.dawan.AppliCFABack.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.dawan.AppliCFABack.dto.DossierProjetDto;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprojet.DossierProjetEtudiantDto;
import fr.dawan.AppliCFABack.services.DossierProjetService;
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
	private ObjectMapper objectMapper;
	
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
	
	@PutMapping(value = "/update/etudiant/{id}", consumes = "multipart/form-data", produces = "application/json")
    public DossierProjetEtudiantDto updateDossierProjet(@PathVariable("id") long id, 
    		@RequestParam("dossierProjet") String dpDto,
    		@RequestParam("pieceJointe") List<MultipartFile> files, @RequestParam("import")MultipartFile file) throws IOException{
		//Chemin a changer selon les directives
		String path = storageFolder + "DossierProjet" + "/" ;
        fileService.createDirectory(path);
        DossierProjetEtudiantDto dpEtuDto = objectMapper.readValue(dpDto, DossierProjetEtudiantDto.class);
		return dossierProService.saveOrUpdateDossierProjet(dpEtuDto, id, files, file);
    }
	
	@PostMapping(value = "/creation/etudiant/{id}", consumes = "multipart/form-data", produces = "application/json")
    public ResponseEntity<DossierProjetEtudiantDto> creationDossierProjet(@PathVariable("id") long id,
    		@RequestParam("dossierProjet") String dpDto,
    		@RequestParam("pieceJointe") List<MultipartFile> files,@Nullable @RequestParam("import")MultipartFile file) throws IOException{

		//Chemin a changer selon les directives
			String path = storageFolder + "DossierProjet" + "/" ;
        	fileService.createDirectory(path);

        DossierProjetEtudiantDto dpEtuDto = objectMapper.readValue(dpDto, DossierProjetEtudiantDto.class);
        DossierProjetEtudiantDto created = dossierProService.saveOrUpdateDossierProjet(dpEtuDto, id, files, file);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
