package fr.dawan.AppliCFABack.dto;

import fr.dawan.AppliCFABack.entities.Note.Satisfaction;

import java.io.Serializable;

/**
 * 
 * @author Valentin C, Feres BG.
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return DTO d'une note à entrer en base de données
 */
@SuppressWarnings("serial")
public class NoteDtoToSave extends BaseEntityDto implements Serializable {

	private double noteObtenue;
	private Satisfaction satisfaction;
	private long etudiantNoteId;
	private long examenId;
	private String observation;

	/**
	 * @return the noteObtenue
	 */
	public double getNoteObtenue() {
		return noteObtenue;
	}

	/**
	 * @param noteObtenue the noteObtenue to set
	 */
	public void setNoteObtenue(double noteObtenue) {
		this.noteObtenue = noteObtenue;
	}

	/**
	 * @return the satisfaction
	 */
	public Satisfaction getSatisfaction() {
		return satisfaction;
	}

	/**
	 * @param satisfaction the satisfaction to set
	 */
	public void setSatisfaction(Satisfaction satisfaction) {
		this.satisfaction = satisfaction;
	}

	/**
	 * @return the etudiantNoteId
	 */
	public long getEtudiantNoteId() {
		return etudiantNoteId;
	}

	/**
	 * @param etudiantNoteId the etudiantNoteId to set
	 */
	public void setEtudiantNoteId(long etudiantNoteId) {
		this.etudiantNoteId = etudiantNoteId;
	}

	/**
	 * @return the examenId
	 */
	public long getExamenId() {
		return examenId;
	}

	/**
	 * @param examenId the examenId to set
	 */
	public void setExamenId(long examenId) {
		this.examenId = examenId;
	}

	/**
	 * @return le observation
	 */
	public String getObservation() {
		return observation;
	}

	/**
	 * @param observation le observation à affecter
	 
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}


	
}
