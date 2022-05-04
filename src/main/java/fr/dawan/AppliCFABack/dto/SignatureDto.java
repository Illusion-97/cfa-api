package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;

/**
 * 
 *
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return DTO-signature Entity
 */
@SuppressWarnings("serial")
public class SignatureDto extends BaseEntityDto implements Serializable {

	private String pieceJointe;

	private long utilisateurId;

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

	/**
	 * @return the utilisateurId
	 */
	public long getUtilisateurId() {
		return utilisateurId;
	}

	/**
	 * @param utilisateurId the utilisateurId to set
	 */
	public void setUtilisateurId(long utilisateurId) {
		this.utilisateurId = utilisateurId;
	}

}
