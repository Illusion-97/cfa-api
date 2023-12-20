package fr.dawan.AppliCFABack.dto;

import fr.dawan.AppliCFABack.entities.Sifa;

import javax.validation.constraints.Email;
import java.time.LocalDate;

public class SifaDto {
    private String numeroUaiEtablissement;
    private int organismeGestionnaireEtablissement;
    private String numeroUaiSiteFormation;
    private int numeroUaiFormation;
    private String natureStructureJuridique;
    private String statutJeune;
    private String diplomeOuTitrePrepare;
    private int dureeMoisFormationTheorique;
    private int dureeMoisFormationReelle;
    private String situationFormation;
    private String nomFamilleJeune;
    private String nomUsageJeune;
    private String premierPrenomJeune;
    private String deuxiemePrenomJeune;
    private String troisiemePrenomJeune;
    private String adressePostaleJeune;
    private String codePostalResidenceJeune;
    private String codeCommuneResidenceJeune;
    private String numeroTelephoneJeune;
    private int numeroTelephonePersonnelResponsable1;
    private int numeroTelephoneProfessionnelResponsable1;
    private int numeroTelephonePersonnelResponsable2;
    private int numeroTelephoneProfessionnelResponsable2;
    @Email
    private String adresseMailJeune;
    @Email
    private String adresseMailResponsable1;
    @Email
    private String adresseMailResponsable2;
    private LocalDate dateNaissanceJeune;
    private String codeCommuneNaissanceJeune;
    private int sexeJeune;
    private String regimeScolaireJeune;
    private String pcsRepresentantLegalJeune;
    private boolean apprentisReconnaissanceTravailleurHandicape;
    private String nationalite;
    private String situationAvantPremiereAnneeApprentissage;
    private String dernierDiplomeObtenu;
    private int situationOuClasseAnneeDerniere;
    private String numeroUaiEtablissementDerniereAnnee;
    private int typeEmployeur;
    private LocalDate dateEntreeCfa;
    private LocalDate dateDebutContratApprentissage;
    private LocalDate dateRuptureContrat;
    private int codeCommuneEtablissementEmployeur;
    private int codeNafEtablissementEmployeur;
    private int nombreSalariesEntrepriseEmployeur;
    private int numeroSiretEntrepriseEmployeur;
    private Sifa.Etat etat;

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

    public String getSituationFormation() {
        return situationFormation;
    }

    public void setSituationFormation(String situationFormation) {
        this.situationFormation = situationFormation;
    }

    public String getNomFamilleJeune() {
        return nomFamilleJeune;
    }

    public void setNomFamilleJeune(String nomFamilleJeune) {
        this.nomFamilleJeune = nomFamilleJeune;
    }

    public String getNomUsageJeune() {
        return nomUsageJeune;
    }

    public void setNomUsageJeune(String nomUsageJeune) {
        this.nomUsageJeune = nomUsageJeune;
    }

    public String getPremierPrenomJeune() {
        return premierPrenomJeune;
    }

