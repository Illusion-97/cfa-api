package fr.dawan.AppliCFABack.dto;

import java.time.LocalDate;

public class InterventionDto {
	private long id;
	private LocalDate dateDebut; // 12/03
	private LocalDate dateFin; // 18/03
	private FormationDto formationDto; // Java init
	private InterventionDto interventionMereDto; // Java init+appro
	private PromotionDto promotionDto; // CDA 2021

	public InterventionDto() {
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

	public FormationDto getFormationDto() {
		return formationDto;
	}

	public void setFormationDto(FormationDto formationDto) {
		this.formationDto = formationDto;
	}

	public InterventionDto getInterventionMereDto() {
		return interventionMereDto;
	}

	public void setInterventionMereDto(InterventionDto interventionMereDto) {
		this.interventionMereDto = interventionMereDto;
	}

	public PromotionDto getPromotionDto() {
		return promotionDto;
	}

	public void setPromotionDto(PromotionDto promotionDto) {
		this.promotionDto = promotionDto;
	}

}
