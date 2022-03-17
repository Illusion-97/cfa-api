package fr.dawan.AppliCFABack.dto;

import java.util.List;

import fr.dawan.AppliCFABack.entities.Examen;

public class CompetenceProfessionnelleDto {

	private long id;
	private String libelle;
	private byte numeroFiche;
	private List<ExamenDto> examensDto;
	private long activiteTypeId;
	
	public long getActiviteTypeId() {
		return activiteTypeId;
	}
	public void setActiviteTypeId(long activiteTypeId) {
		this.activiteTypeId = activiteTypeId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public byte getNumeroFiche() {
		return numeroFiche;
	}
	public void setNumeroFiche(byte numeroFiche) {
		this.numeroFiche = numeroFiche;
	}
	public List<ExamenDto> getExamensDto() {
		return examensDto;
	}
	public void setExamensDto(List<ExamenDto> examensDto) {
		this.examensDto = examensDto;
	}

	
	
}
