package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Entity
public class Absence implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Version
	private int version;
	
	@Column(nullable = false)
	private LocalDate dateDebut;
	
	private LocalDate dateFin;

	@Column(length = 255)
	private String justificatif;

	@ManyToOne
	private Etudiant etudiant;
	
	@ManyToOne
	private Intervention intervention;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TypeAbsence typeAbsence;
	
	public enum TypeAbsence {
		RETARD,
		ABSENCE
	}

	public Absence() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public String getJustificatif() {
		return justificatif;
	}

	public Intervention getIntervention() {
		return intervention;
	}

	public void setIntervention(Intervention intervention) {
		this.intervention = intervention;
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

	public TypeAbsence getTypeAbsence() {
		return typeAbsence;
	}

	public void setTypeAbsence(TypeAbsence typeAbsence) {
		this.typeAbsence = typeAbsence;
	}

		
}
