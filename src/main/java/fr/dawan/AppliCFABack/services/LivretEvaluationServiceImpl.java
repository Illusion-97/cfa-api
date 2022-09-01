package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.LivretEvaluationDto;
import fr.dawan.AppliCFABack.entities.LivretEvaluation;
import fr.dawan.AppliCFABack.entities.Validation;
import fr.dawan.AppliCFABack.entities.Validation.Etat;
import fr.dawan.AppliCFABack.repositories.LivretEvaluationRepository;
import fr.dawan.AppliCFABack.repositories.ValidationRepository;

@Service
@Transactional
public class LivretEvaluationServiceImpl implements LivretEvaluationService {

	@Autowired
	private LivretEvaluationRepository livretEvaluationRepository;
	@Autowired 
	private ValidationRepository validationRepository;
	
	@Override
	public LivretEvaluationDto getById(long id) {
		Optional<LivretEvaluation> livretEvalOpt = livretEvaluationRepository.findById(id);
		
		if(livretEvalOpt.isPresent()) {
			LivretEvaluationDto livretEvalDto = DtoTools.convert(livretEvalOpt, LivretEvaluationDto.class);		
			return livretEvalDto;
		}
		return null;
	}

	@Override
	public LivretEvaluationDto saveOrUpdate(LivretEvaluationDto tDto) throws Exception {
		LivretEvaluation livretEval = DtoTools.convert(tDto, LivretEvaluation.class);
		if(tDto.getId() == 0 ) {
			//créer un object Validation et l'entrer dans table Validation 
			Validation validation = new Validation();
			validation.setSignature(null);
			validation.setEtat(Etat.NONTRAITE);
			validation.setVersion(0);
			validation = validationRepository.saveAndFlush(validation);
			livretEval.setValidation(validation);
			LivretEvaluation livretEvalDb = livretEvaluationRepository.saveAndFlush(livretEval);
			return DtoTools.convert(livretEvalDb, LivretEvaluationDto.class);
		} else {
			LivretEvaluation livretEvalDb = livretEvaluationRepository.saveAndFlush(livretEval);
			return DtoTools.convert(livretEvalDb, LivretEvaluationDto.class);
		}
		
	}

	@Override
	public CountDto count(String search) {
		long nb = livretEvaluationRepository.count();
		CountDto d = new CountDto();
		d.setNb(nb);
		return d;
	}

	@Override
	public void delete(long id) {
		livretEvaluationRepository.deleteById(id);
		
	}

	@Override
	public List<LivretEvaluationDto> getByEtudiantId(long id) {
		List<LivretEvaluation> livrets = livretEvaluationRepository.findLivretEvaluationByEtudiantId(id);
		List<LivretEvaluationDto> result = new ArrayList<LivretEvaluationDto>();
		for(LivretEvaluation l: livrets) {
			result.add(DtoTools.convert(l, LivretEvaluationDto.class));
		}
		return result;
	}

}
