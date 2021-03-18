package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "cef")
@XmlAccessorType(XmlAccessType.FIELD)
public class CEFDto implements Serializable {
	@XmlElement
	private long id;
	@XmlElement
	private PersonneDto personne;
	@XmlElement
	private CentreDto centre;

	public CEFDto() {
		super();
	}

	public CEFDto(long id, PersonneDto personne, CentreDto centre) {
		super();
		this.id = id;
		this.personne = personne;
		this.centre = centre;
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

	public CentreDto getCentre() {
		return centre;
	}

	public void setCentre(CentreDto centre) {
		this.centre = centre;
	}

}
