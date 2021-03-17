package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "admin")
@XmlAccessorType(XmlAccessType.FIELD)
public class AdminDto implements Serializable {
	@XmlElement
	private long id;
	@XmlElement
	private PersonneDto personne;

	
	public AdminDto() {
		super();
	}

	public AdminDto(long id, PersonneDto personne) {
		super();
		this.id = id;
		this.personne = personne;
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

	
}
