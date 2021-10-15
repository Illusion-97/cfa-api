package fr.dawan.AppliCFABack.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Devoir {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, length = 255)
	String enonce;

	private LocalDate dateDebut;

	private LocalDate dateFin;

	@ManyToOne
	private Intervention intervention;

	public Devoir() {
		super();
	}

	public Devoir(long id, String enonce, LocalDate dateDebut, LocalDate dateFin, Intervention intervention) {
		super();
		this.id = id;
		this.enonce = enonce;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.intervention = intervention;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEnonce() {
		return enonce;
	}

	public void setEnonce(String enonce) {
		this.enonce = enonce;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public Intervention getIntervention() {
		return intervention;
	}

	public void setIntervention(Intervention intervention) {
		this.intervention = intervention;
	}

}
