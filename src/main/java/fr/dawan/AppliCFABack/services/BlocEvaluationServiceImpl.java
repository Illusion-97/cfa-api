package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.BlocEvaluationDto;
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.entities.BlocEvaluation;
import fr.dawan.AppliCFABack.entities.EvaluationFormation;
import fr.dawan.AppliCFABack.repositories.BlocEvaluationRepository;
import fr.dawan.AppliCFABack.repositories.EvaluationFormationRepository;
import fr.dawan.AppliCFABack.tools.SaveInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

	@Autowired
	private EvaluationFormationRepository evaluationFormationRepository;

	@Override
	public BlocEvaluationDto getById(long id) {
	    return blocEvaluationRepository.findById(id)
	            .map(blocEvaluation -> DtoTools.convert(blocEvaluation, BlocEvaluationDto.class))
	            .orElse(null);
	}

	@Override
	public BlocEvaluationDto saveOrUpdate(BlocEvaluationDto tDto) throws SaveInvalidException {

		List<EvaluationFormation> evaluationFormations = new ArrayList<>();
		for (Long id : tDto.getEvaluationsFormationsId()) {
			EvaluationFormation evaF = 	 evaluationFormationRepository.getOne(id);
			evaluationFormations.add(evaF);
		}

		BlocEvaluation blocE = DtoTools.convert(tDto, BlocEvaluation.class);
		blocE.setEvaluationFormations(evaluationFormations);
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

	@Override
	public BlocEvaluationDto finddByActiviteTypeIdAndlivretEvaluationId(long idAt, long idLivretEval) {
		Optional<BlocEvaluation> blocEvaluation = blocEvaluationRepository.findByActiviteTypeIdAndLivretEvaluationId(idAt,idLivretEval);
		if (blocEvaluation.isPresent()) {
			return DtoTools.convert(blocEvaluation.get(), BlocEvaluationDto.class);
		}
		return null;
	}

}
