package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity
public class Soutenance extends BaseEntity implements Serializable {
		
	@OneToOne
	//@Column(nullable = true)
	private Etudiant etudiant;
	private String jour;
	private String heure;
	private String minAccueil;
	private String minEntretien;
	private String minQuestion;
	private String minEntretienFinal;
	private String minDeliberation;
	 

	public Soutenance() {
		super();
	}


	public Etudiant getEtudiant() {
		return etudiant;
	}


	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}


	public String getJour() {
		return jour;
	}


	public void setJour(String jour) {
		this.jour = jour;
	}


	public String getHeure() {
		return heure;
	}


	public void setHeure(String heure) {
		this.heure = heure;
	}


	public String getMinAccueil() {
		return minAccueil;
	}


	public void setMinAccueil(String minAccueil) {
		this.minAccueil = minAccueil;
	}


	public String getMinEntretien() {
		return minEntretien;
	}


	public void setMinEntretien(String minEntretien) {
		this.minEntretien = minEntretien;
	}


	public String getMinQuestion() {
		return minQuestion;
	}


	public void setMinQuestion(String minQuestion) {
		this.minQuestion = minQuestion;
	}


	public String getMinEntretienFinal() {
		return minEntretienFinal;
	}


	public void setMinEntretienFinal(String minEntretienFinal) {
		this.minEntretienFinal = minEntretienFinal;
	}


	public String getMinDeliberation() {
		return minDeliberation;
	}


	public void setMinDeliberation(String minDeliberation) {
		this.minDeliberation = minDeliberation;
	}

}
