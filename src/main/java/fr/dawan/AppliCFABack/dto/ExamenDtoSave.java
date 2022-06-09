package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author Valentin C, Feres BG.
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return DTO d'un examen a enregistrer en base de données
 */
@SuppressWarnings("serial")
public class ExamenDtoSave extends BaseEntityDto implements Serializable {

	private String titre;

	private String descriptif;

	private double duree;

	private String pieceJointe;

	private LocalDate dateExamen;

	private List<Long> activiteTypesId;

	private Set<Long> promotionsId;

	private Set<Long> competencesProfessionnellesId;

	private Set<Long> notesId;
	
	private long interventionId;

	/**
	 * @return le interventionId
	 */
	public long getInterventionId() {
		return interventionId;
	}

	/**
	 * @param interventionId le interventionId à affecter
	 
	 */
	public void setInterventionId(long interventionId) {
		this.interventionId = interventionId;
	}

	/**
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * @param titre the titre to set
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * @return the descriptif
	 */
	public String getDescriptif() {
		return descriptif;
	}

	/**
	 * @param descriptif the descriptif to set
	 */
	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}

	/**
	 * @return the duree
	 */
	public double getDuree() {
		return duree;
	}

	/**
	 * @param duree the duree to set
	 */
	public void setDuree(double duree) {
		this.duree = duree;
	}

	/**
	 * @return the pieceJointe
	 */
	public String getPieceJointe() {
		return pieceJointe;
	}

	/**
	 * @param pieceJointe the pieceJointe to set
	 */
	public void setPieceJointe(String pieceJointe) {
		this.pieceJointe = pieceJointe;
	}

	/**
	 * @return the dateExamen
	 */
	public LocalDate getDateExamen() {
		return dateExamen;
	}

	/**
	 * @param dateExamen the dateExamen to set
	 */
	public void setDateExamen(LocalDate dateExamen) {
		this.dateExamen = dateExamen;
	}

	/**
	 * @return the activiteTypesId
	 */
	public List<Long> getActiviteTypesId() {
		return activiteTypesId;
	}

	/**
	 * @param activiteTypesId the activiteTypesId to set
	 */
	public void setActiviteTypesId(List<Long> activiteTypesId) {
		this.activiteTypesId = activiteTypesId;
	}


	/**
	 * @return the competencesProfessionnellesId
	 */
	public Set<Long> getCompetencesProfessionnellesId() {
		return competencesProfessionnellesId;
	}

	/**
	 * @return le promotionsId
	 */
	public Set<Long> getPromotionsId() {
		return promotionsId;
	}

	/**
	 * @param promotionsId le promotionsId à affecter
	 
	 */
	public void setPromotionsId(Set<Long> promotionsId) {
		this.promotionsId = promotionsId;
	}

	/**
	 * @param competencesProfessionnellesId the competencesProfessionnellesId to set
	 */
	public void setCompetencesProfessionnellesId(Set<Long> competencesProfessionnellesId) {
		this.competencesProfessionnellesId = competencesProfessionnellesId;
	}

	/**
	 * @return the notesId
	 */
	public Set<Long> getNotesId() {
		return notesId;
	}

	/**
	 * @param notesId the notesId to set
	 */
	public void setNotesId(Set<Long> notesId) {
		this.notesId = notesId;
	}

	
	

}
