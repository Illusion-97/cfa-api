package fr.dawan.AppliCFABack.dto;

import fr.dawan.AppliCFABack.dto.customdtos.EtudiantSoutenanceDto;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class SoutenanceDto extends BaseEntityDto implements Serializable {

	private EtudiantSoutenanceDto etudiant;
	private Date examDate;

	private Date heure;
	private Date minAccueil;
	private Date minEntretien;
	private Date minQuestion;
	private Date minEntretienFinal;
	private Date minDeliberation;

	public SoutenanceDto() {
		super();
	}


	public EtudiantSoutenanceDto getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(EtudiantSoutenanceDto etudiant) {
		this.etudiant = etudiant;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public Date getHeure() {
		return heure;
	}

	public void setHeure(Date heure) {
		this.heure = heure;
	}

	public Date getMinAccueil() {
		return minAccueil;
	}

	public void setMinAccueil(Date minAccueil) {
		this.minAccueil = minAccueil;
	}

	public Date getMinEntretien() {
		return minEntretien;
	}

	public void setMinEntretien(Date minEntretien) {
		this.minEntretien = minEntretien;
	}

	public Date getMinQuestion() {
		return minQuestion;
	}

	public void setMinQuestion(Date minQuestion) {
		this.minQuestion = minQuestion;
	}

	public Date getMinEntretienFinal() {
		return minEntretienFinal;
	}

	public void setMinEntretienFinal(Date minEntretienFinal) {
		this.minEntretienFinal = minEntretienFinal;
	}

	public Date getMinDeliberation() {
		return minDeliberation;
	}

	public void setMinDeliberation(Date minDeliberation) {
		this.minDeliberation = minDeliberation;
	}
}
