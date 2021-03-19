package fr.dawan.AppliCFABack.dto;

import java.util.Date;

public class InterventionDto {
	private long id;
	private Date dateDebut; // 12/03
	private Date dateFin; // 18/03
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
