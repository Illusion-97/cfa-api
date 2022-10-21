package fr.dawan.AppliCFABack.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.BlocEvaluationDto;
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.EvaluationFormationDto;
import fr.dawan.AppliCFABack.entities.BlocEvaluation;
import fr.dawan.AppliCFABack.entities.EvaluationFormation;
import fr.dawan.AppliCFABack.repositories.BlocEvaluationRepository;
import fr.dawan.AppliCFABack.tools.SaveInvalidException;
/**
 * 
 * @author Feres BG.
 * @see fr.dawan.appliCFABack.services
 * @since 1.0
 * @version 1.0
 */
@Service
@Transactional
public class BlocEvaluationServiceImpl implements BlocEvaluationService  {
@Autowired
private BlocEvaluationRepository blocEvaluationRepository;
	@Override
	public BlocEvaluationDto getById(long id) {
		Optional<BlocEvaluation> blocEvaluation = blocEvaluationRepository.findById(id);
		if (blocEvaluation.isPresent()) {
			return DtoTools.convert(blocEvaluation.get(), BlocEvaluationDto.class);

		}
		return null;
	}

	@Override
	public BlocEvaluationDto saveOrUpdate(BlocEvaluationDto tDto) throws SaveInvalidException {
		BlocEvaluation blocE = DtoTools.convert(tDto, BlocEvaluation.class);
		blocE = blocEvaluationRepository.saveAndFlush(blocE);
		
		return DtoTools.convert(blocE, BlocEvaluationDto.class);
	}

	@Override
	public CountDto count(String search) {
		return new CountDto(blocEvaluationRepository.count());
	}

	@Override
	public void delete(long id) {
		blocEvaluationRepository.deleteById(id);		
	}

}
