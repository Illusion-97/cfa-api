package fr.dawan.AppliCFABack.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import fr.dawan.AppliCFABack.dto.DossierProjetDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprojet.DossierProjetEtudiantDto;
import fr.dawan.AppliCFABack.tools.DossierProjetException;
import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public interface DossierProjetService {

	List<DossierProjetDto> getAll();

	DossierProjetDto getById(long id);
	
	DossierProjetDto getByName(String nom);

	List<DossierProjetDto> getAllByPage(int page, int size, String string);

	void deleteById(long id);

	List<DossierProjetDto> getByIdEtudiant(long id);

	String genererDossierProjet(long idDossierProjet) throws DossierProjetException, TemplateNotFoundException,
		MalformedTemplateNameException, ParseException, IOException, TemplateException;

	DossierProjetEtudiantDto saveOrUpdateDossierProjet(DossierProjetEtudiantDto dpDto, long id, List<MultipartFile> file) throws IOException;

	/**
	 * @throws IOException ***********************************************************************************************************/
	DossierProjetEtudiantDto uploadDossierProjet(DossierProjetEtudiantDto dpDto, long id, List<MultipartFile> file1,
			List<MultipartFile> file2, List<MultipartFile> file3, List<MultipartFile> file4) throws IOException;


	

}
