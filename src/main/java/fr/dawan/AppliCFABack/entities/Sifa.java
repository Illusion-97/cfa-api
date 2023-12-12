package fr.dawan.AppliCFABack.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;

@Entity
public class Sifa extends BaseEntity{
    @OneToOne
    Utilisateur utilisateur;
    @Column(columnDefinition = "VARCHAR(10)")
    private String nationalite;
    @OneToOne
    Promotion promotion;
    private int situationOuClasseAnneeDerniere;
    private int dureeMoisFormationTheorique;
    private int dureeMoisFormationReelle;
    @OneToOne
    Adresse adresseUser;
    @Column(columnDefinition = "VARCHAR(10)")
    private String codeCommuneNaissanceJeune;
    @OneToOne
    Entreprise entreprise;
    private int typeEmployeur;
    @Column(columnDefinition = "VARCHAR(10)")
    private String numeroUaiEtablissement;
    private int organismeGestionnaireEtablissement;
    @Column(columnDefinition = "VARCHAR(10)")
    private String numeroUaiSiteFormation;
    private int numeroUaiFormation;
    @Column(columnDefinition = "VARCHAR(10)")
    private String natureStructureJuridique;
    @Column(columnDefinition = "VARCHAR(10)")
    private String statutJeune;
    @Column(columnDefinition = "VARCHAR(10)")
    private String diplomeOuTitrePrepare;// Numéro Scolarité ou diplôme en lien avec l'état voir peut être dg2
    private String situationFormation;
    @Column(columnDefinition = "VARCHAR(100)")
    private String nomUsageJeune;
    @Column(columnDefinition = "VARCHAR(10)")
    private String deuxiemePrenomJeune;
    @Column(columnDefinition = "VARCHAR(10)")
    private String troisiemePrenomJeune;
    private int numeroTelephonePersonnelResponsable1;
    private int numeroTelephoneProfessionnelResponsable1;
    private int numeroTelephonePersonnelResponsable2;
    private int numeroTelephoneProfessionnelResponsable2;
    @Email
    @Column(columnDefinition = "VARCHAR(100)")
    private String adresseMailResponsable1;
    @Email
    @Column(columnDefinition = "VARCHAR(100)")
    private String adresseMailResponsable2;
    @Column(columnDefinition = "VARCHAR(10)")
    private String regimeScolaireJeune;
    @Column(columnDefinition = "VARCHAR(10)")
    private String pcsRepresentantLegalJeune;
    private boolean apprentisReconnaissanceTravailleurHandicape;
    @Column(columnDefinition = "VARCHAR(10)")
    private String situationAvantPremiereAnneeApprentissage;
    @Column(columnDefinition = "VARCHAR(10)")
    private String dernierDiplomeObtenu;
    @Column(columnDefinition = "VARCHAR(10)")
    private String numeroUaiEtablissementDerniereAnnee;
    private Etat etat;
    public enum Etat {
        VALIDE,
        INVALIDE
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public int getSituationOuClasseAnneeDerniere() {
        return situationOuClasseAnneeDerniere;
    }

    public void setSituationOuClasseAnneeDerniere(int situationOuClasseAnneeDerniere) {
        this.situationOuClasseAnneeDerniere = situationOuClasseAnneeDerniere;
    }

    public int getDureeMoisFormationTheorique() {
        return dureeMoisFormationTheorique;
    }

    public void setDureeMoisFormationTheorique(int dureeMoisFormationTheorique) {
        this.dureeMoisFormationTheorique = dureeMoisFormationTheorique;
    }

    public int getDureeMoisFormationReelle() {
        return dureeMoisFormationReelle;
    }

    public void setDureeMoisFormationReelle(int dureeMoisFormationReelle) {
        this.dureeMoisFormationReelle = dureeMoisFormationReelle;
    }

    public Adresse getAdresseUser() {
        return adresseUser;
    }

    public void setAdresseUser(Adresse adresseUser) {
        this.adresseUser = adresseUser;
    }

    public String getCodeCommuneNaissanceJeune() {
        return codeCommuneNaissanceJeune;
    }

