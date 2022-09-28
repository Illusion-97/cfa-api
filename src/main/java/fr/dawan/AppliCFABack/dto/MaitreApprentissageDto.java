package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;

/**
 * 
 * @author Valentin C, Feres BG.
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 2.0
 * @return DTO-maitre apprentissage Entity
 */
@SuppressWarnings("serial")
public class MaitreApprentissageDto extends BaseEntityDto implements Serializable {

	private long etudiantId;

	private UtilisateurDto utilisateurDto;
	
	private long promotionId;

	/**
	 * @return le promotionId
	 */
	public long getPromotionId() {
		return promotionId;
	}

	/**
	 * @param promotionId le promotionId à affecter
	 
	 */
	public void setPromotionId(long promotionId) {
		this.promotionId = promotionId;
	}

	/**
	 * @return le etudiantId
	 */
	public long getEtudiantId() {
		return etudiantId;
	}

	/**
	 * @param etudiantId le etudiantId à affecter
	 * 
	 */
	public void setEtudiantId(long etudiantId) {
		this.etudiantId = etudiantId;
	}

	/**
	 * @return le utilisateurDto
	 */
	public UtilisateurDto getUtilisateurDto() {
		return utilisateurDto;
	}

	/**
	 * @param utilisateurDto le utilisateurDto à affecter
	 
	 */
	public void setUtilisateurDto(UtilisateurDto utilisateurDto) {
		this.utilisateurDto = utilisateurDto;
	}





}
