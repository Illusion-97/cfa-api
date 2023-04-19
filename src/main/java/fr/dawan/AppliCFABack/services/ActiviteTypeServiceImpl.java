package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.ActiviteTypeDto;
import fr.dawan.AppliCFABack.dto.CompetenceProfessionnelleDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.ExamenDto;
import fr.dawan.AppliCFABack.entities.ActiviteType;
import fr.dawan.AppliCFABack.entities.CompetenceProfessionnelle;
import fr.dawan.AppliCFABack.entities.Examen;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.ActiviteTypeRepository;

/***
 * 
 * @author Feres BG Valentin C.
 * @see fr.dawan.AppliCFABack.repositories.ActiviteTypeRepository
 * @see fr.dawan.AppliCFABack.dto.ActiviteTypeDto
 * @since 1.0
 * @version 1.0
 *
 */
@Service
@Transactional
public class ActiviteTypeServiceImpl implements ActiviteTypeService {
	@Autowired
	private ActiviteTypeRepository activiteTypeRepo;

	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();

	/**
	 * Récupération de tous les activites types
	 * 
	 * @return ActiviteTypeDto Liste des objets Activite Type DTO
	 */

	@Override
	public List<ActiviteTypeDto> getAllActiviteType() {
		List<ActiviteType> activiteTypes = activiteTypeRepo.findAll();
		List<ActiviteTypeDto> activiteTypesDto = new ArrayList<>();
		for (ActiviteType activiteType : activiteTypes) {
			ActiviteTypeDto atDto = mapper.activiteTypeToActiviteTypeDto(activiteType);
			List<ExamenDto> examensDto = new ArrayList<>();
			for (Examen ex : activiteType.getExamens()) {
				examensDto.add(mapper.examenToExamenDto(ex));

			}

			atDto.setExamensDto(examensDto);
			atDto.setCursusActiviteTypeId(activiteType.getCursusActiviteType().getId());
			
			activiteTypesDto.add(atDto);
		}
		return activiteTypesDto;
	}

	/***
	 * @return liste d'ActivitesTypes DTO
	 */
	@Override
	public List<ActiviteTypeDto> getAllActiviteType(int page, int size, String string) {
		
		return null;
	}

	/**
	 * Récupération de activite types en fonction de son id
	 * 
	 * @param id ActiviteType
	 * @return ActiviteType DTO
	 * 
	 */
	@Override
	public ActiviteTypeDto getById(long id) {
		Optional<ActiviteType> act = activiteTypeRepo.findById(id);
		if (act.isPresent()) {
			ActiviteTypeDto atDto = mapper.activiteTypeToActiviteTypeDto(act.get());
			List<Examen> examens = act.get().getExamens();
			List<ExamenDto> examensDto = new ArrayList<>();
			for (Examen examen : examens) {
				examensDto.add(mapper.examenToExamenDto(examen));
			}
			atDto.setExamensDto(examensDto);
			atDto.setCursusActiviteTypeId(act.get().getCursusActiviteType().getId());
			return atDto;
		}

		return null;
	}

	/**
	 * Sauvegarde ou mise à jour d'une activité type
	 * 
	 * @param ActiviteType DTO
	 * @return ActiviteType DTO
	 */
	@Override
	public ActiviteTypeDto saveOrUpdate(ActiviteTypeDto atDto) {
		ActiviteType activiteType = DtoTools.convert(atDto, ActiviteType.class);

		activiteType = activiteTypeRepo.saveAndFlush(activiteType);

		return mapper.activiteTypeToActiviteTypeDto(activiteType);
	}

	/**
	 * Suppression d'une activité type
	 * 
	 * @param id ActiviteType
	 */
	@Override
	public void deleteById(long id) {
		activiteTypeRepo.deleteById(id);

	}

	/***
	 * Récupération des activites types en fonction de l'id de la promotion
	 * 
	 * @param id Promotion
	 * @return  ActiviteTypeDto Liste des objets Activite Type DTO
	 */
	@Override
	public List<ActiviteTypeDto> getAllActiviteTypesByPromotionId(long id) {

		List<ActiviteType> activiteTypes = activiteTypeRepo.getActiviteTypesByPromotionId(id);
		List<ActiviteTypeDto> activiteTypeDto = new ArrayList<>();
		
		for (ActiviteType activiteType : activiteTypes) {
			ActiviteTypeDto atDto = mapper.activiteTypeToActiviteDto(activiteType);
			List<CompetenceProfessionnelleDto> cpDto = new ArrayList<>();
			for (CompetenceProfessionnelle cp : activiteType.getCompetenceProfessionnelles()) {
				cpDto.add(mapper.competenceProfessionnelleToCompetenceProfessionnelleDto(cp));
				atDto.setCompetenceProfessionnellesDto(cpDto);
			}
			activiteTypeDto.add(atDto);
		}
		return activiteTypeDto;
	}

	@Override
	public List<ActiviteTypeDto> getAllActiviteTypesByCursus(long id) {

	    List<ActiviteType> activiteTypes = activiteTypeRepo.getActiviteTypesByCursus(id);
	    List<ActiviteTypeDto> activiteTypeDto = new ArrayList<>();
	    for (ActiviteType activiteType : activiteTypes) {

	        ActiviteTypeDto atDto = mapper.activiteTypeToActiviteDto(activiteType);
	        atDto.setCursusActiviteTypeId(activiteType.getCursusActiviteType().getId());
	        List<CompetenceProfessionnelleDto> cpDto = new ArrayList<>();

	        //on set pour renvoyé un ensemble set
	        Set<CompetenceProfessionnelle> cps = activiteType.getCompetenceProfessionnelles();
	        //on transform en list pour pouvoir trier
	        List<CompetenceProfessionnelle> cpList = new ArrayList<>(cps); 
	        //trie par id croissant
	        cpList.sort(Comparator.comparing(CompetenceProfessionnelle::getId)); // sort the list

	        for (CompetenceProfessionnelle cp : cpList) {
	            cpDto.add(mapper.competenceProfessionnelleToCompetenceProfessionnelleDto(cp));
	        }
	        atDto.setCompetenceProfessionnellesDto(cpDto);
	        activiteTypeDto.add(atDto);
	    }
	    return activiteTypeDto;
	}

}
