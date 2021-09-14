package fr.dawan.AppliCFABack.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class FicheEntreprise {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private Entreprise entreprise;
	
	// Logo
	//private String Logo;
	
	@Column(nullable = true, length = 255)
	private String historique;

	@Column(nullable = true, length = 255)
	private String nomDirigeant;

	@Column(nullable = true, length = 255)
	private String secteurActivite;
	
	@Column(nullable = true, length = 255)
	private String organisationType;

	@Column(nullable = true, length = 255)
	private String nbSalarie;
	
	@Column(nullable = true, length = 255)
	private String chiffreAffaireAnnuel;

	@Column(nullable = true, length = 255)
	private String activiteDescription;

	@Column(nullable = true, length = 255)
	private String clientType;

	@Column(nullable = true, length = 255)
	private String formationProfil;

	@Column(nullable = true, length = 255)
	private String metiersExerces;
	
	@OneToOne
	private Etudiant etudiant;

	public FicheEntreprise() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
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

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

}
