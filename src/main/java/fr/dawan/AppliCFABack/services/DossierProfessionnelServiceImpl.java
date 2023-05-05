package fr.dawan.AppliCFABack.services;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import fr.dawan.AppliCFABack.dto.ExperienceProfessionnelleDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprofessionnel.*;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprofessionnel.pdf.PdfActiviteDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprofessionnel.pdf.PdfCompetenceDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprojet.DossierProjetEtudiantDto;
import fr.dawan.AppliCFABack.entities.*;
import fr.dawan.AppliCFABack.repositories.*;

import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.multipart.MultipartFile;

import fr.dawan.AppliCFABack.dto.AnnexeDto;
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DossierProfessionnelDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.tools.DossierProfessionnelException;
import fr.dawan.AppliCFABack.tools.DossierProjetException;
import fr.dawan.AppliCFABack.tools.PdfTools;
import fr.dawan.AppliCFABack.tools.ToPdf;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@Service
@Transactional
public class DossierProfessionnelServiceImpl extends GenericServiceImpl<DossierProfessionnel, DossierProfessionnelDto> implements DossierProfessionnelService {


    @Autowired
    DossierProfessionnelRepository dossierProRepo;
    @Autowired
    EtudiantService etudiantService;

    @Autowired
    private DtoMapper mapper;

    @Autowired
    private ExperienceProfessionnelleRepository experienceProfessionnelleRepository;

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

    @Value("src/main/resources/files/bulletinsEvaluations")
    private String storageFolder;
    
