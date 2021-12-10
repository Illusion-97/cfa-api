package fr.dawan.AppliCFABack.dto;

import java.time.LocalDate;

import javax.persistence.Column;

public class CerfaDto {
	
	private long id;
	
	private String employeurPriveOuPublic;
	
	private String nomEmployeur;
	
	private String prenomEmployeur;
	
	private AdresseDto adresseEmployeur;
	
	private String telEmployeur;
	
	private String emailEmployeur;
	
	private String siretEtablissement;
	
	private String employeurSpecifique;
	
	private String naf;
	
	private String effectifEntreprise;
	
	private String conventionCollectiveApplicable;
	
	private String codeIdccConvention;
	
	private String nomNaissanceApprenti;
	
	private String prenomApprenti;
	
	private String nirApprenti;
	
	private String dateDeNaissance;
	
	private String sexe;
	
	private AdresseDto adresseApprenti;
	
	private String departementNaissance;
	
	private String communeNaissance;
	
	private String telApprenti;
	
	private String emailApprenti;
	
	private String nationalite;
	
	private String regimeSocial;
	
	private String sportifs;
	
	private String handicape;
	
	private String situationAvantContrat;
	
	private String dernierDiplome;
	
	private String derniereClasseSuivi;
	
	private String intitulePrecisDernierDiplome;
	
	private String diplomeLePlusEleveObtenu;
	
	private String nomRepresentant;
	
	private String prenomRepresentant;
	
	private AdresseDto adresseRepresentant;
	
	private String nomPremierTuteur;
	
	private String prenomPremierTuteur;
	
	private String dateDeNaissancePremierTuteur;
	
	private String nomDeuxiemeTuteur;
	
	private String prenomDeuxiemeTuteur;
	
	private String dateDeNaissanceDeuxiemeTuteur;
	
	private String contratType;
	
	private String derogationType;
	
	private String contratNum;
	
	private LocalDate dateConclusion;
	
	private LocalDate dateDebutContrat;
	
	private LocalDate dateAvenant;
	
	private LocalDate dateFinContrat;
	
	private String heureTravail;
	
	private String minuteTravail;
	
	private String machineRisque;
	
	private RemunerationDto remuneration1;
	
	private RemunerationDto remuneration2;
	
	private RemunerationDto remuneration3;
	
	private RemunerationDto remuneration4;
	
	private String salaireBrut;
	
	private String caisseDeRetraite;
	
	private String nourriture;
	
	private String logement;
	
	private String autre;
	
	private String cfaEntreprise;
	
	private String cfaResponsable;
	
	private String diplomeVise;
	
	private String intitulePrecisDiplomeVise;
	
	private String cfaUai;
	
	private String cfaSiret;
	
	private String diplomeCode;
	
	private String codeRncp;
	
	private AdresseDto adresseResponsable;
	
	private LocalDate dateDebutFormation;
	
	private LocalDate dateExamen;
	
	private String formationDuree;
	
	private String validationEmployeur;
	
	private String nomOrganisme;
	
	private String siretOrganisme;
	
	private LocalDate receptionDossier;
	
	private LocalDate dateDecision;
	
	private String numDepot;
	
	private String numAvenant;
	
	private EtudiantDto etudiant;

	private String modeContractuelApprentissage;
	
	private String complementEmployeur;
	
	private String employeurType;
	
	private String assuranceChomage;
	
	private String complementApprentit;
	
	private String complementRepresentant;
	
	private String egilibiliteFonction;
	
	private String complementResponsable;
	
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

