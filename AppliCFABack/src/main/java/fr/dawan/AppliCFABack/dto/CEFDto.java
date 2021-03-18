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
	private PersonneDto personneDto;
	@XmlElement
	private CentreDto centreDto;

	public CEFDto() {
		super();
	}

	public CEFDto(long id, PersonneDto personne, CentreDto centre) {
		super();
		this.id = id;
		this.personneDto = personne;
		this.centreDto = centre;
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public PersonneDto getPersonneDto() {
		return personneDto;
	}

	public void setPersonneDto(PersonneDto personneDto) {
		this.personneDto = personneDto;
	}

	public CentreDto getCentreDto() {
		return centreDto;
	}

	public void setCentreDto(CentreDto centreDto) {
		this.centreDto = centreDto;
	}

}
