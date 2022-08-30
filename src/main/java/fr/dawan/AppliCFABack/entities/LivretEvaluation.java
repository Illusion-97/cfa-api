package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import fr.dawan.AppliCFABack.entities.Validation.Etat;


/***
 * 
 * @author Feres BG Valentin C.
 * @see Cursus,CentreFormation,Etudiant,Formateur
 * @see CompetenceProfessionnelle
 * @see Cursus
 * @since 1.0
 * @version 1.0
 *
 */

@SuppressWarnings("serial")
@Entity
public class LivretEvaluation extends BaseEntity implements Serializable {

	@ManyToOne
	private Etudiant etudiant;

	@ManyToOne
	private Cursus titreProfessionnel;

	@ManyToOne
	private CentreFormation organismeFormation;

	@OneToOne(cascade = CascadeType.REMOVE)
	private Validation validation;
	
	@Column(columnDefinition = "TEXT", nullable = false)
	
	private String observation;

	@ManyToOne
	private Formateur formateurEvaluateur;

	private LocalDate dateSignature;

	/**
	 * @return l' etudiant
	 */
	public Etudiant getEtudiant() {
		return etudiant;
	}

	/**
	 * @param etudiant l' etudiant à affecter
	 
	 */
	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	/**
	 * @return le titreProfessionnel
	 */
	public Cursus getTitreProfessionnel() {
		return titreProfessionnel;
	}

	/**
	 * @param titreProfessionnel le titreProfessionnel à affecter
	 
	 */
	public void setTitreProfessionnel(Cursus titreProfessionnel) {
		this.titreProfessionnel = titreProfessionnel;
	}

	/**
	 * @return l' organismeFormation
	 */
	public CentreFormation getOrganismeFormation() {
		return organismeFormation;
	}

	/**
	 * @param organismeFormation l' organismeFormation à affecter
	 
	 */
	public void setOrganismeFormation(CentreFormation organismeFormation) {
		this.organismeFormation = organismeFormation;
	}

	/**
	 * @return le observations
	 */
	public String getObservation() {
		return observation;
	}

	/**
	 * @param observations le observations à affecter
	 
	 */
	public void setObservation(String observation) {
		this.observation = observation;
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
	 * @return le validation
	 */
	public Validation getValidation() {
		return validation;
	}

	/**
	 * @param validation le validation à affecter
	 
	 */
	public void setValidation(Validation validation) {
		this.validation = validation;
	}


}
