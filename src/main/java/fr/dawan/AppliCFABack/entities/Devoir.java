package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/***
 * 
 * @author Feres BG Valentin C.
 * @see Examen
 * @see Intervention
 * @since 1.0
 * @version 1.0
 *
 */
@SuppressWarnings("serial")
@Entity
public class Devoir extends BaseEntity implements Serializable {

	@Column(nullable = false, columnDefinition = "TEXT")
	private String consigne;

	private LocalDate dateDebut;

	private LocalDate dateFin;

	@ManyToOne
	private Intervention intervention;

	/**
	 * @return la consigne
	 */
	public String getConsigne() {
		return consigne;
	}

	/**
	 * @param consigne la consigne à affecter
	 * 
	 */
	public void setConsigne(String consigne) {
		this.consigne = consigne;
	}

	/**
	 * @return la dateDebut
	 */
	public LocalDate getDateDebut() {
		return dateDebut;
	}

	/**
	 * @param dateDebut la dateDebut à affecter
	 * 
	 */
	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * @return la dateFin
	 */
	public LocalDate getDateFin() {
		return dateFin;
	}

	/**
	 * @param dateFin la dateFin à affecter
	 * 
	 */
	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * @return l'intervention
	 */
	public Intervention getIntervention() {
		return intervention;
	}

	/**
	 * @param intervention l'intervention à affecter
	 * 
	 */
	public void setIntervention(Intervention intervention) {
		this.intervention = intervention;
	}

}
