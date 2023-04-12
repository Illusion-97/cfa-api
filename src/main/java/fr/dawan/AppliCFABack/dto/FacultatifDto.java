package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class FacultatifDto extends BaseEntityDto implements Serializable{
 
	 private String organisme;
	 private String intitule;	 
	 private LocalDate date;
	 private long dossierProfessionnelId;
	 
	 
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
	public long getDossierProfessionnelId() {
		return dossierProfessionnelId;
	}
	public void setDossierProfessionnelId(long dossierProfessionnelId) {
		this.dossierProfessionnelId = dossierProfessionnelId;
	}
	 
	 
	 
}
