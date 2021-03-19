package fr.dawan.AppliCFABack.dto;

import java.util.Date;

public class InterventionDto {
	private long id;
	private Date dateDebut; // 12/03
	private Date dateFin; // 18/03
	private FormationDto formation; // Java init
	private InterventionDto interventionMere; // Java init+appro
	private PromotionDto promotion; // CDA 2021

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

	public FormationDto getFormation() {
		return formation;
	}

	public void setFormation(FormationDto formation) {
		this.formation = formation;
	}

	public InterventionDto getInterventionMere() {
		return interventionMere;
	}

	public void setInterventionMere(InterventionDto interventionMere) {
		this.interventionMere = interventionMere;
	}

	public PromotionDto getPromotion() {
		return promotion;
	}

	public void setPromotion(PromotionDto promotion) {
		this.promotion = promotion;
	}

}
