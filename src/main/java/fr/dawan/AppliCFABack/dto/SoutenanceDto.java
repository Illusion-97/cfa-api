package fr.dawan.AppliCFABack.dto;

import fr.dawan.AppliCFABack.dto.customdtos.EtudiantSoutenanceDto;
import java.io.Serializable;

@SuppressWarnings("serial")
public class SoutenanceDto extends BaseEntityDto implements Serializable {
	
	// TODO Recuperation promotion formateur centre depuis etudiantDto 
	private EtudiantSoutenanceDto etudiant;
	private String jour;
	private String heure;
	private String minAccueil;
	private String minEntretien;
	private String minQuestion;
	private String minEntretienFinal;
	private String minDeliberation;
	
	public SoutenanceDto() {
		super();
	}

	public EtudiantSoutenanceDto getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(EtudiantSoutenanceDto etudiant) {
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
