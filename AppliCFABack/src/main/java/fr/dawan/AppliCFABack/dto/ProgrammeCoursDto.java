package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "programmeCours")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProgrammeCoursDto implements Serializable {

	private long id;

	private String nom;

	private String description;

	private long dure;

	private List<CoursDto> cours;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getDure() {
		return dure;
	}

	public void setDure(long dure) {
		this.dure = dure;
	}

	public List<CoursDto> getCours() {
		return cours;
	}

	public void setCours(List<CoursDto> cours) {
		this.cours = cours;
	}

}