    @Value("src/main/resources/files/")
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
    @SuppressWarnings("unchecked")
	@Override
    public DossierProfessionnelDto saveOrUpdate(DossierProfessionnelDto dpDto) {
        DossierProfessionnel d = DtoTools.convert(dpDto, DossierProfessionnel.class);
        d.setId(dpDto.getId());
        d.setNom(dpDto.getNom());
        d.setCursus(DtoTools.convert(dpDto.getCursusDto(), Cursus.class));
        d.setExperienceProfessionnelles((List<ExperienceProfessionnelle>) DtoTools.convert(dpDto.getCursusDto(), ExperienceProfessionnelle.class));
        d.setEtudiant(DtoTools.convert(dpDto.getEtudiantDto(), Etudiant.class));
        d.setAnnexes((List<Annexe>) DtoTools.convert(dpDto.getAnnexeDtos(), Annexe.class));
        d.setFacultatifs((List<Facultatif>) DtoTools.convert(dpDto.getFacultatifDto(), Facultatif.class));
       
        dossierProRepo.saveAndFlush(d);
        return mapper.dossierProfessionnelToDossierProfessionnelDto(d);
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
    	
    	Optional<Etudiant> etudiant = etudiantRepository.findById(id);
 	    List<DossierProfessionnelDto> lstDossierProfessionnelDto = new ArrayList<>();
 	        Etudiant e = etudiant.get();
 	        List<DossierProfessionnel> lstDossierProfessionnel = e.getDossierProfessionnel();
             
 	        for (DossierProfessionnel dp : lstDossierProfessionnel) {

 	            DossierProfessionnelDto dossierProDto = mapper.dossierProfessionnelToDossierProfessionnelDto(dp);
 	            
 	            dossierProDto.setId(dp.getId());
 	            dossierProDto.setNom(dp.getNom());
 	            dossierProDto.setAnnexeDtos(mapper.annexeToAnnexeDto(dp.getAnnexes()));
 	            dossierProDto.setCursusDto(mapper.cursusToCursusDto(dp.getCursus()));
 	            dossierProDto.setExperienceProfessionnelleDtos(mapper.experienceProfessionnelleToExperienceProfessionnelleDto(dp.getExperienceProfessionnelles()));
 	            dossierProDto.setFacultatifDto(mapper.facultatifToFacultatifDto(dp.getFacultatifs()));
 	          
 	            
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
    public String generateDossierProByStudentAndPromo(long etudiantId, long promotionId) throws PdfTools, TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
        Optional<Etudiant> etuOpt = etudiantRepository.findById(etudiantId);
        Etudiant et = null;

        if(etuOpt.isPresent()) {
            et = etuOpt.get();

            List<ActiviteType> at = activiteTypeRepository.getActiviteTypesByPromotionIdAndOrderByNumeroFiche(etudiantId, promotionId);

            List<PdfActiviteDto> pdfActiviteDtos = new ArrayList<>();

            //insérer la liste d'activités type dans l'objet freemarker
            for(ActiviteType a : at) {
                PdfActiviteDto pdfActiviteDto = DtoTools.convert(a, PdfActiviteDto.class);
                pdfActiviteDtos.add(pdfActiviteDto);
            }

            Set<PdfCompetenceDto> pdfCompetenceDtos1 = new HashSet<>();
            Set<PdfCompetenceDto> pdfCompetenceDtos2 = new HashSet<>();
            Set<PdfCompetenceDto> pdfCompetenceDtos3 = new HashSet<>();
            Set<PdfCompetenceDto> pdfCompetenceDtos4 = new HashSet<>();

            //ACTIVITE 1
            if(pdfActiviteDtos.size() > 0) {
                long activiteId1 = pdfActiviteDtos.get(0).getId();
                List<CompetenceProfessionnelle> cp = competenceProfessionnelleRepository.getCompetencesByActivite(activiteId1);
                for(CompetenceProfessionnelle c : cp) {
                    List<ExperienceProfessionnelle> exp = experienceProfessionnelleRepository.getExperienceByCompetenceId(etudiantId,c.getId());
                    List<ExperienceProfessionnelleDto> experienceProfessionnelleDtoList = new ArrayList<>();
                    for(ExperienceProfessionnelle e : exp) {
                        ExperienceProfessionnelleDto eDto = DtoTools.convert(e, ExperienceProfessionnelleDto.class);
                        experienceProfessionnelleDtoList.add(eDto);
                    }
                    PdfCompetenceDto pdfCompetenceDto = DtoTools.convert(c, PdfCompetenceDto.class);
                    assert pdfCompetenceDto != null;
                    pdfCompetenceDto.setExperienceProfessionnelleDtoList(experienceProfessionnelleDtoList);
                    pdfCompetenceDtos1.add(pdfCompetenceDto);
                }
                Set<PdfCompetenceDto> pdfCompetenceDtos1Sorted = pdfCompetenceDtos1.stream().sorted(Comparator.comparingInt(PdfCompetenceDto::getNumeroFiche)).collect(Collectors.toCollection(LinkedHashSet::new));
                pdfActiviteDtos.get(0).setPdfCompetenceDtoSet(pdfCompetenceDtos1Sorted);
            }


            //ACTIVITE 2
            if(pdfActiviteDtos.size() > 1) {
                long activiteId2 = pdfActiviteDtos.get(1).getId();
                List<CompetenceProfessionnelle> cp2 = competenceProfessionnelleRepository.getCompetencesByActivite(activiteId2);
                for(CompetenceProfessionnelle c : cp2) {
                    List<ExperienceProfessionnelle> exp = experienceProfessionnelleRepository.getExperienceByCompetenceId(etudiantId,c.getId());
                    List<ExperienceProfessionnelleDto> experienceProfessionnelleDtoList = new ArrayList<>();
                    for(ExperienceProfessionnelle e : exp) {
                        ExperienceProfessionnelleDto eDto = DtoTools.convert(e, ExperienceProfessionnelleDto.class);
                        experienceProfessionnelleDtoList.add(eDto);
                    }
                    PdfCompetenceDto pdfCompetenceDto = DtoTools.convert(c, PdfCompetenceDto.class);
                    assert pdfCompetenceDto != null;
                    pdfCompetenceDto.setExperienceProfessionnelleDtoList(experienceProfessionnelleDtoList);
                    pdfCompetenceDtos2.add(pdfCompetenceDto);
                }
                Set<PdfCompetenceDto> pdfCompetenceDtos2Sorted = pdfCompetenceDtos2.stream().sorted(Comparator.comparingInt(PdfCompetenceDto::getNumeroFiche)).collect(Collectors.toCollection(LinkedHashSet::new));
                pdfActiviteDtos.get(1).setPdfCompetenceDtoSet(pdfCompetenceDtos2Sorted);
            }


            //ACTIVITE 3
            if(pdfActiviteDtos.size() > 2) {
                long activiteId3 = pdfActiviteDtos.get(2).getId();
                List<CompetenceProfessionnelle> cp3 = competenceProfessionnelleRepository.getCompetencesByActivite(activiteId3);
                for(CompetenceProfessionnelle c : cp3) {
                    List<ExperienceProfessionnelle> exp = experienceProfessionnelleRepository.getExperienceByCompetenceId(etudiantId,c.getId());
                    List<ExperienceProfessionnelleDto> experienceProfessionnelleDtoList = new ArrayList<>();
                    for(ExperienceProfessionnelle e : exp) {
                        ExperienceProfessionnelleDto eDto = DtoTools.convert(e, ExperienceProfessionnelleDto.class);
                        experienceProfessionnelleDtoList.add(eDto);
                    }
                    PdfCompetenceDto pdfCompetenceDto = DtoTools.convert(c, PdfCompetenceDto.class);
                    assert pdfCompetenceDto != null;
                    pdfCompetenceDto.setExperienceProfessionnelleDtoList(experienceProfessionnelleDtoList);
                    pdfCompetenceDtos3.add(pdfCompetenceDto);
                }
                Set<PdfCompetenceDto> pdfCompetenceDtos3Sorted = pdfCompetenceDtos3.stream().sorted(Comparator.comparingInt(PdfCompetenceDto::getNumeroFiche)).collect(Collectors.toCollection(LinkedHashSet::new));
                pdfActiviteDtos.get(2).setPdfCompetenceDtoSet(pdfCompetenceDtos3Sorted);
            }

            //ACTIVITE 4
            if(pdfActiviteDtos.size() > 3) {
                long activiteId4 = pdfActiviteDtos.get(3).getId();
                List<CompetenceProfessionnelle> cp4 = competenceProfessionnelleRepository.getCompetencesByActivite(activiteId4);
                for(CompetenceProfessionnelle c : cp4) {
                    List<ExperienceProfessionnelle> exp = experienceProfessionnelleRepository.getExperienceByCompetenceId(etudiantId,c.getId());
                    List<ExperienceProfessionnelleDto> experienceProfessionnelleDtoList = new ArrayList<>();
                    for(ExperienceProfessionnelle e : exp) {
                        ExperienceProfessionnelleDto eDto = DtoTools.convert(e, ExperienceProfessionnelleDto.class);
                        experienceProfessionnelleDtoList.add(eDto);
                    }
                    PdfCompetenceDto pdfCompetenceDto = DtoTools.convert(c, PdfCompetenceDto.class);
                    assert pdfCompetenceDto != null;
                    pdfCompetenceDto.setExperienceProfessionnelleDtoList(experienceProfessionnelleDtoList);
                    pdfCompetenceDtos4.add(pdfCompetenceDto);
                }
                Set<PdfCompetenceDto> pdfCompetenceDtos4Sorted = pdfCompetenceDtos4.stream().sorted(Comparator.comparingInt(PdfCompetenceDto::getNumeroFiche)).collect(Collectors.toCollection(LinkedHashSet::new));
                pdfActiviteDtos.get(3).setPdfCompetenceDtoSet(pdfCompetenceDtos4Sorted);
            }

//            List<CompetenceProfessionnelle> cp = competenceProfessionnelleRepository.getCompetenceProfessionnellesByEtudiantDossier(etudiantId);
//
//            List<ExperienceProfessionnelle> exp = experienceProfessionnelleRepository.getExperienceByEtudiantDossier(etudiantId, promotionId);

            Signature signature = signatureRepository.getSignatureByEtudiantId(etudiantId);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/uuuu");
            ZonedDateTime now = ZonedDateTime.now();
            String dateNow = dtf.format(now);

            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");

            Template template = freemarkerConfig.getTemplate("DossierPro.ftl");

            Map<String, Object> model = new HashMap<>();
            model.put("backendUrl", backendUrl);
            model.put("et", et);
            model.put("pdfActiviteDtos", pdfActiviteDtos);
            model.put("at", at);
//            model.put("cp", cp);
//            model.put("exp", exp);
            model.put("dateNow", dateNow);
            model.put("signature", signature);

            String htmlContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

            String outputPdf = storageFolder + "/dossier-" + etudiantId + "-pro-" + promotionId + ".pdf";

            try {
				PdfTools.generatePdfFromHtml(outputPdf, htmlContent);
			} catch (Exception e) {
				logger.log(Level.SEVERE,"convertHtmlToPdf failed", e);
			}
            return outputPdf;
        }
        return null;
    }

    @Override
    public GetDossierProDto getAllDossierProfessionnelByEtudiant(long id) {

        Optional<Etudiant> etudiant = etudiantRepository.findById(id);
        GetDossierProDto eDto = DtoTools.convert(etudiant, GetDossierProDto.class);

        List<DossierProfessionnel> dossierProfessionnel = dossierProRepo.findDossierProByEtudiantIdAndCursusId(id);
        for(DossierProfessionnel dp : dossierProfessionnel) {
            DossierProEtudiantDto dpDto = DtoTools.convert(dp, DossierProEtudiantDto.class);
            assert eDto != null;
            for(GetPromotionDossierProDto pDto : eDto.getPromotions()) {
                assert dpDto != null;
                if(pDto.getCursus().getId() == dpDto.getCursusDto().getId()){
                    pDto.getCursus().setDossierProfessionnel(dpDto);
                    pDto.getCursus().getDossierProfessionnel().setCursusDto(dpDto.getCursusDto());
                    pDto.getCursus().getDossierProfessionnel().getCursusDto().getActiviteTypes().stream().map(ac -> {
                        Set<CompetenceDossierProDto> cp = ac.getCompetenceProfessionnelles();
                        for(CompetenceDossierProDto c : cp) {
                            for(ExperienceProfessionnelleDto exp : dpDto.getExperienceProfessionnelleDtos()) {
                                if(exp.getCompetenceProfessionnelleId() == c.getId()) {
                                    c.setExperienceProfessionnelles(dpDto.getExperienceProfessionnelleDtos());
                                }
                            }
                        }
                        return pDto;
                    }).collect(Collectors.toList());
                }
            }
        }
        return eDto;
    }

	

	@Override
	public DossierProEtudiantDto saveOrUpdateDossierProfessionnel(DossierProEtudiantDto dpDto, long id, List<MultipartFile> file) 
	{
		DossierProfessionnel dp = mapper.dossierProfessionnelDtoToDossierProfessionnel(dpDto);
        assert dp != null;
        
    
        List<ExperienceProfessionnelle> exps = dp.getExperienceProfessionnelles();
        for(ExperienceProfessionnelle exp : exps)
        {
            Optional<Etudiant> optEtudiant = etudiantRepository.findById(id);
            exp.setDossierProfessionnel(dp);              
            optEtudiant.ifPresent(exp::setEtudiant);
        }

        //on récupère la liste des diplômes facultatifs d'un dossier professionnel et on les met à jour (en n'oubliant pas de set les clés étrangères de la table diplome_facultatif)
        List<Facultatif> facultatif = dp.getFacultatifs();
        for(Facultatif f : facultatif) {
            f.setDossierProfessionnel(dp);
        }

        //on récupère la liste des annexes d'un dossier professionnel et on les met à jour (en n'oubliant pas de set les clés étrangères de la table annexe)
        String path = storageFolder2 + "DossierProfessionnel" + "/";
        List<Annexe> annexes = dp.getAnnexes();
        int i = 0;
        for(MultipartFile fil : file) {
            String pathFile = path + fil.getOriginalFilename();
            File newAnnexe = new File(pathFile);
            Annexe annex = annexes.get(i++);
            annex.setPieceJointe(pathFile);
            //annex.setLibelle("lib2");
      
            
            try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(newAnnexe))){
                try 
                {
                    bos.write(fil.getBytes());
                }
                catch (IOException e) 
                {
                    e.printStackTrace();
                }
            } 
            catch (FileNotFoundException e1)
            {
                e1.printStackTrace();
            } 
            catch (IOException e1) 
            {
                e1.printStackTrace();
            }
        }
         
