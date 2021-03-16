package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "note")
@XmlAccessorType(XmlAccessType.FIELD)
public class NoteDto implements Serializable {

	private long id;

	private Double value;

	private ExamenDto examen;

	private EtudiantDto etudiant;

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

	public ExamenDto getExamen() {
		return examen;
	}

	public void setExamen(ExamenDto examen) {
		this.examen = examen;
	}

	public EtudiantDto getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(EtudiantDto etudiant) {
		this.etudiant = etudiant;
	}

}
