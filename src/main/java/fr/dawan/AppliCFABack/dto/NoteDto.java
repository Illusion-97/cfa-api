package fr.dawan.AppliCFABack.dto;

import fr.dawan.AppliCFABack.entities.Note.Satisfaction;

import java.io.Serializable;

/**
 * 
 * 
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return DTO-note Entity
 */
@SuppressWarnings("serial")
public class NoteDto extends BaseEntityDto implements Serializable {
	private double noteObtenue;
	private Satisfaction satisfaction;
	private long etudiantNoteId;
	private long examenId;
	private String etudiantNoteUtilisateurNom;
	private String etudiantNoteUtilisateurPrenom;
	private String observation;
	private String ville;
	
	public NoteDto() {
		super();
	}

	public NoteDto(double noteObtenue, long etudiantNoteId) {
		super();
		this.noteObtenue = noteObtenue;
		this.etudiantNoteId = etudiantNoteId;
	}

	public NoteDto(Satisfaction satisfaction) {
		super();
		this.satisfaction = satisfaction;
	}

	public NoteDto(String observation) {
		super();
		this.observation = observation;
	}

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
	 * @return the etudiantNoteUtilisateurNom
	 */
	public String getEtudiantNoteUtilisateurNom() {
		return etudiantNoteUtilisateurNom;
	}

	/**
	 * @param etudiantNoteUtilisateurNom the etudiantNoteUtilisateurNom to set
	 */
	public void setEtudiantNoteUtilisateurNom(String etudiantNoteUtilisateurNom) {
		this.etudiantNoteUtilisateurNom = etudiantNoteUtilisateurNom;
	}

	/**
	 * @return the etudiantNoteUtilisateurPrenom
	 */
	public String getEtudiantNoteUtilisateurPrenom() {
		return etudiantNoteUtilisateurPrenom;
	}

	/**
	 * @param etudiantNoteUtilisateurPrenom the etudiantNoteUtilisateurPrenom to set
	 */
	public void setEtudiantNoteUtilisateurPrenom(String etudiantNoteUtilisateurPrenom) {
		this.etudiantNoteUtilisateurPrenom = etudiantNoteUtilisateurPrenom;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	/**
	 * @return la ville
	 */
	public String getVille() {
		return ville;
	}

	/**
	 * @param ville la ville à affecter
	 
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}



	
}
