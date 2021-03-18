package fr.dawan.AppliCFABack.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity
public class Examen {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, length = 255)
	private Date date;
	
	@OneToMany(mappedBy = "examen", cascade = CascadeType.ALL)
	private List<Note> notes;
	
	@ManyToOne
	private ProgrammeCours programmeCours;
	
	@Version
	private int version;

	public Examen() {
		super();
	}

	public Examen(long id, Date date, List<Note> notes, ProgrammeCours programmeCours) {
		super();
		this.id = id;
		this.date = date;
		this.notes = notes;
		this.programmeCours = programmeCours;
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

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public ProgrammeCours getProgrammeCours() {
		return programmeCours;
	}

	public void setProgrammeCours(ProgrammeCours programmeCours) {
		this.programmeCours = programmeCours;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
