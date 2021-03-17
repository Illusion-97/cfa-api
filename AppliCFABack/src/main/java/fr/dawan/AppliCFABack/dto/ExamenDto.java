package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "examen")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExamenDto implements Serializable {
	@XmlElement
	private long id;
	@XmlElement
	private Date date;
	@XmlElement
	private List<NoteDto> noteDto;
	@XmlElement
	private CoursDto coursDto;

	public ExamenDto() {
		super();
	}
	
	public ExamenDto(long id, Date date, List<NoteDto> noteDto, CoursDto coursDto) {
		super();
		this.id = id;
		this.date = date;
		this.noteDto = noteDto;
		this.coursDto = coursDto;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<NoteDto> getNoteDto() {
		return noteDto;
	}

	public void setNoteDto(List<NoteDto> noteDto) {
		this.noteDto = noteDto;
	}

	public CoursDto getCoursDto() {
		return coursDto;
	}

	public void setCoursDto(CoursDto coursDto) {
		this.coursDto = coursDto;
	}

	
}
