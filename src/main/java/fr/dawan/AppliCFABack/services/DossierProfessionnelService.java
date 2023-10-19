package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.DossierProfessionnelDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprofessionnel.DossierProEtudiantDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprofessionnel.GetDossierProDto;
import fr.dawan.AppliCFABack.tools.DossierProfessionnelException;
import fr.dawan.AppliCFABack.tools.PdfTools;
import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface DossierProfessionnelService extends GenericService<DossierProfessionnelDto>{
	
	List<DossierProfessionnelDto> getAll();

	DossierProfessionnelDto getById(long id);

	List<DossierProfessionnelDto> getAllByPage(int page, int size, String string);

	void deleteById(long id);
	
	List<DossierProfessionnelDto> getByIdEtudiant(long id);

	DossierProfessionnelDto getByName(String nom);

	DossierProEtudiantDto saveOrUpdateDossierProfessionnel (DossierProEtudiantDto dpDto, long id, List<MultipartFile> file);
	
	DossierProEtudiantDto deleteFileImportById(long id, String fileImport) throws Exception;

	List<DossierProEtudiantDto> getAllDossierProfessionnel();

	GetDossierProDto getAllDossierProfessionnelByEtudiant(long id);

	DossierProEtudiantDto saveFileImport(MultipartFile fileImport, Long dossierId) throws FileNotFoundException, IOException;

	String generateDossierProPdf(long dossierId) throws PdfTools, IOException, TemplateException;
		
	}

	
	
