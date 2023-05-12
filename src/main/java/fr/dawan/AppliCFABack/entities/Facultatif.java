package fr.dawan.AppliCFABack.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("serial")
@Entity
public class Facultatif extends BaseEntity implements Serializable{

	
	 @Column(length = 50)
	   private String organisme;
	 
	 @Column(length = 50)
	   private String intitule;
	 
	   private LocalDate date;
	   
     @ManyToOne
	   private DossierProfessionnel dossierProfessionnel;

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
