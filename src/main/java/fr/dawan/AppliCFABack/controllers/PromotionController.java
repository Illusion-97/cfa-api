package fr.dawan.AppliCFABack.controllers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.PromotionDto;
import fr.dawan.AppliCFABack.dto.PromotionForSelectDto;
import fr.dawan.AppliCFABack.dto.UtilisateurDto;
import fr.dawan.AppliCFABack.dto.customdtos.PromotionEtudiantDto;
import fr.dawan.AppliCFABack.services.FileService;
import fr.dawan.AppliCFABack.services.PromotionService;

@RestController
@RequestMapping("/promotions")
public class PromotionController {

	
	@Autowired
	private PromotionService promoService;
	
	@Autowired
	FileService fileSevice; 
	
	private static Logger logger = Logger.getGlobal();
	
	@GetMapping(produces = "application/json")
	public List<PromotionDto> getAll() {
		return promoService.getAll();
	}
	
	@GetMapping(value = "/{page}/{size}", produces = "application/json")
 	public @ResponseBody List<PromotionDto> getAllByPage(@PathVariable("page") int page, @PathVariable(value = "size") int size) {
 		return promoService.getAllPromotions(page, size, "");
 	}
 	
 	@GetMapping(value = "/{page}/{size}/{search}", produces = "application/json")
 	public @ResponseBody List<PromotionDto> getAllByPage(@PathVariable("page") int page,
 			@PathVariable(value = "size") int size, @PathVariable(value = "search", required = false) Optional<String> search) {
 		if(search.isPresent())
 			return promoService.getAllPromotions(page, size, search.get());
 		else
 			return promoService.getAllPromotions(page, size, "");
 	}
	
	@GetMapping(value = "/{id}",produces = "application/json")
	public PromotionDto getById(@PathVariable("id") long id) {
		return promoService.getById(id);
	}
	
	@GetMapping(value = "/count", produces = "application/json")
	public CountDto count() {
		return promoService.count("");
	}
	
	@GetMapping(value = "/count/{search}", produces = "application/json")
	public CountDto count(@PathVariable(value = "search", required = false) Optional<String> search) {
		if(search.isPresent())
			return promoService.count(search.get());
		else
			return promoService.count("");
	}
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public PromotionDto save(@RequestBody PromotionDto pDto) {
		return promoService.saveOrUpdate(pDto);
	}
	
	@DeleteMapping(value = "/{id}", produces = "text/plain")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") long id) {
		try {
			promoService.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression effectuée");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("suppression non réalisée");
		}
	}
	
	@PutMapping(consumes = "application/json", produces = "application/json")
	public PromotionDto update(@RequestBody PromotionDto pDto) {
		return promoService.saveOrUpdate(pDto);
	}
	
	
	@GetMapping(value = "/{id}/referent",produces = "application/json")
	public UtilisateurDto getReferentById(@PathVariable("id") long id) {
		return promoService.getReferentById(id);
	}
	
	@GetMapping(value = "/{id}/etudiants",produces = "application/json")
	public List<EtudiantDto> getEtudiantsById(@PathVariable("id") long id) {
		return promoService.getEtudiantsById(id);
	}

	/**
	 * Erreur méthodes controller-service-repo à refaire avec un dto custom pour l'accueil entier
	 */
	@GetMapping(value = "/{id}/cefs",produces = "application/json")
    public UtilisateurDto getCefById(@PathVariable("id") long id) {
        return promoService.getCefById(id);
    }
	
	@GetMapping(value = "/{id}/etudiants/cursus", produces = "application/json")
	public List<PromotionDto> getPromotionByEtudiantIdAndByCursusId(@PathVariable("id") long id) {
		return promoService.getPromotionByEtudiantIdAndByCursusId(id);
	}

	/**
	 * @param id de l'étudiant
	 * @return dans un get, le service qui va récupérer les données nécessaires pour afficher la section Cursus de l'espace étudiant partie front
	 */
	@GetMapping(value = "/cursus-etudiant/{id}", produces = "application/json")
	public List<PromotionEtudiantDto> getCursusByIdEtudiant(@PathVariable("id") long id) {
		return promoService.getCursusByIdEtudiant(id);
	}
	@GetMapping(value = "/intervention-select/{idIntervention}", produces = "application/json")
	public List<PromotionForSelectDto> getPromotionsByInterventionIdForSelect(@PathVariable("idIntervention") long idIntervention ){
		return promoService.getPromotionByInterventionIdForSelect(idIntervention);
	}
	@GetMapping(value = {"/dg2" ,"/dg2/{idCursusDg2}" }, produces="application/json")
	public ResponseEntity<String> fetchAllDG2(@PathVariable("idCursusDg2") Optional<Long> idCursusDg2, @RequestHeader Map<String, String> headers){
		String userDG2 = headers.get("x-auth-token");
		String[] splitUser = userDG2.split(":");
		
		try {
			int nb = 0;
			if (idCursusDg2.isPresent()) {
				
				nb= promoService.fetchDGPromotions(splitUser[0], splitUser[1],idCursusDg2.get());
				
			}
			else {
				 nb = promoService.fetchDGPromotions(splitUser[0], splitUser[1]);

			}
			return ResponseEntity.status(HttpStatus.OK).body("Succeed to fetch data from the webservice DG2. Promotions updated :" +nb);
		} catch (Exception e) {
			logger.log(Level.WARNING, "Failed Response Entity ERROR FETCH", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error while fetching data from the webservice DG2");
		}
	}
	@GetMapping(value = "/grillePositionnement/{idPromotion}",  produces = "application/octet-stream")
	public ResponseEntity<Resource> getGrillePositionnement(@PathVariable("idPromotion") long idPromotion) throws Exception {
		
		String outpoutPath = promoService.getGrillePositionnement(idPromotion);
		File f = new File(outpoutPath);
		
		Path path = Paths.get(f.getAbsolutePath());
		ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
		
		//Pour afficher un boite de téléchargement dans une réponse web au lieu de changer de page, nous devons
		//spécifier un header : Content-Disposition, attachment;filename=app.log
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=app.log");
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=app.log");
		
		return ResponseEntity.ok()
				.headers(headers).contentLength(f.length()).contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
	}
	
	@GetMapping(value = "grillePositionnement/file/{idPromotion}")
	public ResponseEntity<Resource> getFileGrillePositionnement(@PathVariable("idPromotion") long idPromotion) {
		PromotionDto promotionDto = promoService.getById(idPromotion);

		try {
			Resource file = fileSevice.download("GrillePositionnement " +promotionDto.getNom()+".pdf","grillePositionnements");

			Path path = file.getFile().toPath();

			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(path))
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
					.body(file);

		} catch (Exception e) {
			logger.log(Level.WARNING, "Failed Response Entity ? file ? path", e);
			return null;
		}

	}
	
	@GetMapping(value = "/centreFormation/{idCentreFormation}/{page}/{size}", produces="application/json")
	public List<PromotionDto> getPromoByCentreFormationIdPagination(@PathVariable("idCentreFormation") long id, @PathVariable("page") int page,
			@PathVariable("size") int size){
		return promoService.getPromoByCentreFormationIdPagination(page, size, id);
	}
	
	
}
