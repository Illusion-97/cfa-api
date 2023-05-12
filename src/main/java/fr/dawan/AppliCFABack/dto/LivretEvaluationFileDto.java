package fr.dawan.AppliCFABack.dto;

import fr.dawan.AppliCFABack.dto.customdtos.EvaluationDto;
import fr.dawan.AppliCFABack.entities.BlocEvaluation;
import fr.dawan.AppliCFABack.entities.Cursus;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.LivretEvaluation;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * @author Feres BG.
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 */
@SuppressWarnings("serial")
public class LivretEvaluationFileDto implements Serializable {

	private LivretEvaluation livretEvaluation;
	
	private Cursus cursus;
	
	private Etudiant etudiant;

	private List<EvaluationDto> evaluations;
	
	private List<BlocEvaluation> formateursEvaluateurs;
	
	
	/**
	 * @return le formateurEvaluateurs
	 */
	public List<BlocEvaluation> getFormateursEvaluateurs() {
		return formateursEvaluateurs;
	}

	/**
	 * @param formateurEvaluateurs le formateurEvaluateurs à affecter
	 
	 */
	public void setFormateursEvaluateurs(List<BlocEvaluation> formateursEvaluateurs) {
		this.formateursEvaluateurs = formateursEvaluateurs;
	}

	/**
	 * @return le livretEvaluation
	 */
	public LivretEvaluation getLivretEvaluation() {
		return livretEvaluation;
	}

	/**
	 * @param livretEvaluation le livretEvaluation à affecter
	 
	 */
	public void setLivretEvaluation(LivretEvaluation livretEvaluation) {
		this.livretEvaluation = livretEvaluation;
	}

	/**
	 * @return le cursus
	 */
	public Cursus getCursus() {
		return cursus;
	}

	/**
	 * @param cursus le cursus à affecter
	 
	 */
	public void setCursus(Cursus cursus) {
		this.cursus = cursus;
	}

	/**
	 * @return le etudiant
	 */
	public Etudiant getEtudiant() {
		return etudiant;
	}

	/**
	 * @param etudiant le etudiant à affecter
	 
	 */
	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	/**
	 * @return le evaluations
	 */
	public List<EvaluationDto> getEvaluations() {
		return evaluations;
	}

	/**
	 * @param evaluations le evaluations à affecter
	 
	 */
	public void setEvaluations(List<EvaluationDto> evaluations) {
		this.evaluations = evaluations;
	}



	
}
