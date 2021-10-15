package fr.dawan.AppliCFABack.dto;

import java.time.LocalDate;
import java.util.List;

public class JourneePlanningDto {
	private long idIntervention;
	private LocalDate date;
	private FormationDto formationDto;
	private List<FormateurDto> formateurDto;

	public JourneePlanningDto() {
		super();
	}

	public JourneePlanningDto(LocalDate date, FormationDto formationDto, List<FormateurDto> formateurDto) {
		super();
		this.date = date;
		this.formationDto = formationDto;
		this.formateurDto = formateurDto;
	}

	public long getIdIntervention() {
		return idIntervention;
	}

	public void setIdIntervention(long idIntervention) {
		this.idIntervention = idIntervention;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public FormationDto getFormationDto() {
		return formationDto;
	}

	public void setFormationDto(FormationDto formationDto) {
		this.formationDto = formationDto;
	}

	public List<FormateurDto> getFormateurDto() {
		return formateurDto;
	}

	public void setFormateurDto(List<FormateurDto> formateurDto) {
		this.formateurDto = formateurDto;
	}

}
