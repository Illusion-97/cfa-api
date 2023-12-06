package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DossierProfessionnelDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.ExperienceProfessionnelleDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprofessionnel.DossierProEtudiantDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprofessionnel.EtudiantDossierDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprofessionnel.GetDossierProDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprofessionnel.pdf.PdfActiviteDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprofessionnel.pdf.PdfCompetenceDto;
import fr.dawan.AppliCFABack.entities.*;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.repositories.*;
import fr.dawan.AppliCFABack.tools.DossierProfessionnelException;
import fr.dawan.AppliCFABack.tools.PdfTools;
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
import java.io.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@Transactional
public class DossierProfessionnelServiceImpl extends GenericServiceImpl<DossierProfessionnel, DossierProfessionnelDto> implements DossierProfessionnelService {


    @Autowired
    DossierProfessionnelRepository dossierProRepo;
    @Autowired
    EtudiantService etudiantService;

    @Autowired
    EmailService emailService;
    @Autowired
    TuteurRepository tuteurRepository;
    @Autowired
    private DtoMapper mapper;

    @Autowired
    private ExperienceProfessionnelleRepository experienceProfessionnelleRepository;
    @Autowired
    private ExperienceProfessionnelleService expService;
    @Autowired
    private CompetenceProfessionnelleRepository competenceProfessionnelleRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;
    
    @Autowired
    private AnnexeRepository annexeRepository;

    @Autowired
    private ActiviteTypeRepository activiteTypeRepository;

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private SignatureRepository signatureRepository;

    @Autowired
    private Configuration freemarkerConfig;
    
    @Autowired
    public DossierProfessionnelServiceImpl(DossierProfessionnelRepository dossierProRepo) {
    	super(dossierProRepo, DossierProfessionnelDto.class, DossierProfessionnel.class);
        this.dossierProRepo = dossierProRepo;   
       
    }
   

    @Value("${backend.url}")
    private String backendUrl;
    
    @Value("src/main/resources/pictures/")
    private String storageFolder2;
    
    private static Logger logger = Logger.getGlobal();

    /**
     * Récupération de la liste des dossiers professionnes
     *
     * @return lstDossierProfessionnelDto    Liste des objets dossiers pro
     */

    @Override
    public List<DossierProfessionnelDto> getAll() {
        List<DossierProfessionnel> lstDossierProfessionnel = dossierProRepo.findAll();
        List<DossierProfessionnelDto> lstDossierProfessionnelDto = new ArrayList<>();

        for (DossierProfessionnel dp : lstDossierProfessionnel) {
            DossierProfessionnelDto dpDto = mapper.dossierProfessionnelToDossierProfessionnelDto(dp);
            dpDto.setCursusDto(mapper.cursusToCursusDto(dp.getCursus()));
           // dpDto.setId(dpDto.getId());
            dpDto.setNom(dpDto.getNom());
            dpDto.setAnnexeDtos(mapper.annexeToAnnexeDto(dp.getAnnexes()));
            dpDto.setFacultatifDto(mapper.facultatifToFacultatifDto(dp.getFacultatifs()));
            dpDto.setEtudiantDto(mapper.etudiantToEtudiantDto(dp.getEtudiant()));
            dpDto.setExperienceProfessionnelleDtos(mapper.experienceProfessionnelleToExperienceProfessionnelleDto(dp.getExperienceProfessionnelles()));
            lstDossierProfessionnelDto.add(dpDto);

        }
        return lstDossierProfessionnelDto;
    }

    /**
     * Récupération des dossiers professionnel en fonction de l'id
     */
    @Override
    public DossierProfessionnelDto getById(long id) {
        Optional<DossierProfessionnel> dp = dossierProRepo.findById(id);
        if (dp.isPresent()) {
            DossierProfessionnelDto dpDto = mapper.dossierProfessionnelToDossierProfessionnelDto(dp.get());
            dpDto.setCursusDto(mapper.cursusToCursusDto(dp.get().getCursus()));
            dpDto.setExperienceProfessionnelleDtos(mapper.experienceProfessionnelleToExperienceProfessionnelleDto(dp.get().getExperienceProfessionnelles()));
            dpDto.setAnnexeDtos(mapper.annexeToAnnexeDto(dp.get().getAnnexes()));
            dpDto.setFacultatifDto(mapper.facultatifToFacultatifDto(dp.get().getFacultatifs()));
            return dpDto;
        }
        return null;
    }

    /**
     * Va permettre de récupérer tous les dossier pro avec pagination
     * recherche par nom
     *
     * @param page   numero de la page
     * @param size   éléments sur la page
     * @param string élémént du dossier pro
     * @return LstDto Liste des objets dossier pro
     */

