package fr.dawan.AppliCFABack.dto;

import java.util.List;
import java.util.Set;


public class ActiviteTypeDto {
	

	private long id;
	
	private String libelle;
	
	private byte numeroFiche;
	
	private List<ExamenDto> examensDto;
	
	private long cursusActiviteTypeId;
	
	private Set<CompetenceProfessionnelleDto> competenceProfessionnellesDto;
	
	
	public ActiviteTypeDto() {
		super();
	}


	public ActiviteTypeDto(long id, String libelle, byte numeroFiche) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.numeroFiche = numeroFiche;
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

	public long getCursusActiviteTypeId() {
		return cursusActiviteTypeId;
	}

	public void setCursusActiviteTypeId(long cursusActiviteTypeId) {
		this.cursusActiviteTypeId = cursusActiviteTypeId;
	}


	public Set<CompetenceProfessionnelleDto> getCompetenceProfessionnellesDto() {
		return competenceProfessionnellesDto;
	}


	public void setCompetenceProfessionnellesDto(Set<CompetenceProfessionnelleDto> competenceProfessionnellesDto) {
		this.competenceProfessionnellesDto = competenceProfessionnellesDto;
	}

	
}
