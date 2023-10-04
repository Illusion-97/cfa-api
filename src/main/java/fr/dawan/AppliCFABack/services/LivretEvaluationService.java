package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.LivretEvaluationDto;
import fr.dawan.AppliCFABack.dto.customdtos.EtudiantLivretEvaluationDto;
import fr.dawan.AppliCFABack.tools.LivretEvaluationException;
import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import javassist.NotFoundException;

import java.io.IOException;
import java.util.List;

public interface LivretEvaluationService extends GenericService<LivretEvaluationDto>{

    void notificationMail(LivretEvaluationDto note) throws NotFoundException;

    //une liste car si étudiant fait plusieurs formations
	List<LivretEvaluationDto> getByEtudiantId(long id);
	
	LivretEvaluationDto getByIdEtudiantAndIdCurcus(long idEtudiant, long idCursus);

	String getLivretEvaluation(long idEtudiant , long idCursus) throws LivretEvaluationException, TemplateNotFoundException,
			MalformedTemplateNameException, ParseException, IOException, TemplateException;

	List<EtudiantLivretEvaluationDto> getLivretEtudiant(long id);
}
