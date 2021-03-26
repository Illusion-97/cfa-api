package fr.dawan.AppliCFABack.dto;

import java.time.LocalDate;

import fr.dawan.AppliCFABack.entities.Examen;
import fr.dawan.AppliCFABack.entities.Intervention;

public class PassageExamenDto {
	private long id;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private Examen examenDto;
	private Intervention interventionDto;

	public PassageExamenDto() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

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

	public Examen getExamenDto() {
		return examenDto;
	}

	public void setExamenDto(Examen examenDto) {
		this.examenDto = examenDto;
	}

	public Intervention getInterventionDto() {
		return interventionDto;
	}

	public void setInterventionDto(Intervention interventionDto) {
		this.interventionDto = interventionDto;
	}

}
