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
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.FormationDto;
import fr.dawan.AppliCFABack.dto.InterventionDto;
import fr.dawan.AppliCFABack.dto.PromotionDto;
import fr.dawan.AppliCFABack.entities.Formateur;
import fr.dawan.AppliCFABack.entities.Formation;
import fr.dawan.AppliCFABack.entities.Intervention;
import fr.dawan.AppliCFABack.entities.Promotion;
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

	@Override
	public List<InterventionDto> getAllIntervention() {
		List<Intervention> lst = interventionRepository.findAll();

		List<InterventionDto> lstDto = new ArrayList<InterventionDto>();
		for (Intervention i : lst) {
			lstDto.add(DtoTools.convert(i, InterventionDto.class));
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
			InterventionDto interventionDto = DtoTools.convert(i, InterventionDto.class);
			FormationDto formationDto = DtoTools.convert(i.getFormation(), FormationDto.class);
			interventionDto.setFormationDto(formationDto);

			lstDto.add(interventionDto);
		}
		return lstDto;
	}

	@Override
	public List<InterventionDto> getAllByPage(int page, int size, String search) {
		List<Intervention> lstIn = interventionRepository
				.findAllByFormationTitreContainingIgnoringCaseOrPromotionsNomContainingIgnoringCase(search, search,
						PageRequest.of(page, size))
				.get().collect(Collectors.toList());

		List<InterventionDto> lstDto = new ArrayList<InterventionDto>();

		for (Intervention intervention : lstIn) {
			/**
			 * on recup une intervention de type Intervention que l'on convertis en
			 * InterventionDto
			 **/
			InterventionDto interventionDto = DtoTools.convert(intervention, InterventionDto.class);
			/**
			 * on recup une formation de type Formation que l'on convertis en FormationDto
			 **/
			FormationDto formationDto = DtoTools.convert(intervention.getFormation(), FormationDto.class);
			// Les convertion en Dto faite => on ajoute la formationDto à l'interventionDto
			interventionDto.setFormationDto(formationDto);

			Intervention inter = intervention.getInterventionMere();

			InterventionDto interventionMereDto = DtoTools.convert(inter, InterventionDto.class);
			interventionDto.setInterventionMereDto(interventionMereDto);

			// On affiche une liste de promotions de type List<Promotion>
			List<Promotion> lstPromo = intervention.getPromotion();
			List<PromotionDto> lstPromoDto = new ArrayList<PromotionDto>();
			for (Promotion promotion : lstPromo) {
				/** On convertis List<Promotion> en List<PromotionDto> **/
				if (promotion != null)
					lstPromoDto.add(DtoTools.convert(promotion, PromotionDto.class));
			}

			// On ajoute la liste de promotion a l'intervention
			interventionDto.setPromotionDto(lstPromoDto);
			// On ajoute l'intervention a la liste d'intervention
			lstDto.add(interventionDto);

		}
		return lstDto;
	}

	@Override
	public InterventionDto getById(long id) {
		Optional<Intervention> i = interventionRepository.findById(id);
		if (i.isPresent()) {
			InterventionDto interventionDto = DtoTools.convert(i.get(), InterventionDto.class);
			// Recupere les formations par rapport à l'id de l'intervention
			// Convertion de l'entitie Formation en FormationDto
			Formation formation = i.get().getFormation();
			FormationDto formationDto = DtoTools.convert(formation, FormationDto.class);

			List<Promotion> lstPromo = i.get().getPromotion();
			List<PromotionDto> lstPromoDto = new ArrayList<PromotionDto>();
			for (Promotion promo : lstPromo) {
				if (promo != null)
					lstPromoDto.add(DtoTools.convert(promo, PromotionDto.class));
			}

			interventionDto.setPromotionDto(lstPromoDto);
			interventionDto.setFormationDto(formationDto);
			return interventionDto;
		}

		return null;
	}

	@Override
	public InterventionDto saveOrUpdate(InterventionDto iDto) {
		Intervention i = DtoTools.convert(iDto, Intervention.class);

		i = interventionRepository.saveAndFlush(i);

		return DtoTools.convert(i, InterventionDto.class);
	}

	@Override
	public void deleteById(long id) {
		InterventionDto interventionDto = getById(id);
		Intervention intervention = DtoTools.convert(interventionDto, Intervention.class);

		if (intervention == null) {
			return;
		}

		// TODO List<Promotion> lstPromo => @ManyToMany
		// TODO List<Formateur> lstFormateur => @ManyToMany
		List<Promotion> lstPromotions = intervention.getPromotion();
		List<Formateur> lstFormateurs = intervention.getFormateurs();
		intervention.setPromotion(null);
		intervention.setFormateurs(null);
		interventionRepository.save(intervention);

		Intervention interventionToDelete = new Intervention();

		for (Promotion promotion : lstPromotions) {
			for (Intervention interv : promotion.getInterventions()) {
				if (interv.getId() == intervention.getId()) {
					interventionToDelete = interv;
					break;
				}
				promotion.getInterventions().remove(interventionToDelete);
				promoRepository.save(promotion);
				System.out.println("DELETE promo OK");
			}
		}

		for (Formateur formateur : lstFormateurs) {
			for (Intervention interv : formateur.getInterventions()) {
				if (interv.getId() == intervention.getId()) {
					interventionToDelete = interv;
					break;
				}
				formateur.getInterventions().remove(interventionToDelete);
				formateurRepository.save(formateur);
				System.out.println("DELETE Formateur OK");
			}
		}
		// TODO Formation formation => @ManyToOne
		Formation formation = intervention.getFormation();
		intervention.setFormation(null);
		formationRepository.save(formation);
		formationRepository.delete(formation);
		System.out.println("DELETE formation OK");
		interventionRepository.delete(interventionToDelete);

		System.out.println("DELETE FAILED");
	}

	@Override
	public List<InterventionDto> getAllInterventionWithObject() {
		// En passant par les Dto on perd les relation Objet entre les differentes
		// classes
		/**
		 * Le principe est de pouvoir recuperer les infos de la classe ainsi que leur
		 * relation Objet avec les autre classe qu'il contienne en passant par les Dto
		 **/

		// On recuperent d'abord les interventions
		List<Intervention> lstIn = interventionRepository.findAll();
		List<InterventionDto> lstDto = new ArrayList<InterventionDto>();

		for (Intervention intervention : lstIn) {
			/**
			 * on recup une intervention de type Intervention que l'on convertis en
			 * InterventionDto
			 **/
			InterventionDto interventionDto = DtoTools.convert(intervention, InterventionDto.class);
			/**
			 * on recup une formation de type Formation que l'on convertis en FormationDto
			 **/
			FormationDto formationDto = DtoTools.convert(intervention.getFormation(), FormationDto.class);
			// Les convertion en Dto faite => on ajoute la formationDto à l'interventionDto
			interventionDto.setFormationDto(formationDto);

			Intervention inter = intervention.getInterventionMere();

			InterventionDto interventionMereDto = DtoTools.convert(inter, InterventionDto.class);
			interventionDto.setInterventionMereDto(interventionMereDto);

			// On affiche une liste de promotions de type List<Promotion>
			List<Promotion> lstPromo = intervention.getPromotion();
			List<PromotionDto> lstPromoDto = new ArrayList<PromotionDto>();
			for (Promotion promotion : lstPromo) {
				/** On convertis List<Promotion> en List<PromotionDto> **/
				if (promotion != null)
					lstPromoDto.add(DtoTools.convert(promotion, PromotionDto.class));
			}

			// On ajoute la liste de promotion a l'intervention
			interventionDto.setPromotionDto(lstPromoDto);
			// On ajoute l'intervention a la liste d'intervention
			lstDto.add(interventionDto);

		}
		return lstDto;
	}

	@Override
	public CountDto count(String search) {
		return new CountDto(interventionRepository
				.countByFormationTitreContainingIgnoringCaseOrPromotionsNomContainingIgnoringCase(search, search));
	}

}
