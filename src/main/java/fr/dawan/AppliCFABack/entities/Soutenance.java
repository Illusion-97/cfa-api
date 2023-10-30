package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity
public class Soutenance extends BaseEntity implements Serializable {
		
	@OneToOne
	private Etudiant etudiant;
	
	private String jour;
	
	private String heure;
	
	@Column(nullable = true)
	private int minAccueil;
	
	@Column(nullable = true)
	private int minEntretien;
	
	@Column(nullable = true)
	private int minQuestion;
	
	@Column(nullable = true)
	private int minEntretienFinal;
	
	@Column(nullable = true)
	private int minDeliberation;
	 

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


	public int getMinAccueil() {
		return minAccueil;
	}


	public void setMinAccueil(int minAccueil) {
		this.minAccueil = minAccueil;
	}


	public int getMinEntretien() {
		return minEntretien;
	}


	public void setMinEntretien(int minEntretien) {
		this.minEntretien = minEntretien;
	}


	public int getMinQuestion() {
		return minQuestion;
	}


	public void setMinQuestion(int minQuestion) {
		this.minQuestion = minQuestion;
	}


	public int getMinEntretienFinal() {
		return minEntretienFinal;
	}


	public void setMinEntretienFinal(int minEntretienFinal) {
		this.minEntretienFinal = minEntretienFinal;
	}


	public int getMinDeliberation() {
		return minDeliberation;
	}


	public void setMinDeliberation(int minDeliberation) {
		this.minDeliberation = minDeliberation;
	}

	
}
