package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.*;
import fr.dawan.AppliCFABack.dto.customdtos.GrillePositionnementDto;
import fr.dawan.AppliCFABack.dto.customdtos.PromotionEtudiantDto;
import fr.dawan.AppliCFABack.entities.*;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.*;
import fr.dawan.AppliCFABack.services.dg2Imports.DG2ImportTools;
import fr.dawan.AppliCFABack.tools.GrilleException;
import fr.dawan.AppliCFABack.tools.PdfTools;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
public class PromotionServiceImpl extends DG2ImportTools implements PromotionService {

	private final PromotionRepository promotionRepository;

	private final FilesService filesService;

	private final InterventionRepository interventionRepository;

	private final PositionnementRepository positionnementRepository;


	private final NiveauService niveauService;

	@Value("${app.storagefolder}")
	private String storageFolder;

	@Autowired
	private Configuration freemarkerConfig;

	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();
	@Autowired
	private DtoTools mapperTools;

	private static final Logger logger = Logger.getGlobal();

	public PromotionServiceImpl(
			@Autowired PromotionRepository promotionRepository,
			@Autowired PositionnementRepository positionnementRepository,
			@Autowired NiveauService niveauService,
			@Autowired FilesService filesService,
			@Autowired InterventionRepository interventionRepository

	) {
		this.promotionRepository = promotionRepository;
		this.positionnementRepository = positionnementRepository;
		this.niveauService = niveauService;
		this.filesService = filesService;
		this.interventionRepository = interventionRepository;
	}

	/**
	 * Récupération de la liste des promo
	 *
	 * @return lstDto	Liste des objets promotion
	 */
	//recuperation de la liste des promo
	@Override
	public List<PromotionDto> getAll() {
		List<Promotion> lst = promotionRepository.findAll();
		List<PromotionDto> lstDto = new ArrayList<>();
		for (Promotion promo : lst) {
			PromotionDto pDto = mapper.promotionToPromotionDto(promo);
			pDto.setCentreFormationDto(mapper.centreFormationToCentreFormationDto(promo.getCentreFormation()));
			lstDto.add(pDto);
		}
		return lstDto;
	}

	/**
	 * Récupération des promotions en fonction de l'id
	 *
	 * @param id	id de la promotion
	 * @return pDto	la promo
	 */

	@Override
	public PromotionDto getById(long id){
		return mapper.promotionToPromotionDto(promotionRepository.findById(id).get());
	}
	/**
	 * Sauvegarde ou mise à jour d'une promotion
	 *
	 */

	@Override
	public PromotionDto saveOrUpdate(PromotionDto pDto) {
		Promotion p = DtoTools.convert(pDto, Promotion.class);
		p = promotionRepository.saveAndFlush(p);
		filesService.createDirectory("promotions/" + p.getId());

		return mapper.promotionToPromotionDto(p);
	}

	/**
	 * Suppression d'une promotion
	 *
	 * @param id	Id concernant la promotion
	 */

	@Override
	public void deleteById(long id) {
		promotionRepository.deleteById(id);
		filesService.deleteDirectoryWithContent("promotions/"+id);
	}

	/**
	 * Récuperation du referent avec id promo
	 *
	 * @param id	Id de la promotion
	 */

	@Override
	public UtilisateurDto getReferentById(long id) {
		return mapper.utilisateurToUtilisateurDto(promotionRepository.getOne(id).getReferentPedagogique());
	}

	/**
	 * Recherche d'une promotion
	 *
	 * @param search recherche par nom
	 */
	@Override
	public CountDto count(String search) {
		return new CountDto(promotionRepository.countByNomContaining(search));
	}

	@Override
	public CountDto countByCentreFormationId(long id, String date) {
		return new CountDto(promotionRepository.countByCentreFormationIdAndDateDebutContainingAllIgnoringCase(id, date));
	}


	/**
	 * Va permettre de récupérer toutes les promotions avec pagination
	 * et recherche
	 *
	 * @param page	numero de la page
	 * @param size	éléments sur la page
	 * @param search	éléments promotions (nom)
	 * @return res Liste des objets promotions
	 */

