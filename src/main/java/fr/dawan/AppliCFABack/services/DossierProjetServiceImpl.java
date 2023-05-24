package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.DossierProjetDto;
import fr.dawan.AppliCFABack.entities.DossierProjet;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.repositories.CompetenceProfessionnelleRepository;
import fr.dawan.AppliCFABack.repositories.DossierProjetRepository;
import fr.dawan.AppliCFABack.repositories.EtudiantRepository;
import fr.dawan.AppliCFABack.repositories.ProjetRepository;
import fr.dawan.AppliCFABack.tools.DossierProjetException;
import fr.dawan.AppliCFABack.tools.ToPdf;
import freemarker.core.ParseException;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
/**
 * 
 * @author Anas J
 * @see fr.dawan.appliCFABack.serviceImpl
 * @since 1.0
 * @version 2.0
 * @return 
 */
@Service
@Transactional
public class DossierProjetServiceImpl implements DossierProjetService {

	@Autowired
	DossierProjetRepository dossierProRepo;

	@Autowired
	EtudiantRepository etudiantRepository;

	@Autowired
	EtudiantService etudiantService;

	@Autowired
	CompetenceProfessionnelleRepository cpRepo;

	@Autowired
	private ProjetRepository projetRepository;

	@Value("${app.storagefolder}")
	private String storageFolder;

	@Value("${backend.url}")
	private String backendUrl;

	@Autowired
	private Configuration freemarkerConfig;

	private static Logger logger = Logger.getGlobal();

