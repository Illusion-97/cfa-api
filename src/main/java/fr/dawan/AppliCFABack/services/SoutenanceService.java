package fr.dawan.AppliCFABack.services;

import java.io.IOException;
import java.util.List;

import fr.dawan.AppliCFABack.dto.SoutenanceDto;
import fr.dawan.AppliCFABack.tools.DossierProjetException;
import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public interface SoutenanceService extends GenericService<SoutenanceDto> {

    List<SoutenanceDto> getByPromotionId(long id);

    List<SoutenanceDto> getPageByPromotionId(long id, int page, int size);

    List<SoutenanceDto> getAll();

    String genererLstSoutenance(String promotion, long idPromotion) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException, DossierProjetException;

}
