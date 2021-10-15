package fr.dawan.AppliCFABack.dto;

import java.time.LocalDate;

public class PassageExamenDto {
	private long id;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private ExamenDto examenDto;
	private InterventionDto interventionDto;

	public PassageExamenDto() {
		// TODO Auto-generated constructor stub
	}

	public PassageExamenDto(long id, LocalDate dateDebut, LocalDate dateFin, ExamenDto examenDto,
			InterventionDto interventionDto) {
		super();
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.examenDto = examenDto;
		this.interventionDto = interventionDto;
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

	public ExamenDto getExamenDto() {
		return examenDto;
	}

	public void setExamenDto(ExamenDto examenDto) {
		this.examenDto = examenDto;
	}

	public InterventionDto getInterventionDto() {
		return interventionDto;
	}

	public void setInterventionDto(InterventionDto interventionDto) {
		this.interventionDto = interventionDto;
	}

	
}
