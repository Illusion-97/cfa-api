package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;

/**
 * 
 * 
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return DTO-Fiche entreprise Entity
 */
@SuppressWarnings("serial")
public class FicheEntrepriseDto extends BaseEntityDto implements Serializable {

	private EntrepriseDto entrepriseDto;
	private String historique;
	private String nomDirigeant;
	private String secteurActivite;
	private String organisationType;
	private String nbSalarie;
	private String chiffreAffaireAnnuel;
	private String activiteDescription;
	private String clientType;
	private String formationProfil;
	private String metiersExerces;
	private EtudiantDto etudiantDto;

	public FicheEntrepriseDto() {
		super();
	}

	/**
	 * @return the entrepriseDto
	 */
	public EntrepriseDto getEntrepriseDto() {
		return entrepriseDto;
	}

	/**
	 * @param entrepriseDto the entrepriseDto to set
	 */
	public void setEntrepriseDto(EntrepriseDto entrepriseDto) {
		this.entrepriseDto = entrepriseDto;
	}

	/**
	 * @return the historique
	 */
	public String getHistorique() {
		return historique;
	}

	/**
	 * @param historique the historique to set
	 */
	public void setHistorique(String historique) {
		this.historique = historique;
	}

	/**
	 * @return the nomDirigeant
	 */
	public String getNomDirigeant() {
		return nomDirigeant;
	}

	/**
	 * @param nomDirigeant the nomDirigeant to set
	 */
	public void setNomDirigeant(String nomDirigeant) {
		this.nomDirigeant = nomDirigeant;
	}

	/**
	 * @return the secteurActivite
	 */
	public String getSecteurActivite() {
		return secteurActivite;
	}

	/**
	 * @param secteurActivite the secteurActivite to set
	 */
	public void setSecteurActivite(String secteurActivite) {
		this.secteurActivite = secteurActivite;
	}

	/**
	 * @return the organisationType
	 */
	public String getOrganisationType() {
		return organisationType;
	}

	/**
	 * @param organisationType the organisationType to set
	 */
	public void setOrganisationType(String organisationType) {
		this.organisationType = organisationType;
	}

	/**
	 * @return the nbSalarie
	 */
	public String getNbSalarie() {
		return nbSalarie;
	}

	/**
	 * @param nbSalarie the nbSalarie to set
	 */
	public void setNbSalarie(String nbSalarie) {
		this.nbSalarie = nbSalarie;
	}

	/**
	 * @return the chiffreAffaireAnnuel
	 */
	public String getChiffreAffaireAnnuel() {
		return chiffreAffaireAnnuel;
	}

	/**
	 * @param chiffreAffaireAnnuel the chiffreAffaireAnnuel to set
	 */
	public void setChiffreAffaireAnnuel(String chiffreAffaireAnnuel) {
		this.chiffreAffaireAnnuel = chiffreAffaireAnnuel;
	}

	/**
	 * @return the activiteDescription
	 */
	public String getActiviteDescription() {
		return activiteDescription;
	}

	/**
	 * @param activiteDescription the activiteDescription to set
	 */
	public void setActiviteDescription(String activiteDescription) {
		this.activiteDescription = activiteDescription;
	}

	/**
	 * @return the clientType
	 */
	public String getClientType() {
		return clientType;
	}

	/**
	 * @param clientType the clientType to set
	 */
	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	/**
	 * @return the formationProfil
	 */
	public String getFormationProfil() {
		return formationProfil;
	}

	/**
	 * @param formationProfil the formationProfil to set
	 */
	public void setFormationProfil(String formationProfil) {
		this.formationProfil = formationProfil;
	}

	/**
	 * @return the metiersExerces
	 */
	public String getMetiersExerces() {
		return metiersExerces;
	}

	/**
	 * @param metiersExerces the metiersExerces to set
	 */
	public void setMetiersExerces(String metiersExerces) {
		this.metiersExerces = metiersExerces;
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