	@Override
	public List<PromotionDto> getAllPromotions(int page, int size, Optional<String> choixOpt, String search) {
		List<Promotion> promoVille = promotionRepository.findAllByNomOrCentreFormationNomIgnoreCase(search,choixOpt.get());
		String choix = choixOpt.get();
		List<PromotionDto> res = new ArrayList<>();
		switch (choix){
			case "sort_participants": promoVille.sort(Comparator.comparing(Promotion::getNbParticipants).reversed());
				break;
			case "sort_datefin": promoVille.sort(Comparator.comparing(Promotion::getDateFin).reversed());
				break;
			case "sort_datedebut": promoVille.sort(Comparator.comparing(Promotion::getDateDebut));
				break;
		}

		int start = page * size;
		int end = Math.min(start + size, promoVille.size());
		List<Promotion> promotionsOnPage = promoVille.subList(start, end);

		Page<Promotion> pageResult = new PageImpl<>(promotionsOnPage, PageRequest.of(page, size), promoVille.size());
		/*

		*/
		if (!promoVille.isEmpty()){
			for (Promotion p : pageResult) {
				PromotionDto promotionDto = mapper.promotionToPromotionDto(p);
				res.add(promotionDto);
			}
		}

		return res;
	}

	/**
	 * Récupération des etudiants en fonction de l'id de la promotion
	 *
	 * @param id	id de la promotion
	 */

	@Override
	public List<EtudiantDto> getEtudiantsById(long id) {
		List<Etudiant> lst = promotionRepository.getOne(id).getEtudiants();
		List<EtudiantDto> lstDto = new ArrayList<>();
		for (Etudiant e : lst) {
			List<PromotionDto> promoList = new ArrayList<>();
			for(Promotion p : e.getPromotions()) {
				promoList.add(mapper.promotionToPromotionDto(p));
			}
			EtudiantDto eDto = mapper.etudiantToEtudiantDto(e);
			eDto.setPromotionsDto(promoList);
			eDto.setUtilisateurDto(mapper.utilisateurToUtilisateurDto(e.getUtilisateur()));
			lstDto.add(eDto);
		}
		return lstDto;
	}

	/**
	 * Récupération des promo en fonction de l'id du cursus
	 *
	 * @param id	id du cursus
	 * @return result	List des promotions
	 */

	@Override
	public List<PromotionDto> getAllByCursusId(long id) {
		List<Promotion> lst = promotionRepository.findAllByCursusId(id);

		List<PromotionDto> result = new ArrayList<>();

		for(Promotion p : lst) {
			result.add(mapper.promotionToPromotionDto(p));
		}

		return result;
	}

	/**
	 * Erreur méthodes controller-service-repo à refaire avec un dto custom pour l'accueil entier
	 * Récupération du cef en fonction de l'id de la promotion
	 *
	 * @param id	id de la promotion
	 */

	@Override
    public UtilisateurDto getCefById(long id) {
        return mapper.utilisateurToUtilisateurDto(promotionRepository.getOne(id).getCef().getUtilisateur());
    }

	/**
	 * Récupération des promotions en fonction de l'id du cursus et de l'etudiant
	 *
	 * @param id	id de l'etudiant
	 * @return result	Liste des promotions
	 */
	@Override
	public List<PromotionDto> getPromotionByEtudiantIdAndByCursusId(long id) {
		List<Promotion> promos = promotionRepository.getByEtudiantId(id);
		List<PromotionDto> result = new ArrayList<>();

		for(Promotion p : promos) {
			PromotionDto pDto = mapper.promotionToPromotionDto(p);
			result.add(pDto);
		}
		return result;
	}

	/**
	 * @param id de l'étudiant
	 * utilise le PromotionRepository pour récupérer toutes les promotions par id de l'étudiant
	 * @return toutes les données nécessaires pour remplir la section Cursus du front partie étudiant par le mapper (DtoTools) :
	 * 			- titre du cursus
	 * 			- description du cursus
	 * 			- durée du cursus
	 * 			- nom de la promotion
	 * 			- dates de début et de fin de la promotion
	 * 			- plannings de l'étudiant par rapport à ses promotions
	 */

	@Override
	public List<PromotionEtudiantDto> getCursusByIdEtudiant(long id) {
		List<Promotion> promotions = promotionRepository.getByEtudiantId(id);

		return promotions.stream().map(p -> mapperTools.promotionToPromotionEtudiantDto(p)).collect(Collectors.toList());
	}

