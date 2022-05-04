package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;

import fr.dawan.AppliCFABack.entities.Positionnement.Niveau;

/**
 * 
 * 
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return DTO-Positionnement Entity
 */
@SuppressWarnings("serial")

public class PositionnementDto extends BaseEntityDto implements Serializable {

	private Niveau niveauDebut;

	private Niveau niveauFin;

	private long interventionId;

	private long etudiantId;

	/**
	 * @return the niveauDebut
	 */
	public Niveau getNiveauDebut() {
		return niveauDebut;
	}

	/**
	 * @param niveauDebut the niveauDebut to set
	 */
	public void setNiveauDebut(Niveau niveauDebut) {
		this.niveauDebut = niveauDebut;
	}

	/**
	 * @return the niveauFin
	 */
	public Niveau getNiveauFin() {
		return niveauFin;
	}

	/**
	 * @param niveauFin the niveauFin to set
	 */
	public void setNiveauFin(Niveau niveauFin) {
		this.niveauFin = niveauFin;
	}

	/**
	 * @return the interventionId
	 */
	public long getInterventionId() {
		return interventionId;
	}

	/**
	 * @param interventionId the interventionId to set
	 */
	public void setInterventionId(long interventionId) {
		this.interventionId = interventionId;
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

}
