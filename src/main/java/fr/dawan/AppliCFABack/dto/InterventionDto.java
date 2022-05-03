package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("serial")
public class InterventionDto extends BaseEntityDto implements Serializable {

	private LocalDate dateDebut; // 12/03
	private LocalDate dateFin; // 18/03
	private FormationDto formationDto; // Java init
	private InterventionDto interventionMereDto; // Java init+appro
	private List<PromotionDto> promotionsDto; // CDA 2021
	private List<FormateurDto> formateursDto;
	private String noteInfoPersonnel;
	private long heuresDisponsees;

	public InterventionDto() {
		// TODO Auto-generated constructor stub

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

	public FormationDto getFormationDto() {
		return formationDto;
	}

	public void setFormationDto(FormationDto formationDto) {
		this.formationDto = formationDto;
	}

	public InterventionDto getInterventionMereDto() {
		return interventionMereDto;
	}

	public void setInterventionMereDto(InterventionDto interventionMereDto) {
		this.interventionMereDto = interventionMereDto;
	}

	public List<PromotionDto> getPromotionsDto() {
		return promotionsDto;
	}

	public void setPromotionsDto(List<PromotionDto> promotionsDto) {
		this.promotionsDto = promotionsDto;
	}

	public List<FormateurDto> getFormateursDto() {
		return formateursDto;
	}

	public void setFormateursDto(List<FormateurDto> formateursDto) {
		this.formateursDto = formateursDto;
	}

	public String getNoteInfoPersonnel() {
		return noteInfoPersonnel;
	}

	public void setNoteInfoPersonnel(String noteInfoPersonnel) {
		this.noteInfoPersonnel = noteInfoPersonnel;
	}

	public long getHeuresDisponsees() {
		return heuresDisponsees;
	}

	public void setHeuresDisponsees() {
		if (this.getDateDebut() != null && this.getDateFin() != null) {
			this.heuresDisponsees = Duration
					.between(this.getDateDebut().atStartOfDay(), this.getDateFin().atStartOfDay()).toDays() * 7;

		}
	}

	public void setHeuresDisponsees(long heuresDisponsees) {
		this.heuresDisponsees = heuresDisponsees;
	}

}
