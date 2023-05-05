package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.DossierProjetDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprojet.DossierProjetEtudiantDto;
import fr.dawan.AppliCFABack.entities.*;
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
			DossierProjetDto dpDto = mapper.dossierProjetToDpDto(dossierProjet);
			dpDto.setProjet(mapper.projetToProjetDto(dossierProjet.getProjet()));
			lstDossierProjetDto.add(dpDto);

		}
		return lstDossierProjetDto;
	}

	/**
	 * Récupération des dossiers projets en fonction de l'id
	 * 
	 */

	@Override
	public DossierProjetDto getById(long id) {
		DossierProjet dp = dossierProRepo.getByDossierProjetId(id);
			DossierProjetDto dossierProjetDto = mapper.dossierProjetToDpDto(dp);

			dossierProjetDto.setId(dp.getId());
			dossierProjetDto.setNom(dp.getNom());
			dossierProjetDto.setProjet(mapper.projetToProjetDto(dp.getProjet()));
			dossierProjetDto
					.setAnnexeDossierProjetDtos(mapper.annexeProjetToAnnexeProjetDto(dp.getAnnexeDossierProjets()));
			dossierProjetDto.setContenuDossierProjetDtos(mapper.contenuToContenuDto(dp.getContenuDossierProjets()));
			dossierProjetDto.setInfoDossierProjetDtos(mapper.infoToInfoDto(dp.getInfoDossierProjets()));
			dossierProjetDto.setResumeDossierProjetDtos(mapper.resumeToResumeDto(dp.getResumeDossierProjets()));
			dossierProjetDto.getCompetenceProfessionnelleDtos();
			
			return dossierProjetDto;
		
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
			dpDto.setProjet(mapper.projetToProjetDto(dp.getProjet()));

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
				dpDto.setProjet(mapper.projetToProjetDto(dossierProRepo.getByName(nom).getProjet()));
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

			DossierProjetDto dossierProjetDto = mapper.dossierProjetToDpDto(dp);

			dossierProjetDto.setId(dp.getId());
			dossierProjetDto.setNom(dp.getNom());
			dossierProjetDto.setDossierImport((dp.getDossierImport()));
			dossierProjetDto.setProjet(mapper.projetToProjetDto(dp.getProjet()));
			dossierProjetDto.setAnnexeDossierProjetDtos(mapper.annexeProjetToAnnexeProjetDto(dp.getAnnexeDossierProjets()));
			dossierProjetDto.setContenuDossierProjetDtos(mapper.contenuToContenuDto(dp.getContenuDossierProjets()));
			dossierProjetDto.setInfoDossierProjetDtos(mapper.infoToInfoDto(dp.getInfoDossierProjets()));
			dossierProjetDto.setResumeDossierProjetDtos(mapper.resumeToResumeDto(dp.getResumeDossierProjets()));
			dossierProjetDto.getCompetenceProfessionnelleDtos();

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
	public DossierProjetEtudiantDto saveOrUpdateDossierProjet(DossierProjetEtudiantDto dpDto, long id,
			List<MultipartFile> fileAnnexe, MultipartFile fileDp) throws IOException {
		DossierProjet dp = mapper.dossierProjetDtoToDossierProjet(dpDto);

		assert dp != null;

		importDossierProjet(fileDp, dp);
		saveAnnexesDossierProjet(fileAnnexe, dp);

		saveInfosDossierProjet(dp);

		saveContenusDossierProjet(dp);

		saveResumeDossierProjet(dp);

		saveCompetenceCouvertesDossierProjet(dpDto, dp);

		// on met à jour la clé étrangère etudiant de la table dossier_projet
		// (dans le cas d'un save)
		Optional<Etudiant> etudiant = etudiantRepository.findById(id);
		if (etudiant.isPresent()) {
			dp.setEtudiant(etudiant.get());
		}

		// on insert ou met à jour le dossier en question
		dp = dossierProRepo.saveAndFlush(dp);

		return mapper.dossierProjetToDossierProjetEtudiantDto(dp);

	}

	/*
	 * 
	 * Méthodes refactorisés pour la méthode saveOrUpdateDossierProjet
	 *
	 */
	private void importDossierProjet(MultipartFile files, DossierProjet dp) throws IOException {
			if (!files.isEmpty()) {
				String strr = files.getOriginalFilename();
				String pathDossierProjet = storageFolder + "/DossierProjet/" + strr;
				saveFile(files, pathDossierProjet);
				dp.setDossierImport(strr);
			}
	}
	private void saveCompetenceCouvertesDossierProjet(DossierProjetEtudiantDto dpDto, DossierProjet dp) {
		List<Long> competenceIds = dpDto.getCompetenceProfessionnelleIds();
		List<CompetenceProfessionnelle> competences = new ArrayList<>();
		for (Long idComp : competenceIds) {
			CompetenceProfessionnelle cp = cpRepo.getOne(idComp.longValue());
			competences.add(cp);
		}
		dp.setCompetenceProfessionnelles(competences);
	}

	private void saveResumeDossierProjet(DossierProjet dp) {
		List<ResumeDossierProjet> resumes = dp.getResumeDossierProjets();
		for (ResumeDossierProjet resume : resumes) {
			resume.setDossierProjet(dp);
		}
	}

	private void saveContenusDossierProjet(DossierProjet dp) {
		List<ContenuDossierProjet> contenus = dp.getContenuDossierProjets();
		for (ContenuDossierProjet contenu : contenus) {
			contenu.setDossierProjet(dp);
		}
	}

	private void saveInfosDossierProjet(DossierProjet dp) {
		List<InfoDossierProjet> infos = dp.getInfoDossierProjets();
		for (InfoDossierProjet info : infos) {
			info.setDossierProjet(dp);
		}
	}

	private void saveAnnexesDossierProjet(List<MultipartFile> files, DossierProjet dp) throws IOException {
		List<AnnexeDossierProjet> annexes = dp.getAnnexeDossierProjets();

		int i = 0;
		for (MultipartFile file : files) {
		    String pathFile = storageFolder + "/DossierProjet/" + file.getOriginalFilename();
		    AnnexeDossierProjet annexe = annexes.get(i++);
		    annexe.setPieceJointe(file.getOriginalFilename());
		    saveFile(file, pathFile);
		}
		for (AnnexeDossierProjet annexe : annexes) {
			annexe.setDossierProjet(dp);
		}
	}


	@Override
	public String genererDossierProjet(long idDossierProjet) throws TemplateNotFoundException,
			MalformedTemplateNameException, ParseException, IOException, TemplateException, DossierProjetException {

		Optional<DossierProjet> dossierProjet = dossierProRepo.findById(idDossierProjet);

		if (!dossierProjet.isPresent()) {
			throw new DossierProjetException("Dossier projet non trouvé");
		}
		DossierProjetEtudiantDto dossierProjetFile = mapper
				.dossierProjetToDossierProjetEtudiantDto(dossierProjet.get());

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
