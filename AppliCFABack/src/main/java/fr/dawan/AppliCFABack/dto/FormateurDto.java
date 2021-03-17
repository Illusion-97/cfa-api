package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "formateur")
@XmlAccessorType(XmlAccessType.FIELD)
public class FormateurDto implements Serializable{
	
	@XmlElement
	private long id;
	@XmlElement
	private PersonneDto personne;
	@XmlElement
	private List<CoursDto> cours;

	public FormateurDto() {
		super();
	}
	
	public FormateurDto(long id, PersonneDto personne, List<CoursDto> cours) {
		super();
		this.id = id;
		this.personne = personne;
		this.cours = cours;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public PersonneDto getPersonne() {
		return personne;
	}

	public void setPersonne(PersonneDto personne) {
		this.personne = personne;
	}

	public List<CoursDto> getCours() {
		return cours;
	}

	public void setCours(List<CoursDto> cours) {
		this.cours = cours;
	}

}
