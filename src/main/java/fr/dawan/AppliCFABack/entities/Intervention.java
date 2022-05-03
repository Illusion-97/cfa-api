package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Intervention extends BaseEntity implements Serializable { // intervention prévue

//	@Temporal(value = TemporalType.DATE)
	private LocalDate dateDebut; // 12/03

//	@Temporal(value = TemporalType.DATE)
	private LocalDate dateFin; // 18/03

	@ManyToOne
	private Formation formation; // Java init

	@ManyToOne
	private Intervention interventionMere; // Java init+appro

	@ManyToMany
	private List<Promotion> promotions; // CDA 2021

	@ManyToMany
	private List<Formateur> formateurs;

	@ManyToMany(mappedBy = "interventions")
	private List<SupportCours> supportsCours;

	@Column
	private String noteInfoPersonnel;

	public Intervention() {
		super();
	}

	public Intervention(LocalDate dateDebut, LocalDate dateFin, Formation formation, Intervention interventionMere,
			List<Promotion> promotions) {
		super();
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.formation = formation;
		this.interventionMere = interventionMere;
		this.promotions = promotions;
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

	public List<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}

	public List<Formateur> getFormateurs() {
		return formateurs;
	}

	public void setFormateurs(List<Formateur> formateurs) {
		this.formateurs = formateurs;
	}

	public String getNoteInfoPersonnel() {
		return noteInfoPersonnel;
	}

	public void setNoteInfoPersonnel(String noteInfoPersonnel) {
		this.noteInfoPersonnel = noteInfoPersonnel;
	}

}
