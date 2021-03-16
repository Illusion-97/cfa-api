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
	private List<CoursDto> coursDto;

	public FormateurDto() {
		super();
	}
	
	public FormateurDto(long id, PersonneDto personneDto, List<CoursDto> coursDto) {
		super();
		this.id = id;
		this.personneDto = personneDto;
		this.coursDto = coursDto;
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

	public List<CoursDto> getCoursDto() {
		return coursDto;
	}

	public void setCoursDto(List<CoursDto> coursDto) {
		this.coursDto = coursDto;
	}

}
