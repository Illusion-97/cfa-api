package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
/**
 * 
 * @author Valentin C, Feres BG.
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return DTO-DevoirEtudiant Entity
 */
@SuppressWarnings("serial")

public class DevoirEtudiantDto extends BaseEntityDto implements Serializable {

	private long devoirId;

	private long etudiantId;

	private LocalDateTime dateRendu;

	private String pieceJointe;

	/**
	 * @return the devoirId
	 */
	public long getDevoirId() {
		return devoirId;
	}

	/**
	 * @param devoirId the devoirId to set
	 */
	public void setDevoirId(long devoirId) {
		this.devoirId = devoirId;
	}

	/**
	 * @return the etudiantId
	 */
	public long getEtudiantId() {
		return etudiantId;
	}

	/**
	 * @param etudiantId the etudiantId to set
	 */
	public void setEtudiantId(long etudiantId) {
		this.etudiantId = etudiantId;
	}

	/**
	 * @return the dateRendu
	 */
	public LocalDateTime getDateRendu() {
		return dateRendu;
	}

	/**
	 * @param dateRendu the dateRendu to set
	 */
	public void setDateRendu(LocalDateTime dateRendu) {
		this.dateRendu = dateRendu;
	}

	/**
	 * @return the pieceJointe
	 */
	public String getPieceJointe() {
		return pieceJointe;
	}

	/**
	 * @param pieceJointe the pieceJointe to set
	 */
	public void setPieceJointe(String pieceJointe) {
		this.pieceJointe = pieceJointe;
	}

}
