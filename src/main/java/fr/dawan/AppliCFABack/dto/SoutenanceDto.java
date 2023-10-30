package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SoutenanceDto extends BaseEntityDto implements Serializable {
	
	private EtudiantDto etudiant;
	
	private String jour;
	
	private String heure;
	
	private int minAccueil;
	
	private int minEntretien;
	
	private int minQuestion;
	
	private int minEntretienFinal;
	
	private int minDeliberation;
	
	public SoutenanceDto() {
		super();
	}

	public EtudiantDto getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(EtudiantDto etudiant) {
		this.etudiant = etudiant;
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
	
}