    @Override
    public List<DossierProfessionnelDto> getAllByPage(int page, int size, String string) {
        List<DossierProfessionnel> lst = dossierProRepo.findByNomContaining(string, PageRequest.of(page, size)).get().collect(Collectors.toList());

        // conversion vers Dto
        List<DossierProfessionnelDto> lstDto = new ArrayList<>();
        for (DossierProfessionnel dp : lst) {
            DossierProfessionnelDto dpDto = mapper.dossierProfessionnelToDossierProfessionnelDto(dp);
            dpDto.setCursusDto(mapper.cursusToCursusDto(dp.getCursus()));

            lstDto.add(dpDto);
        }
        return lstDto;
    }


    /**
     * Sauvegarde ou mise à jour d'un dossier pro
     */
    @Override
    public DossierProEtudiantDto saveOrUpdateDossierProfessionnel(DossierProEtudiantDto dpDto, long id, List<MultipartFile> files) {
        DossierProfessionnel dp = mapper.dossierProfessionnelDtoToDossierProfessionnel(dpDto);
        
        // Récupération de l'étudiant 
        Optional<Etudiant> etudiantOptional = etudiantRepository.findById(id);
        if (etudiantOptional.isPresent()) {
            Etudiant etudiant = etudiantOptional.get();
            dp.setEtudiant(etudiant);
        }

        // Mettre à jour les expériences professionnelles
        List<ExperienceProfessionnelle> exps = dp.getExperienceProfessionnelles();
        if (exps != null && !exps.isEmpty()) {
            for (ExperienceProfessionnelle exp : exps) {
                exp.setDossierProfessionnel(dp);
            }
        }

        // Mettre à jour les diplômes facultatifs
        List<Facultatif> facultatifs = dp.getFacultatifs();
        if (facultatifs != null && !facultatifs.isEmpty()) {
            for (Facultatif f : facultatifs) {
                f.setDossierProfessionnel(dp);
            }
        }

        // Mettre à jour les annexes
        String path = storageFolder2 + "DossierProfessionnel" + "/";
        List<Annexe> annexes = dp.getAnnexes();

        for (MultipartFile file : files) {
            String pathFile = storageFolder2 + "DossierProfessionnel" + "/" + file.getOriginalFilename();
            Annexe annexe = new Annexe();
            annexe.setPieceJointe(file.getOriginalFilename()); 
            annexe.setLibelleAnnexe("");
            annexes.add(annexe);
            annexe = annexeRepository.save(annexe);

            try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(pathFile))) {
                bos.write(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (Annexe annexe : annexes) {
            annexe.setDossierProfessionnel(dp);
        }

        dp = dossierProRepo.saveAndFlush(dp);

        return DtoTools.convert(dp, DossierProEtudiantDto.class);
    }



    /**
     * Suppression d'un dossier pro
     *
     * @param id Id concernant le dossier pro
     */

    @Override
    public void deleteById(long id) {

        dossierProRepo.deleteById(id);
    }

    /**
     * Recuperation du dossier pro de l'etudiant
     *
     * @param id Id concernant l'etudiant
     * @return dossier pro de l'etudiant concerné
     */


    @Override
    public List<DossierProfessionnelDto> getByIdEtudiant(long id) {
    	
    	  List<DossierProfessionnel> et = dossierProRepo.findDossierProByEtudiantIdAndCursusId(id);
 	      List<DossierProfessionnelDto> lstDossierProfessionnelDto = new ArrayList<>();
 	        
 	        for (DossierProfessionnel dp : et) {

 	            DossierProfessionnelDto dossierProDto = mapper.dossierProfessionnelToDossierProfessionnelDto(dp);
 	            
 	            dossierProDto.setId(dp.getId());
 	            dossierProDto.setNom(dp.getNom());
 	            dossierProDto.setAnnexeDtos(mapper.annexeToAnnexeDto(dp.getAnnexes()));
 	            dossierProDto.setCursusDto(mapper.cursusToCursusDto(dp.getCursus()));
 	            dossierProDto.setExperienceProfessionnelleDtos(mapper.experienceProfessionnelleToExperienceProfessionnelleDto(dp.getExperienceProfessionnelles()));
 	            dossierProDto.setFacultatifDto(mapper.facultatifToFacultatifDto(dp.getFacultatifs()));
 	            dossierProDto.setFileImport(dp.getFileImport());
 	            
 	           lstDossierProfessionnelDto.add(dossierProDto);
 	        }
 	    
 	    return lstDossierProfessionnelDto;
    }

    /**
     * Recuperation du dossier pro par nom
     *
     * @param nom nom concernant le dossier pro
     */
    @Override
    public DossierProfessionnelDto getByName(String nom) {
        DossierProfessionnelDto dpDto = mapper.dossierProfessionnelToDossierProfessionnelDto(dossierProRepo.getByName(nom));
        if (dpDto != null) {
            if (dossierProRepo.getByName(nom).getCursus() == null)
                dpDto.setCursusDto(mapper.cursusToCursusDto(dossierProRepo.getByName(nom).getCursus()));
            return dpDto;
        }
        return null;
    }

   
    @Override
    public List<DossierProEtudiantDto> getAllDossierProfessionnel() {
        List<DossierProfessionnel> lstDossierProfessionnel = dossierProRepo.findAllDossierPro();
        List<DossierProEtudiantDto> lstDossierProfessionnelDto = new ArrayList<>();

        for (DossierProfessionnel dp : lstDossierProfessionnel) {
            DossierProEtudiantDto dpDto = DtoTools.convert(dp, DossierProEtudiantDto.class);
            lstDossierProfessionnelDto.add(dpDto);
        }
        return lstDossierProfessionnelDto;
    }


    @Override
    public GetDossierProDto getAllDossierProfessionnelByEtudiant(long id) {

        Optional<Etudiant> etudiant = etudiantRepository.findById(id);
        GetDossierProDto eDto = DtoTools.convert(etudiant, GetDossierProDto.class);
        return eDto;
    }
    

	@Override
	public CountDto count(String search) {
		long nb = dossierProRepo.countByNom(search);
		CountDto result =  new CountDto();
		result.setNb(nb);
		return result;
	}

	@Override
	public void delete(long id) {
		Optional<DossierProfessionnel> opt = dossierProRepo.findById(id);
		if(opt.isPresent())
		{
			DossierProfessionnel d = opt.get();
			d.setEtudiant(null);
            d.setExperienceProfessionnelles(null);
            d.setCursus(null);
            d.setAnnexes(null);
            d.setFacultatifs(null);
			dossierProRepo.delete(d);
			
		}
		
	}


	@Override
	public DossierProEtudiantDto deleteFileImportById(long id, String fileImport) {
	    String path = storageFolder2 + "DossierProfessionnel" + "/" + fileImport;
	    File fileToDelete = new File(path);

	    if (fileToDelete.exists()) {
	        DossierProfessionnel dp = dossierProRepo.getByDossierbyId(id);

	        fileToDelete.delete();

	        String fileImp = dp.getFileImport();

	        if (fileImp != null && fileImp.contains(fileImport)) {
	            dp.setFileImport(null);

	            try {
	                // Mettre à jour  dp dans la bd
	                dp = dossierProRepo.save(dp);

	                DossierProEtudiantDto dpDto = mapper.dossierProfessionnelToDossierProEtudiantDto(dp);
	                return dpDto;
	            } catch (Exception e) {
	                e.printStackTrace(); 
	                return null;
	            }
	        }
	    }

	    return null;
	}


	@Override
	public DossierProEtudiantDto saveFileImport(MultipartFile fileImport, Long dossierId) throws IOException {
		DossierProfessionnel dp = dossierProRepo.getByDossierbyId(dossierId);
		String nomFile = dp.getFileImport();
		String path = storageFolder2 + "DossierProfessionnel" + "/" + nomFile;
		File fichier = new File(path);
		if (fichier.exists()) {
			fichier.delete();
		}
			String file = fileImport.getOriginalFilename();
			String pathDossierProjet = storageFolder2 + "DossierProfessionnel" + "/" + file;
			File newFile = new File(path);
		    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(newFile));
		    bos.write(fileImport.getBytes());
		    bos.close();
			dp.setFileImport(file);
			DossierProEtudiantDto dpDto = mapper.dossierProfessionnelToDossierProEtudiantDto(dp);
			return dpDto;
	}

