package fr.dawan.AppliCFABack.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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

	@ManyToOne
	private PassageExamen examen;

	@ManyToOne
	private Devoir devoir;

	public Note() {
		super();
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
