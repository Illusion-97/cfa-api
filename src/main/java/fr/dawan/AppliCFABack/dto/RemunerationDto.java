package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 
 * 
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return DTO-rémunération Entity
 */
@SuppressWarnings("serial")
public class RemunerationDto extends BaseEntityDto implements Serializable {

	private LocalDate dateDebut;

	private LocalDate dateFin;

	private String pourcentage;

	private String smicOuSmc;

	public RemunerationDto() {
		super();
	}

	/**
	 * @return the dateDebut
	 */
	public LocalDate getDateDebut() {
		return dateDebut;
	}

	/**
	 * @param dateDebut the dateDebut to set
	 */
	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * @return the dateFin
	 */
	public LocalDate getDateFin() {
		return dateFin;
	}

	/**
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * @return the pourcentage
	 */
	public String getPourcentage() {
		return pourcentage;
	}

	/**
	 * @param pourcentage the pourcentage to set
	 */
	public void setPourcentage(String pourcentage) {
		this.pourcentage = pourcentage;
	}

	/**
	 * @return the smicOuSmc
	 */
	public String getSmicOuSmc() {
		return smicOuSmc;
	}

	/**
	 * @param smicOuSmc the smicOuSmc to set
	 */
	public void setSmicOuSmc(String smicOuSmc) {
		this.smicOuSmc = smicOuSmc;
	}

}
