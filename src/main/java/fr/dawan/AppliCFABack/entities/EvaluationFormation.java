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

	@ManyToOne
	private BlocEvaluation blocEvaluation;
	
	@Column(columnDefinition = "TEXT", nullable = false)

	private String contenu;

	@ManyToMany
	private List<CompetenceProfessionnelle> competencesEvaluees;

	
	private LocalDate dateEvaluation;
	
	
	/**
	 * @return le blocEvaluation
	 */
	public BlocEvaluation getBlocEvaluation() {
		return blocEvaluation;
	}

	/**
	 * @param blocEvaluation le blocEvaluation à affecter
	 
	 */
	public void setBlocEvaluation(BlocEvaluation blocEvaluation) {
		this.blocEvaluation = blocEvaluation;
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