        for(Annexe annexe : annexes) {
            annexe.setDossierProfessionnel(dp);
        }

        //on met à jour la clé étrangère etudiant de la table dossier_professionnel (dans le cas d'un save)
        EtudiantDossierDto eDto = etudiantService.getByEtudiantIdForDossierPro(id);
        Optional<Etudiant> etudiant = etudiantRepository.findById(id);
        if(etudiant.isPresent()){
             dp.setEtudiant(etudiant.get());
        }
       
       
  
        //on insert ou met à jour le dossier en question
        dp = dossierProRepo.saveAndFlush(dp);
        

        return DtoTools.convert(dp, DossierProEtudiantDto.class);
		
		
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
	public String genererDossierProfessionnel(long idDossierPro) throws DossierProfessionnelException,
			TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		
		
          Optional<DossierProfessionnel> dossierPro = dossierProRepo.findById(idDossierPro);
    	
    	if (!dossierPro.isPresent()) {
    		throw new DossierProfessionnelException("le DossierProfessionnel et non trouvable");
		}
		DossierProEtudiantDto dossierproFile = mapper.dossierProfessionnelToDossierProEtudiantDto(dossierPro.get());
    	
    	Map<String, Object> model = new HashMap<>();
    	model.put("backendUrl", backendUrl);
    	model.put("dossierPro", dossierproFile);
		freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");
		Template template = freemarkerConfig.getTemplate("DossierProfessionnel.ftl");

		String htmlContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
		
		String outputPdf = storageFolder2 + "/DossierProfessionnel" + "/dossier-professionnel" + idDossierPro + ".pdf";
		try {
			ToPdf.convertHtmlToPdf(htmlContent, outputPdf);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "convertHtmlToPdf failed", e);
		}

		return outputPdf;
	}

	}

