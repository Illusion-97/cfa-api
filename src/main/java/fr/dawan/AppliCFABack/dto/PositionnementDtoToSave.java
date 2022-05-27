package fr.dawan.AppliCFABack.dto;

import fr.dawan.AppliCFABack.entities.Positionnement.Niveau;

public class PositionnementDtoToSave {

	private NiveauDto niveauDebut;

	private NiveauDto niveauFin;

	private long interventionId;

	private long etudiantId;

	/**
	 * @return le niveauDebut
	 */
	public NiveauDto getNiveauDebut() {
		return niveauDebut;
	}

	/**
	 * @param niveauDebut le niveauDebut à affecter
	 
	 */
	public void setNiveauDebut(NiveauDto niveauDebut) {
		this.niveauDebut = niveauDebut;
	}

	/**
	 * @return le niveauFin
	 */
	public NiveauDto getNiveauFin() {
		return niveauFin;
	}

	/**
	 * @param niveauFin le niveauFin à affecter
	 
	 */
	public void setNiveauFin(NiveauDto niveauFin) {
		this.niveauFin = niveauFin;
	}

	/**
	 * @return le interventionId
	 */
	public long getInterventionId() {
		return interventionId;
	}

	/**
	 * @param interventionId le interventionId à affecter
	 
	 */
	public void setInterventionId(long interventionId) {
		this.interventionId = interventionId;
	}

	/**
	 * @return le etudiantId
	 */
	public long getEtudiantId() {
		return etudiantId;
	}

	/**
	 * @param etudiantId le etudiantId à affecter
	 
	 */
	public void setEtudiantId(long etudiantId) {
		this.etudiantId = etudiantId;
	}
	
	
}
