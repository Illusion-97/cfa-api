package fr.dawan.AppliCFABack.controllers;

import fr.dawan.AppliCFABack.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/mail")
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping(value = "/tocfa")
    public ResponseEntity<String> sendEmailToFormateur(@RequestParam String from, @RequestParam String to , @RequestParam String header,
                                                       @RequestParam String msg){
        emailService.sendMailUser1ToUser2(from, to, header, msg);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @PostMapping(value = "/notification")
    public ResponseEntity<String> sendEmailSmtp(@RequestParam long id, @RequestParam String header,
                                                       @RequestParam String msg,
                                                @PathVariable(value = "path", required = false)Optional<String> path,
                                                @PathVariable(value = "fileName", required = false)Optional<String> fileName){
        if(path.isPresent()){
            emailService.sendMailSmtpUser(id, header, msg, path, fileName);

        }else {
            emailService.sendMailSmtpUser(id, header, msg, Optional.of(""), Optional.of(""));
        }
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @PostMapping(value = "/schedule")
    public ResponseEntity.BodyBuilder mailScheduler(@RequestBody Map<String, Object> request){
        try {
            if ((boolean) request.get("isFormateur")) {
                Integer integerIdUser = (Integer) request.get("userId");
                long idUser = (long) integerIdUser;
                //emailService.scheduleMailSender(idUser);
                return ResponseEntity.status(200);
            }
        } catch (Exception e) {
            return (ResponseEntity.BodyBuilder) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite : " + e.getMessage());
        }

        return (ResponseEntity.BodyBuilder) ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Requête incorrecte");    }
}
