package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 *
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return DTO de l'appel API DG2 intervention
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class InterventionDG2Dto extends BaseEntityDto implements Serializable {

	// private long locationId;
	private String dateStart;
	private String dateEnd;
	private String slug;
	// private long courseId;
	@JsonProperty("shared")
	private boolean type;
	private String nbParticipants;

	public InterventionDG2Dto() {
		super();
	}

	/**
	 * @return the dateStart
	 */
	public String getDateStart() {
		return dateStart;
	}

	/**
	 * @param dateStart the dateStart to set
	 */
	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	/**
	 * @return the dateEnd
	 */
	public String getDateEnd() {
		return dateEnd;
	}

	/**
	 * @param dateEnd the dateEnd to set
	 */
	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	/**
	 * @return the slug
	 */
	public String getSlug() {
		return slug;
	}

	/**
	 * @param slug the slug to set
	 */
	public void setSlug(String slug) {
		this.slug = slug;
	}

	/**
	 * @return the type
	 */
	public boolean isType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(boolean type) {
		this.type = type;
	}

	/**
	 * @return the nbParticipants
	 */
	public String getNbParticipants() {
		return nbParticipants;
	}

	/**
	 * @param nbParticipants the nbParticipants to set
	 */
	public void setNbParticipants(String nbParticipants) {
		this.nbParticipants = nbParticipants;
	}

}