    public void setPremierPrenomJeune(String premierPrenomJeune) {
        this.premierPrenomJeune = premierPrenomJeune;
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

    public String getAdressePostaleJeune() {
        return adressePostaleJeune;
    }

    public void setAdressePostaleJeune(String adressePostaleJeune) {
        this.adressePostaleJeune = adressePostaleJeune;
    }

    public String getCodePostalResidenceJeune() {
        return codePostalResidenceJeune;
    }

    public void setCodePostalResidenceJeune(String codePostalResidenceJeune) {
        this.codePostalResidenceJeune = codePostalResidenceJeune;
    }

    public String getCodeCommuneResidenceJeune() {
        return codeCommuneResidenceJeune;
    }

    public void setCodeCommuneResidenceJeune(String codeCommuneResidenceJeune) {
        this.codeCommuneResidenceJeune = codeCommuneResidenceJeune;
    }

    public String getNumeroTelephoneJeune() {
        return numeroTelephoneJeune;
    }

    public void setNumeroTelephoneJeune(String numeroTelephoneJeune) {
        this.numeroTelephoneJeune = numeroTelephoneJeune;
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

    public String getAdresseMailJeune() {
        return adresseMailJeune;
    }

    public void setAdresseMailJeune(String adresseMailJeune) {
        this.adresseMailJeune = adresseMailJeune;
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

    public LocalDate getDateNaissanceJeune() {
        return dateNaissanceJeune;
    }

    public void setDateNaissanceJeune(LocalDate dateNaissanceJeune) {
        this.dateNaissanceJeune = dateNaissanceJeune;
    }

    public String getCodeCommuneNaissanceJeune() {
        return codeCommuneNaissanceJeune;
    }

    public void setCodeCommuneNaissanceJeune(String codeCommuneNaissanceJeune) {
        this.codeCommuneNaissanceJeune = codeCommuneNaissanceJeune;
    }

    public int getSexeJeune() {
        return sexeJeune;
    }

    public void setSexeJeune(int sexeJeune) {
        this.sexeJeune = sexeJeune;
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

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
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

    public int getSituationOuClasseAnneeDerniere() {
        return situationOuClasseAnneeDerniere;
    }

    public void setSituationOuClasseAnneeDerniere(int situationOuClasseAnneeDerniere) {
        this.situationOuClasseAnneeDerniere = situationOuClasseAnneeDerniere;
    }

    public String getNumeroUaiEtablissementDerniereAnnee() {
        return numeroUaiEtablissementDerniereAnnee;
    }

    public void setNumeroUaiEtablissementDerniereAnnee(String numeroUaiEtablissementDerniereAnnee) {
        this.numeroUaiEtablissementDerniereAnnee = numeroUaiEtablissementDerniereAnnee;
    }

    public int getTypeEmployeur() {
        return typeEmployeur;
    }

    public void setTypeEmployeur(int typeEmployeur) {
        this.typeEmployeur = typeEmployeur;
    }

    public LocalDate getDateEntreeCfa() {
        return dateEntreeCfa;
    }

    public void setDateEntreeCfa(LocalDate dateEntreeCfa) {
        this.dateEntreeCfa = dateEntreeCfa;
    }

    public LocalDate getDateDebutContratApprentissage() {
        return dateDebutContratApprentissage;
    }

    public void setDateDebutContratApprentissage(LocalDate dateDebutContratApprentissage) {
        this.dateDebutContratApprentissage = dateDebutContratApprentissage;
    }

    public LocalDate getDateRuptureContrat() {
        return dateRuptureContrat;
    }

    public void setDateRuptureContrat(LocalDate dateRuptureContrat) {
        this.dateRuptureContrat = dateRuptureContrat;
    }

    public int getCodeCommuneEtablissementEmployeur() {
        return codeCommuneEtablissementEmployeur;
    }

    public void setCodeCommuneEtablissementEmployeur(int codeCommuneEtablissementEmployeur) {
        this.codeCommuneEtablissementEmployeur = codeCommuneEtablissementEmployeur;
    }

    public int getCodeNafEtablissementEmployeur() {
        return codeNafEtablissementEmployeur;
    }

    public void setCodeNafEtablissementEmployeur(int codeNafEtablissementEmployeur) {
        this.codeNafEtablissementEmployeur = codeNafEtablissementEmployeur;
    }

    public int getNombreSalariesEntrepriseEmployeur() {
        return nombreSalariesEntrepriseEmployeur;
    }

    public void setNombreSalariesEntrepriseEmployeur(int nombreSalariesEntrepriseEmployeur) {
        this.nombreSalariesEntrepriseEmployeur = nombreSalariesEntrepriseEmployeur;
    }

    public int getNumeroSiretEntrepriseEmployeur() {
        return numeroSiretEntrepriseEmployeur;
    }

    public void setNumeroSiretEntrepriseEmployeur(int numeroSiretEntrepriseEmployeur) {
        this.numeroSiretEntrepriseEmployeur = numeroSiretEntrepriseEmployeur;
    }

    public Sifa.Etat getEtat() {
        return etat;
    }

    public void setEtat(Sifa.Etat etat) {
        this.etat = etat;
    }
}
