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

	@ManyToMany
	private List<ActiviteType> activiteTypes;



	/**
	 * @param activiteTypes le activiteTypes à affecter
	 
	 */
	public void setActiviteTypes(List<ActiviteType> activiteTypes) {
		this.activiteTypes = activiteTypes;
	}

	@ManyToOne
	private Promotion promotion;

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
	 * @return la promotion
	 */
	public Promotion getPromotion() {
		return promotion;
	}

	/**
	 * @param promotion la promotion à affecter
	 * 
	 */
	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
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
	 * @return les activiteTypes
	 */


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
