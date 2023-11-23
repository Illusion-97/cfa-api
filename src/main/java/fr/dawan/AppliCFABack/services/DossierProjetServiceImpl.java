package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.DossierProjetDto;
import fr.dawan.AppliCFABack.entities.DossierProjet;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.Tuteur;
import fr.dawan.AppliCFABack.entities.Utilisateur;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.repositories.DossierProjetRepository;
import fr.dawan.AppliCFABack.repositories.EtudiantRepository;
import fr.dawan.AppliCFABack.repositories.TuteurRepository;
import fr.dawan.AppliCFABack.repositories.UtilisateurRepository;
import fr.dawan.AppliCFABack.tools.DossierProjetException;
import fr.dawan.AppliCFABack.tools.ToPdf;
import freemarker.core.ParseException;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
	EtudiantRepository studentRepository;
	@Autowired
	TuteurRepository tuteurRepository;
	@Autowired
	FilesService filesService;
	@Value("${app.storagefolder}")
	private String storageFolder;

	@Value("${backend.url}")
	private String backendUrl;

	@Autowired
	private Configuration freemarkerConfig;
	@Autowired
	private EmailService emailService;
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	private static Logger logger = Logger.getGlobal();

	@Autowired
	private DtoMapper mapper;

	String pathDpRef = storageFolder + "/DossierProjet/";

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
	public DossierProjetDto saveOrUpdate(DossierProjetDto dpDto) throws DossierProjetException, TemplateException, IOException {
		DossierProjet dp = mapper.dossierProjetDtoToDossierProjet(dpDto);
		dossierProRepo.saveAndFlush(dp);
		directory(dpDto);
		emailTuteur(dpDto);
		return mapper.dossierProjetToDossierProjetDto(dp);
	}
	private void directory(DossierProjetDto dpDto){
		if (dpDto.getEtudiant() == null){
			return;
		}
		String nomDossierEtudiant = utilisateurRepository.findByIdEtudiant(dpDto.getEtudiant().getId()) + dpDto.getEtudiant().getId() + "_" + dpDto.getNom() +"/";

		Path isPathPresent = Paths.get(storageFolder + "/DossierProjet/" + nomDossierEtudiant);
		if (!Files.isDirectory(isPathPresent)){
			filesService.createDirectory("/DossierProjet/" + nomDossierEtudiant);
		}
	}

	/**
	 * Envoi un EMail au tuteur de l'étudiant pour l'informer de la modification du DossierProjet
	 *
	 * @param DossierProjetDto dp
	 * @return
	 */
	private void emailTuteur(DossierProjetDto dp) throws IOException, TemplateException, DossierProjetException {
		if (dp.getEtudiant() == null){
			return;
		}
		Tuteur tuteurStudent = null;
		Optional<Etudiant> studentOpt = studentRepository.findById(dp.getEtudiant().getId());
		Etudiant student = studentOpt.get();
		// Vérifier si l'étudiant a un tuteur
		if (student.getTuteur() == null || student.getTuteur().getId() == 0) {
			// Gérer le cas où l'étudiant n'as pas de tuteur n'est pas trouvé
			return;
		}
		// Recherche du tuteur de l'étudiant
		tuteurStudent = tuteurRepository.findById(student.getTuteur().getId()).get();

		if (!studentOpt.isPresent()) {
			// Gérer le cas où l'étudiant n'est pas trouvé
			return;
		}
		// Reste du code si l'étudiant a un tuteur
		String header = "Votre étudiant " + student.getUtilisateur().getFullName() + " a crée son Dossier Projet";
		String message = "Le Dossier " + dp.getNom() + " du projet " + dp.getProjet().getNom() + " a été crée";

		String body = message + "</br>Veuillez cliquer sur ce lien pour voir le dossier : <a href=\"http://localhost:8080/#/tuteur/detailEtudiant/"+ student.getId()+"\">Voir le dossier </a>";

		if (dp.getVersion() > 0) {
			header = "Votre étudiant " + student.getUtilisateur().getFullName() + " à ajouté des modification à son Dossier Projet";
		}
		//Mail Automatique pour informer le tuteur lors de la modification du DossierProjet
		emailService.sendMailSmtpUser(tuteurStudent.getUtilisateur().getId(), header, body, Optional.of(""), Optional.of(""));
	}
	/**
	 * Va permettre d'importer un Dossier Projet
	 *
	 * @param DossierProjet
	 * @param id id du Dossier Projet
	 * @param files file
	 * @return dpDto Dto du Dossier Projet
	 */
	public DossierProjetDto importDossierProjet(MultipartFile files, Long id) throws IOException {
		DossierProjet dp = dossierProRepo.getByDossierProjetId(id);
		String nomDossierEtudiant;
		String nom_import = dp.getDossierImport();

		if (dp.getEtudiant() == null){
			nomDossierEtudiant = dp.getNom()+"_";
		}else{
		nomDossierEtudiant = utilisateurRepository.findByIdEtudiant(dp.getEtudiant().getId())
				+ dp.getEtudiant().getId() + "_" + dp.getNom() +"/";
		}

		String pathDp = storageFolder + "/DossierProjet/" + nomDossierEtudiant;
		String cheminFichier = pathDp + nom_import;

		File fichier = new File(cheminFichier);
		if (fichier.exists()) {
			fichier.delete();
		}
			String nomFichier = files.getOriginalFilename();
			String pathDossierProjet = pathDp + nomFichier;
			saveFile(files, pathDossierProjet);
			dp.setDossierImport(nomFichier);
			DossierProjetDto dpDto = mapper.dossierProjetToDossierProjetDto(dp);
			return dpDto;
	}

	/**
	 * Delete le file
	 *
	 * @param id id de l'étudiant
	 * @param String nom du fichier
	 * @return dpDto Dto du Dossier Projet
	 */
	public void deleteFile(String file, long id) {

	   DossierProjet dp = dossierProRepo.getByDossierProjetId(id);
		String nomDossierEtudiant = utilisateurRepository.findByIdEtudiant(dp.getEtudiant().getId())
				+ dp.getEtudiant().getId() + "_" + dp.getNom() +"/";
	   String cheminFichier = storageFolder + "/DossierProjet/" + nomDossierEtudiant + file;
	   File fichier = new File(cheminFichier);
	   if (fichier.exists()) {

	      fichier.delete();
	      // Supprimer l'élément de fichier de la liste d'annexes du DossierProjet
	      String importDp = dp.getDossierImport();
	      List<String> annexes = dp.getAnnexeDossierProjets();

	      if(annexes.contains(file)) {
	         annexes.removeIf(annexe -> annexe.equals(file));
	      }
	      if(importDp != null){
	         dp.setDossierImport(null);
	      }
	   }
	}

	public DossierProjetDto saveAnnexesDossierProjet(List<MultipartFile> files, Long id) throws IOException {
		DossierProjet dp = dossierProRepo.getByDossierProjetId(id);
		String nomDossierEtudiant = utilisateurRepository.findByIdEtudiant(dp.getEtudiant().getId())
				+ dp.getEtudiant().getId() + "_" + dp.getNom() +"/";

		List<String> getList = dp.getAnnexeDossierProjets();
		if (getList == null) {
			dp.setAnnexeDossierProjets(new ArrayList<>());
		}
		for (MultipartFile file : files) {

			String pathFile = storageFolder + "/DossierProjet/" + nomDossierEtudiant + file.getOriginalFilename();
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

		return outputPdf; // Retournez le chemin du fichier PDF généré
	}

}
