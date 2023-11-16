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
	private long groupeId;

	public String getGroupeNom() {
		return groupeNom;
	}

	public void setGroupeNom(String groupeNom) {
		this.groupeNom = groupeNom;
	}

	private String groupeNom;

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

	public long getGroupeId() {
		return groupeId;
	}

	public void setGroupeId(long groupeId) {
		this.groupeId = groupeId;
	}
}
