package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.List;

import fr.dawan.AppliCFABack.entities.ExperienceProfessionnelle;

/**
 * 
 * @author Valentin C, Feres BG.
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return DTO-Competence professionnelle Entity
 */
@SuppressWarnings("serial")
public class CompetenceProfessionnelleDto extends BaseEntityDto implements Serializable {

	private String libelle;
	private byte numeroFiche;
	private List<ExamenDto> examensDto;
	private long activiteTypeId;
    
	

	public CompetenceProfessionnelleDto() {
		super();
	}

	/**
	 * @param libelle
	 * @param numeroFiche
	 * @param examensDto
	 * @param activiteTypeId
	 */
	

	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	public CompetenceProfessionnelleDto(String libelle, byte numeroFiche, List<ExamenDto> examensDto,
			 long activiteTypeId) {
		super();
		this.libelle = libelle;
		this.numeroFiche = numeroFiche;
		this.examensDto = examensDto;
		this.activiteTypeId = activiteTypeId;
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
	 * @return the activiteTypeId
	 */
	public long getActiviteTypeId() {
		return activiteTypeId;
	}

	/**
	 * @param activiteTypeId the activiteTypeId to set
	 */
	public void setActiviteTypeId(long activiteTypeId) {
		this.activiteTypeId = activiteTypeId;
	}


}
