package fr.dawan.AppliCFABack.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.dawan.AppliCFABack.tools.LocalDateDeserializer;

import java.time.LocalDate;
/**
 * 
 * @author  Feres BG.
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeDG2Dto {

	@JsonIgnore
	private long id;
	
	private String personId;
	
	private String firstName;
	
	private String lastName;
	
	private long  locationId;
	
	private String email;
	@JsonIgnore
	private String job;
	@JsonIgnore
	private String planningGroups; 
	@JsonIgnore
	private String name;
	@JsonIgnore
	private String skill;
	@JsonIgnore
	private String slug;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate bornAt;
	@JsonIgnore
    private String birthPlace;
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
	 * @return le personId
	 */
	public String getPersonId() {
		return personId;
	}
	/**
	 * @param personId le personId à affecter
	 
	 */
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	/**
	 * @return le firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName le firstName à affecter
	 
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return le lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName le lastName à affecter
	 
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	 * @return le email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email le email à affecter
	 
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return le job
	 */
	public String getJob() {
		return job;
	}
	/**
	 * @param job le job à affecter
	 
	 */
	public void setJob(String job) {
		this.job = job;
	}
	/**
	 * @return le name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name le name à affecter
	 
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return le skill
	 */
	public String getSkill() {
		return skill;
	}
	/**
	 * @param skill le skill à affecter
	 
	 */
	public void setSkill(String skill) {
		this.skill = skill;
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
	public String getPlanningGroups() {
		return planningGroups;
	}
	public void setPlanningGroups(String planningGroups) {
		this.planningGroups = planningGroups;
	}
	 
	public LocalDate getBornAt() {
		return bornAt;
	}
	public void setBornAt(LocalDate bornAt) {
		this.bornAt = bornAt;
	}
	public String getBirthPlace() {
		return birthPlace;
	}
	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}
	
	
}
