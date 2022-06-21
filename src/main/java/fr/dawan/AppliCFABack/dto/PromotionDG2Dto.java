package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PromotionDG2Dto implements Serializable {

	private long id;
	private long locationId;
	private LocalDateTime dateStart;
	private LocalDateTime dateEnd;
	private String slug;
	private long courseId;
	@JsonIgnore
	private String type;
	@JsonIgnore
	private long nbParticipants;
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
	public LocalDateTime getDateStart() {
		return dateStart;
	}
	/**
	 * @param dateStart le dateStart à affecter
	 
	 */
	public void setDateStart(LocalDateTime dateStart) {
		this.dateStart = dateStart;
	}
	/**
	 * @return le dateEnd
	 */
	public LocalDateTime getDateEnd() {
		return dateEnd;
	}
	/**
	 * @param dateEnd le dateEnd à affecter
	 
	 */
	public void setDateEnd(LocalDateTime dateEnd) {
		this.dateEnd = dateEnd;
	}
	/**
	 * @return le slug
	 */
	public String getSlug() {
		return slug;
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
	
	
}
