package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;
import java.time.LocalDate;
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
@SuppressWarnings({ "serial", "unused" })
@Entity
public class EvaluationFormation extends BaseEntity implements Serializable {

	
	@Column(columnDefinition = "TEXT", nullable = false)

	private String contenu;

	@ManyToMany
	private List<CompetenceProfessionnelle> competencesEvaluees;

	
	private LocalDate dateEvaluation;
	
	@ManyToOne
	private Intervention intervention; 
	
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
	 * @return la dateEvaluation
	 */
	public LocalDate getDateEvaluation() {
		return dateEvaluation;
	}

	/**
	 * @param dateEvaluation la dateEvaluation à affecter
	 
	 */
	public void setDateEvaluation(LocalDate dateEvaluation) {
		this.dateEvaluation = dateEvaluation;
	}

	/**
	 * @return l'intervention
	 */
	public Intervention getIntervention() {
		return intervention;
	}

	/**
	 * @param intervention l'intervention à affecter
	 
	 */
	public void setIntervention(Intervention intervention) {
		this.intervention = intervention;
	}
	
	

}
