package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
/**
 * 
 * @author Feres BG.
 * @see fr.dawan.appliCFABack.entities
 * @since 1.0
 * @version 1.0
 */
@SuppressWarnings({ "serial", "unused" })
@Entity
public class BlocEvaluation extends BaseEntity implements Serializable {
	
	@ManyToMany
	private List<EvaluationFormation> evaluationsFormations;
	@ManyToOne
	private LivretEvaluation livretEvaluation;

	@ManyToOne
	private ActiviteType activiteType;

	private boolean criteresSatisfaits;

	@Column(columnDefinition = "TEXT")

	private String commentaireInsatisfaction;

	@Column(columnDefinition = "TEXT")

	private String commentaireEvaluationsComplementaires;
	
	@ManyToOne
	private Formateur formateurEvaluateur;
	
	private LocalDate dateSignature;
	
	
	/**
	 * @return le evaluationFormations
	 */
	public List<EvaluationFormation> getEvaluationsFormations() {
		return evaluationsFormations;
	}

	/**
	 * @param evaluationFormations le evaluationFormations à affecter
	 
	 */
	public void setEvaluationFormations(List<EvaluationFormation> evaluationsFormations) {
		this.evaluationsFormations = evaluationsFormations;
	}

	/**
	 * @return le dateSignature
	 */
	public LocalDate getDateSignature() {
		return dateSignature;
	}

	/**
	 * @param dateSignature le dateSignature à affecter
	 
	 */
	public void setDateSignature(LocalDate dateSignature) {
		this.dateSignature = dateSignature;
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
	 * @return le formateurEvaluateur
	 */
	public Formateur getFormateurEvaluateur() {
		return formateurEvaluateur;
	}

	/**
	 * @param formateurEvaluateur le formateurEvaluateur à affecter
	 
	 */
	public void setFormateurEvaluateur(Formateur formateurEvaluateur) {
		this.formateurEvaluateur = formateurEvaluateur;
	}



}
