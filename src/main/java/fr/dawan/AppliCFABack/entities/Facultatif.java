package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

public class Facultatif extends BaseEntity implements Serializable{

	
	 @Column(nullable = false)
	    private String libelle;
	 
	 @Column(nullable = false)
	   private String organisme;
	 
	 @Column(nullable = false)
	   private String intitule;
	 
	   private LocalDate date;
	   
     @ManyToOne
	   private DossierProfessionnel dossierProfessionnel;

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getOrganisme() {
		return organisme;
	}

	public void setOrganisme(String organisme) {
		this.organisme = organisme;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public DossierProfessionnel getDossierProfessionnel() {
		return dossierProfessionnel;
	}

	public void setDossierProfessionnel(DossierProfessionnel dossierProfessionnel) {
		this.dossierProfessionnel = dossierProfessionnel;
	}
     
     

}
