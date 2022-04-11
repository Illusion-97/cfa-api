package fr.dawan.AppliCFABack.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class ExamenDtoSave {

	
	private long id;

	private String titre;
	
	private String descriptif;
	
	private double duree;
	
	private String pieceJointe;
	
	private LocalDate dateExamen; 
	
	private List<Long> activitesTypesId;
	
	private long promotionId;
	
	private Set<Long> competencesProfessionnellesId;
	
	private Set<Long> notesId;

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



	public List<Long> getActivitesTypesId() {
		return activitesTypesId;
	}

	public void setActivitesTypesId(List<Long> activitesTypesId) {
		this.activitesTypesId = activitesTypesId;
	}

	public long getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(long promotionId) {
		this.promotionId = promotionId;
	}

	public Set<Long> getCompetencesProfessionnellesId() {
		return competencesProfessionnellesId;
	}

	public void setCompetencesProfessionnellesId(Set<Long> competencesProfessionnellesId) {
		this.competencesProfessionnellesId = competencesProfessionnellesId;
	}

	public Set<Long> getNotesId() {
		return notesId;
	}

	public void setNotesId(Set<Long> notesId) {
		this.notesId = notesId;
	}
	

	
}
