package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.ExamenDto;
import fr.dawan.AppliCFABack.dto.ExamenDtoSave;
import fr.dawan.AppliCFABack.dto.customdtos.DoubleDto;
import fr.dawan.AppliCFABack.dto.customdtos.LivretEvaluationDto;
import fr.dawan.AppliCFABack.tools.SaveInvalidException;

public interface ExamenService {

	List<ExamenDto> getAllExamen();

	List<ExamenDto> getAllByPage(int page, int size, String string);

	CountDto count(String string);

	ExamenDto getById(long id);

	ExamenDtoSave saveOrUpdate(ExamenDtoSave eDto) throws SaveInvalidException;

	void deleteById(long id);
	
	List<ExamenDto> findExamensByInterventionId(long id);

	/**
	 * @param id de l'étudiant
	 * @return toutes les données nécessaires pour remplir la section livret d'évaluation dans le front partie étudiant, par le service implémenté
	 */
    List<LivretEvaluationDto> getLivretEvaluation(long id);

	String generateBulletinPdfByStudentAndPromo(long etudiantId, long promotionId) throws Exception;

	DoubleDto getAvgByEtudiantIdAndActiviteTypeId(long etudiantId, long activiteTypeId) throws Exception;

	DoubleDto getAvgByPromoIdAndActiviteTypeId(long promotionId, long activiteTypeId) throws Exception;

	DoubleDto getAvgByPromotionId(long promotionId) throws Exception;
}
