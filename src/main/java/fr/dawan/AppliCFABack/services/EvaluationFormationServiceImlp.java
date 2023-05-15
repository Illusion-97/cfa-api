package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.EvaluationFormationDto;
import fr.dawan.AppliCFABack.entities.BlocEvaluation;
import fr.dawan.AppliCFABack.entities.CompetenceProfessionnelle;
import fr.dawan.AppliCFABack.entities.EvaluationFormation;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.BlocEvaluationRepository;
import fr.dawan.AppliCFABack.repositories.CompetenceProfessionnelleRepository;
import fr.dawan.AppliCFABack.repositories.EvaluationFormationRepository;
import fr.dawan.AppliCFABack.repositories.InterventionRepository;
import fr.dawan.AppliCFABack.tools.SaveInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class EvaluationFormationServiceImlp implements EvaluationFormationService {

	@Autowired
	EvaluationFormationRepository evaluationFormationRepository;

	@Autowired
	CompetenceProfessionnelleRepository competenceProfessionnelleRepository;
	
	@Autowired
	BlocEvaluationRepository blocEvaluationRepository;
	
	@Autowired
	InterventionRepository interventionRepository;
	
	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();
	
	@Override
	public EvaluationFormationDto getById(long id) {
		Optional<EvaluationFormation> evaluationF = evaluationFormationRepository.findById(id);
		if (evaluationF.isPresent()) {
			
			EvaluationFormationDto evaluationFormationDto = DtoTools.convert(evaluationF.get(), EvaluationFormationDto.class);
			
			List<Long> competencesEvalueesIds = evaluationF.get().getCompetencesEvaluees().stream()
					.map(CompetenceProfessionnelle::getId).collect(Collectors.toList());
			
			evaluationFormationDto.setCompetencesEvalueesId(competencesEvalueesIds);
			
			return evaluationFormationDto;

		}
		return null;
	}

	@Override
	public EvaluationFormationDto saveOrUpdate(EvaluationFormationDto tDto) throws SaveInvalidException {
		
		
			tDto.getCompetencesEvalueesId();
			List<CompetenceProfessionnelle> compEvaluee = new ArrayList<>();
			for (long competenceId : tDto.getCompetencesEvalueesId()) {
				CompetenceProfessionnelle comp = competenceProfessionnelleRepository.getOne(competenceId);
				compEvaluee.add(comp);						
		}
		EvaluationFormation evaluationF = DtoTools.convert(tDto, EvaluationFormation.class);
		evaluationF.setCompetencesEvaluees(compEvaluee);
	
		EvaluationFormation evaluationFDb = evaluationFormationRepository.saveAndFlush(evaluationF);

		return DtoTools.convert(evaluationFDb, EvaluationFormationDto.class);
	}
	
	public EvaluationFormationDto update(EvaluationFormationDto evaluationFormationDto) throws SaveInvalidException {
	    // Vérifiez si l'ID de l'évaluation de formation est égal à zéro
	    if (evaluationFormationDto.getId() == 0L) {
	        // Nouvelle évaluation de formation, effectuez l'ajout
	        return saveOrUpdate(evaluationFormationDto);
	    } else {
	        // Évaluation de formation existante, effectuez la mise à jour
	        // Recherchez l'évaluation de formation existante dans la base de données
	        EvaluationFormation evaluationFormation = evaluationFormationRepository.findById(evaluationFormationDto.getId())
	                .orElseThrow(() -> new EntityNotFoundException("L'évaluation de formation n'a pas été trouvée."));

	        //  mise à jour sur l'entité en utilisant les valeurs du DTO
	        evaluationFormation.setContenu(evaluationFormationDto.getContenu());
	        evaluationFormation.setDateEvaluation(evaluationFormationDto.getDateEvaluation());
	        evaluationFormation.setIntervention(interventionRepository.findById(evaluationFormationDto.getInterventionId())
	                .orElseThrow(() -> new EntityNotFoundException("L'intervention n'a pas été trouvée.")));

	        // Mise à jour des compétences évaluées en utilisant le mapper et les IDs du DTO
	        List<Long> competencesEvalueesIds = evaluationFormationDto.getCompetencesEvalueesId();
	        List<CompetenceProfessionnelle> competencesEvaluees = new ArrayList<>();
	        for (Long competenceId : competencesEvalueesIds) {
	            CompetenceProfessionnelle competence = competenceProfessionnelleRepository.findById(competenceId)
	                    .orElseThrow(() -> new EntityNotFoundException("La compétence professionnelle n'a pas été trouvée."));
	            competencesEvaluees.add(competence);
	        }
	        evaluationFormation.setCompetencesEvaluees(competencesEvaluees);

	        // Enregistrez les modifications dans la base de données
	        EvaluationFormation updatedEvaluationFormation = evaluationFormationRepository.save(evaluationFormation);

	        // Retournez le DTO correspondant à l'évaluation de formation mise à jour
	        return DtoMapper.INSTANCE.toEvaluationFormationDto(updatedEvaluationFormation);
	    }
	}



	@Override
	public CountDto count(String search) {
		return new CountDto(evaluationFormationRepository.count());
	}

	@Override
	public void delete(long id) {
	 	EvaluationFormation evaluationFormation = evaluationFormationRepository.getOne(id);
	 	evaluationFormation.setCompetencesEvaluees(null);
	 	List<BlocEvaluation> blocEvaluations = blocEvaluationRepository.finAllByEvaluationsFormationsId(evaluationFormation.getId());
	 	for (BlocEvaluation blocEvaluation : blocEvaluations) {
			blocEvaluation.getEvaluationsFormations().remove(evaluationFormation);
			blocEvaluationRepository.saveAndFlush(blocEvaluation);
		}
		evaluationFormationRepository.delete(evaluationFormation);
	}

	@Override
	public List<EvaluationFormationDto> getByPromotionIdAndActiviteTypeId(long promotionId, long activiteTypeId) {
		List<EvaluationFormation> evaluationFormations = evaluationFormationRepository.getByPrmotionIdAndActiviteTypeId(promotionId,activiteTypeId);
		List<EvaluationFormationDto> result = new ArrayList<>();

		for (EvaluationFormation evaluationFormation : evaluationFormations) {
			result.add(DtoTools.convert(evaluationFormation, EvaluationFormationDto.class));
		}
		
		return result;
	}

	@Override
	public List<EvaluationFormationDto> getByInterventionId(long idIntervention) {
		List<EvaluationFormation> evaluationFormations = evaluationFormationRepository.findAllByInterventionId(idIntervention);
		List<EvaluationFormationDto> result = new ArrayList<>();

		for (EvaluationFormation evaluationFormation : evaluationFormations) {
			result.add(DtoTools.convert(evaluationFormation, EvaluationFormationDto.class));
		}
		
		return result;
	}

}
