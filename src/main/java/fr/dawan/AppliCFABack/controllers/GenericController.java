package fr.dawan.AppliCFABack.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import fr.dawan.AppliCFABack.dto.BaseEntityDto;
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.services.GenericService;

/**
 * @author Feres BG.
 * @see fr.dawan.appliCFABack.controller
 * @since 1.0
 * @version 1.0
 * @return Controller générique
 */
public abstract class GenericController<TDto extends BaseEntityDto> {

	protected final GenericService<TDto> service;

	protected GenericController(GenericService<TDto> service) {
		super();
		this.service = service;
	}

	/**
	 * 
	 * @param id a rechercher
	 * @return getById service
	 */
	@GetMapping(value = "/{id}", produces = "application/json")
	public TDto findById(@PathVariable("id") long id) {
		return service.getById(id);
	}

	/**
	 * 
	 * @param id a supprimer
	 * @return le status de la requête un message d'information
	 */
	@DeleteMapping(value = "/{id}", produces = "text/plain")
	public ResponseEntity<String> deleteById(@PathVariable(value = "id") long id) {
		try {
			service.delete(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression effectuée");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("suppression non réalisée");
		}
	}

	/**
	 * 
	 * @param Dto à sauvegarder
	 * @return le status et l'objet a enregistrer en base de données
	 * @throws Exception
	 */
	@PostMapping(consumes = "application/json", produces = "application/json")
	ResponseEntity<TDto> save(@RequestBody TDto tDto) throws Exception {

		return ResponseEntity.status(HttpStatus.CREATED).body(service.saveOrUpdate(tDto));
	}

	/**
	 * 
	 * @param Dto à sauvegarder
	 * @return le détail de l'objet mis a jour
	 * @throws Exception
	 */
	@PutMapping(consumes = "application/json", produces = "application/json")
	TDto update(@RequestBody TDto tDto) throws Exception {
		return service.saveOrUpdate(tDto);
	}

	/**
	 * 
	 * @param search dont on veut connaitre le nombre
	 * @return le nombre de fois ou la valeur recherchée a été trouvée.
	 */
	@GetMapping(value = {"/count/{search}","/count"}, produces = "application/json")
	public CountDto count(@PathVariable(value = "search" ,required = false) Optional<String> search) {
		if (search.isPresent())
			return service.count(search.get());
		return service.count("");
	}
}