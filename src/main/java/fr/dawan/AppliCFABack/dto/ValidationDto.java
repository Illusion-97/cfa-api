package fr.dawan.AppliCFABack.dto;

import fr.dawan.AppliCFABack.entities.Validation.Etat;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ValidationDto extends BaseEntityDto implements Serializable{

	private Etat etatValidation;
	private long signatureId;
	private long livretEvaluationId;

	
	/**
	 * @return le etatValidation
	 */
	public Etat getEtatValidation() {
		return etatValidation;
	}

	/**
	 * @return le signatureId
	 */
	public long getSignatureId() {
		return signatureId;
	}

	/**
	 * @param signatureId le signatureId à affecter
	 
	 */
	public void setSignatureId(long signatureId) {
		this.signatureId = signatureId;
	}

	/**
	 * @param etatValidation le etatValidation à affecter
	 
	 */
	public void setEtatValidation(Etat etatValidation) {
		this.etatValidation = etatValidation;
	}


	/**
	 * @return le livretEvaluationId
	 */
	public long getLivretEvaluationId() {
		return livretEvaluationId;
	}
	/**
	 * @param livretEvaluationId le livretEvaluationId à affecter
	 
	 */
	public void setLivretEvaluationId(long livretEvaluationId) {
		this.livretEvaluationId = livretEvaluationId;
	}

	
}
