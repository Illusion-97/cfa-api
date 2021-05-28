package fr.dawan.AppliCFABack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.AppliCFABack.dto.LoginDto;
import fr.dawan.AppliCFABack.services.LoginService;

@RestController
@RequestMapping("/AppliCFABack/authenticate")
public class LoginController {
	
	@Autowired
	LoginService loginService;

	@PostMapping(consumes = "application/json", produces = "application/json")
	public LoginDto authenticate(@RequestBody LoginDto lDto) {
		return loginService.authenticate(lDto);
	}
}
