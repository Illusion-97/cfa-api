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
	private List<NoteDto> notes;
	@XmlElement
	private CoursDto cours;

	public ExamenDto() {
		super();
	}
	
	public ExamenDto(long id, Date date, List<NoteDto> notes, CoursDto cours) {
		super();
		this.id = id;
		this.date = date;
		this.notes = notes;
		this.cours = cours;
	}

	public long getIdExamenDto() {
		return id;
	}

	public void setIdExamenDto(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<NoteDto> getNoteDto() {
		return notes;
	}

	public void setNoteDto(List<NoteDto> notes) {
		this.notes = notes;
	}

	public CoursDto getCoursDto() {
		return cours;
	}

	public void setCoursDto(CoursDto cours) {
		this.cours = cours;
	}

	
}
