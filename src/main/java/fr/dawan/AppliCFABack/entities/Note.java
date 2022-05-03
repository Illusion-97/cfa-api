package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

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

	public Etudiant getEtudiantNote() {
		return etudiantNote;
	}

	public void setEtudiantNote(Etudiant etudiantNote) {
		this.etudiantNote = etudiantNote;
	}

	public Note() {
		super();
	}

	public double getNoteObtenue() {
		return noteObtenue;
	}

	public void setNoteObtenue(double noteObtenue) {
		this.noteObtenue = noteObtenue;
	}

	public Satisfaction getSatisfaction() {
		return satisfaction;
	}

	public void setSatisfaction(Satisfaction satisfaction) {
		this.satisfaction = satisfaction;
	}

	public Examen getExamen() {
		return examen;
	}

	public void setExamen(Examen examen) {
		this.examen = examen;
	}

}
