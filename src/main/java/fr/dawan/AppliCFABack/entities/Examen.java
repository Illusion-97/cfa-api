package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
public class Examen extends BaseEntity implements Serializable {

	@Column(nullable = false, length = 255)
	private String titre;

	@Lob
	private String descriptif;

	@Column(nullable = false, precision = 4, scale = 2)
	private double duree;

	@Column(nullable = false)
	private String pieceJointe;

	@Column(nullable = false)
	private LocalDate dateExamen;

	@ManyToMany
	private List<ActiviteType> activiteTypes;

	@ManyToOne
	private Promotion promotion;

	@ManyToMany
	private Set<CompetenceProfessionnelle> competencesProfessionnelles;

	@OneToMany(mappedBy = "examen", cascade = CascadeType.ALL)
	private Set<Note> notes;

	public Set<Note> getNotes() {
		return notes;
	}

	public void setNotes(Set<Note> notes) {
		this.notes = notes;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	public Examen() {
		super();
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescriptif() {
		return descriptif;
	}

	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}

	public double getDuree() {
		return duree;
	}

	public void setDuree(double duree) {
		this.duree = duree;
	}

	public String getPieceJointe() {
		return pieceJointe;
	}

	public void setPieceJointe(String pieceJointe) {
		this.pieceJointe = pieceJointe;
	}

	public LocalDate getDateExamen() {
		return dateExamen;
	}

	public void setDateExamen(LocalDate dateExamen) {
		this.dateExamen = dateExamen;
	}

	public List<ActiviteType> getActiviteType() {
		return activiteTypes;
	}

	public void setActiviteType(List<ActiviteType> activiteType) {
		this.activiteTypes = activiteType;
	}

	public Set<CompetenceProfessionnelle> getCompetencesProfessionnelles() {
		return competencesProfessionnelles;
	}

	public void setCompetencesProfessionnelles(Set<CompetenceProfessionnelle> competencesProfessionnelles) {
		this.competencesProfessionnelles = competencesProfessionnelles;
	}

	public List<Long> getActiviteTypesId() {
		List<Long> activityteTypesId = new ArrayList<Long>();
		for (ActiviteType at : activiteTypes) {

			if (at != null) {
				activityteTypesId.add(at.getId());
			}
		}
		return activityteTypesId;
	}

	public List<Long> getCompetencesProfessionnellesId() {
		List<Long> competencesProId = new ArrayList<Long>();
		for (CompetenceProfessionnelle cp : competencesProfessionnelles) {

			if (cp != null) {
				competencesProId.add(cp.getId());
			}
		}
		return competencesProId;
	}

}
