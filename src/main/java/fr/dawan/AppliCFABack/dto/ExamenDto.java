
package fr.dawan.AppliCFABack.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ExamenDto {
	private long id;
	private String titre;
	private String descriptif;
	private double duree;
	private String pieceJointe;
	private LocalDate dateExamen;
	private long activiteTypeId;
	private long promotionId;
	private Set<CompetenceProfessionnelleDto> competenceProfessionnelleDto;
	private Set<NoteDto> notesDto;
	private List<Byte> blocksConcernee;
	
	
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
	
	public LocalDate getDateExamen() {
		return dateExamen;
	}
	public void setDateExamen(LocalDate dateExamen) {
		this.dateExamen = dateExamen;
	}

	public long getActiviteTypeId() {
		return activiteTypeId;
	}
	public void setActiviteTypeId(long activiteTypeId) {
		this.activiteTypeId = activiteTypeId;
	}
	public long getPromotionId() {
		return promotionId;
	}
	public void setPromotionId(long promotionId) {
		this.promotionId = promotionId;
	}
	public Set<CompetenceProfessionnelleDto> getCompetenceProfessionnelleDto() {
		return competenceProfessionnelleDto;
	}
	public void setCompetenceProfessionnelleDto(Set<CompetenceProfessionnelleDto> competenceProfessionnelleDto) {
		this.competenceProfessionnelleDto = competenceProfessionnelleDto;
		List<Byte> blocksConcernee=  this.competenceProfessionnelleDto.stream().map(CompetenceProfessionnelleDto::getNumeroFiche).collect(Collectors.toList());
		setBlocksConcernee(blocksConcernee);
	}
	public Set<NoteDto> getNotesDto() {
		return notesDto;
	}
	public void setNotesDto(Set<NoteDto> notesDto) {
		this.notesDto = notesDto;
	}
	public List<Byte> getBlocksConcernee() {
		return blocksConcernee;
	}
	private void setBlocksConcernee(List<Byte> blocksConcernee) {
		this.blocksConcernee = blocksConcernee;
	}
}