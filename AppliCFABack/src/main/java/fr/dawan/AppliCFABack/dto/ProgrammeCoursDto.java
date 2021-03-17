package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "programmeCours")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProgrammeCoursDto implements Serializable {
	@XmlElement
	private long id;
	@XmlElement
	private String nom;
	@XmlElement
	private String description;
	@XmlElement
	private long dure;
	@XmlElement
	private List<CoursDto> cours;

	public ProgrammeCoursDto() {
		super();
	}
	
	public ProgrammeCoursDto(long id, String nom, String description, long dure, List<CoursDto> cours) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.dure = dure;
		this.cours = cours;
	}

	public long getIdProgrammeCoursDto() {
		return id;
	}

	public void setIdProgrammeCoursDto(long id) {
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

	public List<CoursDto> getCoursDto() {
		return cours;
	}

	public void setCoursDto(List<CoursDto> coursDto) {
		this.cours = coursDto;
	}

}
