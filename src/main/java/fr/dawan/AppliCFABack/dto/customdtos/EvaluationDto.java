package fr.dawan.AppliCFABack.dto.customdtos;

import fr.dawan.AppliCFABack.entities.ActiviteType;
import fr.dawan.AppliCFABack.entities.BlocEvaluation;
import fr.dawan.AppliCFABack.entities.EvaluationFormation;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * @author Feres BG.
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 */
@SuppressWarnings("serial")
public class EvaluationDto implements Serializable  {

	private ActiviteType activiteType;
	
	private BlocEvaluation blocEvaluation;
	
	private List<EvaluationFormation> evaluationFormations;

	/**
	 * @return le activiteType
	 */
	public ActiviteType getActiviteType() {
		return activiteType;
	}

	/**
	 * @param activiteType le activiteType à affecter
	 
	 */
	public void setActiviteType(ActiviteType activiteType) {
		this.activiteType = activiteType;
	}

	/**
	 * @return le blocEvaluation
	 */
	public BlocEvaluation getBlocEvaluation() {
		return blocEvaluation;
	}

	/**
	 * @param blocEvaluation le blocEvaluation à affecter
	 
	 */
	public void setBlocEvaluation(BlocEvaluation blocEvaluation) {
		this.blocEvaluation = blocEvaluation;
	}

	/**
	 * @return le evaluationFormations
	 */
	public List<EvaluationFormation> getEvaluationFormations() {
		return evaluationFormations;
	}

	/**
	 * @param evaluationFormations le evaluationFormations à affecter
	 
	 */
	public void setEvaluationFormations(List<EvaluationFormation> evaluationFormations) {
		this.evaluationFormations = evaluationFormations;
	}
	
	
}
