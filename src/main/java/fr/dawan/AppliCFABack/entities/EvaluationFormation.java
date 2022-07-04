package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
/***
 * 
 * @author Feres BG Valentin C.
 * @see LivretEvaluation,ActiviteType,CompetenceProfessionnelle,Intervention
 * @since 1.0
 * @version 1.0
 */
@SuppressWarnings("serial")
@Entity
public class EvaluationFormation extends BaseEntity implements Serializable {

	@ManyToOne
	private LivretEvaluation livretEvaluation;

	@ManyToOne
	private ActiviteType activiteType;

	@Column(columnDefinition = "TEXT", nullable = false)

	private String contenu;

	@ManyToMany
	private List<CompetenceProfessionnelle> competencesEvaluees;

	private boolean criteresSatisfaits;

	@Column(columnDefinition = "TEXT")

	private String commentaireInsatisfaction;

	@Column(columnDefinition = "TEXT")

	private String commentaireEvaluationsComplementaires;

	@ManyToMany
	private List<Intervention> interventions;

	/**
	 * @return l' livretEvaluation
	 */
	public LivretEvaluation getLivretEvaluation() {
		return livretEvaluation;
	}

	/**
	 * @param livretEvaluation l' livretEvaluation à affecter
	 
	 */
	public void setLivretEvaluation(LivretEvaluation livretEvaluation) {
		this.livretEvaluation = livretEvaluation;
	}

	/**
	 * @return le activiteType
	 */
	public ActiviteType getActiviteType() {
		return activiteType;
	}

	/**
	 * @param activiteType le activiteType à affecter
	 
	 */
	public void setActiviteType(ActiviteType activiteType) {
		this.activiteType = activiteType;
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
	public List<CompetenceProfessionnelle> getCompetencesEvaluees() {
		return competencesEvaluees;
	}

	/**
	 * @param competencesEvaluees le competencesEvaluees à affecter
	 
	 */
	public void setCompetencesEvaluees(List<CompetenceProfessionnelle> competencesEvaluees) {
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
	public List<Intervention> getInterventions() {
		return interventions;
	}

	/**
	 * @param interventions le interventions à affecter
	 
	 */
	public void setInterventions(List<Intervention> interventions) {
		this.interventions = interventions;
	}
	


}
