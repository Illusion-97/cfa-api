package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * 
 * @author Feres BG.
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 */
@SuppressWarnings("serial")
public class BlocEvaluationDto extends BaseEntityDto implements Serializable {

	private long livretEvaluationId;

	private long activiteTypeId;

	private boolean criteresSatisfaits;

	private String commentaireInsatisfaction;

	private String commentaireEvaluationsComplementaires;
	
	private long formateurEvaluateurId;

	private LocalDate dateSignature;
	
	private List<Long> evaluationsFormationsId;
	
	
	
	/**
	 * @return le evaluationsFormationsId
	 */
	public List<Long> getEvaluationsFormationsId() {
		return evaluationsFormationsId;
	}


	/**
	 * @param evaluationsFormationsId le evaluationsFormationsId à affecter
	 
	 */
	public void setEvaluationsFormationsId(List<Long> evaluationsFormationsId) {
		this.evaluationsFormationsId = evaluationsFormationsId;
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


	/**
	 * @return le activiteTypeId
	 */
	public long getActiviteTypeId() {
		return activiteTypeId;
	}


	/**
	 * @param activiteTypeId le activiteTypeId à affecter
	 
	 */
	public void setActiviteTypeId(long activiteTypeId) {
		this.activiteTypeId = activiteTypeId;
	}


	/**
	 * @return le criteresSatisfaits
	 */
	public boolean isCriteresSatisfaits() {
		return criteresSatisfaits;
	}


	/**
	 * @param criteresSatisfaits le criteresSatisfaits à affecter
	 
	 */
	public void setCriteresSatisfaits(boolean criteresSatisfaits) {
		this.criteresSatisfaits = criteresSatisfaits;
	}


	/**
	 * @return le commentaireInsatisfaction
	 */
	public String getCommentaireInsatisfaction() {
		return commentaireInsatisfaction;
	}


	/**
	 * @param commentaireInsatisfaction le commentaireInsatisfaction à affecter
	 
	 */
	public void setCommentaireInsatisfaction(String commentaireInsatisfaction) {
		this.commentaireInsatisfaction = commentaireInsatisfaction;
	}


	/**
	 * @return le commentaireEvaluationsComplementaires
	 */
	public String getCommentaireEvaluationsComplementaires() {
		return commentaireEvaluationsComplementaires;
	}


	/**
	 * @param commentaireEvaluationsComplementaires le commentaireEvaluationsComplementaires à affecter
	 
	 */
	public void setCommentaireEvaluationsComplementaires(String commentaireEvaluationsComplementaires) {
		this.commentaireEvaluationsComplementaires = commentaireEvaluationsComplementaires;
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


	

	
}