	public void setEgilibiliteFonction(String egilibiliteFonction) {
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

	public CerfaDto() {
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

	public AdresseDto getAdresseEmployeur() {
		return adresseEmployeur;
	}

	public void setAdresseEmployeur(AdresseDto adresseEmployeur) {
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

	public AdresseDto getAdresseApprenti() {
		return adresseApprenti;
	}

	public void setAdresseApprenti(AdresseDto adresseApprenti) {
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

	public AdresseDto getAdresseRepresentant() {
		return adresseRepresentant;
	}

	public void setAdresseRepresentant(AdresseDto adresseRepresentant) {
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

	public RemunerationDto getRemuneration1() {
		return remuneration1;
	}

	public void setRemuneration1(RemunerationDto remuneration1) {
		this.remuneration1 = remuneration1;
	}

	public RemunerationDto getRemuneration2() {
		return remuneration2;
	}

	public void setRemuneration2(RemunerationDto remuneration2) {
		this.remuneration2 = remuneration2;
	}

	public RemunerationDto getRemuneration3() {
		return remuneration3;
	}

	public void setRemuneration3(RemunerationDto remuneration3) {
		this.remuneration3 = remuneration3;
	}

	public RemunerationDto getRemuneration4() {
		return remuneration4;
	}

	public void setRemuneration4(RemunerationDto remuneration4) {
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

	public AdresseDto getAdresseResponsable() {
		return adresseResponsable;
	}

	public void setAdresseResponsable(AdresseDto adresseResponsable) {
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

	public EtudiantDto getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(EtudiantDto etudiant) {
		this.etudiant = etudiant;
	}

	@Override
	public String toString() {
		return "CerfaDto [id=" + id + ", employeurPriveOuPublic=" + employeurPriveOuPublic + ", nomEmployeur="
				+ nomEmployeur + ", prenomEmployeur=" + prenomEmployeur + ", adresseEmployeur=" + adresseEmployeur
				+ ", telEmployeur=" + telEmployeur + ", emailEmployeur=" + emailEmployeur + ", siretEtablissement="
				+ siretEtablissement + ", employeurSpecifique=" + employeurSpecifique + ", naf=" + naf
				+ ", effectifEntreprise=" + effectifEntreprise + ", conventionCollectiveApplicable="
				+ conventionCollectiveApplicable + ", codeIdccConvention=" + codeIdccConvention
				+ ", nomNaissanceApprenti=" + nomNaissanceApprenti + ", prenomApprenti=" + prenomApprenti
				+ ", nirApprenti=" + nirApprenti + ", dateDeNaissance=" + dateDeNaissance + ", sexe=" + sexe
				+ ", adresseApprenti=" + adresseApprenti + ", departementNaissance=" + departementNaissance
				+ ", communeNaissance=" + communeNaissance + ", telApprenti=" + telApprenti + ", emailApprenti="
				+ emailApprenti + ", nationalite=" + nationalite + ", regimeSocial=" + regimeSocial + ", sportifs="
				+ sportifs + ", handicape=" + handicape + ", situationAvantContrat=" + situationAvantContrat
				+ ", dernierDiplome=" + dernierDiplome + ", derniereClasseSuivi=" + derniereClasseSuivi
				+ ", intitulePrecisDernierDiplome=" + intitulePrecisDernierDiplome + ", diplomeLePlusEleveObtenu="
				+ diplomeLePlusEleveObtenu + ", nomRepresentant=" + nomRepresentant + ", prenomRepresentant="
				+ prenomRepresentant + ", adresseRepresentant=" + adresseRepresentant + ", nomPremierTuteur="
				+ nomPremierTuteur + ", prenomPremierTuteur=" + prenomPremierTuteur + ", dateDeNaissancePremierTuteur="
				+ dateDeNaissancePremierTuteur + ", nomDeuxiemeTuteur=" + nomDeuxiemeTuteur + ", prenomDeuxiemeTuteur="
				+ prenomDeuxiemeTuteur + ", dateDeNaissanceDeuxiemeTuteur=" + dateDeNaissanceDeuxiemeTuteur
				+ ", contratType=" + contratType + ", derogationType=" + derogationType + ", contratNum=" + contratNum
				+ ", dateConclusion=" + dateConclusion + ", dateDebutContrat=" + dateDebutContrat + ", dateAvenant="
				+ dateAvenant + ", dateFinContrat=" + dateFinContrat + ", heureTravail=" + heureTravail
				+ ", minuteTravail=" + minuteTravail + ", machineRisque=" + machineRisque + ", remuneration1="
				+ remuneration1 + ", remuneration2=" + remuneration2 + ", remuneration3=" + remuneration3
				+ ", remuneration4=" + remuneration4 + ", salaireBrut=" + salaireBrut + ", caisseDeRetraite="
				+ caisseDeRetraite + ", nourriture=" + nourriture + ", logement=" + logement + ", autre=" + autre
				+ ", cfaEntreprise=" + cfaEntreprise + ", cfaResponsable=" + cfaResponsable + ", diplomeVise="
				+ diplomeVise + ", intitulePrecisDiplomeVise=" + intitulePrecisDiplomeVise + ", cfaUai=" + cfaUai
				+ ", cfaSiret=" + cfaSiret + ", diplomeCode=" + diplomeCode + ", codeRncp=" + codeRncp
				+ ", adresseResponsable=" + adresseResponsable + ", dateDebutFormation=" + dateDebutFormation
				+ ", dateExamen=" + dateExamen + ", formationDuree=" + formationDuree + ", validationEmployeur="
				+ validationEmployeur + ", nomOrganisme=" + nomOrganisme + ", siretOrganisme=" + siretOrganisme
				+ ", receptionDossier=" + receptionDossier + ", dateDecision=" + dateDecision + ", numDepot=" + numDepot
				+ ", numAvenant=" + numAvenant + ", etudiant=" + etudiant + ", modeContractuelApprentissage="
				+ modeContractuelApprentissage + ", complementEmployeur=" + complementEmployeur + ", employeurType="
				+ employeurType + ", assuranceChomage=" + assuranceChomage + ", complementApprentit="
				+ complementApprentit + ", complementRepresentant=" + complementRepresentant + ", egilibiliteFonction="
				+ egilibiliteFonction + ", complementResponsable=" + complementResponsable + ", faitA=" + faitA + "]";
	}
	
	
	
}