    public void setCodeCommuneNaissanceJeune(String codeCommuneNaissanceJeune) {
        this.codeCommuneNaissanceJeune = codeCommuneNaissanceJeune;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    public int getTypeEmployeur() {
        return typeEmployeur;
    }

    public void setTypeEmployeur(int typeEmployeur) {
        this.typeEmployeur = typeEmployeur;
    }

    public String getNumeroUaiEtablissement() {
        return numeroUaiEtablissement;
    }

    public void setNumeroUaiEtablissement(String numeroUaiEtablissement) {
        this.numeroUaiEtablissement = numeroUaiEtablissement;
    }

    public int getOrganismeGestionnaireEtablissement() {
        return organismeGestionnaireEtablissement;
    }

    public void setOrganismeGestionnaireEtablissement(int organismeGestionnaireEtablissement) {
        this.organismeGestionnaireEtablissement = organismeGestionnaireEtablissement;
    }

    public String getNumeroUaiSiteFormation() {
        return numeroUaiSiteFormation;
    }

    public void setNumeroUaiSiteFormation(String numeroUaiSiteFormation) {
        this.numeroUaiSiteFormation = numeroUaiSiteFormation;
    }

    public int getNumeroUaiFormation() {
        return numeroUaiFormation;
    }

    public void setNumeroUaiFormation(int numeroUaiFormation) {
        this.numeroUaiFormation = numeroUaiFormation;
    }

    public String getNatureStructureJuridique() {
        return natureStructureJuridique;
    }

    public void setNatureStructureJuridique(String natureStructureJuridique) {
        this.natureStructureJuridique = natureStructureJuridique;
    }

    public String getStatutJeune() {
        return statutJeune;
    }

    public void setStatutJeune(String statutJeune) {
        this.statutJeune = statutJeune;
    }

    public String getDiplomeOuTitrePrepare() {
        return diplomeOuTitrePrepare;
    }

    public void setDiplomeOuTitrePrepare(String diplomeOuTitrePrepare) {
        this.diplomeOuTitrePrepare = diplomeOuTitrePrepare;
    }

    public String getSituationFormation() {
        return situationFormation;
    }

    public void setSituationFormation(String situationFormation) {
        this.situationFormation = situationFormation;
    }

    public String getNomUsageJeune() {
        return nomUsageJeune;
    }

    public void setNomUsageJeune(String nomUsageJeune) {
        this.nomUsageJeune = nomUsageJeune;
    }

    public String getDeuxiemePrenomJeune() {
        return deuxiemePrenomJeune;
    }

    public void setDeuxiemePrenomJeune(String deuxiemePrenomJeune) {
        this.deuxiemePrenomJeune = deuxiemePrenomJeune;
    }

    public String getTroisiemePrenomJeune() {
        return troisiemePrenomJeune;
    }

    public void setTroisiemePrenomJeune(String troisiemePrenomJeune) {
        this.troisiemePrenomJeune = troisiemePrenomJeune;
    }

    public int getNumeroTelephonePersonnelResponsable1() {
        return numeroTelephonePersonnelResponsable1;
    }

    public void setNumeroTelephonePersonnelResponsable1(int numeroTelephonePersonnelResponsable1) {
        this.numeroTelephonePersonnelResponsable1 = numeroTelephonePersonnelResponsable1;
    }

    public int getNumeroTelephoneProfessionnelResponsable1() {
        return numeroTelephoneProfessionnelResponsable1;
    }

    public void setNumeroTelephoneProfessionnelResponsable1(int numeroTelephoneProfessionnelResponsable1) {
        this.numeroTelephoneProfessionnelResponsable1 = numeroTelephoneProfessionnelResponsable1;
    }

    public int getNumeroTelephonePersonnelResponsable2() {
        return numeroTelephonePersonnelResponsable2;
    }

    public void setNumeroTelephonePersonnelResponsable2(int numeroTelephonePersonnelResponsable2) {
        this.numeroTelephonePersonnelResponsable2 = numeroTelephonePersonnelResponsable2;
    }

    public int getNumeroTelephoneProfessionnelResponsable2() {
        return numeroTelephoneProfessionnelResponsable2;
    }

    public void setNumeroTelephoneProfessionnelResponsable2(int numeroTelephoneProfessionnelResponsable2) {
        this.numeroTelephoneProfessionnelResponsable2 = numeroTelephoneProfessionnelResponsable2;
    }

    public String getAdresseMailResponsable1() {
        return adresseMailResponsable1;
    }

    public void setAdresseMailResponsable1(String adresseMailResponsable1) {
        this.adresseMailResponsable1 = adresseMailResponsable1;
    }

    public String getAdresseMailResponsable2() {
        return adresseMailResponsable2;
    }

    public void setAdresseMailResponsable2(String adresseMailResponsable2) {
        this.adresseMailResponsable2 = adresseMailResponsable2;
    }

    public String getRegimeScolaireJeune() {
        return regimeScolaireJeune;
    }

    public void setRegimeScolaireJeune(String regimeScolaireJeune) {
        this.regimeScolaireJeune = regimeScolaireJeune;
    }

    public String getPcsRepresentantLegalJeune() {
        return pcsRepresentantLegalJeune;
    }

    public void setPcsRepresentantLegalJeune(String pcsRepresentantLegalJeune) {
        this.pcsRepresentantLegalJeune = pcsRepresentantLegalJeune;
    }

    public boolean isApprentisReconnaissanceTravailleurHandicape() {
        return apprentisReconnaissanceTravailleurHandicape;
    }

    public void setApprentisReconnaissanceTravailleurHandicape(boolean apprentisReconnaissanceTravailleurHandicape) {
        this.apprentisReconnaissanceTravailleurHandicape = apprentisReconnaissanceTravailleurHandicape;
    }

    public String getSituationAvantPremiereAnneeApprentissage() {
        return situationAvantPremiereAnneeApprentissage;
    }

    public void setSituationAvantPremiereAnneeApprentissage(String situationAvantPremiereAnneeApprentissage) {
        this.situationAvantPremiereAnneeApprentissage = situationAvantPremiereAnneeApprentissage;
    }

    public String getDernierDiplomeObtenu() {
        return dernierDiplomeObtenu;
    }

    public void setDernierDiplomeObtenu(String dernierDiplomeObtenu) {
        this.dernierDiplomeObtenu = dernierDiplomeObtenu;
    }

    public String getNumeroUaiEtablissementDerniereAnnee() {
        return numeroUaiEtablissementDerniereAnnee;
    }

    public void setNumeroUaiEtablissementDerniereAnnee(String numeroUaiEtablissementDerniereAnnee) {
        this.numeroUaiEtablissementDerniereAnnee = numeroUaiEtablissementDerniereAnnee;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }
}
