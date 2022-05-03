
package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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

	public List<ActiviteTypeDto> getActiviteTypes() {
		return activiteTypesDto;
	}

	public void setActiviteTypes(List<ActiviteTypeDto> activiteTypes) {
		this.activiteTypesDto = activiteTypes;
	}

	public long getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(long promotionId) {
		this.promotionId = promotionId;
	}

	public Set<CompetenceProfessionnelleDto> getCompetenceProfessionnelleDto() {
		return competencesProfessionnellesDto;
	}

	public void setCompetenceProfessionnelleDto(Set<CompetenceProfessionnelleDto> competenceProfessionnelleDto) {
		this.competencesProfessionnellesDto = competenceProfessionnelleDto;
	}

	public Set<NoteDto> getNotesDto() {
		return notesDto;
	}

	public void setNotesDto(Set<NoteDto> notesDto) {
		this.notesDto = notesDto;
	}
}