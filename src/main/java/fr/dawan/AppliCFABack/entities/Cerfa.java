package fr.dawan.AppliCFABack.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Cerfa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = true, length = 255)
	private String employeurPriveOuPublic;
	
	@Column(nullable = true, length = 255)
	private String nomEmployeur;
	
	@Column(nullable = true, length = 255)
	private String prenomEmployeur;
	
	@ManyToOne
	@Cascade(CascadeType.DELETE)
	private Adresse adresseEmployeur;
	
	@Column(nullable = true, length = 255)
	private String telEmployeur;
	
	@Column(nullable = true, length = 255)
	private String emailEmployeur;
	
	@Column(nullable = true, length = 255)
	private String siretEtablissement;
	
	@Column(nullable = true, length = 255)
	private String employeurSpecifique;
	
	@Column(nullable = true, length = 255)
	private String naf;
	
	@Column(nullable = true, length = 255)
	private String effectifEntreprise;
	
	@Column(nullable = true, length = 255)
	private String conventionCollectiveApplicable;
	
	@Column(nullable = true, length = 255)
	private String codeIdccConvention;
	
	@Column(nullable = true, length = 255)
	private String nomNaissanceApprenti;
	
	@Column(nullable = true, length = 255)
	private String prenomApprenti;
	
	@Column(nullable = true, length = 255)
	private String nirApprenti;
	
	@Column(nullable = true, length = 255)
	private String dateDeNaissance;
	
	@Column(nullable = true, length = 255)
	private String sexe;
	
	@ManyToOne
	private Adresse adresseApprenti;
	
	@Column(nullable = true, length = 255)
	private String departementNaissance;
	
	@Column(nullable = true, length = 255)
	private String communeNaissance;
	
	@Column(nullable = true, length = 255)
	private String telApprenti;
	
	@Column(nullable = true, length = 255)
	private String emailApprenti;
	
	@Column(nullable = true, length = 255)
	private String nationalite;
	
	@Column(nullable = true, length = 255)
	private String regimeSocial;
	
	@Column(nullable = true, length = 255)
	private String sportifs;
	
	@Column(nullable = true, length = 255)
	private String handicape;
	
	@Column(nullable = true, length = 255)
	private String situationAvantContrat;
	
	@Column(nullable = true, length = 255)
	private String dernierDiplome;
	
	@Column(nullable = true, length = 255)
	private String derniereClasseSuivi;
	
	@Column(nullable = true, length = 255)
	private String intitulePrecisDernierDiplome;
	
	@Column(nullable = true, length = 255)
	private String diplomeLePlusEleveObtenu;
	
	@Column(nullable = true, length = 255)
	private String nomRepresentant;
	
	@Column(nullable = true, length = 255)
	private String prenomRepresentant;
	
	@ManyToOne
	@Cascade(CascadeType.DELETE)
	private Adresse adresseRepresentant;
	
	@Column(nullable = true, length = 255)
	private String nomPremierTuteur;
	
	@Column(nullable = true, length = 255)
	private String prenomPremierTuteur;
	
	@Column(nullable = true, length = 255)
	private String dateDeNaissancePremierTuteur;
	
	@Column(nullable = true, length = 255)
	private String nomDeuxiemeTuteur;
	
	@Column(nullable = true, length = 255)
	private String prenomDeuxiemeTuteur;
	
	@Column(nullable = true, length = 255)
	private String dateDeNaissanceDeuxiemeTuteur;
	
	@Column(nullable = true, length = 255)
	private String contratType;
	
	@Column(nullable = true, length = 255)
	private String derogationType;
	
	@Column(nullable = true, length = 255)
	private String contratNum;
	
	@Column(nullable = true, length = 255)
	private LocalDate dateConclusion;
	
	@Column(nullable = true, length = 255)
	private LocalDate dateDebutContrat;
	
	@Column(nullable = true, length = 255)
	private LocalDate dateAvenant;
	
	@Column(nullable = true, length = 255)
	private LocalDate dateFinContrat;
	
	@Column(nullable = true, length = 255)
	private String heureTravail;
	
	@Column(nullable = true, length = 255)
	private String minuteTravail;
	
	@Column(nullable = true, length = 255)
	private String machineRisque;
	
	@ManyToOne
	@Cascade(CascadeType.DELETE)
	private Remuneration remuneration1;
	
	@ManyToOne
	@Cascade(CascadeType.DELETE)
	private Remuneration remuneration2;
	
	@ManyToOne
	@Cascade(CascadeType.DELETE)
	private Remuneration remuneration3;
	
	@ManyToOne
	@Cascade(CascadeType.DELETE)
	private Remuneration remuneration4;
	
	@Column(nullable = true, length = 255)
	private String salaireBrut;
	
	@Column(nullable = true, length = 255)
	private String caisseDeRetraite;
	
	@Column(nullable = true, length = 255)
	private String nourriture;
	
	@Column(nullable = true, length = 255)
	private String logement;
	
	@Column(nullable = true, length = 255)
	private String autre;
	
	@Column(nullable = true, length = 255)
	private String cfaEntreprise;
	
	@Column(nullable = true, length = 255)
	private String cfaResponsable;
	
	@Column(nullable = true, length = 255)
	private String diplomeVise;
	
	@Column(nullable = true, length = 255)
	private String intitulePrecisDiplomeVise;
	
	@Column(nullable = true, length = 255)
	private String cfaUai;
	
	@Column(nullable = true, length = 255)
	private String cfaSiret;
	
	@Column(nullable = true, length = 255)
	private String diplomeCode;
	
	@Column(nullable = true, length = 255)
	private String codeRncp;
	
	@ManyToOne
	@Cascade(CascadeType.DELETE)
	private Adresse adresseResponsable;
	
	@Column(nullable = true, length = 255)
	private LocalDate dateDebutFormation;
	
	@Column(nullable = true, length = 255)
	private LocalDate dateExamen;
	
	@Column(nullable = true, length = 255)
	private String formationDuree;
	
	@Column(nullable = true, length = 255)
	private String validationEmployeur;
	
	@Column(nullable = true, length = 255)
	private String nomOrganisme;
	
	@Column(nullable = true, length = 255)
	private String siretOrganisme;
	
	@Column(nullable = true, length = 255)
	private LocalDate receptionDossier;
	
	@Column(nullable = true, length = 255)
	private LocalDate dateDecision;
	
	@Column(nullable = true, length = 255)
	private String numDepot;
	
	@Column(nullable = true, length = 255)
	private String numAvenant;
	
	@Column(nullable = true, length = 255)
	private String modeContractuelApprentissage;
	
	@Column(nullable = true, length = 255)
	private String complementEmployeur;
	
	@Column(nullable = true, length = 255)
	private String employeurType;
	
	@Column(nullable = true, length = 255)
	private String assuranceChomage;
	
	@Column(nullable = true, length = 255)
	private String complementApprentit;
	
	@Column(nullable = true, length = 255)
	private String complementRepresentant;
	
	@Column(nullable = true, length = 255)
	private String egilibiliteFonction;
	
	@Column(nullable = true, length = 255)
	private String complementResponsable;
	
	@Column(nullable = true, length = 255)
	private String faitA;
	
	public String getModeContractuelApprentissage() {
		return modeContractuelApprentissage;
	}

	public void setModeContractuelApprentissage(String modeContractuelApprentissage) {
		this.modeContractuelApprentissage = modeContractuelApprentissage;
	}

	public String getComplementEmployeur() {
		return complementEmployeur;
	}

	public void setComplementEmployeur(String complementEmployeur) {
		this.complementEmployeur = complementEmployeur;
	}

	public String getEmployeurType() {
		return employeurType;
	}

	public void setEmployeurType(String employeurType) {
		this.employeurType = employeurType;
	}

	public String getAssuranceChomage() {
		return assuranceChomage;
	}

	public void setAssuranceChomage(String assuranceChomage) {
		this.assuranceChomage = assuranceChomage;
	}

	public String getComplementApprentit() {
		return complementApprentit;
	}

	public void setComplementApprentit(String complementApprentit) {
		this.complementApprentit = complementApprentit;
	}

	public String getComplementRepresentant() {
		return complementRepresentant;
	}

	public void setComplementRepresentant(String complementRepresentant) {
		this.complementRepresentant = complementRepresentant;
	}

	public String getEgilibiliteFonction() {
		return egilibiliteFonction;
	}

	public void setÉegilibiliteFonction(String egilibiliteFonction) {
		this.egilibiliteFonction = egilibiliteFonction;
	}

	public String getComplementResponsable() {
		return complementResponsable;
	}

	public void setComplementResponsable(String complementResponsable) {
		this.complementResponsable = complementResponsable;
	}

	public String getFaitA() {
		return faitA;
	}

	public void setFaitA(String faitA) {
		this.faitA = faitA;
	}

	@OneToOne
	private Etudiant etudiant;

	public Cerfa() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmployeurPriveOuPublic() {
		return employeurPriveOuPublic;
	}

	public void setEmployeurPriveOuPublic(String employeurPriveOuPublic) {
		this.employeurPriveOuPublic = employeurPriveOuPublic;
	}

	public String getNomEmployeur() {
		return nomEmployeur;
	}

	public void setNomEmployeur(String nomEmployeur) {
		this.nomEmployeur = nomEmployeur;
	}

	public String getPrenomEmployeur() {
		return prenomEmployeur;
	}

	public void setPrenomEmployeur(String prenomEmployeur) {
		this.prenomEmployeur = prenomEmployeur;
	}

	public Adresse getAdresseEmployeur() {
		return adresseEmployeur;
	}

	public void setAdresseEmployeur(Adresse adresseEmployeur) {
		this.adresseEmployeur = adresseEmployeur;
	}

	public String getTelEmployeur() {
		return telEmployeur;
	}

	public void setTelEmployeur(String telEmployeur) {
		this.telEmployeur = telEmployeur;
	}

	public String getEmailEmployeur() {
		return emailEmployeur;
	}

	public void setEmailEmployeur(String emailEmployeur) {
		this.emailEmployeur = emailEmployeur;
	}

	public String getSiretEtablissement() {
		return siretEtablissement;
	}

	public void setSiretEtablissement(String siretEtablissement) {
		this.siretEtablissement = siretEtablissement;
	}

	public String getEmployeurSpecifique() {
		return employeurSpecifique;
	}

	public void setEmployeurSpecifique(String employeurSpecifique) {
		this.employeurSpecifique = employeurSpecifique;
	}

	public String getNaf() {
		return naf;
	}

	public void setNaf(String naf) {
		this.naf = naf;
	}

	public String getEffectifEntreprise() {
		return effectifEntreprise;
	}

	public void setEffectifEntreprise(String effectifEntreprise) {
		this.effectifEntreprise = effectifEntreprise;
	}

	public String getConventionCollectiveApplicable() {
		return conventionCollectiveApplicable;
	}

	public void setConventionCollectiveApplicable(String conventionCollectiveApplicable) {
		this.conventionCollectiveApplicable = conventionCollectiveApplicable;
	}

	public String getCodeIdccConvention() {
		return codeIdccConvention;
	}

	public void setCodeIdccConvention(String codeIdccConvention) {
		this.codeIdccConvention = codeIdccConvention;
	}

	public String getNomNaissanceApprenti() {
		return nomNaissanceApprenti;
	}

	public void setNomNaissanceApprenti(String nomNaissanceApprenti) {
		this.nomNaissanceApprenti = nomNaissanceApprenti;
	}

	public String getPrenomApprenti() {
		return prenomApprenti;
	}

	public void setPrenomApprenti(String prenomApprenti) {
		this.prenomApprenti = prenomApprenti;
	}

	public String getNirApprenti() {
		return nirApprenti;
	}

	public void setNirApprenti(String nirApprenti) {
		this.nirApprenti = nirApprenti;
	}

	public String getDateDeNaissance() {
		return dateDeNaissance;
	}

	public void setDateDeNaissance(String dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public Adresse getAdresseApprenti() {
		return adresseApprenti;
	}

	public void setAdresseApprenti(Adresse adresseApprenti) {
		this.adresseApprenti = adresseApprenti;
	}

	public String getDepartementNaissance() {
		return departementNaissance;
	}

	public void setDepartementNaissance(String departementNaissance) {
		this.departementNaissance = departementNaissance;
	}

	public String getCommuneNaissance() {
		return communeNaissance;
	}

	public void setCommuneNaissance(String communeNaissance) {
		this.communeNaissance = communeNaissance;
	}

	public String getTelApprenti() {
		return telApprenti;
	}

	public void setTelApprenti(String telApprenti) {
		this.telApprenti = telApprenti;
	}

	public String getEmailApprenti() {
		return emailApprenti;
	}

	public void setEmailApprenti(String emailApprenti) {
		this.emailApprenti = emailApprenti;
	}

	public String getNationalite() {
		return nationalite;
	}

	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

	public String getRegimeSocial() {
		return regimeSocial;
	}

	public void setRegimeSocial(String regimeSocial) {
		this.regimeSocial = regimeSocial;
	}

	public String getSportifs() {
		return sportifs;
	}

	public void setSportifs(String sportifs) {
		this.sportifs = sportifs;
	}

	public String getHandicape() {
		return handicape;
	}

	public void setHandicape(String handicape) {
		this.handicape = handicape;
	}

	public String getSituationAvantContrat() {
		return situationAvantContrat;
	}

	public void setSituationAvantContrat(String situationAvantContrat) {
		this.situationAvantContrat = situationAvantContrat;
	}

	public String getDernierDiplome() {
		return dernierDiplome;
	}

	public void setDernierDiplome(String dernierDiplome) {
		this.dernierDiplome = dernierDiplome;
	}

	public String getDerniereClasseSuivi() {
		return derniereClasseSuivi;
	}

	public void setDerniereClasseSuivi(String derniereClasseSuivi) {
		this.derniereClasseSuivi = derniereClasseSuivi;
	}

	public String getIntitulePrecisDernierDiplome() {
		return intitulePrecisDernierDiplome;
	}

	public void setIntitulePrecisDernierDiplome(String intitulePrecisDernierDiplome) {
		this.intitulePrecisDernierDiplome = intitulePrecisDernierDiplome;
	}

	public String getDiplomeLePlusEleveObtenu() {
		return diplomeLePlusEleveObtenu;
	}

	public void setDiplomeLePlusEleveObtenu(String diplomeLePlusEleveObtenu) {
		this.diplomeLePlusEleveObtenu = diplomeLePlusEleveObtenu;
	}

	public String getNomRepresentant() {
		return nomRepresentant;
	}

	public void setNomRepresentant(String nomRepresentant) {
		this.nomRepresentant = nomRepresentant;
	}

	public String getPrenomRepresentant() {
		return prenomRepresentant;
	}

	public void setPrenomRepresentant(String prenomRepresentant) {
		this.prenomRepresentant = prenomRepresentant;
	}

	public Adresse getAdresseRepresentant() {
		return adresseRepresentant;
	}

	public void setAdresseRepresentant(Adresse adresseRepresentant) {
		this.adresseRepresentant = adresseRepresentant;
	}

	public String getNomPremierTuteur() {
		return nomPremierTuteur;
	}

	public void setNomPremierTuteur(String nomPremierTuteur) {
		this.nomPremierTuteur = nomPremierTuteur;
	}

	public String getPrenomPremierTuteur() {
		return prenomPremierTuteur;
	}

	public void setPrenomPremierTuteur(String prenomPremierTuteur) {
		this.prenomPremierTuteur = prenomPremierTuteur;
	}

	public String getDateDeNaissancePremierTuteur() {
		return dateDeNaissancePremierTuteur;
	}

	public void setDateDeNaissancePremierTuteur(String dateDeNaissancePremierTuteur) {
		this.dateDeNaissancePremierTuteur = dateDeNaissancePremierTuteur;
	}

	public String getNomDeuxiemeTuteur() {
		return nomDeuxiemeTuteur;
	}

	public void setNomDeuxiemeTuteur(String nomDeuxiemeTuteur) {
		this.nomDeuxiemeTuteur = nomDeuxiemeTuteur;
	}

	public String getPrenomDeuxiemeTuteur() {
		return prenomDeuxiemeTuteur;
	}

	public void setPrenomDeuxiemeTuteur(String prenomDeuxiemeTuteur) {
		this.prenomDeuxiemeTuteur = prenomDeuxiemeTuteur;
	}

	public String getDateDeNaissanceDeuxiemeTuteur() {
		return dateDeNaissanceDeuxiemeTuteur;
	}

	public void setDateDeNaissanceDeuxiemeTuteur(String dateDeNaissanceDeuxiemeTuteur) {
		this.dateDeNaissanceDeuxiemeTuteur = dateDeNaissanceDeuxiemeTuteur;
	}

	public String getContratType() {
		return contratType;
	}

	public void setContratType(String contratType) {
		this.contratType = contratType;
	}

	public String getDerogationType() {
		return derogationType;
	}

	public void setDerogationType(String derogationType) {
		this.derogationType = derogationType;
	}

	public String getContratNum() {
		return contratNum;
	}

	public void setContratNum(String contratNum) {
		this.contratNum = contratNum;
	}

	public LocalDate getDateConclusion() {
		return dateConclusion;
	}

	public void setDateConclusion(LocalDate dateConclusion) {
		this.dateConclusion = dateConclusion;
	}

	public LocalDate getDateDebutContrat() {
		return dateDebutContrat;
	}

	public void setDateDebutContrat(LocalDate dateDebutContrat) {
		this.dateDebutContrat = dateDebutContrat;
	}

	public LocalDate getDateAvenant() {
		return dateAvenant;
	}

	public void setDateAvenant(LocalDate dateAvenant) {
		this.dateAvenant = dateAvenant;
	}

	public LocalDate getDateFinContrat() {
		return dateFinContrat;
	}

	public void setDateFinContrat(LocalDate dateFinContrat) {
		this.dateFinContrat = dateFinContrat;
	}

	public String getHeureTravail() {
		return heureTravail;
	}

	public void setHeureTravail(String heureTravail) {
		this.heureTravail = heureTravail;
	}

	public String getMinuteTravail() {
		return minuteTravail;
	}

	public void setMinuteTravail(String minuteTravail) {
		this.minuteTravail = minuteTravail;
	}

	public String getMachineRisque() {
		return machineRisque;
	}

	public void setMachineRisque(String machineRisque) {
		this.machineRisque = machineRisque;
	}

	public Remuneration getRemuneration1() {
		return remuneration1;
	}

	public void setRemuneration1(Remuneration remuneration1) {
		this.remuneration1 = remuneration1;
	}

	public Remuneration getRemuneration2() {
		return remuneration2;
	}

	public void setRemuneration2(Remuneration remuneration2) {
		this.remuneration2 = remuneration2;
	}

	public Remuneration getRemuneration3() {
		return remuneration3;
	}

	public void setRemuneration3(Remuneration remuneration3) {
		this.remuneration3 = remuneration3;
	}

	public Remuneration getRemuneration4() {
		return remuneration4;
	}

	public void setRemuneration4(Remuneration remuneration4) {
		this.remuneration4 = remuneration4;
	}

	public String getSalaireBrut() {
		return salaireBrut;
	}

	public void setSalaireBrut(String salaireBrut) {
		this.salaireBrut = salaireBrut;
	}

	public String getCaisseDeRetraite() {
		return caisseDeRetraite;
	}

	public void setCaisseDeRetraite(String caisseDeRetraite) {
		this.caisseDeRetraite = caisseDeRetraite;
	}

	public String getNourriture() {
		return nourriture;
	}

	public void setNourriture(String nourriture) {
		this.nourriture = nourriture;
	}

	public String getLogement() {
		return logement;
	}

	public void setLogement(String logement) {
		this.logement = logement;
	}

	public String getAutre() {
		return autre;
	}

	public void setAutre(String autre) {
		this.autre = autre;
	}

	public String getCfaEntreprise() {
		return cfaEntreprise;
	}

	public void setCfaEntreprise(String cfaEntreprise) {
		this.cfaEntreprise = cfaEntreprise;
	}

	public String getCfaResponsable() {
		return cfaResponsable;
	}

	public void setCfaResponsable(String cfaResponsable) {
		this.cfaResponsable = cfaResponsable;
	}

	public String getDiplomeVise() {
		return diplomeVise;
	}

	public void setDiplomeVise(String diplomeVise) {
		this.diplomeVise = diplomeVise;
	}

	public String getIntitulePrecisDiplomeVise() {
		return intitulePrecisDiplomeVise;
	}

	public void setIntitulePrecisDiplomeVise(String intitulePrecisDiplomeVise) {
		this.intitulePrecisDiplomeVise = intitulePrecisDiplomeVise;
	}

	public String getCfaUai() {
		return cfaUai;
	}

	public void setCfaUai(String cfaUai) {
		this.cfaUai = cfaUai;
	}

	public String getCfaSiret() {
		return cfaSiret;
	}

	public void setCfaSiret(String cfaSiret) {
		this.cfaSiret = cfaSiret;
	}

	public String getDiplomeCode() {
		return diplomeCode;
	}

	public void setDiplomeCode(String diplomeCode) {
		this.diplomeCode = diplomeCode;
	}

	public String getCodeRncp() {
		return codeRncp;
	}

	public void setCodeRncp(String codeRncp) {
		this.codeRncp = codeRncp;
	}

	public Adresse getAdresseResponsable() {
		return adresseResponsable;
	}

	public void setAdresseResponsable(Adresse adresseResponsable) {
		this.adresseResponsable = adresseResponsable;
	}

	public LocalDate getDateDebutFormation() {
		return dateDebutFormation;
	}

	public void setDateDebutFormation(LocalDate dateDebutFormation) {
		this.dateDebutFormation = dateDebutFormation;
	}

	public LocalDate getDateExamen() {
		return dateExamen;
	}

	public void setDateExamen(LocalDate dateExamen) {
		this.dateExamen = dateExamen;
	}

	public String getFormationDuree() {
		return formationDuree;
	}

	public void setFormationDuree(String formationDuree) {
		this.formationDuree = formationDuree;
	}

	public String getValidationEmployeur() {
		return validationEmployeur;
	}

	public void setValidationEmployeur(String validationEmployeur) {
		this.validationEmployeur = validationEmployeur;
	}

	public String getNomOrganisme() {
		return nomOrganisme;
	}

	public void setNomOrganisme(String nomOrganisme) {
		this.nomOrganisme = nomOrganisme;
	}

	public String getSiretOrganisme() {
		return siretOrganisme;
	}

	public void setSiretOrganisme(String siretOrganisme) {
		this.siretOrganisme = siretOrganisme;
	}

	public LocalDate getReceptionDossier() {
		return receptionDossier;
	}

	public void setReceptionDossier(LocalDate receptionDossier) {
		this.receptionDossier = receptionDossier;
	}

	public LocalDate getDateDecision() {
		return dateDecision;
	}

	public void setDateDecision(LocalDate dateDecision) {
		this.dateDecision = dateDecision;
	}

	public String getNumDepot() {
		return numDepot;
	}

	public void setNumDepot(String numDepot) {
		this.numDepot = numDepot;
	}

	public String getNumAvenant() {
		return numAvenant;
	}

	public void setNumAvenant(String numAvenant) {
		this.numAvenant = numAvenant;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}
	
	

}
