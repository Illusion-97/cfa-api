package fr.dawan.AppliCFABack.dto;

import java.time.LocalDate;

public class DevoirDto {
	private long id;
	String enonce;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private InterventionDto interventionDto;

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

	public InterventionDto getInterventionDto() {
		return interventionDto;
	}

	public void setInterventionDto(InterventionDto interventionDto) {
		this.interventionDto = interventionDto;
	}

}
