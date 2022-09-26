package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return DTO-Formation Entity
 */
@SuppressWarnings("serial")
public class FormationDto extends BaseEntityDto implements Serializable {

	private String titre;
	private long idDg2;
	private String slug;
	private String duration;
	private String objectif;
	private String prerequis;
	private String plan;
	private List<Long> cursusLstId;
	private List<CursusDto> cursus;
	private List<InterventionDto> interventions;

	/**
	 * @return le cursus
	 */
	public List<CursusDto> getCursus() {
		return cursus;
	}

	/**
	 * @param cursus le cursus à affecter
	 
	 */
	public void setCursus(List<CursusDto> cursus) {
		this.cursus = cursus;
	}

	/**
	 * @return le interventions
	 */
	public List<InterventionDto> getInterventions() {
		return interventions;
	}

	/**
	 * @param interventions le interventions à affecter
	 
	 */
	public void setInterventions(List<InterventionDto> interventions) {
		this.interventions = interventions;
	}

	public FormationDto() {
		super();
	}

	/**
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * @param titre the titre to set
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}


	/**
	 * @return le cursusLstId
	 */
	public List<Long> getCursusLstId() {
		return cursusLstId;
	}

	/**
	 * @param cursusLstId le cursusLstId à affecter
	 
	 */
	public void setCursusLstId(List<Long> cursusLstId) {
		this.cursusLstId = cursusLstId;
	}

	public long getIdDg2() {
		return idDg2;
	}

	public void setIdDg2(long idDg2) {
		this.idDg2 = idDg2;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getObjectif() {
		return objectif;
	}

	public void setObjectif(String objectif) {
		this.objectif = objectif;
	}

	public String getPrerequis() {
		return prerequis;
	}

	public void setPrerequis(String prerequis) {
		this.prerequis = prerequis;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}
	

}
