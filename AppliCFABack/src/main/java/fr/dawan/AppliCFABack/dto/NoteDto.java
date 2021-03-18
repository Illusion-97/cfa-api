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
	
	public NoteDto(long id, Double value, ExamenDto examenDto, EtudiantDto etudiantDto) {
		super();
		this.id = id;
		this.value = value;
<<<<<<< HEAD
		this.examenDto = examen;
		this.etudiantDto = etudiant;
=======
		this.examen = examenDto;
		this.etudiant = etudiantDto;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
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
<<<<<<< HEAD
		return examenDto;
=======
		return examen;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

	public void setExamenDto(ExamenDto examenDto) {
<<<<<<< HEAD
		this.examenDto = examenDto;
=======
		this.examen = examenDto;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

	public EtudiantDto getEtudiantDto() {
<<<<<<< HEAD
		return etudiantDto;
=======
		return etudiant;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

	public void setEtudiantDto(EtudiantDto etudiantDto) {
<<<<<<< HEAD
		this.etudiantDto = etudiantDto;
=======
		this.etudiant = etudiantDto;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

}
