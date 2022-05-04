package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;

/**
 * 
 *
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return DTO-projet Entity
 */
@SuppressWarnings("serial")
public class ProjetDto extends BaseEntityDto implements Serializable {

	private String nom;
	private String description;
	private GroupeEtudiantDto groupeDto;

	public ProjetDto() {
		super();
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the groupeDto
	 */
	public GroupeEtudiantDto getGroupeDto() {
		return groupeDto;
	}

	/**
	 * @param groupeDto the groupeDto to set
	 */
	public void setGroupeDto(GroupeEtudiantDto groupeDto) {
		this.groupeDto = groupeDto;
	}

}
