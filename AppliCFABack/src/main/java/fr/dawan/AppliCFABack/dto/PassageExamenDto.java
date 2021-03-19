package fr.dawan.AppliCFABack.dto;

import java.util.Date;

import fr.dawan.AppliCFABack.entities.Examen;
import fr.dawan.AppliCFABack.entities.Intervention;

public class PassageExamenDto {
	private long id;
	private Date dateDebut;
	private Date dateFin;
	private Examen examen;
	private Intervention intervention;

	public PassageExamenDto() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
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
