package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

/**
 * 
 * * @see fr.dawan.appliCFABack.dto
 * 
 * @since 1.0
 * @version 1.0
 * @return DTO-Intervention Entity
 */
@SuppressWarnings("serial")
public class InterventionDto extends BaseEntityDto implements Serializable {

	private LocalDate dateDebut; // 12/03
	private LocalDate dateFin; // 18/03
	private FormationDto formationDto; // Java init
	private InterventionDto interventionMereDto; // Java init+appro
	private List<PromotionDto> promotionsDto; // CDA 2021
	private List<FormateurDto> formateursDto;
	private List<Long> promotionsId;
	private String noteInfoPersonnel;
	private long heuresDisponsees;
	

	public InterventionDto() {
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
	 * @return the interventionMereDto
	 */
	public InterventionDto getInterventionMereDto() {
		return interventionMereDto;
	}

	/**
	 * @param interventionMereDto the interventionMereDto to set
	 */
	public void setInterventionMereDto(InterventionDto interventionMereDto) {
		this.interventionMereDto = interventionMereDto;
	}

	/**
	 * @return the promotionsDto
	 */
	public List<PromotionDto> getPromotionsDto() {
		return promotionsDto;
	}

	/**
	 * @param promotionsDto the promotionsDto to set
	 */
	public void setPromotionsDto(List<PromotionDto> promotionsDto) {
		this.promotionsDto = promotionsDto;
	}

	/**
	 * @return the formateursDto
	 */
	public List<FormateurDto> getFormateursDto() {
		return formateursDto;
	}

	/**
	 * @param formateursDto the formateursDto to set
	 */
	public void setFormateursDto(List<FormateurDto> formateursDto) {
		this.formateursDto = formateursDto;
	}

	/**
	 * @return the noteInfoPersonnel
	 */
	public String getNoteInfoPersonnel() {
		return noteInfoPersonnel;
	}

	/**
	 * @param noteInfoPersonnel the noteInfoPersonnel to set
	 */
	public void setNoteInfoPersonnel(String noteInfoPersonnel) {
		this.noteInfoPersonnel = noteInfoPersonnel;
	}

	/**
	 * @return the heuresDisponsees
	 */
	public long getHeuresDisponsees() {
		return heuresDisponsees;
	}
	/**
	 * @return le promotionsId
	 */
	public List<Long> getPromotionsId() {
		return promotionsId;
	}

	/**
	 * @param promotionsId le promotionsId à affecter
	 
	 */
	public void setPromotionsId(List<Long> promotionsId) {
		this.promotionsId = promotionsId;
	}

	public void setHeuresDisponsees() {
		if (this.getDateDebut() != null && this.getDateFin() != null) {
			this.heuresDisponsees = Duration
					.between(this.getDateDebut().atStartOfDay(), this.getDateFin().atStartOfDay()).toDays() * 7;

		}
	}

}
