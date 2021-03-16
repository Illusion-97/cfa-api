package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "examen")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExamenDto implements Serializable {

	private long id;

	private Date date;

	private List<NoteDto> notes;

	private CoursDto cours;

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

	public List<NoteDto> getNotes() {
		return notes;
	}

	public void setNotes(List<NoteDto> notes) {
		this.notes = notes;
	}

	public CoursDto getCours() {
		return cours;
	}

	public void setCours(CoursDto cours) {
		this.cours = cours;
	}
	
	
}
