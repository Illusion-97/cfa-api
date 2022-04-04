package fr.dawan.AppliCFABack.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import fr.dawan.AppliCFABack.entities.Note.Satisfaction;


@Entity
public class Note {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, precision = 4, scale = 2)
	private double noteObtenue;
	
	@Column
	@Enumerated(EnumType.STRING)
	private  Satisfaction satisfaction;
	
	@ManyToOne
	private Examen examen;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Etudiant etudiantNote;

	public enum Satisfaction {
			OUI,NON
	}
//	@Column(nullable = false, length = 255)
//	private int noteObtenu;
//
//	@Column(nullable = false, length = 255)
//	private String observations;
//
//	@ManyToOne
//	private Etudiant etudiant;
//
//
//	@OneToOne
//	private PassageExamen examen;
//
//	@OneToOne
//	private Devoir devoir;

	public Etudiant getEtudiantNote() {
		return etudiantNote;
	}

	public void setEtudiantNote(Etudiant etudiantNote) {
		this.etudiantNote = etudiantNote;
	}

	public Note() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
	
	

//	public Note(int noteObtenu, String observations, Etudiant etudiant, PassageExamen examen, Devoir devoir) {
//		super();
//		this.noteObtenu = noteObtenu;
//		this.observations = observations;
//		this.etudiant = etudiant;
//		this.examen = examen;
//		this.devoir = devoir;
//	}
//
//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}
//
//	public int getNoteObtenu() {
//		return noteObtenu;
//	}
//
//	public void setNoteObtenu(int noteObtenu) {
//		this.noteObtenu = noteObtenu;
//	}
//
//	public String getObservations() {
//		return observations;
//	}
//
//	public void setObservations(String observations) {
//		this.observations = observations;
//	}
//
//	public Etudiant getEtudiant() {
//		return etudiant;
//	}
//
//	public void setEtudiant(Etudiant etudiant) {
//		this.etudiant = etudiant;
//	}
//
//	public PassageExamen getExamen() {
//		return examen;
//	}
//
//	
//	public void setExamen(PassageExamen examen) {
//		this.examen = examen;
//	}
//
//	public Devoir getDevoir() {
//		return devoir;
//	}
//
//	public void setDevoir(Devoir devoir) {
//		this.devoir = devoir;
//	}

}
