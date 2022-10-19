package fr.dawan.AppliCFABack.services;

import java.io.IOException;
import java.util.List;

import fr.dawan.AppliCFABack.dto.LivretEvaluationDto;
import fr.dawan.AppliCFABack.tools.LivretEvaluationException;
import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public interface LivretEvaluationService extends GenericService<LivretEvaluationDto>{

	//une liste car si étudiant fait plusieurs formations
	List<LivretEvaluationDto> getByEtudiantId(long id);

	String getLivretEvaluation(long idEtudiant , long idCursus) throws LivretEvaluationException, TemplateNotFoundException,
			MalformedTemplateNameException, ParseException, IOException, TemplateException;
}
