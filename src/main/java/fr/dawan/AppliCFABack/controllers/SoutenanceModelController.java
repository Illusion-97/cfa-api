package fr.dawan.AppliCFABack.controllers;

import fr.dawan.AppliCFABack.controllers.generic.v2.GenericController;
import fr.dawan.AppliCFABack.dto.customdtos.SoutenanceModelDto;
import fr.dawan.AppliCFABack.services.examen.SoutenanceModelService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/soutenance-models")
public class SoutenanceModelController extends GenericController<SoutenanceModelDto, SoutenanceModelService> {
    public SoutenanceModelController(SoutenanceModelService service) {
        super(service);
    }
}
