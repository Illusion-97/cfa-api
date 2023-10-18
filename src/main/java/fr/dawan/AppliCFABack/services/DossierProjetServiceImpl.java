package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.DossierProjetDto;
import fr.dawan.AppliCFABack.entities.DossierProjet;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.Tuteur;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.repositories.DossierProjetRepository;
import fr.dawan.AppliCFABack.repositories.EtudiantRepository;
import fr.dawan.AppliCFABack.repositories.TuteurRepository;
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

	@Value("${app.storagefolder}")
	private String storageFolder;

	@Value("${backend.url}")
	private String backendUrl;

	@Autowired
	private Configuration freemarkerConfig;
	@Autowired
	private EmailService emailService;
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
	public DossierProjetDto saveOrUpdate(DossierProjetDto dpDto) throws DossierProjetException, TemplateException, IOException {
		return mapper.dossierProjetToDossierProjetDto(dossierProRepo.saveAndFlush(mapper.dossierProjetDtoToDossierProjet(dpDto)));
	}
	/**
	 * Envoi un EMail au tuteur de l'étudiant pour l'informer de la modification du DossierProjet
	 *
	 * @param DossierProjetDto dp
	 * @return
	 */
	public void emailTuteur(DossierProjetDto dp) throws IOException, TemplateException, DossierProjetException {
		Optional<Etudiant> student = studentRepository.findById(dp.getEtudiant().getId());

		String header = "Votre étudiant " + student.get().getUtilisateur().getFullName() + " a crée son Dossier Projet";
		String message = "Le Dossier " + dp.getNom() + " du projet " + dp.getProjet().getNom() + " a été crée";

		Optional<String> path = Optional.of("");
		Optional<String> fileName = Optional.of("");
		String body = message + "</br>Veuillez cliquer sur ce lien pour voir le dossier : <a href=\"http://localhost:8080/#/tuteur/detailEtudiant/"+ student.get().getId()+"\">Voir le dossier </a>";
		// On vérifie si l'étudiant possède un tuteur
		if (student.get().getTuteur().getId() != 0){
			Optional<Tuteur> tuteurStudent = tuteurRepository.findById(student.get().getTuteur().getId());
			//On génère le fichier seulement lors d'un update

			//A voir dès la validation mais régler le problème du fichier qui se télecharge en .bin et non en pdf
			//Voir les anciens commit, une version stable a déja été push sur "notification_sender"
			if (dp.getVersion() > 0) {
				header = "Votre étudiant " + student.get().getUtilisateur().getFullName() + " à ajouté des modification à son Dossier Projet";
				//message = "Le Dossier " + dp.getNom() + " du projet " + dp.getProjet().getNom() + " a été modifié";
				//genererDossierProjet(dp.getId()) génère le chemin du fichier télecharger (voir s'il ne faut pas modifier le chemin du fichier pour ca)
				//path = Optional.of(genererDossierProjet(dp.getId()));
				//fileName = Optional.of(dp.getNom());
			}

			//Mail Automatique pour informer le tuteur lors de la modification du DossierProjet
			emailService.sendMailSmtpUser(tuteurStudent.get().getUtilisateur().getId(), header, body,path, fileName);
		}
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
		String nom_import = dp.getDossierImport();
		String cheminFichier = storageFolder + "/DossierProjet/" + nom_import;
		//Penser à rajouter un sous dossier par étudiant (voir file Servie sur le controller de DossierProjet)
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
	/**
	 * Delete le file
	 *
	 * @param id id de l'étudiant
	 * @param String nom du fichier
	 * @return dpDto Dto du Dossier Projet
	 */
	public DossierProjetDto deleteFile(String file, long id) {
	   String cheminFichier = storageFolder + "/DossierProjet/" + file;
	   File fichier = new File(cheminFichier);
	   DossierProjet dp = dossierProRepo.getByDossierProjetId(id);
	   if (fichier.exists()) {

	      fichier.delete();
	      // Supprimer l'élément de fichier de la liste d'annexes du DossierProjet
	      String importDp = dp.getDossierImport();
	      List<String> annexes = dp.getAnnexeDossierProjets();

	      if(annexes.contains(file)) {
	         annexes.removeIf(annexe -> annexe.equals(file));
	         //dp.setAnnexeDossierProjets(annexes);
	      }
	      if(importDp != null){
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

		return outputPdf; // Retournez le chemin du fichier PDF généré
	}

}
