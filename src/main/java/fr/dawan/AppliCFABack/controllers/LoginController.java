package fr.dawan.AppliCFABack.controllers;

import fr.dawan.AppliCFABack.dto.LoginDto;
import fr.dawan.AppliCFABack.dto.LoginResponseDto;
import fr.dawan.AppliCFABack.services.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.annotation.MultipartConfig;

@MultipartConfig
@RestController
public class LoginController {
	
	@Autowired
	private UtilisateurService utilisateurService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@PostMapping(value = "/authenticate", consumes = "application/json")
	public LoginResponseDto checkLogin(@RequestBody LoginDto loginDto) throws Exception {
		// appel à la méthode du service
		return utilisateurService.checkLogin(loginDto);
	}
}
