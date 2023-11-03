package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.SoutenanceDto;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.Soutenance;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.EtudiantRepository;
import fr.dawan.AppliCFABack.repositories.SoutenanceRepository;
import fr.dawan.AppliCFABack.tools.DossierProjetException;
import fr.dawan.AppliCFABack.tools.SaveInvalidException;
import fr.dawan.AppliCFABack.tools.ToPdf;
import freemarker.core.ParseException;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@Transactional
public class SoutenanceServiceImpl implements SoutenanceService {

	@Autowired
	SoutenanceRepository soutenanceRepository;
	@Autowired
	EtudiantRepository etudiantRepository;
	@Autowired
	EmailService emailService;
	private static Logger logger =Logger.getGlobal();
	@Value("${app.storagefolder}")
	private String storageFolder;
	@Value("${backend.url}")
	private String backendUrl;
	@Autowired
	private Configuration freemarkerConfig;
	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();

	@Override
	public SoutenanceDto getById(long id) {
		Optional<Soutenance> soutenance = soutenanceRepository.findById(id);
		if (soutenance.isPresent()) {
			
			return mapper.soutenanceToSoutenanceDto(soutenance.get());
		}
		
		return null;
	}
	
	@Override
	public List<SoutenanceDto> getAll() {
		List<Soutenance> soutenances = soutenanceRepository.findAll();
		if (!soutenances.isEmpty()) {
			List<SoutenanceDto> soutenanceDtos = new ArrayList<>();
			for (Soutenance soutenance : soutenances) {
				SoutenanceDto soutenanceDto = new SoutenanceDto();
				soutenanceDto = mapper.soutenanceToSoutenanceDto(soutenance);
				soutenanceDtos.add(soutenanceDto);
			}
			
			return soutenanceDtos;
		}
		
		return null;
	}

	@Override
	public SoutenanceDto saveOrUpdate(SoutenanceDto tDto) throws SaveInvalidException {
		Soutenance soutenance = new Soutenance();
		if (tDto.getEtudiant().getId() > 0) {
			Etudiant etudiant = etudiantRepository.getOne(tDto.getEtudiant().getId());
			tDto.setEtudiant(mapper.etudiantToEtudiantSoutenanceDto(etudiant));
			soutenance = mapper.soutenanceDtoToSoutenance(tDto);
		}
		//emailService.sendMailSmtpUser(tDto.getEtudiant().getUtilisateurDto().getId(),
			//	"Convocation examen", "Vous êtes convoquez pour l'éxamen ...",
				//Optional.of("/cfa-portal-api/src/main/resources/templates/convocationExamen.ftl"),
				//Optional.of("Convocation examen"), tDto);
		try {
			soutenanceRepository.saveAndFlush(soutenance);
		} catch (Exception ex) {
			logger.log(Level.WARNING, "Error save etudiant", ex);
		}		
		
		return mapper.soutenanceToSoutenanceDto(soutenance);
	}

	@Override
	public CountDto count(String search) {
		long nb = soutenanceRepository.countByPromotionId(Long.parseLong(search));
		CountDto result =  new CountDto();
		result.setNb(nb);
				
		return result;
	}

	@Override
	public void delete(long id) {
		soutenanceRepository.deleteById(id);
	}

	@Override
	public List<SoutenanceDto> getByPromotionId(long id) {
		List<Soutenance> soutenances = soutenanceRepository.getByPromotionId(id);
		List<SoutenanceDto> soutenanceDtos = new ArrayList<>();
		for (Soutenance soutenance : soutenances) {
			soutenanceDtos.add(mapper.soutenanceToSoutenanceDto(soutenance));
		}
		
		return soutenanceDtos;
	}
	
	@Override
	public List<SoutenanceDto> getPageByPromotionId(long id, int page, int size) {
		List<Soutenance> lstSoutenances = soutenanceRepository.getPageByPromotionId(id, PageRequest.of(page, size)).get().collect(Collectors.toList());
		List<SoutenanceDto> lstSoutenanceDtos = new ArrayList<>();
		if (!lstSoutenances.isEmpty()) {
			for (Soutenance soutenance : lstSoutenances) {
				SoutenanceDto soutenanceDto = mapper.soutenanceToSoutenanceDto(soutenance);
				lstSoutenanceDtos.add(soutenanceDto);
			}
		}
		return lstSoutenanceDtos;
	}

	@Override
	public String genererLstSoutenance(String promotion, long idPromotion) throws TemplateNotFoundException,
	MalformedTemplateNameException, ParseException, IOException, TemplateException, DossierProjetException {
		List<SoutenanceDto> soutenanceDto = getByPromotionId(idPromotion);
		Map<String, Object> model = new HashMap<>();
		model.put("backendUrl", backendUrl);
		model.put("soutenaceDto", soutenanceDto);
		freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");
		// TODO
		Template template = freemarkerConfig.getTemplate("LstSoutenance.ftl");

		String htmlContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
		String outputPdf = storageFolder + "List_soutenance_"+ promotion +".pdf";
		try {
			ToPdf.convertHtmlToPdf(htmlContent, outputPdf);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "convertHtmlToPdf failed", e);
		}

		return outputPdf;
	}	
	
}
