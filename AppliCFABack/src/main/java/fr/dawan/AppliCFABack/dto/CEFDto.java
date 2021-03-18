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

	public CEFDto(long id, PersonneDto personneDto, CentreDto centreDto) {
		super();
		this.id = id;
<<<<<<< HEAD
		this.personneDto = personne;
		this.centreDto = centre;
=======
		this.personne = personneDto;
		this.centre = centreDto;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public PersonneDto getPersonneDto() {
<<<<<<< HEAD
		return personneDto;
=======
		return personne;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

	public void setPersonneDto(PersonneDto personneDto) {
<<<<<<< HEAD
		this.personneDto = personneDto;
=======
		this.personne = personneDto;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

	public CentreDto getCentreDto() {
<<<<<<< HEAD
		return centreDto;
=======
		return centre;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

	public void setCentreDto(CentreDto centreDto) {
<<<<<<< HEAD
		this.centreDto = centreDto;
=======
		this.centre = centreDto;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

}
