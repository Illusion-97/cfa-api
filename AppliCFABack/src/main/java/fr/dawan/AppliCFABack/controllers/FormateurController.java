package fr.dawan.AppliCFABack.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.AppliCFABack.dto.FormateurDto;
import fr.dawan.AppliCFABack.services.FormateurService;

@RestController
@RequestMapping("/AppliCFABack/formateurs")
public class FormateurController {
	@Autowired
	private FormateurService formateurService;

	@GetMapping(produces = { "application/json", "application/xml" })
	public List<FormateurDto> getAll() {
		return formateurService.getAll();
	}
}
