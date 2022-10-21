package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@SuppressWarnings("serial")
public class EvaluationFormationDto  extends BaseEntityDto implements Serializable{

	
	private long blocEvaluationId;


	private String contenu;

	private List<Long> competencesEvaluees;

	private LocalDate dateEvaluation;


	/**
	 * @return le blocEvaluationId
	 */
	public long getBlocEvaluationId() {
		return blocEvaluationId;
	}

	/**
	 * @param blocEvaluationId le blocEvaluationId à affecter
	 
	 */
	public void setBlocEvaluationId(long blocEvaluationId) {
		this.blocEvaluationId = blocEvaluationId;
	}

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
	public List<Long> getCompetencesEvaluees() {
		return competencesEvaluees;
	}

	/**
	 * @param competencesEvaluees le competencesEvaluees à affecter
	 
	 */
	public void setCompetencesEvaluees(List<Long> competencesEvaluees) {
		this.competencesEvaluees = competencesEvaluees;
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
	
	
	
}
