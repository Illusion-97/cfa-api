package fr.dawan.AppliCFABack.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Entity
public class Absence {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, length = 255)
	private Date dateDebut;
	
	@Column(nullable = false, length = 255)
	private Date dateFin;
	
	@Column(nullable = false, length = 255)
	private String justificatif;
	
	@ManyToOne
	private Etudiant etudiant;
	
	@Version
	private int version;

	public Absence() {
		super();
	}

	public Absence(long id, Date dateDebut, Date dateFin, String justificatif, Etudiant etudiant) {
		super();
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.justificatif = justificatif;
		this.etudiant = etudiant;
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

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
}
