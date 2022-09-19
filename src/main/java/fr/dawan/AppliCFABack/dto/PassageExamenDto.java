package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 
 * 
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return DTO-passage examen Entity
 */
@SuppressWarnings("serial")
public class PassageExamenDto extends BaseEntityDto implements Serializable {

	private LocalDate dateDebut;
	private LocalDate dateFin;
	private ExamenDto examenDto;
	private InterventionDto interventionDto;

	public PassageExamenDto() {
		
	}

	public PassageExamenDto(long id, LocalDate dateDebut, LocalDate dateFin, ExamenDto examenDto,
			InterventionDto interventionDto) {
		super();
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.examenDto = examenDto;
		this.interventionDto = interventionDto;
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
	 * @return the examenDto
	 */
	public ExamenDto getExamenDto() {
		return examenDto;
	}

	/**
	 * @param examenDto the examenDto to set
	 */
	public void setExamenDto(ExamenDto examenDto) {
		this.examenDto = examenDto;
	}

	/**
	 * @return the interventionDto
	 */
	public InterventionDto getInterventionDto() {
		return interventionDto;
	}

	/**
	 * @param interventionDto the interventionDto to set
	 */
	public void setInterventionDto(InterventionDto interventionDto) {
		this.interventionDto = interventionDto;
	}

}
