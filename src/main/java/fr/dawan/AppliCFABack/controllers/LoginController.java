package fr.dawan.AppliCFABack.controllers;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.AppliCFABack.dto.LoginDto;
import fr.dawan.AppliCFABack.dto.LoginResponseDto;
import fr.dawan.AppliCFABack.dto.UtilisateurDto;
import fr.dawan.AppliCFABack.interceptors.TokenSaver;
import fr.dawan.AppliCFABack.services.UtilisateurService;
import fr.dawan.AppliCFABack.tools.JwtTokenUtil;

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
