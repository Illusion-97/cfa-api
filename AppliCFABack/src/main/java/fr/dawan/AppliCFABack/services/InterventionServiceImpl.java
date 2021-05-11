package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.FormationDto;
import fr.dawan.AppliCFABack.dto.InterventionDto;
import fr.dawan.AppliCFABack.dto.PromotionDto;
import fr.dawan.AppliCFABack.entities.Formation;
import fr.dawan.AppliCFABack.entities.Intervention;
import fr.dawan.AppliCFABack.entities.Promotion;
import fr.dawan.AppliCFABack.repositories.InterventionRepository;

@Service
@Transactional
public class InterventionServiceImpl implements InterventionService {

	@Autowired
	InterventionRepository interventionRepository;

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
			lstDto.add(DtoTools.convert(i, InterventionDto.class));
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
		interventionRepository.deleteById(id);

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

}
