package fr.dawan.AppliCFABack.controllers;

import fr.dawan.AppliCFABack.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/mail")
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping(value = "")
    public ResponseEntity<String> sendEmailToFormateur(@RequestParam String from, @RequestParam String to , @RequestParam String header,
                                                       @RequestParam String msg){
        emailService.sendMailUser1ToUser2(from, to, header, msg);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @PostMapping(value = "/notification")
    public ResponseEntity<String> sendEmailSmtp(@RequestParam long id, @RequestParam String header,
                                                       @RequestParam String msg,
                                                @PathVariable(value = "path", required = false)Optional<String> path){
        if(path.isPresent()){
            emailService.sendMailSmtpUser(id, header, msg, path);

        }else {
            emailService.sendMailSmtpUser(id, header, msg, Optional.of(""));
        }
        return ResponseEntity.status(HttpStatus.OK).build();

    }
}
