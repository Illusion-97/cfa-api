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

	private long utilisateurId;

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
	 * @return le utilisateurId
	 */
	public long getUtilisateurId() {
		return utilisateurId;
	}

	/**
	 * @param utilisateurId le utilisateurId à affecter
	 * 
	 */
	public void setUtilisateurId(long utilisateurId) {
		this.utilisateurId = utilisateurId;
	}

}
