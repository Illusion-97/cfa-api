package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 
 * @author Valentin C, Feres BG.
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return DTO-Contrat Entity
 */
@SuppressWarnings("serial")
public class ContratDto extends BaseEntityDto implements Serializable {

	private LocalDate dateDebut;
	private LocalDate dateFin;
	private MaitreApprentissageDto maitreApprentissageDto;
	private EtudiantDto etudiantDto;

	public ContratDto() {
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
	 * @return the maitreApprentissageDto
	 */
	public MaitreApprentissageDto getMaitreApprentissageDto() {
		return maitreApprentissageDto;
	}

	/**
	 * @param maitreApprentissageDto the maitreApprentissageDto to set
	 */
	public void setMaitreApprentissageDto(MaitreApprentissageDto maitreApprentissageDto) {
		this.maitreApprentissageDto = maitreApprentissageDto;
	}

	/**
	 * @return the etudiantDto
	 */
	public EtudiantDto getEtudiantDto() {
		return etudiantDto;
	}

	/**
	 * @param etudiantDto the etudiantDto to set
	 */
	public void setEtudiantDto(EtudiantDto etudiantDto) {
		this.etudiantDto = etudiantDto;
	}

}
