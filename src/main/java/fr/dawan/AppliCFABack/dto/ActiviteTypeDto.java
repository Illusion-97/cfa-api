package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.List;


@SuppressWarnings("serial")
public class ActiviteTypeDto extends BaseEntityDto implements Serializable{
	
	private String libelle;
	
	private byte numeroFiche;
	
	private List<ExamenDto> examensDto;
	
	private long cursusActiviteTypeId;
	
	private List<CompetenceProfessionnelleDto> competencesProfessionnellesDto;
	
	public ActiviteTypeDto() {
		super();
	}

	public ActiviteTypeDto(long id, String libelle, byte numeroFiche) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.numeroFiche = numeroFiche;
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


	public List<CompetenceProfessionnelleDto> getCompetenceProfessionnellesDto() {
		return competencesProfessionnellesDto;
	}


	public void setCompetenceProfessionnellesDto(List<CompetenceProfessionnelleDto> competenceProfessionnellesDto) {
		this.competencesProfessionnellesDto = competenceProfessionnellesDto;
	}



}
