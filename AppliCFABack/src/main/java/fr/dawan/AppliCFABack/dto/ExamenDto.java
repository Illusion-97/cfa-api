package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.sql.Date;
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
	private ProgrammeCoursDto programmeCoursDto;

	public ExamenDto() {
		super();
	}
	
	public ExamenDto(long id, Date date, List<NoteDto> noteDto, ProgrammeCoursDto programmeCoursDto) {
		super();
		this.id = id;
		this.date = date;
		this.noteDto = noteDto;
		this.programmeCoursDto = programmeCoursDto;
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

	public List<NoteDto> getNote() {
		return notes;
	}

	public void setNote(List<NoteDto> notes) {
		this.notes = notes;
	}

	public ProgrammeCoursDto getProgrammeCoursDto() {
		return programmeCoursDto;
	}

	public void setProgrammeCoursDto(ProgrammeCoursDto programmeCoursDto) {
		this.programmeCoursDto = programmeCoursDto;
	}

	
}
