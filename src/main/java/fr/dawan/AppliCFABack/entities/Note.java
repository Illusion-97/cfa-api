package fr.dawan.AppliCFABack.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Note {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, length = 255)
	private int noteObtenu;

	@Column(nullable = false, length = 255)
	private String observations;

	@ManyToOne
	private Etudiant etudiant;


	@OneToOne
	private PassageExamen examen;

	@OneToOne
	private Devoir devoir;

	public Note() {
		super();
	}
	
	

	public Note(int noteObtenu, String observations, Etudiant etudiant, PassageExamen examen, Devoir devoir) {
		super();
		this.noteObtenu = noteObtenu;
		this.observations = observations;
		this.etudiant = etudiant;
		this.examen = examen;
		this.devoir = devoir;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNoteObtenu() {
		return noteObtenu;
	}

	public void setNoteObtenu(int noteObtenu) {
		this.noteObtenu = noteObtenu;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	public PassageExamen getExamen() {
		return examen;
	}

	
	public void setExamen(PassageExamen examen) {
		this.examen = examen;
	}

	public Devoir getDevoir() {
		return devoir;
	}

	public void setDevoir(Devoir devoir) {
		this.devoir = devoir;
	}

}
