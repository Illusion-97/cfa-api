package fr.dawan.AppliCFABack.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/***
 * 
 * @author Feres BG Valentin C.
 * @see Signature,LivretEvaluation
 * @see Cursus
 * @since 1.0
 * @version 1.0
 *
 */
@SuppressWarnings("serial")
@Entity
public class Validation extends BaseEntity implements Serializable {

	@Enumerated(EnumType.STRING)
	private Etat etat;

	@ManyToOne
	private Signature signature;

//	@OneToOne(mappedBy = "validation")
//	private LivretEvaluation livretEvaluation;

	public enum Etat {
		REFUSE, NONVALIDE, NONTRAITE, ENATTENTEDEVALIDATION, VALIDE,
	}

	/**
	 * @return le etat
	 */
	public Etat getEtat() {
		return etat;
	}

	/**
	 * @param etat le etat à affecter
	 * 
	 */
	public void setEtat(Etat etat) {
		this.etat = etat;
	}

	/**
	 * @return le signature
	 */
	public Signature getSignature() {
		return signature;
	}

	/**
	 * @param signature le signature à affecter
	 * 
	 */
	public void setSignature(Signature signature) {
		this.signature = signature;
	}

	/**
	 * @return le livretEvaluation
	 */


}
