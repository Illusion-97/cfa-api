package fr.dawan.AppliCFABack.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.CursusDto;
import fr.dawan.AppliCFABack.dto.PromotionDto;
import fr.dawan.AppliCFABack.services.CursusService;
import fr.dawan.AppliCFABack.services.EtudiantService;
import fr.dawan.AppliCFABack.services.PromotionService;

@RestController
@RequestMapping("/cursus")
public class CursusController {

	
	@Autowired
	private CursusService cursusService;
	
	@Autowired
	private EtudiantService etudiantService;
	
	@GetMapping(produces = "application/json")
	public List<CursusDto> getAll() {
		return cursusService.getAll();
	}
	
	@GetMapping(value = "/{id}",produces = "application/json")
	public CursusDto getById(@PathVariable("id") long id) {
		return cursusService.getById(id);
	}
	
	@GetMapping(value = "/{page}/{size}", produces = "application/json")
	public @ResponseBody List<CursusDto> getAllByPage(@PathVariable("page") int page,
			@PathVariable(value = "size") int size) {
		return cursusService.getAllByPage(page, size, "");
	}
	
	@GetMapping(value = "/{page}/{size}/{search}", produces = "application/json")
 	public @ResponseBody List<CursusDto> getAllByPage(@PathVariable("page") int page,
 			@PathVariable(value = "size") int size, @PathVariable(value = "search", required = false) Optional<String> search) {
 		if(search.isPresent())
 			return cursusService.getAllByPage(page, size, search.get());
 		else
 			return cursusService.getAllByPage(page, size, "");
 	}
	
	@GetMapping(value = "/promotion/{id}",produces = "application/json")
	public CursusDto getByIdPromotion(@PathVariable("id") long id) {
		return cursusService.getByIdPromotion(id);
	}
	
	@GetMapping(value = "/{id}/promotions",produces = "application/json")
	public List<PromotionDto> getPromotionsById(@PathVariable("id") long id) {
		return cursusService.getPromotionsById(id);
	}
	
	@GetMapping(value = "/etudiant/{id}",produces = "application/json")
	public List<CursusDto> getByIdEtudiant(@PathVariable("id") long id) {
		List<PromotionDto> lstpDto = etudiantService.getPromotionsByIdEtudiant(id);
		List<CursusDto> lstCursus = new ArrayList<CursusDto>();
		List<CursusDto> lstCursusMostRecent = new ArrayList<CursusDto>();
		for (PromotionDto pDto : lstpDto) {
			CursusDto cdto = cursusService.getByIdPromotion(pDto.getId());
			lstCursus.add(cdto);
		}
		for( int i=lstCursus.size()-1;i>=0;i--) {
			lstCursusMostRecent.add(lstCursus.get(i));
		}
		return lstCursusMostRecent;
	}
	@GetMapping(value = "/etudiant/{id}/{page}/{size}",produces = "application/json")
	public List<CursusDto> getByIdEtudiant(@PathVariable("id") long id,@PathVariable("page") int page,
 			@PathVariable(value = "size") int size) {
		List<PromotionDto> lstpDto = etudiantService.getPromotionsByIdEtudiant(id);
		List<CursusDto> lstCursus = new ArrayList<CursusDto>();
		List<CursusDto> lstCursusMostRecent = new ArrayList<CursusDto>();
		for (PromotionDto pDto : lstpDto) {
			CursusDto cdto = cursusService.getByIdPromotion(pDto.getId());
			lstCursus.add(cdto);
		}
		for( int i=lstCursus.size()-1;i>=0;i--) {
			lstCursusMostRecent.add(lstCursus.get(i));
		}
		List<CursusDto> lstPaginate =  new ArrayList<CursusDto>();
		
		if(lstCursusMostRecent.size() >= (page*size)) {
			for (int i=(page)*size;i <(page+1)*size;i++) {
				lstPaginate.add(lstCursusMostRecent.get(i));
			}
		}else {
			for (int i=(page)*size;i <lstCursusMostRecent.size();i++) {
				lstPaginate.add(lstCursusMostRecent.get(i));
			}
		}
		
		return lstPaginate;
	}
	@GetMapping(value = "/CurrentCursus/{id}",produces = "application/json")
	public CursusDto getCurrentCursusByIdEtudiant(@PathVariable("id") long id) {
		List<CursusDto> lstCursusDto = getByIdEtudiant(id);
		CursusDto cdto = lstCursusDto.get(0);
		return cdto;
	}

		
	@GetMapping(value = "/count", produces = "application/json")
	public CountDto count() {
		return cursusService.count("");
	}
    
    @GetMapping(value = "/count/{search}", produces = "application/json")
	public CountDto count(@PathVariable(value = "search", required = false) Optional<String> search) {
		if(search.isPresent())
			return cursusService.count(search.get());
		else
			return cursusService.count("");
	}
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public CursusDto save(@RequestBody CursusDto cDto) {
		return cursusService.saveOrUpdate(cDto);
	}
	
	
	@DeleteMapping(value = "/{id}", produces = "text/plain")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") long id) {
		try {
			cursusService.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression effectuée");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("suppression non réalisée");
		}

	}
	
	
	@PutMapping(consumes = "application/json", produces = "application/json")
	public CursusDto update(@RequestBody CursusDto cDto) {
		return cursusService.saveOrUpdate(cDto);
	}
	
}