	  @Override
		public String generateDossierProPdf(long dossierId) throws PdfTools, IOException, TemplateException {
		    Optional<DossierProfessionnel> dp = dossierProRepo.findById(dossierId);

		    if (dp.isPresent()) {
		        DossierProfessionnel dossier = dp.get();

		        List<ActiviteType> at = activiteTypeRepository.getActiviteTypesByCursus(dossier.getCursus().getId());

		        List<PdfActiviteDto> pdfActiviteDtos = new ArrayList<>();

		        Set<CompetenceProfessionnelle> cp = at.stream().map(ActiviteType::getCompetenceProfessionnelles).reduce(new HashSet<>(),
		        		(l1,l2) -> {l1.addAll(l2);
		        		return l1;
		        		}
		        		);
		        
		        List<ExperienceProfessionnelle> exp = dossier.getExperienceProfessionnelles();

		        
		        for (ActiviteType activite : at) {
		            PdfActiviteDto pdfActiviteDto = DtoTools.convert(activite, PdfActiviteDto.class);

		            Set<PdfCompetenceDto> pdfCompetenceDtos = new HashSet<>();

		            for (CompetenceProfessionnelle competence : cp) {
		                PdfCompetenceDto pdfCompetenceDto = DtoTools.convert(competence, PdfCompetenceDto.class);

		                List<ExperienceProfessionnelleDto> experienceProfessionnelleDtoList = new ArrayList<>();

		                for (ExperienceProfessionnelle experience : exp) {
		                    if (experience.getCompetenceProfessionnelle().getId() == competence.getId() && 
		                        experience.getCompetenceProfessionnelle().getActiviteType().getId() == activite.getId()) {
		                        ExperienceProfessionnelleDto eDto = DtoTools.convert(experience, ExperienceProfessionnelleDto.class);
		                        experienceProfessionnelleDtoList.add(eDto);
		                    }
		                }

		                if (!experienceProfessionnelleDtoList.isEmpty()) {
		                    pdfCompetenceDto.setExperienceProfessionnelleDtoList(experienceProfessionnelleDtoList);
		                    pdfCompetenceDtos.add(pdfCompetenceDto);
		                }
		            }

		            if (!pdfCompetenceDtos.isEmpty()) {
		                pdfActiviteDto.setPdfCompetenceDtoSet(pdfCompetenceDtos);
		                pdfActiviteDtos.add(pdfActiviteDto);
		            }
		        }

		 

		        long etudiantId = dossier.getEtudiant().getId();
		        Signature signature = signatureRepository.getSignatureByEtudiantId(etudiantId);

		        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		        ZonedDateTime now = ZonedDateTime.now();
		        String dateNow = dtf.format(now);

		        freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");

		        Template template = freemarkerConfig.getTemplate("DossierPro.ftl");

		        Map<String, Object> model = new HashMap<>();
		        model.put("backendUrl", backendUrl);
		        model.put("et", dossier.getEtudiant());
		        model.put("at", at);
		        model.put("dp", dossier);
		        model.put("cp", cp);
		        model.put("exp", exp);
		        model.put("pdfActiviteDtos", pdfActiviteDtos);
		        model.put("signature", signature);
		        model.put("dateNow", dateNow);

		        String htmlContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

		        String outputPdf = storageFolder2 + "DossierProfessionnel" + "/" + dossier.getNom() + "_DP.pdf";

		        try {
					PdfTools.generatePdfFromHtml(outputPdf, htmlContent);
				} catch (Exception e) {
					logger.log(Level.SEVERE,"convertHtmlToPdf failed", e);
				}
	            return outputPdf;
	        }
	        return null;
	    } 


