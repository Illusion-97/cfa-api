package fr.dawan.AppliCFABack.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CentreFormationDG2Dto {

	private long id;
	private String name;
	private boolean published;
	private String country;
	
	public CentreFormationDG2Dto() {
		super();
	}

	public CentreFormationDG2Dto(long id, String name, boolean published, String country) {
		super();
		this.id = id;
		this.name = name;
		this.published = published;
		this.country = country;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
}
