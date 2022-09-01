package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import fr.dawan.AppliCFABack.dto.DossierProfessionnelDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.customdtos.DossierProEtudiantDto;
import fr.dawan.AppliCFABack.dto.customdtos.EtudiantDossierDto;
import fr.dawan.AppliCFABack.entities.Cursus;
import fr.dawan.AppliCFABack.entities.DossierProfessionnel;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.ExperienceProfessionnelle;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.repositories.CompetenceProfessionnelleRepository;
import fr.dawan.AppliCFABack.repositories.DossierProfessionnelRepository;
import fr.dawan.AppliCFABack.repositories.EtudiantRepository;
import fr.dawan.AppliCFABack.repositories.ExperienceProfessionnelleRepository;
import fr.dawan.AppliCFABack.tools.PdfTools;
import freemarker.template.Configuration;
import freemarker.template.Template;

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
    private Configuration freemarkerConfig;

    @Value("${backend.url}")
    private String backendUrl;

    @Value("src/main/resources/files/bulletinsEvaluations")
    private String storageFolder;

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
            DossierProfessionnelDto dpDto = mapper.DossierProfessionnelToDossierProfessionnelDto(dp);
            dpDto.setCursusDto(mapper.CursusToCursusDto(dp.getCursus()));
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
            DossierProfessionnelDto dpDto = mapper.DossierProfessionnelToDossierProfessionnelDto(dp.get());
            dpDto.setCursusDto(mapper.CursusToCursusDto(dp.get().getCursus()));
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
            DossierProfessionnelDto dpDto = mapper.DossierProfessionnelToDossierProfessionnelDto(dp);
            dpDto.setCursusDto(mapper.CursusToCursusDto(dp.getCursus()));

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
        return mapper.DossierProfessionnelToDossierProfessionnelDto(d);
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
        DossierProfessionnelDto dpDto = mapper.DossierProfessionnelToDossierProfessionnelDto(dossierProRepo.getByName(nom));
        if (dpDto != null) {
            if (dossierProRepo.getByName(nom).getCursus() == null)
                dpDto.setCursusDto(mapper.CursusToCursusDto(dossierProRepo.getByName(nom).getCursus()));
            return dpDto;
        }
        return null;
    }

    @Override
    public DossierProEtudiantDto saveOrUpdateDossierProfessionnel(DossierProEtudiantDto dpDto, long id) {
        DossierProfessionnel dp = DtoTools.convert(dpDto, DossierProfessionnel.class);
        List<ExperienceProfessionnelle> exps = dp.getExperienceProfessionnelles();
        for(ExperienceProfessionnelle exp : exps){
            exp.setDossierProfessionnel(dp);
        }
        dp = dossierProRepo.saveAndFlush(dp);

        EtudiantDossierDto eDto = etudiantService.getByEtudiantIdForDossierPro(id);
        DossierProEtudiantDto dossierDto = DtoTools.convert(dp, DossierProEtudiantDto.class);
        List<DossierProEtudiantDto> dpList = eDto.getDossierProfessionnel();
        if(!dpList.contains(dossierDto)){
            dpList.add(dossierDto);
        } else {
            int index = dpList.indexOf(dossierDto);
            dpList.set(index, dossierDto);
        }

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
    public String generateDossierProByStudentAndPromo(long etudiantId, long promotionId) throws Exception {
        Optional<Etudiant> etuOpt = etudiantRepository.findById(etudiantId);
        if(etuOpt.isPresent()) {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");

            Template template = freemarkerConfig.getTemplate("DossierPro.ftl");

            Map<String, Object> model = new HashMap<>();
            model.put("backendUrl", backendUrl);

            String htmlContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

            String outputPdf = storageFolder + "/dossier-" + etudiantId + "-pro-" + promotionId + ".pdf";

            PdfTools.generatePdfFromHtml(outputPdf, htmlContent);

            return outputPdf;
        }
        return null;
    }


}
