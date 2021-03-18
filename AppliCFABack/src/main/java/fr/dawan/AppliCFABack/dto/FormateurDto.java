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
	private List<ProgrammeCoursDto> programmeCours;

	public FormateurDto() {
		super();
	}
	
	public FormateurDto(long id, PersonneDto personneDto, List<ProgrammeCoursDto> programmeCoursDto) {
		super();
		this.id = id;
		this.personne = personneDto;
		this.programmeCours = programmeCoursDto;
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

	public List<ProgrammeCoursDto> getProgrammeCoursDto() {
		return programmeCours;
	}

	public void setProgrammeCoursDto(List<ProgrammeCoursDto> programmeCoursDto) {
		this.programmeCours = programmeCoursDto;
	}

}
