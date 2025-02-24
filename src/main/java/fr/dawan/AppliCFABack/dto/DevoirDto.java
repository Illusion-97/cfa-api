package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 * 
 * @author Valentin C, Feres BG.
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return DTO-Devoir Entity
 */
@SuppressWarnings("serial")
public class DevoirDto extends BaseEntityDto implements Serializable {

	private String consigne;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private long interventionId;
	private Set<DevoirEtudiantDto> devoirsEtudiantDto;

	/**
	 * @return the consigne
	 */
	public String getConsigne() {
		return consigne;
	}

	/**
	 * @param consigne the consigne to set
	 */
	public void setConsigne(String consigne) {
		this.consigne = consigne;
	}


	/**
	 * @return le dateDebut
	 */
	public LocalDate getDateDebut() {
		return dateDebut;
	}

	/**
	 * @param dateDebut le dateDebut à affecter
	 
	 */
	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * @return le dateFin
	 */
	public LocalDate getDateFin() {
		return dateFin;
	}

	/**
	 * @param dateFin le dateFin à affecter
	 
	 */
	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * @return the interventionId
	 */
	public long getInterventionId() {
		return interventionId;
	}

	/**
	 * @param interventionId the interventionId to set
	 */
	public void setInterventionId(long interventionId) {
		this.interventionId = interventionId;
	}

	/**
	 * @return le devoirsEtudiantDto
	 */
	public Set<DevoirEtudiantDto> getDevoirsEtudiantDto() {
		return devoirsEtudiantDto;
	}

	/**
	 * @param devoirsEtudiantDto le devoirsEtudiantDto à affecter
	 
	 */
	public void setDevoirsEtudiantDto(Set<DevoirEtudiantDto> devoirsEtudiantDto) {
		this.devoirsEtudiantDto = devoirsEtudiantDto;
	}
	

}
