package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("serial")
public class DevoirDto extends BaseEntityDto implements Serializable {

	private String consigne;
	
	private LocalDate dateDebut;

	private LocalDate dateFin;


	private long interventionId;


	public DevoirDto() {
		// TODO Auto-generated constructor stub
	}

	public String getConsigne() {
		return consigne;
	}

	public void setConsigne(String consigne) {
		this.consigne = consigne;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public long getInterventionId() {
		return interventionId;
	}

	public void setInterventionId(long interventionId) {
		this.interventionId = interventionId;
	}



}
