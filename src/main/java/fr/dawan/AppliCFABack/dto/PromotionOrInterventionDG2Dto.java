package fr.dawan.AppliCFABack.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PromotionOrInterventionDG2Dto implements Serializable {

	private long id;
	private long locationId;
	private String dateStart;
	private String dateEnd;
	private String slug;
	private long courseId;
	private String type;
	private long nbParticipants;
	private long trainerPersonId;
	/**
	 * @return le id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id le id à affecter
	 
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return le locationId
	 */
	public long getLocationId() {
		return locationId;
	}
	/**
	 * @param locationId le locationId à affecter
	 
	 */
	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}
	/**
	 * @return le dateStart
	 */

	/**
	 * @param dateEnd le dateEnd à affecter
	 

	/**
	 * @return le slug
	 */
	public String getSlug() {
		return slug;
	}
	/**
	 * @return le dateStart
	 */
	public String getDateStart() {
		return dateStart;
	}
	/**
	 * @param dateStart le dateStart à affecter
	 
	 */
	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}
	/**
	 * @return le dateEnd
	 */
	public String getDateEnd() {
		return dateEnd;
	}
	/**
	 * @param dateEnd le dateEnd à affecter
	 
	 */
	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}
	/**
	 * @param slug le slug à affecter
	 
	 */
	public void setSlug(String slug) {
		this.slug = slug;
	}
	/**
	 * @return le courseId
	 */
	public long getCourseId() {
		return courseId;
	}
	/**
	 * @param courseId le courseId à affecter
	 
	 */
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	/**
	 * @return le type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type le type à affecter
	 
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return le nbParticipants
	 */
	public long getNbParticipants() {
		return nbParticipants;
	}
	/**
	 * @param nbParticipants le nbParticipants à affecter
	 
	 */
	public void setNbParticipants(long nbParticipants) {
		this.nbParticipants = nbParticipants;
	}
	/**
	 * @return le trainerPersonId
	 */
	public long getTrainerPersonId() {
		return trainerPersonId;
	}
	/**
	 * @param trainerPersonId le trainerPersonId à affecter
	 
	 */
	public void setTrainerPersonId(long trainerPersonId) {
		this.trainerPersonId = trainerPersonId;
	}
	
	
}
