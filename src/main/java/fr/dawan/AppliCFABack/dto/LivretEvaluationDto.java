package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.time.LocalDate;

import fr.dawan.AppliCFABack.entities.LivretEvaluation.EtatLivertEval;
/**
 * 
 * @author Feres BG.
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 */
@SuppressWarnings("serial")
public class LivretEvaluationDto extends BaseEntityDto implements Serializable{

	private long etudiantId;
	private long titreProfessionnelId;
	private long organismeFormationId;
	private String observation;
	private long formateurEvaluateurId;
	private LocalDate dateSignature;
	private EtatLivertEval etat;


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

	/**
	 * @return le titreProfessionnelId
	 */
	public long getTitreProfessionnelId() {
		return titreProfessionnelId;
	}
	/**
	 * @param titreProfessionnelId le titreProfessionnelId à affecter
	 
	 */
	public void setTitreProfessionnelId(long titreProfessionnelId) {
		this.titreProfessionnelId = titreProfessionnelId;
	}
	/**
	 * @return le organismeFormationId
	 */
	public long getOrganismeFormationId() {
		return organismeFormationId;
	}
	/**
	 * @param organismeFormationId le organismeFormationId à affecter
	 
	 */
	public void setOrganismeFormationId(long organismeFormationId) {
		this.organismeFormationId = organismeFormationId;
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
	/**
	 * @return le formateurEvaluateurId
	 */
	public long getFormateurEvaluateurId() {
		return formateurEvaluateurId;
	}
	/**
	 * @param formateurEvaluateurId le formateurEvaluateurId à affecter
	 
	 */
	public void setFormateurEvaluateurId(long formateurEvaluateurId) {
		this.formateurEvaluateurId = formateurEvaluateurId;
	}
	/**
	 * @return le dateSignature
	 */
	public LocalDate getDateSignature() {
		return dateSignature;
	}
	/**
	 * @param dateSignature le dateSignature à affecter
	 
	 */
	public void setDateSignature(LocalDate dateSignature) {
		this.dateSignature = dateSignature;
	}
	/**
	 * @return le etat
	 */
	public EtatLivertEval getEtat() {
		return etat;
	}
	/**
	 * @param etat le etat à affecter
	 
	 */
	public void setEtat(EtatLivertEval etat) {
		this.etat = etat;
	}

	
	
	
}
