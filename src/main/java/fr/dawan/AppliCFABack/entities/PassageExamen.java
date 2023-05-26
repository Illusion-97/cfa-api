package fr.dawan.AppliCFABack.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("serial")
@Entity
public class PassageExamen extends BaseEntity implements Serializable {

	private LocalDate dateDebut;

	private LocalDate dateFin;

	@ManyToOne
	private Examen examen;

	@ManyToOne
	private Intervention intervention;

	public PassageExamen() {
		super();
	}

	public PassageExamen(LocalDate dateDebut, LocalDate dateFin, Examen examen, Intervention intervention) {
		super();
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.examen = examen;
		this.intervention = intervention;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
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

	public Examen getExamen() {
		return examen;
	}

	public void setExamen(Examen examen) {
		this.examen = examen;
	}

	public Intervention getIntervention() {
		return intervention;
	}

	public void setIntervention(Intervention intervention) {
		this.intervention = intervention;
	}

}
