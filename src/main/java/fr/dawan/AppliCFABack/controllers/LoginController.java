package fr.dawan.AppliCFABack.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.MultipartConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.AppliCFABack.dto.LoginDto;
import fr.dawan.AppliCFABack.dto.LoginResponseDto;
import fr.dawan.AppliCFABack.dto.UtilisateurDto;
import fr.dawan.AppliCFABack.interceptors.TokenSaver;
import fr.dawan.AppliCFABack.services.UtilisateurService;
import fr.dawan.AppliCFABack.tools.HashTools;
import fr.dawan.AppliCFABack.tools.JwtTokenUtil;

@MultipartConfig
@RestController
public class LoginController {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping(value = "/authenticate", consumes = "application/json")
    public ResponseEntity<?> checkLogin(@RequestBody LoginDto loginObj) throws Exception {
        UtilisateurDto uDto = utilisateurService.findByEmail(loginObj.getLogin());
        String hashedPwd = HashTools.hashSHA512(loginObj.getPassword());
//        String password = loginObj.getPassword();
        if (uDto != null && uDto.getPassword().contentEquals(hashedPwd)) {
            //Fabrication du token en utilisant jjwt (librairie incluse dans le pom)
            Map<String, Object> claims = new HashMap<String, Object>();
            claims.put("user_id", uDto.getId());
            claims.put("user_email", uDto.getLogin());

            //ajouter les données que l'on souhaite
            String token = jwtTokenUtil.doGenerateToken(claims, loginObj.getLogin());
            TokenSaver.tokensByEmail.put(loginObj.getLogin(), token);

            return ResponseEntity.ok(new LoginResponseDto(token, uDto.getId()));
        } else
            throw new Exception("Erreur : identifiants incorrects !");

    }

}
