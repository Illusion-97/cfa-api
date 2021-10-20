package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DevoirDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.FormateurDto;
import fr.dawan.AppliCFABack.dto.FormationDto;
import fr.dawan.AppliCFABack.dto.InterventionDto;
import fr.dawan.AppliCFABack.dto.PromotionDto;
import fr.dawan.AppliCFABack.entities.Devoir;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.Formateur;
import fr.dawan.AppliCFABack.entities.Intervention;
import fr.dawan.AppliCFABack.entities.PassageExamen;
import fr.dawan.AppliCFABack.entities.Promotion;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.DevoirRepository;
import fr.dawan.AppliCFABack.repositories.EtudiantRepository;
import fr.dawan.AppliCFABack.repositories.FormateurRepository;
import fr.dawan.AppliCFABack.repositories.FormationRepository;
import fr.dawan.AppliCFABack.repositories.InterventionRepository;
import fr.dawan.AppliCFABack.repositories.PassageExamenRepository;
import fr.dawan.AppliCFABack.repositories.PromotionRepository;

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
	private DtoMapper mapper = new DtoMapperImpl();

	@Override
	public List<InterventionDto> getAllIntervention() {
		List<Intervention> lst = interventionRepository.findAll();

		List<InterventionDto> lstDto = new ArrayList<InterventionDto>();
		for (Intervention i : lst) {
			lstDto.add(mapper.InterventionToInterventionDto(i));
		}
		return lstDto;
	}

	@Override
	public List<InterventionDto> getAllIntervention(int page, int size) {
		List<Intervention> lst = interventionRepository.findAll(PageRequest.of(page, size)).get()
				.collect(Collectors.toList());
		// conversion vers Dto
		List<InterventionDto> lstDto = new ArrayList<InterventionDto>();

		for (Intervention i : lst) {
			InterventionDto interventionDto = mapper.InterventionToInterventionDto(i);
			FormationDto formationDto = mapper.FormationToFormationDto(i.getFormation());
			interventionDto.setFormationDto(formationDto);

			lstDto.add(interventionDto);
		}
		return lstDto;
	}

	@Override
	public List<InterventionDto> getAllByPage(int page, int size, String search) {
		List<Intervention> lstIn = interventionRepository
				.findAllDistinctByFormationTitreContainingIgnoringCaseOrPromotionsNomContainingIgnoringCase(search,
						search, PageRequest.of(page, size))
				.get().collect(Collectors.toList());

		List<InterventionDto> lstDto = new ArrayList<InterventionDto>();

		for (Intervention intervention : lstIn) {
			/**
			 * on recup une intervention de type Intervention que l'on convertis en
			 * InterventionDto
			 **/
			InterventionDto interventionDto = mapper.InterventionToInterventionDto(intervention);
			/**
			 * on recup une formation de type Formation que l'on convertis en FormationDto
			 **/
			FormationDto formationDto = mapper.FormationToFormationDto(intervention.getFormation());
			// Les convertion en Dto faite => on ajoute la formationDto à l'interventionDto
			interventionDto.setFormationDto(formationDto);

//			Intervention inter = intervention.getInterventionMere();
//
//			InterventionDto interventionMereDto = DtoTools.convert(inter, InterventionDto.class);
//			interventionDto.setInterventionMereDto(interventionMereDto);

			// On affiche une liste de promotions de type List<Promotion>
			List<Promotion> lstPromo = intervention.getPromotions();
			List<PromotionDto> lstPromoDto = new ArrayList<PromotionDto>();
			for (Promotion promotion : lstPromo) {
				/** On convertis List<Promotion> en List<PromotionDto> **/
				if (promotion != null)
					lstPromoDto.add(mapper.PromotionToPromotionDto(promotion));
			}

			List<FormateurDto> lstFormDto = new ArrayList<FormateurDto>();
			for (Formateur formateur : intervention.getFormateurs()) {
				if (formateur != null)
					lstFormDto.add(DtoTools.convert(formateur, FormateurDto.class));
			}

			// On ajoute la liste des formateurs a l'intervention
			interventionDto.setFormateursDto(lstFormDto);
			
			// On ajoute la liste de promotions a l'intervention
			interventionDto.setPromotionsDto(lstPromoDto);
			// On ajoute l'intervention a la liste d'intervention
			lstDto.add(interventionDto);

		}
		return lstDto;
	}

	@Override
	public InterventionDto getById(long id) {
		Optional<Intervention> i = interventionRepository.findById(id);
		if (i.isPresent()) {
			InterventionDto interventionDto = mapper.InterventionToInterventionDto(i.get());
//			// Recupere les formations par rapport à l'id de l'intervention
//			// Convertion de l'entitie Formation en FormationDto
			FormationDto formationDto = mapper.FormationToFormationDto(i.get().getFormation());

			List<PromotionDto> lstPromoDto = new ArrayList<PromotionDto>();
			for (Promotion promo : i.get().getPromotions()) {
				if (promo != null)
					lstPromoDto.add(mapper.PromotionToPromotionDto(promo));
			}

			List<FormateurDto> lstFormaDto = new ArrayList<FormateurDto>();
			for (Formateur formateur : i.get().getFormateurs()) {
				if (formateur != null)
					lstFormaDto.add(mapper.FormateurToFormateurDto(formateur));
			}

			interventionDto.setFormateursDto(lstFormaDto);
			interventionDto.setPromotionsDto(lstPromoDto);
			interventionDto.setFormationDto(formationDto);
			return interventionDto;
		}

		return null;
	}

	@Override
	public InterventionDto saveOrUpdate(InterventionDto iDto) {
		Intervention i = DtoTools.convert(iDto, Intervention.class);

		i = interventionRepository.saveAndFlush(i);
		
		filesService.createDirectory("interventions/" + i.getId());

		return mapper.InterventionToInterventionDto(i);
	}

	@Override
	public void deleteById(long id) {
		// On regarde si un devoir est lié à une intervention
		Devoir dev = devoirRepository.findByInterventionId(id);
		// Si c'est le cas : on enleve sa liaison en rendant l'intervention à null
		if (dev != null)
			dev.setIntervention(null);
		// Meme chose : on regarde si un passage d'examen est lié une intervention
		PassageExamen passExam = passageExamenRepository.findByInterventionId(id);
		// Si c'est le cas : on enleve sa liaison en rendant l'intervention à null
		if (passExam != null)
			passExam.setIntervention(null);
		// Une fois les liaisions enlevées on peux supprimer l'intervention
		interventionRepository.deleteById(id);
		filesService.deleteDirectoryWithContent("interventions/" + id);
	}

	@Override
	public CountDto count(String search) {
		return new CountDto(interventionRepository
				.countDistinctByFormationTitreContainingIgnoringCaseOrPromotionsNomContainingIgnoringCase(search,
						search));
	}

	// Liste des etudiants des promotions par l'id de l'intervention
	@Override
	public List<EtudiantDto> findAllByPromotionInterventionsId(long id) {
		List<Etudiant> lstEtu = etudiantRepository.findAllDistinctByPromotionsInterventionsId(id);
		List<EtudiantDto> lstEtuDto = new ArrayList<EtudiantDto>();
		for (Etudiant etu : lstEtu) {
			if (etu != null) {
				EtudiantDto eDto = mapper.EtudiantToEtudiantDto(etu);
				eDto.setUtilisateurDto(mapper.UtilisateurToUtilisateurDto(etu.getUtilisateur()));
				lstEtuDto.add(eDto);
			}				
		}
		return lstEtuDto;
	}

	// Liste des promotions par l'id de l'intervention
	@Override
	public List<PromotionDto> findPromotionsByInterventionId(long id) {
		List<Promotion> lstProm = promoRepository.findAllByInterventionsId(id);
		List<PromotionDto> lstPromDto = new ArrayList<PromotionDto>();
		for (Promotion prom : lstProm) {
			if (prom != null)
				lstPromDto.add(mapper.PromotionToPromotionDto(prom));
		}
		return lstPromDto;
	}

	// Liste des devoirs par l'id de l'intervention
	@Override
	public List<DevoirDto> findDevoirsByInterventionId(long id) {
		List<Devoir> lsDev = devoirRepository.findAllByInterventionId(id);
		List<DevoirDto> lsDevDto = new ArrayList<DevoirDto>();

		for (Devoir dev : lsDev) {
			if (dev != null)
				lsDevDto.add(mapper.DevoirToDevoirDto(dev));
		}
		return lsDevDto;
	}

	@Override
	public List<FormateurDto> findFormateursByInterventionsId(long id) {
		List<Formateur> lstForm = formateurRepository.findByInterventionsId(id);
		List<FormateurDto> lstFormDto = new ArrayList<FormateurDto>();
		for (Formateur form : lstForm) {
			if (form != null) {
				FormateurDto fDto = mapper.FormateurToFormateurDto(form);
				fDto.setUtilisateurDto(mapper.UtilisateurToUtilisateurDto(form.getUtilisateur()));
				lstFormDto.add(fDto);				
			}
		}
		return lstFormDto;
	}

}
