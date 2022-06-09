package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Absence extends BaseEntity implements Serializable {

	@Column(nullable = false)
	private LocalDateTime dateDebut;
	@Column(nullable = false)
	private LocalDateTime dateFin;

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

	public LocalDateTime getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDateTime dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDateTime getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDateTime dateFin) {
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


	public TypeAbsence getTypeAbsence() {
		return typeAbsence;
	}

	public void setTypeAbsence(TypeAbsence typeAbsence) {
		this.typeAbsence = typeAbsence;
	}

		
}
