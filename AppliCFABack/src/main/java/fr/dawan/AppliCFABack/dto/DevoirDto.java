package fr.dawan.AppliCFABack.dto;

import java.util.Date;

public class DevoirDto {
	private long id;
	String enonce;
	private Date dateDebut;
	private Date dateFin;
	private InterventionDto intervention;

	public DevoirDto() {
		// TODO Auto-generated constructor stub
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

	public InterventionDto getIntervention() {
		return intervention;
	}

	public void setIntervention(InterventionDto intervention) {
		this.intervention = intervention;
	}

}
