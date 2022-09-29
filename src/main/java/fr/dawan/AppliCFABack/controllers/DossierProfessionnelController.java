package fr.dawan.AppliCFABack.controllers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import fr.dawan.AppliCFABack.dto.customdtos.GetDossierProDto;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.AppliCFABack.dto.DossierProfessionnelDto;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.customdtos.DossierProEtudiantDto;
import fr.dawan.AppliCFABack.services.DossierProfessionnelService;
import fr.dawan.AppliCFABack.services.EtudiantService;

@RestController
@RequestMapping("/dossierProfessionnel")
public class DossierProfessionnelController {

	@Autowired
	DossierProfessionnelService dossierProService;
	@Autowired
	EtudiantService etudiantService;

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
	public DossierProfessionnelDto save(@PathVariable("id") long id, @RequestBody DossierProfessionnelDto dpDto) {
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

	@PutMapping(value = "/save/{id}", consumes = "application/json", produces = "application/json")
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

	@PostMapping(value = "/save/etudiant/{id}", consumes = "application/json", produces = "application/json")
	public DossierProEtudiantDto saveDossierProfessionnel(@RequestBody DossierProEtudiantDto dpDto, @PathVariable("id") long id) {
		return dossierProService.saveOrUpdateDossierProfessionnel(dpDto, id);
	}

	@GetMapping(value = "/etudiant",produces = "application/json")
	public List<DossierProEtudiantDto> getAllDossierProfessionnel() {
		return dossierProService.getAllDossierProfessionnel();
	}

	@GetMapping(value = "/cursus/etudiant/{id}", produces = "application/json")
	public GetDossierProDto getAllDossierProfessionnelByEtudiantAndByCursus(@PathVariable("id") long id) {
		return dossierProService.getAllDossierProfessionnelByEtudiant(id);
	}

	@PutMapping(value = "/update/etudiant/{id}", consumes = "application/json", produces = "application/json")
	public DossierProEtudiantDto updateDossierProfessionnel(@PathVariable("id") long id, @RequestBody DossierProEtudiantDto dpDto) {
		return dossierProService.saveOrUpdateDossierProfessionnel(dpDto, id);
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
}
