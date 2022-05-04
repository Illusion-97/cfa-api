package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.time.LocalDate;

import fr.dawan.AppliCFABack.entities.StatusConge;
import fr.dawan.AppliCFABack.entities.TypeConge;

/**
 * 
 * @author Valentin C, Feres BG.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return DTO-Conge Entity
 */
@SuppressWarnings("serial")
public class CongeDto extends BaseEntityDto implements Serializable {
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private String motif;
	private TypeConge type;
	private UtilisateurDto utilisateurDto;
	private StatusConge status;
	private String justificatif;

	public CongeDto() {
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
	 * @return the motif
	 */
	public String getMotif() {
		return motif;
	}

	/**
	 * @param motif the motif to set
	 */
	public void setMotif(String motif) {
		this.motif = motif;
	}

	/**
	 * @return the type
	 */
	public TypeConge getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(TypeConge type) {
		this.type = type;
	}

	/**
	 * @return the utilisateurDto
	 */
	public UtilisateurDto getUtilisateurDto() {
		return utilisateurDto;
	}

	/**
	 * @param utilisateurDto the utilisateurDto to set
	 */
	public void setUtilisateurDto(UtilisateurDto utilisateurDto) {
		this.utilisateurDto = utilisateurDto;
	}

	/**
	 * @return the status
	 */
	public StatusConge getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(StatusConge status) {
		this.status = status;
	}

	/**
	 * @return the justificatif
	 */
	public String getJustificatif() {
		return justificatif;
	}

	/**
	 * @param justificatif the justificatif to set
	 */
	public void setJustificatif(String justificatif) {
		this.justificatif = justificatif;
	}

}
