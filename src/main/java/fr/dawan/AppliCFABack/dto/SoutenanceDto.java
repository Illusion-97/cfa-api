package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.Promotion;

@SuppressWarnings("serial")
public class SoutenanceDto extends BaseEntityDto implements Serializable {
	
	private EtudiantDto etudiant;
	
	private String jour;
	
	private String date;
	
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
	
}
