package fr.dawan.AppliCFABack.services;

import java.io.IOException;
import java.util.List;

import fr.dawan.AppliCFABack.dto.customdtos.dossierprofessionnel.DossierProEtudiantDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprofessionnel.GetDossierProDto;
import fr.dawan.AppliCFABack.tools.PdfTools;
import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import fr.dawan.AppliCFABack.dto.DossierProfessionnelDto;

public interface DossierProfessionnelService {
	List<DossierProfessionnelDto> getAll();

	DossierProfessionnelDto getById(long id);

	List<DossierProfessionnelDto> getAllByPage(int page, int size, String string);

	DossierProfessionnelDto saveOrUpdate(DossierProfessionnelDto dpDto);

	void deleteById(long id);
	
	List<DossierProfessionnelDto> getByIdEtudiant(long id);

	DossierProfessionnelDto getByName(String nom);


	DossierProEtudiantDto saveOrUpdateDossierProfessionnel(DossierProEtudiantDto dpDto, long id);

	List<DossierProEtudiantDto> getAllDossierProfessionnel();

    String generateDossierProByStudentAndPromo(long etudiantId, long promotionId) throws PdfTools, TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException;

	GetDossierProDto getAllDossierProfessionnelByEtudiant(long id);
}