    public void emailTuteurDossierProfessionnelle(DossierProEtudiantDto dp, long id)
            throws TemplateException, DossierProfessionnelException, IOException {
        Optional<Etudiant> student = etudiantRepository.findById(id);

        String header = "Votre étudiant " + student.get().getUtilisateur().getFullName() + " à crée son Dossier Projet";
        String message = "Le Dossier " + dp.getNom() + " de l'étudiant " + student.get().getUtilisateur().getFullName()
                + " à été crée";
        Optional<String> path = Optional.of("");
        Optional<String> fileName = Optional.of("");
        // On vérifie si l'étudiant possède un tuteur
        if (student.get().getTuteur().getId() != 0){
            Optional<Tuteur> tuteurStudent = tuteurRepository.findById(student.get().getTuteur().getId());
            //On génère le fichier seulement lors d'un update
            if (dp.getVersion() > 0) {

                header = "Votre étudiant " + student.get().getUtilisateur().getFullName() +
                        " a ajouté des modification à son Dossier Professionnelle";
                //message = "Le Dossier " + dp.getNom() + " de l'étudiant " + student.get().getUtilisateur().getFullName()+ " a été modifié <br></br> Voir pièce jointe.";
                //path = Optional.of(genererDossierProfessionnel(dp.getId(), student.get().getId()));
                //fileName = Optional.of("DossierProfessionnel_"+dp.getNom()+"_"+student.get().getUtilisateur().getFullName()+"_v"+dp.getVersion());
            }
            String body = message + "</br>Veuillez cliquer sur ce lien pour voir le dossier : " +
                    "<a href=\"http://localhost:8080/#/tuteur/detailEtudiant/"+ student.get().getId()+"\">Voir le dossier </a>";
            //Mail Automatique pour informer le tuteur lors de la modification du DossierProjet
            emailService.sendMailSmtpUser(tuteurStudent.get().getUtilisateur().getId(), header, body,path, fileName);
        }
    }
	

	

}
