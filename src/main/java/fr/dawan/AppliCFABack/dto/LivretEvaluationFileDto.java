package fr.dawan.AppliCFABack.dto;

import java.util.List;
import java.util.Map;

import fr.dawan.AppliCFABack.entities.ActiviteType;
import fr.dawan.AppliCFABack.entities.Cursus;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.EvaluationFormation;
import fr.dawan.AppliCFABack.entities.LivretEvaluation;

public class LivretEvaluationFileDto {

	private LivretEvaluation livretEvaluation;
	
	private Cursus cursus;
	
	private Etudiant etudiant;
	
	private Map<ActiviteType,List<EvaluationFormation>>evaluations;

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
	 * @return les evaluations
	 */
	public Map<ActiviteType, List<EvaluationFormation>> getEvaluations() {
		return evaluations;
	}

	/**
	 * @param evaluations le evaluations à affecter
	 
	 */
	public void setEvaluations(Map<ActiviteType, List<EvaluationFormation>> evaluations) {
		this.evaluations = evaluations;
	}

	
}
