package fr.dawan.AppliCFABack.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/***
 * 
 * @author Feres BG Valentin C.
 * @see Cursus,CentreFormation,Etudiant,Formateur
 * @since 1.0
 * @version 1.0
 */

@SuppressWarnings({ "serial", "unused" })
@Entity
public class LivretEvaluation extends BaseEntity implements Serializable {

	@ManyToOne
	private Etudiant etudiant;

	@ManyToOne
	private Cursus titreProfessionnel;

	@ManyToOne
	private CentreFormation organismeFormation;

//	@OneToOne(cascade = CascadeType.REMOVE)
//	private Validation validation;
	
	@Enumerated(EnumType.STRING)
	private EtatLivertEval etat;
	
	@Column(columnDefinition = "TEXT DEFAULT 'Cliquez ici pour taper du texte.'")
	private String observation;

	private LocalDate dateSignature;

	public enum EtatLivertEval {
		 ENATTENTEDEVALIDATION, VALIDEPARLEFORMATEUR,VALIDEPARLETUDIANT
	}
	/**
	 * @return l' etudiant
	 */
	public Etudiant getEtudiant() {
		return etudiant;
	}

	/**
	 * @param etudiant l' etudiant à affecter
	 * 
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
	 * 
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
	 * 
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
	 * 
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}

	/**
	 * @return le dateSignature
	 */
	public LocalDate getDateSignature() {
		return dateSignature;
	}

	/**
	 * @param dateSignature le dateSignature à affecter
	 * 
	 */
	public void setDateSignature(LocalDate dateSignature) {
		this.dateSignature = dateSignature;
	}


	/**
	 * @return le etat
	 */
	public EtatLivertEval getEtat() {
		return etat;
	}

	/**
	 * @param etat le etat à affecter
	 
	 */
	public void setEtat(EtatLivertEval etat) {
		this.etat = etat;
	}
	

}
