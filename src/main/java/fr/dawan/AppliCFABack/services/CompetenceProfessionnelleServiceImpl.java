package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.CompetenceProfessionnelleDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.ExamenDto;
import fr.dawan.AppliCFABack.entities.CompetenceProfessionnelle;
import fr.dawan.AppliCFABack.entities.Examen;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.CompetenceProfessionnelleRepository;
import fr.dawan.AppliCFABack.repositories.ExperienceProfessionnelleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CompetenceProfessionnelleServiceImpl implements CompetenceProfessionnelleService {

	@Autowired
	private CompetenceProfessionnelleRepository competenceProfessionnelleRepository;

	@Autowired
	private ExperienceProfessionnelleRepository experienceProfessionnelleRepository;

	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();

	/**
	 * Récupération de tous les competences pro
	 * 
	 * @return competenceProfessionnellesDto Liste des objets competences pro
	 */

	@Override
	public List<CompetenceProfessionnelleDto> getAllCompetenceProfessionnelle() {
		List<CompetenceProfessionnelle> competenceProfessionnelles = competenceProfessionnelleRepository.findAll();
		List<CompetenceProfessionnelleDto> competenceProfessionnellesDto = new ArrayList<>();
		for (CompetenceProfessionnelle competenceProfessionnelle : competenceProfessionnelles) {
			CompetenceProfessionnelleDto cptDto = DtoTools.convert(competenceProfessionnelle,
					CompetenceProfessionnelleDto.class);
			List<ExamenDto> examensDto = new ArrayList<>();
			for (Examen ex : competenceProfessionnelle.getExamens()) {

				examensDto.add(mapper.examenToExamenDto(ex));

			}
			cptDto.setExamensDto(examensDto);
			competenceProfessionnellesDto.add(cptDto);
		}
		return competenceProfessionnellesDto;
	}

	/**
	 * Récupération des competence pro en fonction de l'id
	 * 
	 * @param id CompetenceProfessionnelle
	 * @return <CompetenceProfessionnelle DTO
	 * 
	 */

	@Override
	public CompetenceProfessionnelleDto getById(long id) {
		Optional<CompetenceProfessionnelle> cpt = competenceProfessionnelleRepository.findById(id);
		if (cpt.isPresent()) {
			return DtoTools.convert(cpt.get(), CompetenceProfessionnelleDto.class);
		}

		return null;
	}

	/**
	 * Sauvegarde ou mise à jour d'une competence pro
	 * 
	 * @param id CompetenceProfessionnelle
	 * @return <CompetenceProfessionnelle DTO
	 * 
	 */

	@Override
	public CompetenceProfessionnelleDto saveOrUpdate(CompetenceProfessionnelleDto cpDto) {
		CompetenceProfessionnelle cpt = DtoTools.convert(cpDto, CompetenceProfessionnelle.class);
		CompetenceProfessionnelle cptBd = competenceProfessionnelleRepository.saveAndFlush(cpt);

		return DtoTools.convert(cptBd, CompetenceProfessionnelleDto.class);
	}

	/**
	 * Suppression d'une competence pro
	 * 
	 * @param id Id concernant la competence pro
	 */

	@Override
	public void deleteById(long id) {
		competenceProfessionnelleRepository.deleteById(id);
	}
	
	/**
	 * Récupération de toutes les absences en fonctions d'un EtudiantId
	 */
	@Override
	public List<CompetenceProfessionnelleDto> getAllByActiviteTypeId(long id) {
		List<CompetenceProfessionnelle> competenceProfessionnelles = competenceProfessionnelleRepository.findAllByActiviteTypeId(id);
		List<CompetenceProfessionnelleDto> result = new ArrayList<>();
		for (CompetenceProfessionnelle cp : competenceProfessionnelles) {
			result.add(DtoTools.convert(cp, CompetenceProfessionnelleDto.class));
		}
		return result;
	}

}
