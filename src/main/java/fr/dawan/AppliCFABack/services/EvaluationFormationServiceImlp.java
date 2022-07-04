package fr.dawan.AppliCFABack.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.EvaluationFormationDto;
import fr.dawan.AppliCFABack.entities.EvaluationFormation;
import fr.dawan.AppliCFABack.repositories.EvaluationFormationRepository;

@Service
@Transactional
public class EvaluationFormationServiceImlp implements EvaluationFormationService {

	@Autowired
	EvaluationFormationRepository evaluationFormationRepository;

	@Override
	public EvaluationFormationDto getById(long id) {
		Optional<EvaluationFormation> evaluationF = evaluationFormationRepository.findById(id);
		if (evaluationF.isPresent()) {
			return DtoTools.convert(evaluationF.get(), EvaluationFormationDto.class);

		}
		return null;
	}

	@Override
	public EvaluationFormationDto saveOrUpdate(EvaluationFormationDto tDto) throws Exception {
		EvaluationFormation evaluationF = DtoTools.convert(tDto, EvaluationFormation.class);

		EvaluationFormation evaluationFDb = evaluationFormationRepository.saveAndFlush(evaluationF);

		return DtoTools.convert(evaluationFDb, EvaluationFormationDto.class);
	}

	@Override
	public CountDto count(String search) {
		return new CountDto(evaluationFormationRepository.count());
	}

	@Override
	public void delete(long id) {
		evaluationFormationRepository.deleteById(id);
	}

}
