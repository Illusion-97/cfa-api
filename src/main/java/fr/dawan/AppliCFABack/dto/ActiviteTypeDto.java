package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Valentin C, Feres BG.
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return DTO-ActiviteType Entity
 */
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

	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * @return the numeroFiche
	 */
	public byte getNumeroFiche() {
		return numeroFiche;
	}

	/**
	 * @param numeroFiche the numeroFiche to set
	 */
	public void setNumeroFiche(byte numeroFiche) {
		this.numeroFiche = numeroFiche;
	}

	/**
	 * @return the examensDto
	 */
	public List<ExamenDto> getExamensDto() {
		return examensDto;
	}

	/**
	 * @param examensDto the examensDto to set
	 */
	public void setExamensDto(List<ExamenDto> examensDto) {
		this.examensDto = examensDto;
	}

	/**
	 * @return the cursusActiviteTypeId
	 */
	public long getCursusActiviteTypeId() {
		return cursusActiviteTypeId;
	}

	/**
	 * @param cursusActiviteTypeId the cursusActiviteTypeId to set
	 */
	public void setCursusActiviteTypeId(long cursusActiviteTypeId) {
		this.cursusActiviteTypeId = cursusActiviteTypeId;
	}

	/**
	 * @return the competencesProfessionnellesDto
	 */
	public List<CompetenceProfessionnelleDto> getCompetencesProfessionnellesDto() {
		return competencesProfessionnellesDto;
	}

	/**
	 * @param competencesProfessionnellesDto the competencesProfessionnellesDto to set
	 */
	public void setCompetenceProfessionnellesDto(List<CompetenceProfessionnelleDto> competencesProfessionnellesDto) {
		this.competencesProfessionnellesDto = competencesProfessionnellesDto;
	}




}
