package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.CreateSoutenanceDto;
import fr.dawan.AppliCFABack.dto.SoutenanceDto;
import fr.dawan.AppliCFABack.tools.DossierProjetException;
import fr.dawan.AppliCFABack.tools.SaveInvalidException;
import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

import java.io.IOException;
import java.util.List;

public interface SoutenanceService extends GenericService<SoutenanceDto> {

    List<SoutenanceDto> getByPromotionId(long id);

    List<SoutenanceDto> getPageByPromotionId(long id, int page, int size);

    List<SoutenanceDto> getAll();

    String genererLstSoutenance(String promotion, long idPromotion) throws IOException, TemplateException, DossierProjetException;

    SoutenanceDto saveOrUpdate(CreateSoutenanceDto tDto) throws SaveInvalidException;

}
