package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("serial")
public class RemunerationDto extends BaseEntityDto implements Serializable {

	private LocalDate dateDebut;

	private LocalDate dateFin;

	private String pourcentage;

	private String smicOuSmc;

	public RemunerationDto() {
		super();
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

	public String getPourcentage() {
		return pourcentage;
	}

	public void setPourcentage(String pourcentage) {
		this.pourcentage = pourcentage;
	}

	public String getSmicOuSmc() {
		return smicOuSmc;
	}

	public void setSmicOuSmc(String smicOuSmc) {
		this.smicOuSmc = smicOuSmc;
	}

}
