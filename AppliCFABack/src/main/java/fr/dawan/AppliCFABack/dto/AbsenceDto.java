package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "absence")
@XmlAccessorType(XmlAccessType.FIELD)
public class AbsenceDto implements Serializable {
	@XmlElement
	private long id;
	@XmlElement
	private Date dateDebut;
	@XmlElement
	private Date dateFin;
	@XmlElement
	private String justificatif;
	@XmlElement
	private EtudiantDto etudiant;

	public AbsenceDto() {
		super();
	}
	
	public AbsenceDto(long id, Date dateDebut, Date dateFin, String justificatif, EtudiantDto etudiantDto) {
		super();
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.justificatif = justificatif;
		this.etudiant = etudiantDto;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public String getJustificatif() {
		return justificatif;
	}

	public void setJustificatif(String justificatif) {
		this.justificatif = justificatif;
	}

	public EtudiantDto getEtudiantDto() {
		return etudiant;
	}

	public void setEtudiantDto(EtudiantDto etudiantDto) {
		this.etudiant = etudiantDto;
	}

}
