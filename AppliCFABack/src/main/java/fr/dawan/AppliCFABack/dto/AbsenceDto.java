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
	private long idAbsence;
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
	
	public AbsenceDto(long idAbsence, Date dateDebut, Date dateFin, String justificatif, EtudiantDto etudiant) {
		super();
		this.idAbsence = idAbsence;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.justificatif = justificatif;
		this.etudiant = etudiant;
	}



	public long getIdAbsence() {
		return idAbsence;
	}

	public void setIdAbsence(long idAbsence) {
		this.idAbsence = idAbsence;
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

	public void setEtudiantDto(EtudiantDto etudiant) {
		this.etudiant = etudiant;
	}

}
