package fr.dawan.AppliCFABack.services;


import fr.dawan.AppliCFABack.dto.*;
import fr.dawan.AppliCFABack.entities.*;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class InterventionServiceImpl implements InterventionService {

	@Autowired
	InterventionRepository interventionRepository;
	@Autowired
	PromotionRepository promoRepository;
	@Autowired
	FormateurRepository formateurRepository;
	@Autowired
	FormationRepository formationRepository;
	@Autowired
	PassageExamenRepository passageExamRepository;
	@Autowired
	EtudiantRepository etudiantRepository;
	@Autowired
	DevoirRepository devoirRepository;
	@Autowired
	PassageExamenRepository passageExamenRepository;
	@Autowired
	FilesService filesService;
	@Autowired
	UtilisateurRepository utilisateurRepository;
	@Autowired
	CentreFormationRepository centreFormationRepository;

	@Autowired
	UtilisateurRoleRepository utilisateurRoleRepository;
	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();

	private static final Logger logger = LoggerFactory.getLogger(InterventionServiceImpl.class);

	/**
	 * Récupération de toutes les interventions
	 *
	 * @return lstDto Liste des objets interevention
	 */

	@Override
	public List<InterventionDto> getAllIntervention() {
		List<Intervention> lst = interventionRepository.findAll();

		List<InterventionDto> lstDto = new ArrayList<>();
		for (Intervention i : lst) {
			InterventionDto interventionDto = mapper.interventionToInterventionDto(i);
			interventionDto.setHeuresDisponsees();
			lstDto.add(interventionDto);

		}
		return lstDto;
	}

	/**
	 * Va permettre de récupérer toutes les interventions
	 *
	 * @param page numero de la page
	 * @param size éléments sur la page
	 * @return LstDto Liste des objets interventions
	 */
	@Override
	public List<InterventionDto> getAllIntervention(int page, int size) {
		List<Intervention> lst = interventionRepository.findAll(PageRequest.of(page, size)).get()
				.collect(Collectors.toList());
		// conversion vers Dto
		List<InterventionDto> lstDto = new ArrayList<>();

		for (Intervention i : lst) {
			InterventionDto interventionDto = mapper.interventionToInterventionDto(i);
			FormationDto formationDto = mapper.formationToFormationDto(i.getFormation());
			interventionDto.setFormationDto(formationDto);

			lstDto.add(interventionDto);
		}
		return lstDto;
	}

	/**
	 * Va permettre de récupérer toutes les interventions avec pagination
	 *
	 * @param page   numero de page
	 * @param size   nombre d'éléments
	 * @param search éléménts de l'intervention
	 * @return List Liste des devoirs concerné
	 */

	@Override
	public List<InterventionDto> getAllByPage(int page, int size, String sort, String search) {
		List<Intervention> lstIn = interventionRepository
				.findAllDistinctByFormationTitreContainingIgnoringCaseOrPromotionsNomContainingIgnoringCase(search,
						search, PageRequest.of(page, size))
				.get().collect(Collectors.toList());
		switch (sort) {
		case "datefin":
			lstIn.sort(Comparator.comparing(Intervention::getDateFin).reversed());
			break;
		case "datedebut":
			lstIn.sort(Comparator.comparing(Intervention::getDateDebut));
			break;
		}
		List<InterventionDto> lstDto = new ArrayList<>();

		for (Intervention intervention : lstIn) {

			InterventionDto interventionDto = mapper.interventionToInterventionDto(intervention);

			FormationDto formationDto = mapper.formationToFormationDto(intervention.getFormation());
			interventionDto.setFormationDto(formationDto);
			List<Promotion> lstPromo = intervention.getPromotions();
			List<PromotionDto> lstPromoDto = new ArrayList<>();
			for (Promotion promotion : lstPromo) {
				if (promotion != null)
					lstPromoDto.add(mapper.promotionToPromotionDto(promotion));
			}

			FormateurDto lstFormDto = new FormateurDto();

			interventionDto.setFormateurDto(lstFormDto);

			interventionDto.setPromotionsDto(lstPromoDto);

			lstDto.add(interventionDto);
		}
		return lstDto;
	}

	/**
	 * Va permettre de récupérer l'intervention en fonction de son id
	 *
	 * @param id Id concernant l'intervention
	 * @return interventionDto l'objet intervention
	 */

	@Override
	public InterventionDto getById(long id) {
		Optional<Intervention> i = interventionRepository.findById(id);
		if (i.isPresent()) {
			InterventionDto interventionDto = mapper.interventionToInterventionDto(i.get());
			FormationDto formationDto = mapper.formationToFormationDto(i.get().getFormation());

			List<PromotionDto> lstPromoDto = new ArrayList<>();
			for (Promotion promo : i.get().getPromotions()) {
				if (promo != null)
					lstPromoDto.add(mapper.promotionToPromotionDto(promo));
			}

			FormateurDto lstFormaDto = new FormateurDto();
//			for (Formateur formateur : i.get().getFormateurs()) {
//				if (formateur != null)
//					lstFormaDto.add(mapper.formateurToFormateurDto(formateur));
//			}TODO

			interventionDto.setFormateurDto(lstFormaDto);
			interventionDto.setPromotionsDto(lstPromoDto);
			interventionDto.setFormationDto(formationDto);
			interventionDto.setHeuresDisponsees();
			return interventionDto;
		}

		return null;
	}

	/**
	 * Sauvegarde ou mise à jour d'une intervention
	 *
	 */

//	@Override
//	public InterventionDto saveOrUpdate(InterventionDto iDto) {
//		Intervention i = DtoTools.convert(iDto, Intervention.class);
//
//		if (iDto.getPromotionsId() != null) {
//			for (long id : iDto.getPromotionsId()) {
//				Optional<Promotion> promoOpt = promoRepository.findById(id);
//				if (promoOpt.isPresent()) {
//					// subtilité sur update si promo déja présent
//					i.getPromotions().add(promoOpt.get());
//					promoOpt.get().getInterventions().add(i);
//				}
//			}
//		}
//
//		i = interventionRepository.saveAndFlush(i);
//
//		filesService.createDirectory("interventions/" + i.getId());
//
//		return mapper.interventionToInterventionDto(i);
//	}

	@Override
	public InterventionDto saveOrUpdate(InterventionDto iDto) {
		Intervention intervention;
		if (iDto.getId() != 0L) {
			intervention = interventionRepository.getOne(iDto.getId());
			DtoTools.convert(iDto, Intervention.class);
		} else {
			intervention = DtoTools.convert(iDto, Intervention.class);
		}

		// Charger les entités liées avant de les assigner à l'intervention
		Formateur formateur = intervention.getFormateur();
		if (formateur != null) {
			intervention.setFormateur(formateurRepository.getOne(formateur.getId()));
		}

		List<Promotion> promotions = intervention.getPromotions();
		if (promotions != null) {
			Intervention savedIntervention = interventionRepository.save(intervention);
			Set<Promotion> existingPromotions = new HashSet<>(savedIntervention.getPromotions());
			Set<Promotion> newPromotions = new HashSet<>();
			for (Promotion promotion : promotions) {
				if (existingPromotions.contains(promotion)) {
					existingPromotions.remove(promotion);
				} else {
					newPromotions.add(promoRepository.getOne(promotion.getId()));
				}
			}
			savedIntervention.getPromotions().addAll(newPromotions);
			savedIntervention.getPromotions().removeAll(existingPromotions);
			intervention = interventionRepository.save(savedIntervention);
		} else {
			intervention = interventionRepository.saveAndFlush(intervention);
		}

		// Mettre à jour la noteInfoPersonnel si elle est présente dans l'objet DTO
		if (iDto.getNoteInfoPersonnel() != null) {
			intervention.setNoteInfoPersonnel(iDto.getNoteInfoPersonnel());
			interventionRepository.save(intervention);
		}

		filesService.createDirectory("interventions/" + intervention.getId());

		return mapper.interventionToInterventionDto(intervention);
	}

	/**
	 * Suppression d'une intervention
	 *
	 * @param Id Id concernant l'intervention
	 */

	@SuppressWarnings("unused")
	@Override
	public void deleteById(long id) {
		// On regarde si un devoir est lié à une intervention
		Devoir dev = devoirRepository.findByInterventionId(id);
		// Si c'est le cas : on enleve sa liaison en rendant l'intervention à null
		if (dev != null)
			dev.setIntervention(null);
		// Meme chose : on regarde si un passage d'examen est lié une intervention
		// PassageExamen passExam = passageExamenRepository.findByInterventionId(id);
		PassageExamen passExam = null;
		// Si c'est le cas : on enleve sa liaison en rendant l'intervention à null
		if (passExam != null)
			passExam.setIntervention(null);
		// Une fois les liaisions enlevées on peux supprimer l'intervention
		interventionRepository.deleteById(id);
		filesService.deleteDirectoryWithContent("interventions/" + id);
	}

	/**
	 * Recherche d'une intervention
	 *
	 * @param search recherche par titre formation ou promo
	 */

	@Override
	public CountDto count(String search) {
		return new CountDto(interventionRepository
				.countDistinctByFormationTitreContainingIgnoringCaseOrPromotionsNomContainingIgnoringCase(search,
						search));
	}

	/**
	 * Va permettre de récupérer la liste des etudiants de la promotion en fonction
	 * de l'id de l'intervention
	 *
	 * @param id Id concernant l'intervention
	 * @return List Liste des etudiants concerné
	 */

	@Override
	public List<EtudiantDto> findAllEtudiantsByPromotionInterventionsId(long id) {
		List<Etudiant> lstEtu = etudiantRepository.findAllDistinctByPromotionsInterventionsId(id);
		List<EtudiantDto> lstEtuDto = new ArrayList<>();
		for (Etudiant etu : lstEtu) {
			if (etu != null) {
				EtudiantDto eDto = mapper.etudiantToEtudiantDto(etu);
				eDto.setUtilisateurDto(mapper.utilisateurToUtilisateurDto(etu.getUtilisateur()));
				lstEtuDto.add(eDto);
			}
		}
		return lstEtuDto;
	}

	/**
	 * Va permettre de récupérer les promotions en fonction de l'id de
	 * l'intervention
	 *
	 * @param id Id concernant l'intervention
	 * @return List Liste des promotions concerné
	 */

	@Override
	public List<PromotionDto> findPromotionsByInterventionId(long id) {
		List<Promotion> lstProm = promoRepository.findAllByInterventionsId(id);
		List<PromotionDto> lstPromDto = new ArrayList<>();
		for (Promotion prom : lstProm) {
			if (prom != null)
				lstPromDto.add(mapper.promotionToPromotionDto(prom));
		}
		return lstPromDto;
	}

	/**
	 * Va permettre de recupérer le formateur en fonction de l'intervention
	 *
	 * @param id Id concernant l'intervention
	 * @return List Liste des formateurs concerné
	 */

	@Override
	public List<FormateurDto> findFormateursByInterventionsId(long id) {
		Formateur formateur = formateurRepository.findByInterventionId(id);
		List<FormateurDto> lstFormDto = new ArrayList<>();
		if (formateur != null) {
			FormateurDto fDto = mapper.formateurToFormateurDto(formateur);
			fDto.setUtilisateurDto(mapper.utilisateurToUtilisateurDto(formateur.getUtilisateur()));
			lstFormDto.add(fDto);
		}
		return lstFormDto;
	}


	@Override
	public List<InterventionDto> findInterventionByPromotionId(long id, int page, int size, String search) {
		List<Intervention> result = interventionRepository
				.findInterventionByPromotionId(id, search, PageRequest.of(page, size)).get()
				.collect(Collectors.toList());
		List<InterventionDto> res = new ArrayList<>();
		if (!result.isEmpty()) {
			for (Intervention i : result) {
				InterventionDto interventionDto = mapper.interventionToInterventionDto(i);
				res.add(interventionDto);
			}
		}
		return res;
	}

	@Override
	public CountDto countInterventionByPromotionId(long id, String search) {
		return new CountDto(interventionRepository.countInterventionByPromotionId(id, search));
	}

	@Override
	public List<InterventionDto> findAllByFormateurId(long formateurId) {
		List<InterventionDto> interventionDtos = new ArrayList<>();
		List<Intervention> lst = interventionRepository.findAllByFormateurId(formateurId);
		lst.forEach(i -> {
			InterventionDto interventionDto = mapper.interventionToInterventionDto(i);
			interventionDto.setHeuresDisponsees();
			interventionDtos.add(interventionDto);
		});

		return interventionDtos;
	}

	@Override
	public void deleteLstIntervention(List<InterventionDto> interventionDtos) {
		List<Intervention> interventions = new ArrayList<>();
		interventionDtos.forEach(interventionDto -> {
			Intervention intervention = DtoTools.convert(interventionDto, Intervention.class);
			interventions.add(intervention);
		});
		interventionRepository.deleteInBatch(interventions);
	}
}
