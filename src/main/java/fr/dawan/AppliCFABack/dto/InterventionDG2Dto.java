package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

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

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}

	public String getNbParticipants() {
		return nbParticipants;
	}

	public void setNbParticipants(String nbParticipants) {
		this.nbParticipants = nbParticipants;
	}

}
