package fr.dawan.AppliCFABack.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FormationDG2Dto {
	private long id; //
    private String title; //titre pro ...
    private String duration; // durée en h/j
    private String slug;  //slug
    @JsonProperty("published")
    private boolean state; //status
    
	public FormationDG2Dto() {
		super();
	}

	public FormationDG2Dto(long id, String title, String duration, String slug, boolean state) {
		super();
		this.id = id;
		this.title = title;
		this.duration = duration;
		this.slug = slug;
		this.state = state;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}


}
