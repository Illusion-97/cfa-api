package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@SuppressWarnings("serial")
public class EvaluationFormationDto  extends BaseEntityDto implements Serializable{

	private String contenu;

	private List<Long> competencesEvalueesId;

	private LocalDate dateEvaluation;

	private long interventionId;
	
	/**
	 * @return le contenu
	 */
	public String getContenu() {
		return contenu;
	}

	/**
	 * @param contenu le contenu à affecter
	 
	 */
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	/**
	 * @return le competencesEvaluees
	 */
	public List<Long> getCompetencesEvalueesId() {
		return competencesEvalueesId;
	}

	/**
	 * @param competencesEvaluees le competencesEvaluees à affecter
	 
	 */
	public void setCompetencesEvalueesId(List<Long> competencesEvalueesId) {
		this.competencesEvalueesId = competencesEvalueesId;
	}


	/**
	 * @return le dateEvaluation
	 */
	public LocalDate getDateEvaluation() {
		return dateEvaluation;
	}

	/**
	 * @param dateEvaluation le dateEvaluation à affecter
	 
	 */
	public void setDateEvaluation(LocalDate dateEvaluation) {
		this.dateEvaluation = dateEvaluation;
	}

	/**
	 * @return le interventionId
	 */
	public long getInterventionId() {
		return interventionId;
	}

	/**
	 * @param interventionId le interventionId à affecter
	 
	 */
	public void setInterventionId(long interventionId) {
		this.interventionId = interventionId;
	}
	
	
	
}
