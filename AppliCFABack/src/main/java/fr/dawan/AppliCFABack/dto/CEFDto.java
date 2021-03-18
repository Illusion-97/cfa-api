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

	public CEFDto(long id, PersonneDto personneDto, CentreDto centreDto) {
		super();
		this.id = id;
		this.personne = personneDto;
		this.centre = centreDto;
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public PersonneDto getPersonneDto() {
		return personne;
	}

	public void setPersonneDto(PersonneDto personneDto) {
		this.personne = personneDto;
	}

	public CentreDto getCentreDto() {
		return centre;
	}

	public void setCentreDto(CentreDto centreDto) {
		this.centre = centreDto;
	}

}
