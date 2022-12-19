package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.EvaluationFormationDto;
import fr.dawan.AppliCFABack.entities.ActiviteType;
import fr.dawan.AppliCFABack.entities.BlocEvaluation;
import fr.dawan.AppliCFABack.entities.CompetenceProfessionnelle;
import fr.dawan.AppliCFABack.entities.EvaluationFormation;
import fr.dawan.AppliCFABack.repositories.BlocEvaluationRepository;
import fr.dawan.AppliCFABack.repositories.CompetenceProfessionnelleRepository;
import fr.dawan.AppliCFABack.repositories.EvaluationFormationRepository;
import fr.dawan.AppliCFABack.tools.SaveInvalidException;

@Service
@Transactional
public class EvaluationFormationServiceImlp implements EvaluationFormationService {

	@Autowired
	EvaluationFormationRepository evaluationFormationRepository;

	@Autowired
	CompetenceProfessionnelleRepository competenceProfessionnelleRepository;
	
	@Autowired
	BlocEvaluationRepository blocEvaluationRepository;
	
	@Override
	public EvaluationFormationDto getById(long id) {
		Optional<EvaluationFormation> evaluationF = evaluationFormationRepository.findById(id);
		if (evaluationF.isPresent()) {
			return DtoTools.convert(evaluationF.get(), EvaluationFormationDto.class);

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
