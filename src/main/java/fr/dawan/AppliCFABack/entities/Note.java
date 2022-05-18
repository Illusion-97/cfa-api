package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/***
 * 
 * @author Feres BG Valentin C.
 * @see Examen
 * @see Etudiant
 * @since 1.0
 * @version 1.0
 *
 */
@SuppressWarnings("serial")
@Entity
public class Note extends BaseEntity implements Serializable {

	@Column(nullable = false, precision = 4, scale = 2)
	private double noteObtenue;

	@Column
	@Enumerated(EnumType.STRING)
	private Satisfaction satisfaction;

	@ManyToOne
	private Examen examen;

	@ManyToOne(fetch = FetchType.EAGER)
	private Etudiant etudiantNote;

	public enum Satisfaction {
		OUI, NON
	}

	@Column(columnDefinition = "TEXT")
	private String observation;

	/**
	 * @return la noteObtenue
	 */
	public double getNoteObtenue() {
		return noteObtenue;
	}

	/**
	 * @param noteObtenue la noteObtenue à affecter
	 * 
	 */
	public void setNoteObtenue(double noteObtenue) {
		this.noteObtenue = noteObtenue;
	}

	/**
	 * @return le satisfaction
	 */
	public Satisfaction getSatisfaction() {
		return satisfaction;
	}

	/**
	 * @param satisfaction le satisfaction à affecter
	 * 
	 */
	public void setSatisfaction(Satisfaction satisfaction) {
		this.satisfaction = satisfaction;
	}

	/**
	 * @return le examen
	 */
	public Examen getExamen() {
		return examen;
	}

	/**
	 * @param examen l'examen à affecter
	 * 
	 */
	public void setExamen(Examen examen) {
		this.examen = examen;
	}

	/**
	 * @return l'etudiantNote
	 */
	public Etudiant getEtudiantNote() {
		return etudiantNote;
	}

	/**
	 * @param etudiantNote l'etudiantNote à affecter
	 * 
	 */
	public void setEtudiantNote(Etudiant etudiantNote) {
		this.etudiantNote = etudiantNote;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}
}