	@Autowired
	private DtoMapper mapper;

	
	private void saveFile(MultipartFile file, String filePath) throws IOException {
	    File newFile = new File(filePath);
	    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(newFile));
	    bos.write(file.getBytes());
	    bos.close();
	}

	/**
	 * Récupération de la liste des dossiers projets
	 * 
	 * @return lstDossierProjetDto Liste des objets dossiers projets
	 */

	@Override
	public List<DossierProjetDto> getAll() {
		List<DossierProjet> lstDossierProjets = dossierProRepo.findAll();

		List<DossierProjetDto> lstDossierProjetDto = new ArrayList<>();
		for (DossierProjet dossierProjet : lstDossierProjets) {
			lstDossierProjetDto.add(mapper.dossierProjetToDossierProjetDto(dossierProjet));
		}
		return lstDossierProjetDto;
	}

	/**
	 * Récupération des dossiers projets en fonction de l'id
	 * 
	 */

	@Override
	public DossierProjetDto getById(long id) {
		Optional<DossierProjet>  dp = dossierProRepo.findById(id);
		if(!dp.isPresent()){
			return null;
		}
		return mapper.dossierProjetToDossierProjetDto(dp.get());
		
	}

	/**
	 * Va permettre de récupérer tous les dossier projets avec pagination recherche
	 * par nom
	 * 
	 * @param page   numero de la page
	 * @param size   éléments sur la page
	 * @param string élémént du dossier projet
	 * @return LstDto Liste des objets dossier projet
	 */

	@Override
	public List<DossierProjetDto> getAllByPage(int page, int size, String string) {
		List<DossierProjet> lst = dossierProRepo.findByNomContaining(string, PageRequest.of(page, size)).get()
				.collect(Collectors.toList());

		// conversion vers Dto
		List<DossierProjetDto> lstDto = new ArrayList<>();
		for (DossierProjet dp : lst) {
			DossierProjetDto dpDto = mapper.dossierProjetToDpDto(dp);

			lstDto.add(dpDto);
		}
		return lstDto;
	}

	/**
	 * Recuperation du dossier projet par nom
	 * 
	 * @param nom nom concernant le dossier projet
	 */

	@Override
	public DossierProjetDto getByName(String nom) {
		DossierProjetDto dpDto = mapper.dossierProjetToDpDto(dossierProRepo.getByName(nom));
		if (dpDto != null) {
			if (dossierProRepo.getByName(nom).getProjet() == null)
			return dpDto;
		}
		return null;
	}

	/**
	 * Suppression d'un dossier projet
	 * 
	 * @param id Id concernant le dossier projet
	 */

	@Override
	public void deleteById(long id) {
		dossierProRepo.deleteById(id);

	}

	/**
	 * Recuperation du dossier projet de l'etudiant
	 * 
	 * @param id Id concernant l'etudiant
	 * @return dossier projet de l'etudiant concerné
	 */

	@Override
	public List<DossierProjetDto> getByIdEtudiant(long id) {
		List<DossierProjet> dpEtu = dossierProRepo.findByIdEtudiant(id);
		List<DossierProjetDto> dossierProjetDtoList = new ArrayList<>();

		for (DossierProjet dp : dpEtu) {

			DossierProjetDto dossierProjetDto = mapper.dossierProjetToDossierProjetDto(dp);

			dossierProjetDto.setId(dp.getId());
			dossierProjetDto.setNom(dp.getNom());
			dossierProjetDto.setDossierImport((dp.getDossierImport()));
			dossierProjetDto.getCompetenceProfessionnelleIds();

			dossierProjetDtoList.add(dossierProjetDto);
		}

		return dossierProjetDtoList;
	}
	
	/**
	 * Va permettre de récupérer tous les dossier projets avec pagination recherche
	 * par nom
	 * 
	 * @param DossierProjet
	 * @param id id de l'étudiant
	 * @param files file pour les annexes
	 * @return dpDto Dto du Dossier Projet
	 */

	@Override
	public DossierProjetDto saveOrUpdate(DossierProjetDto dpDto) {
		return mapper.dossierProjetToDossierProjetDto(dossierProRepo.saveAndFlush(mapper.dossierProjetDtoToDossierProjet(dpDto)));
	}
	public DossierProjetDto importDossierProjet(MultipartFile files, Long id) throws IOException {
		DossierProjet dp = dossierProRepo.getByDossierProjetId(id);
		String nom_import = dp.getDossierImport();
		String cheminFichier = storageFolder + "/DossierProjet/" + nom_import;
		File fichier = new File(cheminFichier);
		if (fichier.exists()) {
			fichier.delete();
		}
			String strr = files.getOriginalFilename();
			String pathDossierProjet = storageFolder + "/DossierProjet/" + strr;
			saveFile(files, pathDossierProjet);
			dp.setDossierImport(strr);
			DossierProjetDto dpDto = mapper.dossierProjetToDossierProjetDto(dp);
			return dpDto;
	}

	public DossierProjetDto deleteFile(String file, long id) {
		String cheminFichier = storageFolder + "/DossierProjet/" + file;
		File fichier = new File(cheminFichier);
		if (fichier.exists()) {
			DossierProjet dp = dossierProRepo.getByDossierProjetId(id);
			fichier.delete();

			// Supprimer l'élément de fichier de la liste d'annexes du DossierProjet
			String importDp = dp.getDossierImport();
			List<String> annexes = dp.getAnnexeDossierProjets();

			if(annexes.contains(file)) {
				annexes.removeIf(annexe -> annexe.equals(file));
				dp.setAnnexeDossierProjets(annexes);
			}
			if(importDp.contains(file)){
				dp.setDossierImport(null);
			}

			DossierProjetDto dpDto = mapper.dossierProjetToDossierProjetDto(dossierProRepo.save(dp));
			return dpDto;
		}
		return null;
	}
	public DossierProjetDto saveAnnexesDossierProjet(List<MultipartFile> files, Long id) throws IOException {
		DossierProjet dp = dossierProRepo.getByDossierProjetId(id);
		List<String> getList = dp.getAnnexeDossierProjets();
		if (getList == null) {
			dp.setAnnexeDossierProjets(new ArrayList<>());
		}
		for (MultipartFile file : files) {
			String pathFile = storageFolder + "/DossierProjet/" + file.getOriginalFilename();
			dp.getAnnexeDossierProjets().add(file.getOriginalFilename());
			saveFile(file, pathFile);
		}
		DossierProjetDto dpDto = mapper.dossierProjetToDossierProjetDto(dp);
		return dpDto;
	}
	@Override
	public String genererDossierProjet(long idDossierProjet) throws TemplateNotFoundException,
			MalformedTemplateNameException, ParseException, IOException, TemplateException, DossierProjetException {

		Optional<DossierProjet> dossierProjet = dossierProRepo.findById(idDossierProjet);

		if (!dossierProjet.isPresent()) {
			throw new DossierProjetException("Dossier projet non trouvé");
		}
		DossierProjetDto dossierProjetFile = mapper
				.dossierProjetToDossierProjetDto(dossierProjet.get());

		Map<String, Object> model = new HashMap<>();
		model.put("backendUrl", backendUrl);
		model.put("dossierProjet", dossierProjetFile);
		freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");
		Template template = freemarkerConfig.getTemplate("DossierProjet.ftl");

		String htmlContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
		String outputPdf = storageFolder + "dossierProjet/dossierProjet.pdf";
		try {
			ToPdf.convertHtmlToPdf(htmlContent, outputPdf);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "convertHtmlToPdf failed", e);
		}

		return outputPdf;
	}

}
