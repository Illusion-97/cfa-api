package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "devoir")
@XmlAccessorType(XmlAccessType.FIELD)
public class DevoirDto implements Serializable {
	@XmlElement
	private long id;
	@XmlElement
	private String nom;
	@XmlElement
	private String consigne;
	@XmlElement
	private Date debut;
	@XmlElement
	private Date fin;
	@XmlElement
	private CoursDto cours;

	public DevoirDto() {
		super();
	}
	
	public DevoirDto(long id, String nom, String consigne, Date debut, Date fin, CoursDto cours) {
		super();
		this.id = id;
		this.nom = nom;
		this.consigne = consigne;
		this.debut = debut;
		this.fin = fin;
		this.cours = cours;
	}

	public long getIdDevoirDto() {
		return id;
	}

	public void setIdDevoirDto(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getConsigne() {
		return consigne;
	}

	public void setConsigne(String consigne) {
		this.consigne = consigne;
	}

	public Date getDebut() {
		return debut;
	}

	public void setDebut(Date debut) {
		this.debut = debut;
	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	public CoursDto getCoursDto() {
		return cours;
	}

	public void setCoursDto(CoursDto cours) {
		this.cours = cours;
	}
	
}
