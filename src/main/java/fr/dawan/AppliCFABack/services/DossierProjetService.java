package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.DossierProjetDto;
import fr.dawan.AppliCFABack.tools.DossierProjetException;
import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DossierProjetService {

	List<DossierProjetDto> getAll();

	DossierProjetDto getById(long id);
	
	DossierProjetDto getByName(String nom);

	List<DossierProjetDto> getAllByPage(int page, int size, String string);

	void deleteById(long id);

	List<DossierProjetDto> getByIdEtudiant(long id);

	String genererDossierProjet(long idDossierProjet) throws DossierProjetException, IOException, TemplateException;

	DossierProjetDto saveOrUpdate(DossierProjetDto dpDto) throws DossierProjetException, TemplateException, IOException;
	DossierProjetDto importDossierProjet(MultipartFile files, Long id) throws IOException;
	DossierProjetDto saveAnnexesDossierProjet(List<MultipartFile> files, Long id) throws IOException;
	DossierProjetDto deleteFile(String file, long id);

	void emailTuteur(DossierProjetDto dpDto) throws IOException, TemplateException, DossierProjetException;

	}
