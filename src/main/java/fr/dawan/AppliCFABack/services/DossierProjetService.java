package fr.dawan.AppliCFABack.services;

import java.io.IOException;
import java.util.List;

import fr.dawan.AppliCFABack.dto.DossierProjetDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprojet.DossierProjetEtudiantDto;
import fr.dawan.AppliCFABack.tools.DossierProjetException;
import fr.dawan.AppliCFABack.tools.LivretEvaluationException;
import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public interface DossierProjetService {

	List<DossierProjetDto> getAll();

	DossierProjetEtudiantDto getById(long id);
	
	DossierProjetDto getByName(String nom);

	List<DossierProjetDto> getAllByPage(int page, int size, String string);

	DossierProjetDto saveOrUpdate(DossierProjetDto dpDto);

	void deleteById(long id);

	List<DossierProjetDto> getByIdEtudiant(long id);

    DossierProjetEtudiantDto saveOrUpdateDossierProjet(DossierProjetEtudiantDto dpDto, long id);

	String genererDossierProjet(long idDossierProjet) throws DossierProjetException, TemplateNotFoundException,
		MalformedTemplateNameException, ParseException, IOException, TemplateException;
	

}
