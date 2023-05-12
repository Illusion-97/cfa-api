package fr.dawan.AppliCFABack.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * 
 * 
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return DTO de l'appel API DG2 de formation
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FormationDG2Dto extends BaseEntityDto implements Serializable {

	private String title;
	private String duration;
	private String slug;
	private String objectives;
	private String prerequisites;
	private String plan;

	
	/**
	 * @return le id
	 */
	@Override
	public long getId() {
		return id;
	}

	/**
	 * @param id le id à affecter
	 
	 */
	@Override
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return le duration
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * @param duration le duration à affecter
	 
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}


	/**
	 * @return le objectives
	 */
	public String getObjectives() {
		return objectives;
	}

	/**
	 * @param objectives le objectives à affecter
	 
	 */
	public void setObjectives(String objectives) {
		this.objectives = objectives;
	}

	/**
	 * @return le prerequisites
	 */
	public String getPrerequisites() {
		return prerequisites;
	}

	/**
	 * @param prerequisites le prerequisites à affecter
	 
	 */
	public void setPrerequisites(String prerequisites) {
		this.prerequisites = prerequisites;
	}

	/**
	 * @return le plan
	 */
	public String getPlan() {
		return plan;
	}

	/**
	 * @param plan le plan à affecter
	 
	 */
	public void setPlan(String plan) {
		this.plan = plan;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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


}
