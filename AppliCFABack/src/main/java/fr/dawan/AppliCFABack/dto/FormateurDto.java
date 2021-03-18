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
	private PersonneDto personneDto;
	@XmlElement
	private List<ProgrammeCoursDto> programmeCours;

	public FormateurDto() {
		super();
	}
	
	public FormateurDto(long id, PersonneDto personneDto, List<ProgrammeCoursDto> programmeCoursDto) {
		super();
		this.id = id;
<<<<<<< HEAD
		this.personneDto = personneDto;
		this.programmeCoursDto = programmeCoursDto;
=======
		this.personne = personneDto;
		this.programmeCours = programmeCoursDto;
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

	public List<ProgrammeCoursDto> getProgrammeCoursDto() {
		return programmeCours;
	}

	public void setProgrammeCoursDto(List<ProgrammeCoursDto> programmeCoursDto) {
		this.programmeCours = programmeCoursDto;
	}

}
