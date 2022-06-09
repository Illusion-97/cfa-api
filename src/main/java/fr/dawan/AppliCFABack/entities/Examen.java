package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

/***
 * 
 * @author Feres BG Valentin C.
 * @see ActiviteType
 * @see Promotion
 * @see CompetenceProfessionnelle
 * @since 1.0
 * @version 1.0
 *
 */
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
	
	@ManyToOne
	private Intervention intervention;

	@ManyToMany
	private List<ActiviteType> activiteTypes;

	@ManyToMany
	private Set<Promotion> promotions;

	@ManyToMany
	private Set<CompetenceProfessionnelle> competencesProfessionnelles;

	@OneToMany(mappedBy = "examen", cascade = CascadeType.ALL)
	private Set<Note> notes;
	

	public Set<Note> getNotes() {
		return notes;
	}

	/**
	 * @return le titre
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * @param titre le titre à affecter
	 * 
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * @return le descriptif
	 */
	public String getDescriptif() {
		return descriptif;
	}

	/**
	 * @param descriptif le descriptif à affecter
	 * 
	 */
	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}

	/**
	 * @return la duree
	 */
	public double getDuree() {
		return duree;
	}

	/**
	 * @param duree la duree à affecter
	 * 
	 */
	public void setDuree(double duree) {
		this.duree = duree;
	}

	/**
	 * @return la pieceJointe
	 */
	public String getPieceJointe() {
		return pieceJointe;
	}

	/**
	 * @param pieceJointe la pieceJointe à affecter
	 * 
	 */
	public void setPieceJointe(String pieceJointe) {
		this.pieceJointe = pieceJointe;
	}

	/**
	 * @return la dateExamen
	 */
	public LocalDate getDateExamen() {
		return dateExamen;
	}

	/**
	 * @param dateExamen la dateExamen à affecter
	 * 
	 */
	public void setDateExamen(LocalDate dateExamen) {
		this.dateExamen = dateExamen;
	}

	/**
	 * @return les promotions
	 * @
	 */
	public Set<Promotion> getPromotions() {
		return promotions;
	}

	/**
	 * @param promotions les promotions à affecter
	 
	 */
	public void setPromotions(Set<Promotion> promotions) {
		this.promotions = promotions;
	}

	/**
	 * @return les competencesProfessionnelles
	 */
	public Set<CompetenceProfessionnelle> getCompetencesProfessionnelles() {
		return competencesProfessionnelles;
	}

	/**
	 * @param competencesProfessionnelles les competencesProfessionnelles à affecter
	 * 
	 */
	public void setCompetencesProfessionnelles(Set<CompetenceProfessionnelle> competencesProfessionnelles) {
		this.competencesProfessionnelles = competencesProfessionnelles;
	}

	/**
	 * @param notes les notes à affecter
	 * 
	 */
	public void setNotes(Set<Note> notes) {
		this.notes = notes;
	}


	/**
	 * @return le intervention
	 */
	public Intervention getIntervention() {
		return intervention;
	}

	/**
	 * @param intervention le intervention à affecter
	 
	 */
	public void setIntervention(Intervention intervention) {
		this.intervention = intervention;
	}

	/**
	 * @param activiteTypes led activiteTypes à affecter
	 * 
	 */
	public void setActiviteType(List<ActiviteType> activiteType) {
		this.activiteTypes = activiteType;
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

	/**
	 * @return le activiteTypes
	 */
	public List<ActiviteType> getActiviteTypes() {
		return activiteTypes;
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
