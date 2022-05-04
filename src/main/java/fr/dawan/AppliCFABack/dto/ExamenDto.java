
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
 * @return DTO-Examen Entity
 */
@SuppressWarnings("serial")
public class ExamenDto extends BaseEntityDto implements Serializable {

	private String titre;
	private String descriptif;
	private double duree;
	private String pieceJointe;
	private LocalDate dateExamen;
	private List<ActiviteTypeDto> activiteTypesDto;
	private long promotionId;
	private Set<CompetenceProfessionnelleDto> competencesProfessionnellesDto;
	private Set<NoteDto> notesDto;

	public ExamenDto() {
		super();
	}

	public ExamenDto(long id, String titre, String descriptif, double duree, String pieceJointe, LocalDate dateExamen) {
		super();
		this.id = id;
		this.titre = titre;
		this.descriptif = descriptif;
		this.duree = duree;
		this.pieceJointe = pieceJointe;
		this.dateExamen = dateExamen;
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
	 * @return the activiteTypesDto
	 */
	public List<ActiviteTypeDto> getActiviteTypesDto() {
		return activiteTypesDto;
	}

	/**
	 * @param activiteTypesDto the activiteTypesDto to set
	 */
	public void setActiviteTypesDto(List<ActiviteTypeDto> activiteTypesDto) {
		this.activiteTypesDto = activiteTypesDto;
	}

	/**
	 * @return the promotionId
	 */
	public long getPromotionId() {
		return promotionId;
	}

	/**
	 * @param promotionId the promotionId to set
	 */
	public void setPromotionId(long promotionId) {
		this.promotionId = promotionId;
	}

	/**
	 * @return the competencesProfessionnellesDto
	 */
	public Set<CompetenceProfessionnelleDto> getCompetencesProfessionnellesDto() {
		return competencesProfessionnellesDto;
	}

	/**
	 * @param competencesProfessionnellesDto the competencesProfessionnellesDto to set
	 */
	public void setCompetencesProfessionnellesDto(Set<CompetenceProfessionnelleDto> competencesProfessionnellesDto) {
		this.competencesProfessionnellesDto = competencesProfessionnellesDto;
	}

	/**
	 * @return the notesDto
	 */
	public Set<NoteDto> getNotesDto() {
		return notesDto;
	}

	/**
	 * @param notesDto the notesDto to set
	 */
	public void setNotesDto(Set<NoteDto> notesDto) {
		this.notesDto = notesDto;
	}

	
}