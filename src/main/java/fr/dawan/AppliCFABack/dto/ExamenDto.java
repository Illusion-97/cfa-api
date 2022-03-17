package fr.dawan.AppliCFABack.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

public class ExamenDto {
	private long id;
	private String titre;
	private String descriptif;
	private double duree;
	private String pieceJointe;
	private LocalDateTime dateExamen;
	private ActiviteTypeDto activiteTypeDto;
	private PromotionDto promotionDto;
	private Set<CompetenceProfessionnelleDto> competenceProfessionnelleDto;
	private Set<NoteDto> notesDto;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public LocalDateTime getDateExamen() {
		return dateExamen;
	}
	public void setDateExamen(LocalDateTime dateExamen) {
		this.dateExamen = dateExamen;
	}
	public ActiviteTypeDto getActiviteTypeDto() {
		return activiteTypeDto;
	}
	public void setActiviteTypeDto(ActiviteTypeDto activiteTypeDto) {
		this.activiteTypeDto = activiteTypeDto;
	}
	public PromotionDto getPromotionDto() {
		return promotionDto;
	}
	public void setPromotionDto(PromotionDto promotionDto) {
		this.promotionDto = promotionDto;
	}
	public Set<CompetenceProfessionnelleDto> getCompetenceProfessionnelleDto() {
		return competenceProfessionnelleDto;
	}
	public void setCompetenceProfessionnelleDto(Set<CompetenceProfessionnelleDto> competenceProfessionnelleDto) {
		this.competenceProfessionnelleDto = competenceProfessionnelleDto;
	}
	public Set<NoteDto> getNotesDto() {
		return notesDto;
	}
	public void setNotesDto(Set<NoteDto> notesDto) {
		this.notesDto = notesDto;
	}

	
//	private FormationDto formationDto;
//	private CursusDto cursusDto;



//	public FormationDto getFormationDto() {
//		return formationDto;
//	}
//
//	public void setFormationDto(FormationDto formationDto) {
//		this.formationDto = formationDto;
//	}
//
//	public CursusDto getCursusDto() {
//		return cursusDto;
//	}
//
//	public void setCursusDto(CursusDto cursusDto) {
//		this.cursusDto = cursusDto;
//	}

}