	/**
	 * @param idIntervention identifiant de l'intervention
	 * @return liste de promotions (seulement avec l'id le version et le nom)  pour le select de promotions
	 */
	@Override
	public List<PromotionForSelectDto> getPromotionByInterventionIdForSelect(long idIntervention) {
		List<PromotionForSelectDto> result = new ArrayList<>();
		List<Promotion> promotions = promotionRepository.findAllByInterventionsId(idIntervention);

		for (Promotion promotion : promotions) {
			result.add(DtoTools.convert(promotion, PromotionForSelectDto.class));
		}

		return result;
	}



	@Override
	public String getGrillePositionnement(long idPromotion) throws GrilleException,
			IOException, TemplateException {
		Optional<Promotion> promotion = promotionRepository.findById(idPromotion);
		if (!promotion.isPresent())
			throw new GrilleException("Promotion non trouvé");

		List<Intervention> interventions = interventionRepository.getInterventionsByIdPromotion(idPromotion);
		if (interventions.isEmpty() || interventions == null)
			throw new GrilleException("Pas d'interventions encore pour cette promotion non trouvé");

		Map<String, List<?>> gp = new HashMap<>();
		List<GrillePositionnementDto> grillesPositionnements = new ArrayList<>();

		for (Intervention i : interventions) {
			GrillePositionnementDto gpd = new GrillePositionnementDto();
			gpd.setDateDebut(i.getDateDebut());
			gpd.setDateFin(i.getDateFin());
			Formation f = i.getFormation();
			if (f != null) {
				gpd.setModule(f.getTitre());
			}else {
				gpd.setModule("Pas de Formation");
			}
			gpd.setObjectifPedagogiques("A définir");
			gpd.setFormateur(i.getFormateur().getUtilisateur().getFullName());
			Map<String, Positionnement> etudiantsPositionnement = new HashMap<>();
			List<Positionnement> positionnements =  positionnementRepository.getAllByInterventionId(i.getId());

			for (Positionnement p : positionnements) {
				etudiantsPositionnement.put(p.getEtudiant().getUtilisateur().getNom() + " "
			+p.getEtudiant().getUtilisateur().getPrenom(), p);
			}
			gpd.setEtudiantsPositionnements(etudiantsPositionnement);
			grillesPositionnements.add(gpd);
		}
		gp.put("interventions", grillesPositionnements);
		List<NiveauDto> niveaux = niveauService.getAllNiveaux();
		gp.put("niveaux", niveaux);
		freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");
		Template template = freemarkerConfig.getTemplate("GrillePositionnement.ftl");
		String htmlContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, gp);
		String outputPdf = storageFolder + "grillePositionnements/GrillePositionnement"+promotion.get().getNom()+".pdf";

		try {
			PdfTools.generatePdfFromHtml(outputPdf,htmlContent );
		} catch (Exception e) {
			logger.log(Level.SEVERE,"convertHtmlToPdf failed", e);
		}
		return outputPdf;
	}

	@Override
	public List<PromotionDto> getPromoByCentreFormationIdPagination(int page, int size, long id, String search) {
		List<Promotion> result = promotionRepository.findPromotionsByCentreFormationIdAndDateDebutContainingAllIgnoringCase(id, PageRequest.of(page, size), search).get().collect(Collectors.toList());
		List<PromotionDto> res = new ArrayList<>();
		for(Promotion p: result) {
			res.add(DtoTools.convert(p, PromotionDto.class));
		}
		return res;
	}

	@Override
	public List<PromotionDto> getPromotionByIdFormateur(long id, int page, int size, String search ) {
		List<Promotion> result = promotionRepository.findAllByFormateurId(id, search, PageRequest.of(page, size)).get().collect(Collectors.toList());
		List<PromotionDto> res = new ArrayList<>();
		if (!result.isEmpty()) {
			for (Promotion p: result) {
				PromotionDto promotionDto =  mapper.promotionToPromotionDto(p);
				res.add(promotionDto);
			}
		}
		return res;
	}

	@Override
	public CountDto countByFormateur(long id, String search) {
		return new CountDto(promotionRepository.countByFormateurId(id, search));
	}

	@Override
	public CountDto countByNomOrCentreFormationOrDate(String search) {
		return new CountDto(promotionRepository.countByNomOrCentreFormationOrDate(search));
	}

}
