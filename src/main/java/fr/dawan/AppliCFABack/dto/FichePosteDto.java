package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;

/**
 * 
 * 
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return DTO-fiche poste Entity
 */
@SuppressWarnings("serial")
public class FichePosteDto extends BaseEntityDto implements Serializable {

	private String intitule;
	private String nature;
	private String mission;
	private String compositionService;
	private String positionnement;
	private String missionPrincipale;
	private EtudiantDto etudiantDto;

	public FichePosteDto() {
		super();
	}

	/**
	 * @return the intitule
	 */
	public String getIntitule() {
		return intitule;
	}

	/**
	 * @param intitule the intitule to set
	 */
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	/**
	 * @return the nature
	 */
	public String getNature() {
		return nature;
	}

	/**
	 * @param nature the nature to set
	 */
	public void setNature(String nature) {
		this.nature = nature;
	}

	/**
	 * @return the mission
	 */
	public String getMission() {
		return mission;
	}

	/**
	 * @param mission the mission to set
	 */
	public void setMission(String mission) {
		this.mission = mission;
	}

	/**
	 * @return the compositionService
	 */
	public String getCompositionService() {
		return compositionService;
	}

	/**
	 * @param compositionService the compositionService to set
	 */
	public void setCompositionService(String compositionService) {
		this.compositionService = compositionService;
	}

	/**
	 * @return the positionnement
	 */
	public String getPositionnement() {
		return positionnement;
	}

	/**
	 * @param positionnement the positionnement to set
	 */
	public void setPositionnement(String positionnement) {
		this.positionnement = positionnement;
	}

	/**
	 * @return the missionPrincipale
	 */
	public String getMissionPrincipale() {
		return missionPrincipale;
	}

	/**
	 * @param missionPrincipale the missionPrincipale to set
	 */
	public void setMissionPrincipale(String missionPrincipale) {
		this.missionPrincipale = missionPrincipale;
	}

	/**
	 * @return the etudiantDto
	 */
	public EtudiantDto getEtudiantDto() {
		return etudiantDto;
	}

	/**
	 * @param etudiantDto the etudiantDto to set
	 */
	public void setEtudiantDto(EtudiantDto etudiantDto) {
		this.etudiantDto = etudiantDto;
	}

}
