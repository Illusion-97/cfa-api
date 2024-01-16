package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.CreateSoutenanceDto;
import fr.dawan.AppliCFABack.dto.SoutenanceDto;
import fr.dawan.AppliCFABack.entities.Soutenance;
import fr.dawan.AppliCFABack.services.generic.GenericService;
import fr.dawan.AppliCFABack.tools.DossierProjetException;
import fr.dawan.AppliCFABack.tools.SaveInvalidException;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.List;

public interface SoutenanceService extends GenericService<SoutenanceDto> {

    List<SoutenanceDto> getByPromotionId(long id);

    List<SoutenanceDto> getPageByPromotionId(long id, int page, int size);

    List<SoutenanceDto> getAll();

    String genererLstSoutenance(String promotion, long idPromotion) throws IOException, TemplateException, DossierProjetException;

    SoutenanceDto saveOrUpdate(CreateSoutenanceDto tDto) throws SaveInvalidException;

    List<Soutenance> getSoutenanceWhereMailNotSent();

    void markSoutenanceMailSentAsTrue(Soutenance soutenance);

}
