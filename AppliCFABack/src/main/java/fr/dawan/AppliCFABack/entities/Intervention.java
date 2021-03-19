package fr.dawan.AppliCFABack.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Intervention { // intervention prévue

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Temporal(value = TemporalType.DATE)
	private Date dateDebut; // 12/03

	@Temporal(value = TemporalType.DATE)
	private Date dateFin; // 18/03

	@ManyToOne
	private Formation formation; // Java init

	@ManyToOne
	private Intervention interventionMere; // Java init+appro

	@ManyToOne
	private Promotion promotion; // CDA 2021

	public Intervention() {
		super();
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

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public Intervention getInterventionMere() {
		return interventionMere;
	}

	public void setInterventionMere(Intervention interventionMere) {
		this.interventionMere = interventionMere;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

}
