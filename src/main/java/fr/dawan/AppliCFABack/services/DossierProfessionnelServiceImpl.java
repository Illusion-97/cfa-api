package fr.dawan.AppliCFABack.services;

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
import fr.dawan.AppliCFABack.entities.*;
import fr.dawan.AppliCFABack.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import fr.dawan.AppliCFABack.dto.DossierProfessionnelDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.tools.PdfTools;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@Service
@Transactional
public class DossierProfessionnelServiceImpl implements DossierProfessionnelService {

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
    private ActiviteTypeRepository activiteTypeRepository;

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private SignatureRepository signatureRepository;

    @Autowired
    private Configuration freemarkerConfig;

    @Value("${backend.url}")
    private String backendUrl;

    @Value("src/main/resources/files/bulletinsEvaluations")
    private String storageFolder;
    
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
    @Override
    public DossierProfessionnelDto saveOrUpdate(DossierProfessionnelDto dpDto) {
        DossierProfessionnel d = DtoTools.convert(dpDto, DossierProfessionnel.class);
        d.setCursus(DtoTools.convert(dpDto.getCursusDto(), Cursus.class));
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
        EtudiantDto e = etudiantService.getById(id);

        return e.getDossierProfessionnel();
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
    public DossierProEtudiantDto saveOrUpdateDossierProfessionnel(DossierProEtudiantDto dpDto, long id) {
        DossierProfessionnel dp = DtoTools.convert(dpDto, DossierProfessionnel.class);
        List<ExperienceProfessionnelle> exps = dp.getExperienceProfessionnelles();

        for(ExperienceProfessionnelle exp : exps){
            Optional<Etudiant> optEtudiant = etudiantRepository.findById(id);
            exp.setDossierProfessionnel(dp);
            exp.setEtudiant(optEtudiant.get());
        }

        EtudiantDossierDto eDto = etudiantService.getByEtudiantIdForDossierPro(id);
        //Etudiant etudiant = DtoTools.convert(eDto, Etudiant.class);
        Optional<Etudiant> etudiant = etudiantRepository.findById(id);
        dp.setEtudiant(etudiant.get());
        dp = dossierProRepo.saveAndFlush(dp);

        DossierProEtudiantDto dossierDto = DtoTools.convert(dp, DossierProEtudiantDto.class);
        List<DossierProEtudiantDto> dpList = eDto.getDossierProfessionnel();
        if(!dpList.contains(dossierDto)){
            dpList.add(dossierDto);
        } else {
            int index = dpList.indexOf(dossierDto);
            dpList.set(index, dossierDto);
        }
        //etudiant = etudiantRepository.saveAndFlush(etudiant);
        return dossierDto;
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
            for(GetPromotionDossierProDto pDto : eDto.getPromotions()) {
                if(pDto.getCursus().getId() == dpDto.getCursus().getId()){
                    pDto.getCursus().setDossierProfessionnel(dpDto);
                    pDto.getCursus().getDossierProfessionnel().setCursus(dpDto.getCursus());
                    pDto.getCursus().getDossierProfessionnel().getCursus().getActiviteTypes().stream().map(ac -> {
                        Set<CompetenceDossierProDto> cp = ac.getCompetenceProfessionnelles();
                        for(CompetenceDossierProDto c : cp) {
                            for(ExperienceProfessionnelleDto exp : dpDto.getExperienceProfessionnelles()) {
                                if(exp.getCompetenceProfessionnelleId() == c.getId()) {
                                    c.setExperienceProfessionnelles(dpDto.getExperienceProfessionnelles());
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


}
