package fr.dawan.AppliCFABack.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.AppliCFABack.dto.UtilisateurDto;
import fr.dawan.AppliCFABack.services.UtilisateurService;

@RestController
@RequestMapping("/AppliCFABack/utilisateurs")
public class UtilisateurController {
	@Autowired
	private UtilisateurService utilisateurService;

	@GetMapping(produces = { "application/json", "application/xml" })
	public List<UtilisateurDto> getAll() {
		return utilisateurService.getAll();
	}
}
