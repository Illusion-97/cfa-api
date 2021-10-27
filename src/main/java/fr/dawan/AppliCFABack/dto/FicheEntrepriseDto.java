package fr.dawan.AppliCFABack.dto;

public class FicheEntrepriseDto {
	
	private long id;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public EntrepriseDto getEntrepriseDto() {
		return entrepriseDto;
	}

	public void setEntrepriseDto(EntrepriseDto entrepriseDto) {
		this.entrepriseDto = entrepriseDto;
	}

	public String getHistorique() {
		return historique;
	}

	public void setHistorique(String historique) {
		this.historique = historique;
	}

	public String getNomDirigeant() {
		return nomDirigeant;
	}

	public void setNomDirigeant(String nomDirigeant) {
		this.nomDirigeant = nomDirigeant;
	}

	public String getSecteurActivite() {
		return secteurActivite;
	}

	public void setSecteurActivite(String secteurActivite) {
		this.secteurActivite = secteurActivite;
	}

	public String getOrganisationType() {
		return organisationType;
	}

	public void setOrganisationType(String organisationType) {
		this.organisationType = organisationType;
	}

	public String getNbSalarie() {
		return nbSalarie;
	}

	public void setNbSalarie(String nbSalarie) {
		this.nbSalarie = nbSalarie;
	}

	public String getChiffreAffaireAnnuel() {
		return chiffreAffaireAnnuel;
	}

	public void setChiffreAffaireAnnuel(String chiffreAffaireAnnuel) {
		this.chiffreAffaireAnnuel = chiffreAffaireAnnuel;
	}

	public String getActiviteDescription() {
		return activiteDescription;
	}

	public void setActiviteDescription(String activiteDescription) {
		this.activiteDescription = activiteDescription;
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public String getFormationProfil() {
		return formationProfil;
	}

	public void setFormationProfil(String formationProfil) {
		this.formationProfil = formationProfil;
	}

	public String getMetiersExerces() {
		return metiersExerces;
	}

	public void setMetiersExerces(String metiersExerces) {
		this.metiersExerces = metiersExerces;
	}

	public EtudiantDto getEtudiantDto() {
		return etudiantDto;
	}

	public void setEtudiantDto(EtudiantDto etudiantDto) {
		this.etudiantDto = etudiantDto;
	}
	
	
}
