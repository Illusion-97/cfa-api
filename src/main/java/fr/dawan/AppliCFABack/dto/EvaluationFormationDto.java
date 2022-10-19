package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@SuppressWarnings("serial")
public class EvaluationFormationDto  extends BaseEntityDto implements Serializable{

	
	private long livretEvaluationId;

	private long activiteTypeId;

	private String contenu;

	private List<Long> competencesEvaluees;

	private boolean criteresSatisfaits;

	private String commentaireInsatisfaction;

	private String commentaireEvaluationsComplementaires;

	private List<Long> interventions;
	
	private LocalDate dateEvaluation;


	/**
	 * @return le livretEvaluationId
	 */
	public long getLivretEvaluationId() {
		return livretEvaluationId;
	}

	/**
	 * @param livretEvaluationId le livretEvaluationId à affecter
	 
	 */
	public void setLivretEvaluationId(long livretEvaluationId) {
		this.livretEvaluationId = livretEvaluationId;
	}

	/**
	 * @return le activiteTypeId
	 */
	public long getActiviteTypeId() {
		return activiteTypeId;
	}

	/**
	 * @param activiteTypeId le activiteTypeId à affecter
	 
	 */
	public void setActiviteTypeId(long activiteTypeId) {
		this.activiteTypeId = activiteTypeId;
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
	 * @return le criteresSatisfaits
	 */
	public boolean isCriteresSatisfaits() {
		return criteresSatisfaits;
	}

	/**
	 * @param criteresSatisfaits le criteresSatisfaits à affecter
	 
	 */
	public void setCriteresSatisfaits(boolean criteresSatisfaits) {
		this.criteresSatisfaits = criteresSatisfaits;
	}

	/**
	 * @return le commentaireInsatisfaction
	 */
	public String getCommentaireInsatisfaction() {
		return commentaireInsatisfaction;
	}

	/**
	 * @param commentaireInsatisfaction le commentaireInsatisfaction à affecter
	 
	 */
	public void setCommentaireInsatisfaction(String commentaireInsatisfaction) {
		this.commentaireInsatisfaction = commentaireInsatisfaction;
	}

	/**
	 * @return le commentaireEvaluationsComplementaires
	 */
	public String getCommentaireEvaluationsComplementaires() {
		return commentaireEvaluationsComplementaires;
	}

	/**
	 * @param commentaireEvaluationsComplementaires le commentaireEvaluationsComplementaires à affecter
	 
	 */
	public void setCommentaireEvaluationsComplementaires(String commentaireEvaluationsComplementaires) {
		this.commentaireEvaluationsComplementaires = commentaireEvaluationsComplementaires;
	}

	/**
	 * @return le interventions
	 */
	public List<Long> getInterventions() {
		return interventions;
	}

	/**
	 * @param interventions le interventions à affecter
	 
	 */
	public void setInterventions(List<Long> interventions) {
		this.interventions = interventions;
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
