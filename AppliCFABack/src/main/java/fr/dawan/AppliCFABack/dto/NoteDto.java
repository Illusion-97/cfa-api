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
	private ExamenDto examenDto;
	@XmlElement
	private EtudiantDto etudiantDto;

	public NoteDto() {
		super();
	}
	
	public NoteDto(long id, Double value, ExamenDto examen, EtudiantDto etudiant) {
		super();
		this.id = id;
		this.value = value;
		this.examenDto = examen;
		this.etudiantDto = etudiant;
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
		return examenDto;
	}

	public void setExamenDto(ExamenDto examenDto) {
		this.examenDto = examenDto;
	}

	public EtudiantDto getEtudiantDto() {
		return etudiantDto;
	}

	public void setEtudiantDto(EtudiantDto etudiantDto) {
		this.etudiantDto = etudiantDto;
	}

}
