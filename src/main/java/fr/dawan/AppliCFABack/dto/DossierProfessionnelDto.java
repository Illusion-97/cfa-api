package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;

/**
 * 
 * 
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return DTO-Dossier profesionnel Entity
 */
@SuppressWarnings("serial")
public class DossierProfessionnelDto extends BaseEntityDto implements Serializable {

	private String nom;

	private CursusDto cursusDto;

	public DossierProfessionnelDto() {
		// TODO Auto-generated constructor stub
	}

	public DossierProfessionnelDto(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the cursusDto
	 */
	public CursusDto getCursusDto() {
		return cursusDto;
	}

	/**
	 * @param cursusDto the cursusDto to set
	 */
	public void setCursusDto(CursusDto cursusDto) {
		this.cursusDto = cursusDto;
	}

}
