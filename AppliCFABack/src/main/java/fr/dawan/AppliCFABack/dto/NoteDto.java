package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "note")
@XmlAccessorType(XmlAccessType.FIELD)
public class NoteDto implements Serializable {
	@XmlElement
	private long id;
	@XmlElement
	private Double value;
	@XmlElement
	private ExamenDto examen;
	@XmlElement
	private EtudiantDto etudiant;

	public NoteDto() {
		super();
	}
	
	public NoteDto(long id, Double value, ExamenDto examenDto, EtudiantDto etudiantDto) {
		super();
		this.id = id;
		this.value = value;
		this.examen = examenDto;
		this.etudiant = etudiantDto;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public ExamenDto getExamenDto() {
		return examen;
	}

	public void setExamenDto(ExamenDto examenDto) {
		this.examen = examenDto;
	}

	public EtudiantDto getEtudiantDto() {
		return etudiant;
	}

	public void setEtudiantDto(EtudiantDto etudiantDto) {
		this.etudiant = etudiantDto;
	}

	
}
