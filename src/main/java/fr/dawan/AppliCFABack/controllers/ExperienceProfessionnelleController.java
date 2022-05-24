package fr.dawan.AppliCFABack.controllers;

import fr.dawan.AppliCFABack.dto.ExperienceProfessionnelleDto;
import fr.dawan.AppliCFABack.services.ExperienceProfessionnelleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/experiences")
public class ExperienceProfessionnelleController {

    @Autowired
    private ExperienceProfessionnelleService experienceProfessionnelleService;

    @GetMapping(produces = "application/json")
    public List<ExperienceProfessionnelleDto> getAll() {
        return experienceProfessionnelleService.getAll();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ExperienceProfessionnelleDto getById(@PathVariable("id") long id) {
        return experienceProfessionnelleService.getById(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ExperienceProfessionnelleDto save(@RequestBody ExperienceProfessionnelleDto epDto){
        return experienceProfessionnelleService.saveOrUpdate(epDto);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public ExperienceProfessionnelleDto update(@RequestBody ExperienceProfessionnelleDto epDto) {
        return experienceProfessionnelleService.saveOrUpdate(epDto);
    }

    @DeleteMapping(value = "/{id}", produces = "text/plain")
    public ResponseEntity<?> deleteById(@PathVariable(value = "id") long id) {
        try {
            experienceProfessionnelleService.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Suppression effectuée");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Suppression non réalisée");
        }
    }
}
