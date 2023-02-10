package fr.dawan.AppliCFABack.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.FormateurDto;
import fr.dawan.AppliCFABack.dto.TuteurDto;
import fr.dawan.AppliCFABack.services.TuteurService;

@RestController
@RequestMapping("/tuteur")
public class TuteurController {
	
	@Autowired
	TuteurService tuteurService;
	

	@GetMapping(produces = "application/json")
	public List<TuteurDto> getAll() {
		return tuteurService.getAll();
	}
	
	@GetMapping(produces = "application/json", value = "/{page}/{size}")
	public @ResponseBody List<TuteurDto> getAll(@PathVariable("page") int page, @PathVariable("size") int size) {
		return tuteurService.getAllByPage(page, size);
	}
	
	 
		@GetMapping(produces = "application/json", value = "/{page}/{size}/{search}")
		public @ResponseBody List<TuteurDto> getAll(@PathVariable("page") int page, @PathVariable("size") int size,
				@PathVariable("search") Optional<String> search) {
			if (search.isPresent())
				return tuteurService.getAllByPageWithKeyword(page, size, search.get());
			return tuteurService.getAllByPage(page, size);
		}
		
		@GetMapping(produces = "application/json", value = "/{id}")
		public TuteurDto getById(@PathVariable("id") long id) {
			return tuteurService.getById(id);
		}
		
		 
			@PostMapping(consumes = "application/json", produces = "application/json")
			public TuteurDto save(@RequestBody TuteurDto tuteurDto) {
				return tuteurService.saveOrUpdate(tuteurDto);
			}
	
			@PutMapping(consumes = "application/json", produces = "application/json")
			public TuteurDto update(@RequestBody TuteurDto tuteurDto) {
				return tuteurService.saveOrUpdate(tuteurDto);
			}
			
		
			
			
}

