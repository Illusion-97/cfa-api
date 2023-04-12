package fr.dawan.AppliCFABack.dto;

import java.time.LocalDate;
import java.util.List;

/**
 * 
 * @author Valentin C, Feres BG.
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return DTO-journée planning Entity
 */
public class JourneePlanningDto {
	private long idIntervention;
	private LocalDate date;
	private FormationDto formationDto;
	private FormateurDto formateurDto;

	public JourneePlanningDto() {
		super();
	}

	public JourneePlanningDto(LocalDate date, FormationDto formationDto, FormateurDto formateurDto) {
		super();
		this.date = date;
		this.formationDto = formationDto;
		this.formateurDto = formateurDto;
	}

	/**
	 * @return the idIntervention
	 */
	public long getIdIntervention() {
		return idIntervention;
	}

	/**
	 * @param idIntervention the idIntervention to set
	 */
	public void setIdIntervention(long idIntervention) {
		this.idIntervention = idIntervention;
	}

	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * @return the formationDto
	 */
	public FormationDto getFormationDto() {
		return formationDto;
	}

	/**
	 * @param formationDto the formationDto to set
	 */
	public void setFormationDto(FormationDto formationDto) {
		this.formationDto = formationDto;
	}

	/**
	 * @return the formateurDto
	 */
	public FormateurDto getFormateurDto() {
		return formateurDto;
	}

	/**
	 * @param formateurDto the formateurDto to set
	 */
	public void setFormateurDto(FormateurDto formateurDto) {
		this.formateurDto = formateurDto;
	}

}
