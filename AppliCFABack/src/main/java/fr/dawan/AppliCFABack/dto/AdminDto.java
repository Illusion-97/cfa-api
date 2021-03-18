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
	private PersonneDto personneDto;

	
	public AdminDto() {
		super();
	}

	public AdminDto(long id, PersonneDto personneDto) {
		super();
		this.id = id;
<<<<<<< HEAD
		this.personneDto = personne;
=======
		this.personne = personneDto;
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

<<<<<<< HEAD
	public void setPersonneDto(PersonneDto personne) {
		this.personneDto = personne;
=======
	public void setPersonneDto(PersonneDto personneDto) {
		this.personne = personneDto;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

	
}
