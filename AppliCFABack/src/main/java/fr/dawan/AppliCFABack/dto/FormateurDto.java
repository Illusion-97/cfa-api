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
	private List<ProgrammeCoursDto> programmeCoursDto;

	public FormateurDto() {
		super();
	}
	
	public FormateurDto(long id, PersonneDto personneDto, List<ProgrammeCoursDto> programmeCoursDto) {
		super();
		this.id = id;
		this.personne = personneDto;
		this.programmeCoursDto = programmeCoursDto;
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

	public List<ProgrammeCoursDto> getProgrammeCoursDto() {
		return programmeCoursDto;
	}

	public void setProgrammeCoursDto(List<ProgrammeCoursDto> programmeCoursDto) {
		this.programmeCoursDto = programmeCoursDto;
	}

}
