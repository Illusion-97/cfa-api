package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class CompetenceProfessionnelleDto extends BaseEntityDto implements Serializable {

	private String libelle;
	private byte numeroFiche;
	private List<ExamenDto> examensDto;
	private long activiteTypeId;

	public CompetenceProfessionnelleDto() {
		super();
	}

	public CompetenceProfessionnelleDto(long id, String libelle, byte numeroFiche, long activiteTypeId) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.numeroFiche = numeroFiche;
		this.activiteTypeId = activiteTypeId;
	}

	public long getActiviteTypeId() {
		return activiteTypeId;
	}

	public void setActiviteTypeId(long activiteTypeId) {
		this.activiteTypeId = activiteTypeId;
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
